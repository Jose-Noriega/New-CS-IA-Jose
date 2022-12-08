import java.util.*;
import java.io.*;
import java.nio.file.*;

public class File extends Main{
    String attendanceFileName;
    String memberFileName;
    Formatter formatter;
    File memberFile;

    File() {
        formatter = new Formatter();
        attendanceFileName = formatter.formatDate() + ".txt";
        memberFileName = "members.txt";
    }

    //writters
    public void writeMembers() {
        try {
            FileWriter memberWritter = new FileWriter(memberFileName, false);
            List<String> memberList = new ArrayList<String>(people.keySet());
            for (int i = 0; i < memberList.size(); i++) {
                String memberID = memberList.get(i);
                Person member = getPerson(memberID);
                String memberName = member.getName();
                String memberRFID = member.getRFID();
                memberWritter.write(memberName + "," + memberID + "," + memberRFID + "\n");//___member name___,___member id___
            }
            memberWritter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void writeAttendance() {
        try {
            FileWriter attendanceWriter = new FileWriter(attendanceFileName, true);
            List<String> attendanceList = new ArrayList<String>(people.keySet());
            for (int i = 0; i < attendanceList.size(); i++) {
                String memberID = attendanceList.get(i);
                Person member = getPerson(memberID);
                if (member.isPresent()) {
                    attendanceWriter.write(memberID);
                } 
            }
            attendanceWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //readers
    public void readMembers() {
        try {
            FileReader memberReader = new FileReader(memberFileName);
            List<String> memberList = Files.readAllLines(Paths.get(memberFileName));
            for (int i = 0; i < memberList.size(); i++) {
                String tempValue = memberList.get(i);
                String[] values = new String[3];
                values = copyArrays(values.length, tempValue.split(",")).clone();
                String memberName = values[0];
                String memberID = values[1];
                String memberRFID = values[2];
                setPerson(memberID, memberName, memberRFID);
            }
            memberReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //this is an abstraction to copy two arrays
    public String[] copyArrays(int length, String[] arrayToCopy) {
        String[] copyOfArray = new String[length];
        for (int i = 0; i < arrayToCopy.length; i++) {
            try {
                copyOfArray[i] = arrayToCopy[i];
            } catch(Exception e) {
                copyOfArray[i] = null;
            }
        }
        return copyOfArray;
    }
}
