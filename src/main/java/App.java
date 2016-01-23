import java.util.Map;
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
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-stylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = new Stylist(request.queryParams("name"));
      stylist.save();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("id")));
      stylist.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/stylist.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

  }
}
