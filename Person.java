//import java.util.*;

public class Person extends File {
    String id;
    String name;
    String RFID;
    String clockIn;
    boolean present;

    Person(){}
    Person(String id, String name, String RFID) {
        this.id = id;
        this.name = name;
        this.RFID = RFID;
    }
    //setters
    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public void clockIn(String time) {
        this.clockIn = time;
    } 

    public void setPresent(boolean isPresent) {
        this.present = isPresent;
    }
    //getters
    public boolean isPresent() {
        return this.present;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }

    public String getRFID() {
        return this.RFID;
    }

    public String getClockIn() {
        return this.clockIn;
    }

    public void writePersonData() {
    }
}
