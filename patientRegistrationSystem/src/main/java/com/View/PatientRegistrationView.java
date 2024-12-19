package com.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.Controller.PatientRegistrationController;
import com.Model.JsonHandler;
import com.Model.Patient;
import com.toedter.calendar.JDateChooser;
public class PatientRegistrationView {
    public JFrame frame;
    public JTextField firstNameField, lastNameField, ageField, emailField, contactField,
     pincodeField, emergencyContactNameField, emergencyContactNumberField,mrdidField;
    public JPanel headerPanel,formPanel;
    public JTextArea addressLine1Field;
	public JTextArea addressLine2Field;
	public JTextArea addressLine3Field;
    public JComboBox<String> sexCombo, maritalStatusCombo, bloodGroupCombo, stateComboBox;
    public JDateChooser dobChooser;
    public static JButton registerButton, viewPatientsButton, updateButton;
    public PatientRegistrationController controller;
    static{
    	JsonHandler.readPatients();
    }
    
   
    public PatientRegistrationView() {
        frame = new JFrame("Patient Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout(5,5));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Header Panel
         headerPanel = new JPanel(new BorderLayout());
         headerPanel.setBackground(new Color(0, 153, 153));
        JLabel headerLabel = new JLabel("Patient Registration",JLabel.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0,150,139));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        headerPanel.add(headerLabel);
       frame.add(headerPanel, BorderLayout.NORTH);
        
       JLabel mrdlabel=new JLabel("MRD ID");
       mrdlabel.setForeground(Color.white);
        mrdidField=new JTextField(15);
        mrdidField.setEditable(false);
        formPanel=new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBackground(new Color(0,150,139));
        formPanel.add(mrdlabel);
        formPanel.add(mrdidField);
        formPanel.setVisible(false);
       
        
        

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.decode("#E3F2FD"));


