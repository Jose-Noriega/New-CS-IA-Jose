import java.util.*;
import java.io.*;
import java.nio.file.*;

public class File extends Main {
    String attendanceFileName;
    String memberFileName;
    File memberFile;

    File() {
        attendanceFileName = formatter.formatFileDate() + ".txt";
        memberFileName = "members.txt";
    }

    public void replaceLine(String lineToReplace, String newLine, String fileToChange) { // this replaces some lines
        try {
            FileWriter memberWritter = new FileWriter(memberFileName, false);
            List<String> memberList = Files.readAllLines(Paths.get(memberFileName));
            int index = memberList.indexOf(lineToReplace); // get the index of the line I want to replace
            memberList.set(index, newLine); // replace the old line with the new line
            for (int i = 0; i < memberList.size(); i++) { // rewrite the entire file
                memberWritter.write(memberList.get(i));
            }
            memberWritter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isThere(String line) {
        boolean found = false;
        try{
            List<String> allLines = Files.readAllLines(Paths.get(memberFileName));
            if (allLines.indexOf(line) != -1) {
                found = true;
            }
        } catch (Exception e) {

        }
        return found;
    }

    // writters
    public void writeMember(String id, String name, String RFID, boolean setRFID) {
        try {
            FileWriter memberWritter = new FileWriter(memberFileName, true);
            if (setRFID) {
                replaceLine("".concat(id + "," + name + ","), "".concat(id + "," + name + "," + RFID),
                        memberFileName);
            } else {
                memberWritter.write(id + "," + name + "," + ((RFID != null) ? RFID : "") + "\n");
            }
            memberWritter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // write attendance into file
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

    // method to write admin data
    public void writeAdminData(double startTime, double endTime) {
        try {
            FileWriter adminWriter = new FileWriter("admin.txt", false);
            adminWriter.write(String.valueOf(startTime) + "," + String.valueOf(endTime));
            adminWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // readers
    public void readMembers() {
        try {
            FileReader memberReader = new FileReader(memberFileName);
            List<String> memberList = Files.readAllLines(Paths.get(memberFileName));
            for (int i = 0; i < memberList.size(); i++) {
                String tempValue = memberList.get(i);
                String[] values = new String[3];
                values = copyArrays(values.length, tempValue.split(",")).clone();
                String memberID = values[0];
                String memberName = values[1];
                String memberRFID = values[2];
                setPerson(memberID, memberName, memberRFID);
            }
            memberReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // method to read admin data to be used every time to file opens
    public void readAdminData() {
        try {
            Scanner adminScanner = new Scanner("admin.txt");
            String[] adminData = adminScanner.nextLine().split(",");
            setMeetingStartTime(Double.parseDouble(adminData[0]));
            setMeetingEndTime(Double.parseDouble(adminData[1]));
            adminScanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }

    // this is an abstraction to copy two arrays
    public String[] copyArrays(int length, String[] arrayToCopy) {
        String[] copyOfArray = new String[length];
        for (int i = 0; i < arrayToCopy.length; i++) {
            try {
                copyOfArray[i] = arrayToCopy[i];
            } catch (Exception e) {
                copyOfArray[i] = null;
            }
        }
        return copyOfArray;
    }

}
