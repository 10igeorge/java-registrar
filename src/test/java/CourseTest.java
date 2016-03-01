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

}
