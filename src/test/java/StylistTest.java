import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_savesStylistInDatabase() {
    Stylist stylist = new Stylist("Nathalie");
    stylist.save();
    assertTrue(Stylist.all().get(0).equals(stylist));
  }

}
