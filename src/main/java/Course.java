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

  public static List<Course> all(){
    String sql = "SELECT * FROM courses";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Course.class);
    }
  }
}
