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

  @Test
  public void find_findsASpecificStudentInDatabase() {
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    Student marge = new Student("Marge Simpson", "2016-04-29");
    marge.save();
    Student newStudent = Student.find(homer.getId());
    assertTrue(newStudent.equals(homer));
    assertTrue(Student.all().contains(marge));
  }

  @Test
  public void delete_deletesObjectFromDatabase(){
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    Student marge = new Student("Marge Simpson", "2016-04-29");
    marge.save();
    homer.delete();
    assertFalse(Student.all().contains(homer));
  }

  @Test
  public void update_updatesInfoOfAnObject(){
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    homer.updateName("Homer Jones");
    homer.updateDate("2016-01-15");
    Student savedStudent = Student.find(homer.getId());
    assertEquals(savedStudent.getName(), "Homer Jones");
    assertEquals(savedStudent.getEnrollmentDate(), java.sql.Date.valueOf("2016-01-15"));
    assertEquals(savedStudent.getEnrollmentDate().getTime(), homer.getEnrollmentDate().getTime());
    assertTrue(savedStudent.equals(homer));
  }
}
