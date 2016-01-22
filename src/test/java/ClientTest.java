import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_savesClientsInDatabase() {
    Client myClient = new Client("Kristen");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void update_updatesChangeOfNameInClientsDatabase() {
    Client myClient = new Client("Kristen");
    myClient.save();
    myClient.update("Gabe");
    assertEquals("Gabe", Client.find(myClient.getId()).getName());
  }

  @Test
  public void delete_deletesClientFromDatabase() {
    Client firstClient = new Client("Kristen");
    Client secondClient = new Client("Gabe");
    firstClient.save();
    secondClient.save();
    firstClient.delete();
    assertEquals(1, Client.all().size());
  }
  //
  // @Test
  // public void referToStylist_assignsStylistIdToClient() {
  //   Client myClient = new Client("Kristen");
  //   Stylist myStylist = new Stylist("Nathalie");
  //   myClient.save();
  //   myStylist.save();
  //   myClient.referToStylist(myStylist.getId());
  //   assertTrue(Stylist.find(myStylist.getId())
  //      .equals(Stylist.find(myClient.getStylistId())));
  // }

}
