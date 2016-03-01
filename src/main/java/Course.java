import org.sql2o.*;
import java.util.List;

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
}