        // Personal Details Panel
        JPanel personalDetailsPanel = new JPanel(new GridBagLayout());
        personalDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#64B5F6")),
                 null, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.decode("#1565C0")));
        personalDetailsPanel.setBackground(Color.WHITE);

        personalDetailsPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        
       // personalDetailsPanel.setBackground(new Color(224, 255, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // First Name and Last Name
        gbc.gridx = 0; gbc.gridy = 0;
        personalDetailsPanel.add(new JLabel("First Name*:"), gbc);
        firstNameField = new JTextField(15);
        gbc.gridx = 1;
        personalDetailsPanel.add(firstNameField, gbc);
        

        gbc.gridx = 2;
        personalDetailsPanel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(15);
        gbc.gridx = 3;
        personalDetailsPanel.add(lastNameField, gbc);

        // DOB and Age
        gbc.gridx = 0; gbc.gridy = 1;
        personalDetailsPanel.add(new JLabel("Date of Birth*:"), gbc);
        dobChooser = new JDateChooser();
        gbc.gridx = 1;
        personalDetailsPanel.add(dobChooser, gbc);

        gbc.gridx = 2;
        personalDetailsPanel.add(new JLabel("Age:"), gbc);
        ageField = new JTextField(10);
        ageField.setEditable(false);
        gbc.gridx = 3;
        personalDetailsPanel.add(ageField, gbc);

        // Sex and Marital Status
        gbc.gridx = 0; gbc.gridy = 2;
        personalDetailsPanel.add(new JLabel("Sex*:"), gbc);
        sexCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        gbc.gridx = 1;
        personalDetailsPanel.add(sexCombo, gbc);

        gbc.gridx = 2;
        personalDetailsPanel.add(new JLabel("Marital Status*:"), gbc);
        maritalStatusCombo = new JComboBox<>(new String[]{"Single", "Married", "Divorced", "Widowed"});
        gbc.gridx = 3;
        personalDetailsPanel.add(maritalStatusCombo, gbc);

        // Blood Group
        gbc.gridx = 0; gbc.gridy = 3;
        personalDetailsPanel.add(new JLabel("Blood Group*:"), gbc);
        bloodGroupCombo = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        gbc.gridx = 1;
        personalDetailsPanel.add(bloodGroupCombo, gbc);

        mainPanel.add(personalDetailsPanel);

        // Contact Details Panel
        JPanel contactDetailsPanel = new JPanel(new GridBagLayout());
        contactDetailsPanel .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#64B5F6")),
                null, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.decode("#1565C0")));
        contactDetailsPanel .setBackground(Color.WHITE);

        contactDetailsPanel.setBorder(BorderFactory.createTitledBorder("Contact Details"));
        //contactDetailsPanel.setBackground(new Color(240, 255, 255));

        // Email and Phone Number
        gbc.gridx = 0; gbc.gridy = 0;
        contactDetailsPanel.add(new JLabel("Email*:"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 1;
        contactDetailsPanel.add(emailField, gbc);

        gbc.gridx = 2;
        contactDetailsPanel.add(new JLabel("Phone Number*:"), gbc);
        contactField = new JTextField(15);
        gbc.gridx = 3;
        contactDetailsPanel.add(contactField, gbc);

        // Address Line 1 and 2
        gbc.gridx = 0; gbc.gridy = 1;
        contactDetailsPanel.add(new JLabel("Address Line 1*:"), gbc);
        addressLine1Field = new JTextArea(5,5);
        gbc.gridx = 1;
        contactDetailsPanel.add(addressLine1Field, gbc);

        gbc.gridx = 2;
        contactDetailsPanel.add(new JLabel("Address Line 2:"), gbc);
        addressLine2Field = new JTextArea(5,5);
        gbc.gridx = 3;
        contactDetailsPanel.add(addressLine2Field, gbc);

        // Address Line 3
        gbc.gridx = 0; gbc.gridy = 2;
        contactDetailsPanel.add(new JLabel("Address Line 3:"), gbc);
        addressLine3Field = new JTextArea(5,5);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        contactDetailsPanel.add(addressLine3Field, gbc);
        gbc.gridwidth = 1;

        // Pincode and State
        gbc.gridx = 0; gbc.gridy = 3;
        contactDetailsPanel.add(new JLabel("Pincode*:"), gbc);
        pincodeField = new JTextField(10);
        gbc.gridx = 1;
        contactDetailsPanel.add(pincodeField, gbc);

        gbc.gridx = 2;
        contactDetailsPanel.add(new JLabel("State*:"), gbc);
        stateComboBox = new JComboBox<>(new String[]{"Andhra Pradesh", "Assam", "Bihar", "Karnataka", "Kerala", "Tamil Nadu", "Telangana", "West Bengal"});
        gbc.gridx = 3;
        contactDetailsPanel.add(stateComboBox, gbc);

        mainPanel.add(contactDetailsPanel);

        // Emergency Contact Panel
        JPanel emergencyContactPanel = new JPanel(new GridBagLayout());
        emergencyContactPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#64B5F6")),
                null, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.decode("#1565C0")));
        emergencyContactPanel.setBackground(Color.WHITE);

        emergencyContactPanel.setBorder(BorderFactory.createTitledBorder("Emergency Contact"));
       // emergencyContactPanel.setBackground(new Color(245, 255, 250));

        // Name and Phone Number
        gbc.gridx = 0; gbc.gridy = 0;
        emergencyContactPanel.add(new JLabel("Name:"), gbc);
        emergencyContactNameField = new JTextField(15);
        gbc.gridx = 1;
        emergencyContactPanel.add(emergencyContactNameField, gbc);

        gbc.gridx = 2;
        emergencyContactPanel.add(new JLabel("Phone Number:"), gbc);
        emergencyContactNumberField = new JTextField(15);
        gbc.gridx = 3;
        emergencyContactPanel.add(emergencyContactNumberField, gbc);

        mainPanel.add(emergencyContactPanel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#64B5F6")),
                null, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.decode("#1565C0")));
        buttonPanel.setBackground(Color.WHITE);
       // buttonPanel.setBackground(new Color(224, 224, 224));
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(0,150,139));
        registerButton.setForeground(Color.white);
        viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.setBackground(new Color(0,150,139));
        viewPatientsButton.setForeground(Color.white);
        updateButton=new JButton("Update");
        updateButton.setBackground(new Color(0,150,139));
        updateButton.setForeground(Color.white);
        updateButton.setVisible(false);
        buttonPanel.add(registerButton);
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(updateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
       
        
        dobChooser.addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
            	LocalDate today=LocalDate.now();
                LocalDate dob = dobChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int age = Period.between(dob, LocalDate.now()).getYears();
                if(dob.getYear()==today.getYear()) {
                	Period period=Period.between(dob, today);
                	int months=period.getMonths();
                	ageField.setText(months+"months");
                	
                }
                else {
                ageField.setText(String.valueOf(age));
            }
            }
        });


        frame.setVisible(true);
        
    }
