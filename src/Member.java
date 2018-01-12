import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Member extends Rectangle {

	public double offsetx, offsety;
	
	private String name, gender, dob;
	private int month, day, year;
	private boolean alive;
	private String email, phone, address;
	private ArrayList<Member> children, parents, siblings, partners;
	
	public Member(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
	}
	
	public Member() {
		super();
	}
	
	public Member(String name, String gender, boolean alive) {
		super();
		setName(name);
		setGender(gender);
		setAlive(alive);
	}
	
	public Member(String name, String gender, int month, int day, int year, boolean alive) {
		this(name, gender, alive);
		setMonth(month);
		setDay(day);
		setYear(year);
		setDOB(month + "/" + day + "/" + year);
	}
	
	public void init() {
		gender = "male";
		if (gender.equals("male")) {
			this.setFill(Color.LIGHTBLUE);
		}
		else if (gender.equals("female")) {
			this.setFill(Color.LIGHTCORAL);
		}
		else {
			this.setFill(Color.LIGHTGREEN);
		}
		this.setStroke(Color.BLACK);
		this.setArcHeight(15);
		this.setArcWidth(15);
		//this.toFront();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getDOB() {
		return dob;
	}
	
	public void setDOB(String dob) {
		this.dob = dob;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Member> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Member> children) {
		this.children = children;
	}

	public ArrayList<Member> getParents() {
		return parents;
	}

	public void setParents(ArrayList<Member> parents) {
		this.parents = parents;
	}

	public ArrayList<Member> getSiblings() {
		return siblings;
	}

	public void setSiblings(ArrayList<Member> siblings) {
		this.siblings = siblings;
	}

	public ArrayList<Member> getPartners() {
		return partners;
	}

	public void setPartners(ArrayList<Member> partners) {
		this.partners = partners;
	}
	
}
