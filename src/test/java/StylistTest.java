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
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void update_updatesChangeOfNameInStylistsDatabase() {
    Stylist myStylist = new Stylist("Nathalie");
    myStylist.save();
    myStylist.update("Michael");
    assertEquals("Michael", Stylist.find(myStylist.getId()).getName());
  }

  @Test
  public void delete_deleteStylistFromDatabase() {
    Stylist firstStylist = new Stylist("Nathalie");
    Stylist secondStylist = new Stylist("Michael");
    firstStylist.save();
    secondStylist.save();
    firstStylist.delete();
    assertEquals(1, Stylist.all().size());
  }

  // @Test
  // public void getClients_returnsAllClientsWithSameStylist() {
  //   Stylist myStylist = new Stylist("Nathalie");
  //   myStylist.save();
  //
  //   Client firstClient = new Client("Kristen");
  //   Client secondClient = new Client("Gabe");
  //   firstClient.save();
  //   secondClient.save();
  //
  //   firstClient.assignStylist(myStylist.getId());
  //   secondClient.assignStylist(myStylist.getId());
  //
  //   Client[] clients = new Client[] {firstClient, secondClient};
  //   assertTrue(myStylist.getClients()
  //                       .containsAll(Arrays.asList(clients)));
  // }

}
