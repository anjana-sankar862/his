package com.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.Model.JsonHandler;
import com.Model.Patient;
import com.View.PatientRegistrationView;
import com.View.PatientTableView;

public class PatientRegistrationController {
	private static PatientRegistrationView view;
	private Patient currentPatient;
	public PatientTableView tableView;
	static PatientTableView patientTableView;

	private JsonHandler jsonHandler;

	public PatientRegistrationController(PatientRegistrationView view) {
		this.view = view;
		this.tableView = tableView;
		// this.jsonHandler = new JsonHandler();

		PatientRegistrationView.registerButton.addActionListener(e -> registerPatient());
		PatientRegistrationView.viewPatientsButton.addActionListener(e -> viewPatients());
		// PatientRegistrationView.updateButton.addActionListener(e->
		// updatePatientDetails());
	}

	public void loadPatientForUpdate(Patient patient) {
		currentPatient = patient; // Set the current patient for update.
		view.putFields(patient); // Populate the form fields.
		view.registerButton.setVisible(false); // Hide the Register button.
		view.updateButton.setVisible(true); // Show the Update button.
	}

	private void registerPatient() {
		try {
			String firstName = view.firstNameField.getText();
			String lastName = view.lastNameField.getText();
			
			Date dobDate = view.dobChooser.getDate();
			String sex = (String) view.sexCombo.getSelectedItem();
			String maritalStatus = (String) view.maritalStatusCombo.getSelectedItem();
			String addressLine1 = view.addressLine1Field.getText();
			String addressLine2 = view.addressLine2Field.getText();
			String addressLine3 = view.addressLine3Field.getText();
			String pincode = view.pincodeField.getText();
			String state = (String) view.stateComboBox.getSelectedItem();
			String fullAddress = String.join(", ", addressLine1, addressLine2, addressLine3, pincode, state).trim();
			String email = view.emailField.getText();
			String bloodGroup = (String) view.bloodGroupCombo.getSelectedItem();
			String contactText = view.contactField.getText().trim();
			long contactNumber = Long.parseLong(contactText);
			String emergencyContactName = view.emergencyContactNameField.getText();
			String emergencyContactText = view.emergencyContactNumberField.getText().trim();
			long emergencyContactNumber=0 ;
			LocalDate dob= dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			// Validation checks
			try {
				if(firstName.isEmpty() || dobDate == null || fullAddress.isEmpty() || pincode.isEmpty() || state.isEmpty()
						|| email.isEmpty() || contactText.isEmpty() || pincode.isEmpty() || state.isEmpty()) {
					JOptionPane.showMessageDialog(view.frame, "All fields are mandatory!");
					return;
				}
			
			if (!firstName.matches("^[A-Za-z]+$")) {
				JOptionPane.showMessageDialog(view.frame, "Name fields must contain letters only");
				return;
			}
			dob = dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (dob.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(view.frame, "Date of birth cannot be in the future");
				return;
			}
			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				JOptionPane.showMessageDialog(view.frame, "Please enter a valid email!");
				return;
			}
			if (!contactText.matches("^[6-9][0-9]{9}$")) {
				JOptionPane.showMessageDialog(view.frame, "Invalid phone number format!");
				return;
			}
			if (!pincode.matches("\\d{6}")) {
				JOptionPane.showMessageDialog(view.frame, "Invalid pincode");
				return;
			}
			if (!emergencyContactText.isEmpty()) {
				emergencyContactNumber=Long.parseLong(emergencyContactText);
				}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(view.frame, "All fields are mandatory!");
			}
				//emergencyContactNumber = Long.parseLong(emergencyContactText);
			
			// Calculate age

			LocalDate currentDate = LocalDate.now();
			Period agePeriod = Period.between(dob, currentDate);
			String ageText;
			int years = agePeriod.getYears();
			int months = agePeriod.getMonths();
			if (dob.getYear() == currentDate.getYear()) {
				// Age in months only if DOB year matches current year
				ageText = months + " Months";
				years = 0; // Set years to 0
			} else {
				// Age in years and months
				ageText = years + " Years, " + months + " Months";
			}
			// Display age in the UI
			view.ageField.setText(ageText);
			// Create Patient object
			Patient patient = new Patient(firstName,lastName, dob, years, months, sex, maritalStatus, bloodGroup, email,
					contactNumber, fullAddress, pincode, state, emergencyContactName, emergencyContactText.isEmpty()?0:emergencyContactNumber);
			// Save to JSON File
			List<Patient> patients = jsonHandler.readPatients();
			patients.add(patient);
			jsonHandler.savePatients(patients);
			JOptionPane.showMessageDialog(view.frame, "Patient registered successfully!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view.frame, "All fields are mandatory");
			return;
		}
		// Reset form fields
		view.resetFields();
	}

	public void viewPatients() {
		List<Patient> patients = jsonHandler.readPatients();
		patientTableView = new PatientTableView(patients, view);
	}
}
