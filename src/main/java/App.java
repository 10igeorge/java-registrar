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
  }
}
