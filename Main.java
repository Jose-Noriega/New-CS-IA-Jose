import java.util.*;

public class Main extends File {
    Map<String, Person> people; //ID, object

    public void setPerson(String id, String name) {
        people.put(id, new Person(id, name));
    }
    public void setPerson(String id, String name, String RFID) {
        people.put(id, new Person(id, name, RFID));
    }

    public Person getPerson(String id) {
        return people.get(id);
    }

    Main() {
        super();
        people = new HashMap<String, Person>();
    }
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setPerson("656255", "Jose");
        main.setPerson("570354", "Gaspard");
        System.out.println(main.getPerson("656255").name);
        System.out.println(main.getPerson("570354").name);
    }
}
