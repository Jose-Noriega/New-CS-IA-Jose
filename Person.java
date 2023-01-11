//import java.util.*;

public class Person extends File {
    String id;
    String name;
    String RFID;
    double clockIn;
    boolean present;


    Person() {}
    Person(String id, String name){
        this.id = id;
        this.name = name;
        this.present = false;
    }
    Person(String id, String name, String RFID) {
        this.id = id;
        this.name = name;
        this.RFID = RFID;
        this.present = false;
    }
    //setters
    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public void clockIn(double time) {
        this.clockIn = time;
        setAttendance(true);
    } 

    public void setAttendance(boolean isPresent) {
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

    public double getClockIn() {
        return this.clockIn;
    }

    //logic to check if they are present
    public void checkAttendance() {
        if (isPresent()) {
            return;
        }
        if (getClockIn() > meetingStartTime && getClockIn() < meetingEndTime) {
            setAttendance(true);
        } else {
            setAttendance(false);
        }
    }

    //check if user has RFID
    // public boolean hasRFID() {
    //     try {
    //         if(this.present) {
    //             return true;
    //         } else {
    //             return true;
    //         }
    //     } catch (Exception e) {
    //         return false;
    //     } finally {
    //     }
    // }
}
