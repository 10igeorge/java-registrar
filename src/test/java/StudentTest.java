import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StudentTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  // UNIT TESTING

  @Test
  public void all_returnsAllStudents_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void save_savesStudentIntoDatabase(){
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    assertEquals(Student.all().get(0), homer);
  }
}
