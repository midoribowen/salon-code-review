import org.sql2o.*;
import java.util.List;

public class Client {
  private int mId;
  private String mName;
  private int mStylistId;

  public Client (String name) {
    this.mName = name;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  // GETSTYLISTID METHOD - getter for Client's stylist id
  public int getStylistId() {
    return mStylistId;
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
  public void save() {
    String sql = "INSERT INTO clients(name) VALUES (:name)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
          .addParameter("name", this.mName)
          .executeUpdate()
          .getKey();
    }
  }


  // ALL METHOD - puts all Clients into a List of Client Objects
  public static List<Client> all() {
    String sql = "SELECT id AS mId, name AS mName FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
                .executeAndFetch(Client.class);
    }
  }


  // UPDATE METHOD - updates DB to change name of Client
  public void update(String newName) {
    this.mName = newName;
    String sql = "UPDATE clients SET name = :newName WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("newName", newName)
         .addParameter("id", this.mId)
         .executeUpdate();
    }
  }


  // DELETE METHOD - deletes Client from DB
  public void delete() {
    String sql = "DELETE FROM clients WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("id", this.mId)
         .executeUpdate();
    }
  }

  // FIND METHOD - finds Client id in DB based on userInput member variable
  public static Client find(int id) {
      String sql = "SELECT id AS mId, name AS mName, stylist_id AS mStylistId FROM clients WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Client.class);
    }
  }


  // assignStylist METHOD - adds a stylist id to the Client
  public void assignStylist(int stylistId) {
    mStylistId = stylistId;
    String sql = "UPDATE clients SET stylist_id = :stylistId WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("stylistId", stylistId)
         .addParameter("id", this.mId)
         .executeUpdate();
    }
  }

  // // GETSTYLISTNAME METHOD - getter for Stylist name
  // public String getStylistName() {
  //   return Stylist.find(mStylistId).getName();
  // }

}
