import org.sql2o.*;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;


public class Student {
  private int id;
  private String name;
  private java.sql.Date enrollment_date;

  public Student(String studentName, String enrollment){
    name = studentName;
    this.enrollment_date = java.sql.Date.valueOf(enrollment);
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public java.sql.Date getEnrollmentDate(){
    return enrollment_date;
  }

  @Override
  public boolean equals(Object newStudent) {
    if (newStudent instanceof Student) {
      Student otherStudent = (Student) newStudent;
      return this.getName().equals(otherStudent.getName()) &&
        this.getEnrollmentDate().getTime() == otherStudent.getEnrollmentDate().getTime() &&
        this.getId() == otherStudent.getId();

    } else {
      return false;
    }
  }

  public static List<Student> all(){
    String sql = "SELECT * FROM students";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Student.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO students (name, enrollment_date) VALUES (:name, :enrollment_date)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("enrollment_date", enrollment_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static Student find(int id){
    String sql = "SELECT * FROM students WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
    }
  }

  public void delete() {
    String sql = "DELETE FROM students WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateName(String newName){
    name = newName;
    String sql = "UPDATE students SET name=:name WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDate(String newDate){
    enrollment_date = java.sql.Date.valueOf(newDate);
    String sql = "UPDATE students SET enrollment_date=:enrollment_date WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("enrollment_date", enrollment_date)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addCourse(int courseId){
    String sql = "INSERT INTO courses_students (course_id, student_id) VALUES (:course_id, :student_id)";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("course_id", courseId)
        .addParameter("student_id", id)
        .executeUpdate();
    }
  }

  public List<Course> getCourses(){
  String sql = "SELECT DISTINCT ON (course_name) courses.* FROM students JOIN courses_students ON (students.id = courses_students.student_id) JOIN courses ON (courses_students.course_id = courses.id) WHERE students.id=:id ORDER BY course_name, id";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Course.class);
    }
  }

  public List<Course> availableCourses(){
    String sql = "SELECT * FROM courses WHERE id NOT IN (SELECT course_id FROM courses_students WHERE student_id = :id)";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Course.class);
      }
  }
}
