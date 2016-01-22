import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int mId;
  private String mName;

  public Stylist (String name) {
    this.mName = name;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }

  // SAVE METHOD - saves new Stylist in the DB with a primary key
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
          .addParameter("name", this.mName)
          .executeUpdate()
          .getKey();
    }
  }

  // ALL METHOD - puts all Stylists into a List of Stylist Objects
  public static List<Stylist> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM stylists";
      return con.createQuery(sql)
                .executeAndFetch(Stylist.class);
    }
  }

  // UPDATE METHOD - updates DB to change name of Stylist
  public void update(String newName) {
    this.mName = newName;
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :newName WHERE id=:id";
      con.createQuery(sql)
      .addParameter("newName", newName)
      .addParameter("id", this.mId)
      .executeUpdate();
    }
  }

  // DELETE METHOD - deletes Stylist from DB
  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id";
      con.createQuery(sql)
         .addParameter("id", this.mId)
         .executeUpdate();
    }
  }

  // FIND METHOD - finds finds Stylist id in DB based on userInput member variable, executeandfetch Stylist Object
  public static Stylist find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM stylists WHERE id=:id";
      return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Stylist.class);
    }
  }

  // // GETCLIENTS METHOD - generates a List of Client Objects that contain the same Stylist id
  // public List<Client> getClients() {
  //   try (Connection con = DB.sql2o.open()) {
  //     String sql = "";
  //     con.createQuery(sql)
  //   }
  // }


}
