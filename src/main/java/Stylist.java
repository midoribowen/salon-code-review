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


  // ALL METHOD - puts all Stylists into a List of Stylist Objects


  // UPDATE METHOD - updates DB to change name of Stylist


  // DELETE METHOD - deletes Stylist from DB


  // FIND METHOD - finds finds Stylist id in DB based on userInput member variable, executeandfetch Stylist Object


  // GETCLIENTS METHOD - generates a List of Client Objects that contain the same Stylist id

  

}
