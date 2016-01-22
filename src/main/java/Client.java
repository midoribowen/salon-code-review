import org.sql2o.*;
import java.util.List;

public class Client {
  private int mId;
  private String mName;

  public Client (String name) {
    this.mName = name;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
        this.getId() == newClient.getId();
    }
  }

  // SAVE METHOD - saves userinput Client in the DB with a primary key


  // ALL METHOD - puts all Clients into a List of Client Objects



  // UPDATE METHOD - updates DB to change name of Client



  // DELETE METHOD - deletes Client from DB


  // FIND METHOD - finds Client id in DB based on userInput member variable



  // REFER TO STYLIST METHOD - adds a stylist id to the Client



  // GETSTYLISTID METHOD - getter for Client's stylist id



  // GETSTYLISTNAME METHOD - getter for Stylist name using Stylist.find(mStylistId).getName();

}
