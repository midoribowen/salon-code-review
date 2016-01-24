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
    fill("#name").with("Nathalie");
    submit(".btn-success");
    assertThat(pageSource()).contains("Nathalie");
  }

  @Test
  public void stylistRemoved() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    goTo("http://localhost:4567/");
    click("option", withText("Nathalie"));
    submit(".btn-danger");
    assertThat(!(pageSource()).contains("Nathalie"));
  }

  @Test
  public void stylistUpdatedSuccessfully() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    goTo("http://localhost:4567/");
    click("option", withText("Nathalie"));
    fill("#newName").with("Michael");
    submit(".btn-warning");
    assertThat(pageSource()).contains("Michael");
  }

}
