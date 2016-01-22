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
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
          .addParameter("name", this.mName)
          .executeUpdate()
          .getKey();
    }
  }


  // ALL METHOD - puts all Clients into a List of Client Objects
  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM clients";
      return con.createQuery(sql)
                .executeAndFetch(Client.class);
    }
  }


  // // UPDATE METHOD - updates DB to change name of Client
  // public void update(String newName) {
  //   this.mName = newName;
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "";
  //     con.createQuery(sql)
  //   }
  // }


  // // DELETE METHOD - deletes Client from DB
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "";
  //     con.createQuery(sql)
  //   }
  // }

  // // FIND METHOD - finds Client id in DB based on userInput member variable
  // public static Client find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "";
  //     con.createQuery(sql)
  //   }
  // }


  // // referToStylist METHOD - adds a stylist id to the Client
  // public void referToStylist(int stylistId) {
  //   mStylistId = stylistId;
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "";
  //     con.createQuery(sql)
  //   }
  // }


  // // GETSTYLISTID METHOD - getter for Client's stylist id
  // public int getStylistId() {
  //   return mStylistId;
  // }


  // // GETSTYLISTNAME METHOD - getter for Stylist name using Stylist.find(mStylistId).getName();
  // public String getStylistName() {
  //   return Stylist.find(mStylistId).getName();
  // }

}
