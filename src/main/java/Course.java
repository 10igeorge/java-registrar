import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Course {
  private int id;
  private String course_name;
  private String course_number;

  public Course(String courseName, String courseNumber) {
    course_name = courseName;
    course_number = courseNumber;
  }

  public int getId(){
    return id;
  }

  public String getCourseName(){
    return course_name;
  }

  public String getCourseNumber(){
    return course_number;
  }

  @Override
  public boolean equals(Object newCourse) {
    if (newCourse instanceof Course) {
      Course otherCourse = (Course) newCourse;
      return this.getCourseName().equals(otherCourse.getCourseName()) &&
        this.getId() == otherCourse.getId() &&
        this.getCourseNumber().equals(otherCourse.getCourseNumber());
    } else {
      return false;
    }
  }

  public static List<Course> all(){
    String sql = "SELECT * FROM courses";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Course.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO courses (course_name, course_number) VALUES (:course_name, :course_number)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
        .addParameter("course_name", course_name)
        .addParameter("course_number", course_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Course find(int id){
    String sql = "SELECT * FROM courses WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class);
    }
  }

  public void delete() {
    String sql = "DELETE FROM courses WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateName(String newName){
    course_name = newName;
    String sql = "UPDATE courses SET course_name=:course_name WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("course_name", course_name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateNumber(String newNumber){
    course_number = newNumber;
    String sql = "UPDATE courses SET course_number=:course_number WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("course_number", course_number)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addStudent(int studentId){
    String sql = "INSERT INTO courses_students (course_id, student_id) VALUES (:course_id, :student_id)";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("course_id", id)
        .addParameter("student_id", studentId)
        .executeUpdate();
    }
  }

  public List<Student> getStudents(){
    String sql = "SELECT DISTINCT ON (id) students.* FROM courses JOIN courses_students ON (courses.id = courses_students.course_id) JOIN students ON (courses_students.student_id = students.id) WHERE courses.id=:id ORDER BY id, enrollment_date";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Student.class);
    }
  }

  public List<Student> availableStudents(){
    String sql = "SELECT * FROM students WHERE id NOT IN (SELECT student_id FROM courses_students WHERE course_id=:id)";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Student.class);
    }
  }
}
