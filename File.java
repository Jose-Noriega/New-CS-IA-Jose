import java.util.*;
import java.io.*;
import java.nio.file.*;

public class File extends Main{
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
                memberWritter.write(memberName+","+memberID);//___member name___,___member id___
            }
            memberWritter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readMembers() {
        try {
            FileReader memberReader = new FileReader(memberFileName);
            List<String> memberList = Files.readAllLines(Paths.get(memberFileName));
            for (int i = 0; i < memberList.size(); i++) {
                String tempValue = memberList.get(i);
                String memberName = tempValue.substring(0, tempValue.indexOf(","));
                String memberID = tempValue.substring(tempValue.indexOf(",") + 1);
                try {
                    String memberRFID = memberID.substring(memberID.indexOf("-") + 1);
                    memberID = memberID.substring(0, memberID.indexOf("-"));
                    setPerson(memberID, memberName, memberRFID);
                } catch (Exception e) {
                    setPerson(memberID, memberName);
                    System.out.println(e);
                }
                
            }
            memberReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
