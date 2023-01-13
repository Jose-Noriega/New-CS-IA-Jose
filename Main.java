import java.util.*;

public class Main {
    Map<String, Person> people; //ID, object
    Map<String, String> mapRFID;
    double meetingStartTime;
    double meetingEndTime;
    Formatter formatter;

    // public void setPerson(String id, String name) {
    //     people.put(id, new Person(id, name));
    // }

    //setters
    public void setPerson(String id, String name, String RFID) {
        people.put(id, new Person(id, name, RFID));
        System.out.println(RFID);
        mapIdRFID(RFID, id);
        System.out.println(mapRFID);
    }

    public void mapIdRFID(String RFID, String id) {
        if(!mapRFID.containsKey(RFID) && RFID != null) {
            mapRFID.put(RFID, id);
        }
    }

    public boolean hasRFID(String RFID) {
        return mapRFID.containsKey(RFID);
    }
    public void setMeetingStartTime(double time) {
        meetingStartTime = time;
    }

    public void setMeetingEndTime(double time) {
        meetingEndTime = time;
    }

    public void setRFID(String RFID, String id) {
        mapRFID.put(RFID, id);
    }

    //getters
    public Person getPerson(String id) {
        return people.get(id);
    }

    public String getIdFromRFID(String RFID) {
        try {
            System.out.println(mapRFID.get(RFID));
            return mapRFID.get(RFID);
        } catch (Exception z) {
            return null;
        }
    }
    
    Main() {
        people = new HashMap<String, Person>();
        mapRFID = new HashMap<String, String>();
        formatter = new Formatter();
    }

    public static void main(String[] args) throws Exception {
        GUI obj = new GUI();
        // obj.setPerson("656255", "Jose", null);
        // obj.setPerson("570354", "Gaspard", null);
        // String[] person = new String[3];
        // person[0] = "123456";
        // person[1] = "Seth";
        // obj.setPerson(person[0], person[1], person[2]);
        // System.out.println(obj.getPerson("656255").getName());
        // System.out.println(obj.getPerson("570354").getName());
        // System.out.println(obj.getPerson("123456").getName());
        // obj.readMembers();
        // System.out.println("adklsj");
        obj.createAttendanceGUI();
        // System.out.println(obj.people.get("656255").getRFID() != null);
    }
}
