import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  //Integration testing
  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Epicodus University");
  }

  @Test
  public void addStudent(){
    goTo("http://localhost:4567/");
    fill("#name").with("Mary");
    fill("#enrollmentDate").with("2000-02-22");
    submit("#createStudent");
    assertThat(pageSource()).contains("Mary has successfully been enrolled!");
  }

  @Test
  public void addCourse(){
    goTo("http://localhost:4567/");
    fill("#courseName").with("Intro to Folklore");
    fill("#courseNumber").with("FLKR1000");
    submit("#createCourse");
    assertThat(pageSource()).contains("FLKR1000: Intro to Folklore has successfully been added to the course list");
  }

  @Test
  public void listAllStudents(){
    goTo("http://localhost:4567/");
    fill("#name").with("Mary");
    fill("#enrollmentDate").with("2000-02-22");
    submit("#createStudent");
    fill("#name").with("Evan");
    fill("#enrollmentDate").with("2000-02-21");
    submit("#createStudent");
    goTo("http://localhost:4567/students");
    assertThat(pageSource()).contains("Mary");
    assertThat(pageSource()).contains("Evan");
  }

  @Test
  public void listAllCourses() {
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    Course homeEconomics = new Course("Home Economics", "ECON304");
    homeEconomics.save();
    Course folklore = new Course("Intro to Folklore", "FLKR1000");
    folklore.save();
    goTo("http://localhost:4567/courses");
    assertThat(pageSource()).contains("Basic Economics");
    assertThat(pageSource()).doesNotContain("Mary");
  }

  @Test
  public void addStudentSpecificCourse() {
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    Course homeEconomics = new Course("Home Economics", "ECON304");
    homeEconomics.save();
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    Student marge = new Student("Marge Simpson", "2016-04-29");
    marge.save();
    goTo("http://localhost:4567/courses");
    click("a", withText("Home Economics"));
    click("option", withText("Homer Simpson"));
    submit("#enrollStudent");
    assertThat(pageSource()).contains("Homer Simpson has been added to ECON304");
  }

  @Test
  public void addCourseToStudent() {
    Course economics = new Course("Basic Economics", "ECON101");
    economics.save();
    Course homeEconomics = new Course("Home Economics", "ECON304");
    homeEconomics.save();
    Student homer = new Student("Homer Simpson", "2016-02-29");
    homer.save();
    Student marge = new Student("Marge Simpson", "2016-04-29");
    marge.save();
    goTo("http://localhost:4567/students");
    click("a", withText("Homer Simpson"));
    click("#course" + economics.getId());
    submit("#addCourse");
    assertThat(pageSource()).contains("Homer Simpson has been enrolled in:");
  }
}