//    
    public PatientRegistrationView(Patient currentPatient, PatientTableView tableView,ArrayList<Patient> patients) {
    	this();
    	putFields(currentPatient);
    	updateButton.addActionListener((e) -> {
    	    if (currentPatient == null) {
    	        JOptionPane.showMessageDialog(frame, "No patient selected");
    	        return;
    	    }

    	    try {
    	        // Validate first name
    	        String firstName = firstNameField.getText().trim();
//    	        if (!firstName.matches("^[A-Za-z]+$")) {
//    	            JOptionPane.showMessageDialog(frame, "First name must contain letters only!");
//    	            return;
//    	        }

//    	      
    	        String lastName = lastNameField.getText().trim();
//    	        

    	        // Validate email
    	        String email = emailField.getText().trim();
    	        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    	            JOptionPane.showMessageDialog(frame, "Please enter a valid email!");
    	            return;
    	        }

    	        // Validate phone number
    	        String contactText = contactField.getText().trim();
    	        if (!contactText.matches("^[6-9][0-9]{9}$")) {
    	            JOptionPane.showMessageDialog(frame, "Invalid phone number format!");
    	            return;
    	        }
    	        long phoneNum = Long.parseLong(contactText);

    	        String emergencyContactText = emergencyContactNumberField.getText().trim();
//    	       
    	        long emergencyContactNum = Long.parseLong(emergencyContactText);

    	        // Validate pincode
    	        String pincode = pincodeField.getText().trim();
    	        if (!pincode.matches("\\d{6}")) {
    	            JOptionPane.showMessageDialog(frame, "Invalid pincode!");
    	            return;
    	        }
    	        //validate dob
    	        Date dobDate = dobChooser.getDate();
    	        LocalDate dob = dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	        if (dob.isAfter(LocalDate.now())) {
    				JOptionPane.showMessageDialog(frame, "Date of birth cannot be in the future");
    				return;
    			}

    	        // Update patient details only after successful validation
    	        currentPatient.setFirstName(firstName);
    	        currentPatient.setLastName(lastName);
    	        currentPatient.setDob(dobChooser.getDate() != null
    	                ? dobChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    	                : null);
    	        currentPatient.setSex((String) sexCombo.getSelectedItem());
    	        currentPatient.setMaritalStatus((String) maritalStatusCombo.getSelectedItem());
    	        currentPatient.setBloodGroup((String) bloodGroupCombo.getSelectedItem());
    	        currentPatient.setEmail(email);
    	        currentPatient.setPhoneNumber(phoneNum);
    	        currentPatient.setAddress(addressLine1Field.getText() + ", " +
    	                                  addressLine2Field.getText() + ", " +
    	                                  addressLine3Field.getText());
    	        currentPatient.setPincode(pincode);
    	        currentPatient.setState((String) stateComboBox.getSelectedItem());
    	        currentPatient.setEmergencyContactName(emergencyContactNameField.getText());
    	        currentPatient.setEmergencyContactNumber(emergencyContactNum);
                //recalculate age
    	        
    	        LocalDate currentDate = LocalDate.now();
    	        Period agePeriod = Period.between(dob, currentDate);
    	        int years = agePeriod.getYears();
    	        int months = agePeriod.getMonths();
    	        currentPatient.setAgeYears(years);
    	        currentPatient.setAgeMonths(months);
    	        String ageText = years + " Years, " + months + " Months";  // Update the age text

    	        // Update the age field in the view
    	        ageField.setText(ageText);
    	        // Save to JSON file and update table
    	        JsonHandler.savePatients(patients);
    	        tableView.updateTable(patients);
    	        tableView.tableModel.fireTableDataChanged();

    	        JOptionPane.showMessageDialog(frame, "Patient details updated successfully");
    	        frame.dispose();

    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
    	    }
    	});
    }
 public void switchtoUpdateMode() {
	 registerButton.setVisible(false);
	 viewPatientsButton.setVisible(false);
	 updateButton.setVisible(true);
	 headerPanel.setVisible(false);
	 frame.add(formPanel,BorderLayout.NORTH);
	 formPanel.setVisible(true);
	
 }
 public void switchtoRegisterMode() {
	 registerButton.setVisible(true);
	 viewPatientsButton.setVisible(true);
	 updateButton.setVisible(false);
	 formPanel.setVisible(false);
 }
    

    public void resetFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        dobChooser.setDate(new Date());
        ageField.setText("");
        sexCombo.setSelectedIndex(0);
        maritalStatusCombo.setSelectedIndex(0);
        bloodGroupCombo.setSelectedIndex(0);
        emailField.setText("");
        contactField.setText("");
        addressLine1Field.setText("");
        addressLine2Field.setText("");
        addressLine3Field.setText("");
        pincodeField.setText("");
        stateComboBox.setSelectedIndex(0);
        emergencyContactNameField.setText("");
        emergencyContactNumberField.setText("");
    }
    
    
    public void putFields(Patient patient) {
        // Set first name and last name
    	mrdidField.setText(String.valueOf(patient.getMrdID()));
        firstNameField.setText(patient.getFirstName());
        lastNameField.setText(patient.getLastName());

        // Set date of birth (convert LocalDate to Date)
        if (patient.getDob() != null) {
            dobChooser.setDate(java.sql.Date.valueOf(patient.getDob()));
        }

        // Calculate and set age based on DOB
        if (patient.getDob() != null) {
            LocalDate dob = patient.getDob();
            LocalDate today = LocalDate.now();
            int age = Period.between(dob, today).getYears();
            ageField.setText(String.valueOf(age));
        } else {
            ageField.setText("");
        }

        // Set sex, marital status, and blood group
        sexCombo.setSelectedItem(patient.getSex());
        maritalStatusCombo.setSelectedItem(patient.getMaritalStatus());
        bloodGroupCombo.setSelectedItem(patient.getBloodGroup());

        // Set email and phone number
        emailField.setText(patient.getEmail());
        contactField.setText(String.valueOf(patient.getPhoneNumber()));

        // Set address fields
        String address = patient.getAddress();
        if (address != null) {
            String[] addressParts = address.split(",\\s*");
            addressLine1Field.setText(addressParts.length > 0 ? addressParts[0] : "");
            addressLine2Field.setText(addressParts.length > 1 ? addressParts[1] : "");
            addressLine3Field.setText(addressParts.length > 2 ? addressParts[2] : "");
        } else {
            addressLine1Field.setText("");
            addressLine2Field.setText("");
            addressLine3Field.setText("");
        }

        // Set pincode and state
        pincodeField.setText(patient.getPincode());
        stateComboBox.setSelectedItem(patient.getState());

        // Set emergency contact name and number
        emergencyContactNameField.setText(patient.getEmergencyContactName());
        emergencyContactNumberField.setText(String.valueOf(patient.getEmergencyContactNumber()));
        switchtoUpdateMode();
    }

}
