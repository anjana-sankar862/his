package com.Model;

import java.io.IOException;
import java.time.LocalDate;

public class Patient {
	private static int tokenCounter;
    private int mrdID;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int ageYears;
    private int ageMonths;
    private String sex;
    private String maritalStatus;
    private String bloodGroup;
    private String email;
    private long phoneNumber;
    private String address;
    private String pincode;
    private String state;
    private String emergencyContactName;
    private long emergencyContactNumber;
    public Patient() {
    	super();
    	// TODO Auto-generated constructor stub
    }
    public Patient( String firstName, String lastName,LocalDate dob, int ageYears,int ageMonths,String sex, String maritalStatus,
                   String bloodGroup, String email, long phoneNumber, String address, String pincode, String state,String emergencyContactName,
                   long emergencyContactNumber) {
    	tokenCounter = tokenCounter +1;
        this.mrdID = tokenCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ageYears=ageYears;
        this.ageMonths=ageMonths;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pincode = pincode;
        this.state = state;
        this.emergencyContactName=emergencyContactName;
        this.emergencyContactNumber=emergencyContactNumber;
    }
    // Getters and setters
    public int getMrdID() {
        return mrdID;
    }
    public void setMrdID(int mrdID) {
        this.mrdID = mrdID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public int getAgeYears() {
		return ageYears;
	}
	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}
	public int getAgeMonths() {
		return ageMonths;
	}
	public void setAgeMonths(int ageMonths) {
		this.ageMonths = ageMonths;
	}
	public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
	public static int getTokenCounter() {
		return tokenCounter;
	}
	public static void setTokenCounter(int tokenCounter) {
		Patient.tokenCounter = tokenCounter;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public long getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(long emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
	
	
	
    
}
