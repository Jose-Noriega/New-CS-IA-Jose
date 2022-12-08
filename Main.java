import java.util.*;

public class Main {
    Map<String, Person> people; //ID, object
    File files;

    // public void setPerson(String id, String name) {
    //     people.put(id, new Person(id, name));
    // }
    public void setPerson(String id, String name, String RFID) {
        people.put(id, new Person(id, name, RFID));
    }

    public Person getPerson(String id) {
        return people.get(id);
    }

    Main() {
        people = new HashMap<String, Person>();
        //files = new File();
    }

    public static void main(String[] args) throws Exception {
        GUI obj = new GUI();
        obj.setPerson("656255", "Jose", null);
        obj.setPerson("570354", "Gaspard", null);
        String[] person = new String[3];
        person[0] = "123456";
        person[1] = "Seth";
        obj.setPerson(person[0], person[1], person[2]);
        System.out.println(obj.getPerson("656255").getName());
        System.out.println(obj.getPerson("570354").getName());
        System.out.println(obj.getPerson("123456").getName());
        obj.readMembers();
        System.out.println("adklsj");
        obj.createAttendanceGUI();
    }
}
