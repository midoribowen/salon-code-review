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
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-stylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = new Stylist(request.queryParams("name-stylist"));
      stylist.save();
      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete-stylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("id-stylist")));
      stylist.delete();
      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update-stylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String newName = request.queryParams("newName-stylist");
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("id-stylist")));
      stylist.update(newName);

      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));

      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("clients", stylist.getClients());

      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/new-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("id-stylist")));
      Client client = new Client(request.queryParams("name-client"));
      client.save();
      client.assignStylist(stylist.getId());

      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("client", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/delete-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      Client client = Client.find(Integer.parseInt(request.queryParams("id-client")));
      client.delete();

      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("client", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/update-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      String newName = request.queryParams("newName-client");
      Client client = Client.find(Integer.parseInt(request.queryParams("id-client")));
      client.update(newName);

      model.put("client", client);
      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("client", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
