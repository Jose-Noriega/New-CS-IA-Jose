import java.util.*;
import java.io.*;
import java.nio.file.*;

public class File {
    String fileName;
    String memberFileName;
    Formatter formatter;
    File memberFile;

    File() {
        formatter = new Formatter();
        fileName = formatter.formatDate() + ".txt";
        memberFileName = "members.txt";
    }

    public void writeMembers(Map<String, Person> map) {
        try {
            FileWriter memberWritter = new FileWriter(memberFileName, true);
            List<String> memberList = new ArrayList<String>(map.keySet());
            for (int i = 0; i < memberList.size(); i++) {
                String memberID = memberList.get(i);
                String memberName = map.get(memberID).getName();
            }
            // memberWritter.write();
            memberWritter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
