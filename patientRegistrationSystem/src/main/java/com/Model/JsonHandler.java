package com.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
	private static  String filePath = "patientsData.json";
	private static  ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}

	public static List<Patient> readPatients() {
		try {
			File file = new File(filePath);
			if (!file.exists() || file.length() == 0) {

				return new ArrayList<>();
			}
			List<Patient> patients =mapper.readValue(file, new TypeReference<List<Patient>>() {});
			Patient.setTokenCounter(patients.size());
			return patients;
		} catch (JsonParseException e) {

			System.err.println("JSON file is empty or corrupted. Returning an empty list.");
			return new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static void savePatients(List<Patient> patients) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), patients);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePatient(Patient updatedPatient) throws IOException {
	    List<Patient> patients = readPatients();
	    for (int i = 0; i < patients.size(); i++) {
	        if (patients.get(i).getMrdID() == updatedPatient.getMrdID()) {
	            patients.set(i, updatedPatient);
	            break;
	        }
	    }
	    savePatients(patients); // Save the updated list back to the JSON file
	}

}
