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

  @Test
  public void find_findsASpecificCourseInDatabase() {
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    Course homeEconomics = new Course("Home Economics", "ECON304");
    homeEconomics.save();
    Course newCourse = Course.find(economics.getId());
    assertTrue(newCourse.equals(economics));
  }

  @Test
  public void delete_deletesObjectFromDatabase(){
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    Course homeEconomics = new Course("Home Economics", "ECON304");
    homeEconomics.save();
    economics.delete();
    assertFalse(Course.all().contains(economics));
  }

  @Test
  public void update_updatesInfoOfAnObject(){
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    economics.updateName("Intro Economics");
    economics.updateNumber("ECON102");
    Course savedCourse = Course.find(economics.getId());
    assertEquals(savedCourse.getCourseName(), "Intro Economics");
    assertEquals(savedCourse.getCourseNumber(), "ECON102");
    assertTrue(savedCourse.equals(economics));
  }

  // @Test
  // public void addStudent_addsAStudentToACourse() {
  //   Course economics = new Course("Basic Economics", "ECON101");
  //   economics.save();
  //   Course homeEconomics = new Course("Home Economics", "ECON304");
  //   homeEconomics.save();
  //   Student homer = new Student("Homer Simpson", "2016-02-29");
  //   homer.save();
  //   Student marge = new Student("Marge Simpson", "2016-04-29");
  //   marge.save();
  //   economics.addStudent(homer.getId());
  //   economics.addStudent(marge.getId());
  //   homeEconomics.addStudent(homer.getId());
  //   assertTrue(homeEconomics.getStudents().contains(homer));
  //   assertEquals(economics.getStudents().size(), 2);
  // }
}
