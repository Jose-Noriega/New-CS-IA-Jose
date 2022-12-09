import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends Person {

    // instance field
    JFrame frameAttendance, frameSignUp, frameRFID, frameAdmin;
    JPanel panelAttendance, panelSignUp, panelRFID, panelAdmin;
    JTextArea instructionsID, instructionsName, instructionsRFID, instructionsAdminStartTime, instructionsAdminEndTime,instructionsTimeAttended;
    JTextField inputID, inputName, inputRFID, inputStartTime, inputEndTime, inputPercentAttended;
    JButton toSignUp, toTakeAttendance, memberSignUp, toRFID, setAdminSettings;
    JComboBox<String> startTime_AM_or_PM, endTime_AM_or_PM;

    // constructor
    GUI() {
        readMembers();
    }

    // method to create GUI
    public void createAttendanceGUI() {
        frameAttendance = new JFrame("Attendance GUI");

        frameAttendance.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do something when close
            }
        });

        panelAttendance = new JPanel();
        toSignUp = new JButton("Haven't signed up? Click here!");
        toRFID = new JButton("Want to set your login with your RFID? Click here!");
        instructionsID = new JTextArea("Enter your id: ");// can be something else but this is juts this for now
        instructionsID.setEditable(false);

        inputID = new JTextField(10);
        inputID.setEditable(true);
        inputID.addActionListener(new AbstractAction() { 
            @Override
            public void actionPerformed(ActionEvent e) { //do the clock in---DONE
                String input = inputID.getText();
                System.out.println(input);
                inputID.setText("");
                Person member = people.get(input);
                try {
                    member.clockIn(formatter.formatCurrentTime());
                    member.checkAttendance();
                } catch (Exception p) {
                    JOptionPane.showMessageDialog(frameAttendance, "Student not signed up");
                }
                if (input.equals("admin")) {
                    frameAttendance.dispose();
                    createAdminGUI();
                }
            }
        });

        toSignUp.setSize(100, 200);
        toSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to sign up page
                frameAttendance.dispose();
                createSignUpGUI();
            }
        });

        toRFID.setSize(100, 200);
        toRFID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to RFID linker
                frameAttendance.dispose();
                linkRFID();
            }
        });

        frameAttendance.add(panelAttendance);

        panelAttendance.setLayout(new GridBagLayout());

        panelAttendance.add(instructionsID, createLayout(0, 0, 0.5, 1));
        panelAttendance.add(inputID, createLayout(1, 0, 0.5, 2));
        panelAttendance.add(toSignUp, createLayout(0, 1, 0.5, 3));
        panelAttendance.add(toRFID, createLayout(0, 2, 0.5, 3));
        frameAttendance.setSize(400, 500);
        frameAttendance.setVisible(true);
        frameAttendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void createSignUpGUI() { // add an rfid for when you sign up maybe
        frameSignUp = new JFrame("Attendance GUI");

        frameSignUp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do the closing
            }
        });

        panelSignUp = new JPanel();
        toTakeAttendance = new JButton("Go to obj");
        memberSignUp = new JButton("Sign up!");
        instructionsID = new JTextArea("Enter your id: ");// can be something else but this is juts this for now
        instructionsID.setEditable(false);
        instructionsName = new JTextArea("Enter your name: ");// name for now may change later
        instructionsName.setEditable(false);

        inputID = new JTextField(10);
        inputID.setEditable(true);

        inputName = new JTextField(20);
        inputName.setEditable(true);
        inputName.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sign the member up
                String nameInput = inputName.getText();
                String IDInput = inputID.getText();
                System.out.println(nameInput);
                System.out.println(IDInput);
                //do the sign up
                writeMember(IDInput, nameInput, null, false);
                setPerson(IDInput, nameInput, null);
                inputName.setText("");
                inputID.setText("");
                nameInput = "";
                IDInput = "";
            }
        });

        memberSignUp.setSize(100, 200);
        memberSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sign the member up
                String nameInput = inputName.getText();
                String IDInput = inputID.getText();
                System.out.println(nameInput);
                System.out.println(IDInput);
                //do the sign up
                writeMember(IDInput, nameInput, null, false);
                setPerson(IDInput, nameInput, null);
                inputName.setText("");
                inputID.setText("");
            }
        });

        toTakeAttendance.setSize(100, 200);
        toTakeAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to the other GUI (take obj GUI)
                frameSignUp.dispose();
                createAttendanceGUI();
            }
        });

        toRFID.setSize(100, 200);
        toRFID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to RFID linker
                frameSignUp.dispose();
                linkRFID();
            }
        });

        frameSignUp.add(panelSignUp);

        panelSignUp.setLayout(new GridBagLayout());

        panelSignUp.add(instructionsID, createLayout(0, 0, 0.5, 1));
        panelSignUp.add(instructionsName, createLayout(0, 1, 0.5, 1));
        panelSignUp.add(toTakeAttendance, createLayout(0, 3, 0.5, 3));// also do the thing that is in the other line
        panelSignUp.add(toRFID, createLayout(0, 4, 0.5, 3)); // change to create 3 rows with each button
        panelSignUp.add(memberSignUp, createLayout(0, 2, 0.5, 3));
        panelSignUp.add(inputID, createLayout(1, 0, 0.5, 2));
        panelSignUp.add(inputName, createLayout(1, 1, 0.5, 2));
        // panelSignUp.add(inputRFID, createLayout(1, 2, 0.5, 1));
        frameSignUp.setSize(400, 500);
        frameSignUp.setVisible(true);
        frameSignUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void linkRFID() {
        frameRFID = new JFrame("RFID Linker");

        frameRFID.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do the finish
            }
        });

        panelRFID = new JPanel();
        instructionsID = new JTextArea("Enter your id: ");// can be something else but this is juts this for now
        instructionsID.setEditable(false);
        instructionsRFID = new JTextArea("Scan your RFID: ");
        instructionsRFID.setEditable(false);
        toSignUp = new JButton("Go to sign up");
        toTakeAttendance = new JButton("Go to obj");

        inputID = new JTextField(10);
        inputID.setEditable(true);

        inputRFID = new JTextField(10);
        inputID.setEditable(true);
        inputRFID.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IDInput = inputID.getText();
                System.out.println(IDInput);
                String RFIDInput = inputRFID.getText();
                System.out.println(RFIDInput);
                //do the RFID link
                writeMember(IDInput, people.get(IDInput).getName(), RFIDInput, true);
                inputID.setText("");
                inputRFID.setText("");
            }
        });

        toSignUp.setSize(100, 200);
        toSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to sign up page
                frameRFID.dispose();
                createSignUpGUI();
            }
        });

        toTakeAttendance.setSize(100, 200);
        toTakeAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to obj page
                frameRFID.dispose();
                createAttendanceGUI();
            }
        });

        frameRFID.add(panelRFID);

        panelRFID.setLayout(new GridBagLayout());

        panelRFID.add(instructionsID, createLayout(0, 0, 0.5, 1));
        panelRFID.add(instructionsRFID, createLayout(0, 1, 0.5, 1));
        panelRFID.add(inputID, createLayout(1, 0, 0.5, 2));
        panelRFID.add(inputRFID, createLayout(1, 1, 0.5, 2));
        panelRFID.add(toSignUp, createLayout(0, 2, 0.5, 3));
        panelRFID.add(toTakeAttendance, createLayout(0, 3, 0.5, 3));

        frameRFID.setSize(400, 500);
        frameRFID.setVisible(true);
        frameRFID.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void createAdminGUI() {// create admin gui to make some settings
        frameAdmin = new JFrame("Admin Frame");

        frameAdmin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do the end
            }
        });

        panelAdmin = new JPanel();
        instructionsAdminStartTime = new JTextArea("Please input the meeting starting time: ");
        instructionsAdminEndTime = new JTextArea("Please input the meeting end time: ");
        instructionsTimeAttended = new JTextArea("Please input the % time people need to be here: ");
        instructionsAdminStartTime.setEditable(false);
        instructionsAdminEndTime.setEditable(false);
        instructionsTimeAttended.setEditable(false);
        inputStartTime = new JTextField("_:_", 5);
        inputEndTime = new JTextField("_:_", 5);
        inputPercentAttended = new JTextField("_%", 5);
        inputStartTime.setEditable(true);
        inputEndTime.setEditable(true);
        inputPercentAttended.setEditable(true);

        setAdminSettings = new JButton("Set Admin Settings");
        setAdminSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to the obj page where we came from
                // writeAdminData(
                //         formatter.formatTime(inputStartTime.getText(),
                //                 (startTime_AM_or_PM.getSelectedItem().equals("AM") ? false : true)),
                //         formatter.formatTime(inputEndTime.getText(),
                //                 (endTime_AM_or_PM.getSelectedItem().equals("AM") ? false : true)),
                //         Integer.parseInt(inputPercentAttended.getText().substring(0,
                //                 inputPercentAttended.getText().indexOf("%"))));
                //do the admin data
                frameAdmin.dispose();
                createAttendanceGUI();
            }
        });

        String[] choices = { "AM", "PM" };
        startTime_AM_or_PM = new JComboBox<String>(choices);
        endTime_AM_or_PM = new JComboBox<String>(choices);

        frameAdmin.add(panelAdmin);

        panelAdmin.setLayout(new GridBagLayout());

        panelAdmin.add(instructionsAdminStartTime, createLayout(0, 0, 0.5, 1));
        panelAdmin.add(inputStartTime, createLayout(1, 0, 0.5, 1));
        panelAdmin.add(startTime_AM_or_PM, createLayout(2, 0, 0.5, 1));
        panelAdmin.add(instructionsAdminEndTime, createLayout(0, 1, 0.5, 1));
        panelAdmin.add(inputEndTime, createLayout(1, 1, 0.5, 1));
        panelAdmin.add(endTime_AM_or_PM, createLayout(2, 1, 0.5, 1));
        panelAdmin.add(instructionsTimeAttended, createLayout(0, 2, 0.5, 1));
        panelAdmin.add(inputPercentAttended, createLayout(1, 2, 0.5, 2));
        panelAdmin.add(setAdminSettings, createLayout(0, 3, 0.5, 3));

        frameAdmin.setSize(400, 500);
        frameAdmin.setVisible(true);
        frameAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public GridBagConstraints createLayout(int gridx, int gridy, double weight, int gridwidth) { // method to never miss
                                                                                                 // a grid bag layout
                                                                                                 // thing (less errors,
                                                                                                 // less confusion)
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weight;
        c.gridwidth = gridwidth;
        return c;
    }

}