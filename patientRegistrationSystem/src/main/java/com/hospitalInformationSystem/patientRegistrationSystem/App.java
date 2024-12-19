package com.hospitalInformationSystem.patientRegistrationSystem;

import java.util.List;

import javax.swing.UIManager;

import com.Controller.PatientRegistrationController;
import com.Model.JsonHandler;
import com.Model.Patient;
import com.View.PatientRegistrationView;
import com.View.PatientTableView;

public class App {
	static PatientRegistrationController registrationController=null;
	public static void main(String[] args) {
		
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus Look and Feel not applied. Falling back to default.");
        }
		       
				PatientRegistrationView view = new  PatientRegistrationView();
				
				registrationController = new PatientRegistrationController(view);
			}
		
	}


