import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
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

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Get a Haircut!");
  }

  @Test
  public void stylistAddedSuccessfully() {
    goTo("http://localhost:4567/");
    fill("#name-stylist").with("Nathalie");
    submit(".new-stylist");
    assertThat(pageSource()).contains("Nathalie");
  }

  @Test
  public void stylistRemoved() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    goTo("http://localhost:4567/");
    click("option", withText("Nathalie"));
    submit(".delete-stylist");
    assertThat(!(pageSource()).contains("Nathalie"));
  }

  @Test
  public void stylistUpdatedSuccessfully() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    goTo("http://localhost:4567/");
    click("option", withText("Nathalie"));
    fill("#newName-stylist").with("Michael");
    submit(".update-stylist");
    assertThat(pageSource()).contains("Michael");
  }

  @Test
  public void clientAddedSuccessfully() {
    goTo("http://localhost:4567/");
    fill("#name-stylist").with("Nathalie");
    submit(".new-stylist");
    click("a", withText("Nathalie"));
    fill("#name-client").with("Gabe");
    click("option", withText("Nathalie"));
    submit(".new-client");
    assertThat(pageSource()).contains("Gabe");
  }

  @Test
  public void clientRemoved() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    Client myClient = new Client("Gabe");
    myClient.save();
    myClient.assignStylist(myStylist.getId());
    goTo("http://localhost:4567/" + Integer.toString(myStylist.getId()));
    click("option", withText("Gabe"));
    submit(".delete-client");
    assertThat(!(pageSource()).contains("Gabe"));
  }

  @Test
  public void clientUpdated() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    Client myClient = new Client("Gabe");
    myClient.save();
    myClient.assignStylist(myStylist.getId());
    goTo("http://localhost:4567/" + Integer.toString(myStylist.getId()));
    click("option", withText("Gabe"));
    fill("#newName-client").with("Michael");
    submit(".update-client");
    assertThat(pageSource()).contains("Michael");
  }

}
