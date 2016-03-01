import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CourseTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  // UNIT TESTING

  @Test
  public void all_returnsAllCourses_emptyAtFirst() {
    assertEquals(Course.all().size(), 0);
  }

  @Test
  public void save_savesCourseIntoDatabase(){
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    assertEquals(Course.all().get(0), economics);
  }

  @Test
  public void save_addsIdToLocalObject() {
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    assertEquals(Course.all().get(0).getId(), economics.getId());
  }
}
