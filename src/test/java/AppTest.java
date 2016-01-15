import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("//insert page contents here for testing//");
  }
    //This tests is a page contains particular contents

  @Test public void testNameHere() {
    goTo("http://localhost:4567/");
    fill("#tag").with("userInput");
    submit(".btn");
    assertThat(pageSource()).contains("//userInput//");
  }
    //This tests if a tagged fill is outputing correctly with the user input in the right place.
}
