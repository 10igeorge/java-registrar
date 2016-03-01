import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-student", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newStudent = request.queryParams("name");
      String newEnrollment = request.queryParams("enrollmentDate");
      Student student = new Student(newStudent, newEnrollment);
      student.save();
      model.put("student", student);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-course", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newCourse = request.queryParams("courseName");
      String newNumber = request.queryParams("courseNumber");
      Course course = new Course(newCourse, newNumber);
      course.save();
      model.put("course", course);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allStudents", Student.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allCourses", Course.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      model.put("enrolledStudents", course.getStudents());
      model.put("course", course);
      model.put("allStudents", Student.all());
      model.put("template", "templates/course.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      int studentId = Integer.parseInt(request.queryParams("newStudent"));
      course.addStudent(studentId);
      Student student = Student.find(studentId);
      model.put("addedStudent", student);
      model.put("enrolledStudents", course.getStudents());
      model.put("course", course);
      model.put("allStudents", Student.all());
      model.put("template", "templates/course.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
