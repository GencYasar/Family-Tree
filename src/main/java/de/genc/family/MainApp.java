package de.genc.family;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private VBox vbox;
	private HBox hbox;
	private Pane pane;
	private ArrayList<Member> members;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		vbox = new VBox();
		hbox = new HBox();
		pane = new Pane();
		members = new ArrayList<Member>();
		
		Member m1 = new Member(100, 100, 180, 60);
		m1.setGender("male");
		m1.init();
		Member m2 = new Member(300, 300, 180, 60);
		m2.setGender("female");
		m2.init();
		Member m3 = new Member(500, 500, 180, 60);
		m3.setGender("other");
		m3.init();
		members.add(m1);
		members.add(m2);
		members.add(m3);
		
		// vbox
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, null, null)));
		vbox.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, null, null, BorderStrokeStyle.SOLID,
				BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, null, null, null)));
		
		// prompt components
		Text nameprompt = new Text("Full name:");
		TextField name = new TextField();
		name.setPromptText("Enter name here");
		
		ToggleGroup tg = new ToggleGroup();
		RadioButton male = new RadioButton("Male");
		male.setToggleGroup(tg);
		
		RadioButton female = new RadioButton("Female");
		female.setToggleGroup(tg);
		
		RadioButton other = new RadioButton("Other");
		other.setToggleGroup(tg);
		other.setSelected(true);
		
		Text genderprompt = new Text("Gender:");
		HBox gender = new HBox();
		gender.setSpacing(8);
		gender.getChildren().addAll(male, female, other);
		
		TextField month = new TextField();
		month.setPromptText("MM");
		month.setPrefWidth(36);
		
		TextField day = new TextField();
		day.setPromptText("DD");
		day.setPrefWidth(36);
		
		TextField year = new TextField();
		year.setPromptText("YYYY");
		year.setPrefWidth(46);
		
		month.textProperty().addListener((obs, old, str) -> {
			if (str.equals("")) {
				month.setText("");
				return;
			}
			if (!str.matches("\\d*")) {
				if (old.equals(""))
					month.clear();
				else month.setText(str.replaceAll("[^\\d]", ""));
			}
			else if (Integer.parseInt(str) > 12 || Integer.parseInt(str) <= 0) {
				month.setText(old);
				return;
			}
			if (str.equals("2")) {
				if (Integer.parseInt(day.getText()) > 28)
					day.setText("28");
			}
			if (str.equals("4") || str.equals("6") || str.equals("9") || str.equals("11")) {
				if (Integer.parseInt(day.getText()) > 30)
					day.setText("30");
			}
		});
		day.textProperty().addListener((obs, old, str) -> {
			if (str.equals("")) {
				day.setText("");
				return;
			}
			if (!str.matches("\\d*")) {
				if (old.equals(""))
					day.clear();
				else day.setText(str.replaceAll("[^\\d]", ""));
			}
			else {
				int d = 31;
				String m = month.getText();
				if (m.equals("2"))
					d = 28;
				else if (m.equals("4") || m.equals("6") || m.equals("9") || m.equals("11"))
					d = 30;
				if (Integer.parseInt(str) > d || Integer.parseInt(str) <= 0) {
					day.setText(old);
					return;
				}
			}
		});
		year.textProperty().addListener((obs, old, str) -> {
			if (str.equals("")) {
				year.setText("");
				return;
			}
			if (!str.matches("\\d*")) {
				if (old.equals(""))
					year.clear();
				else year.setText(str.replaceAll("[^\\d]", ""));
			}
			else if (Integer.parseInt(str) > Calendar.getInstance().get(Calendar.YEAR) || Integer.parseInt(str) <= 0) {
				year.setText(old);
				return;
			}
		});
		
		Text birthdayprompt = new Text("Date of birth:");
		HBox birthday = new HBox();
		birthday.setSpacing(8);
		birthday.getChildren().addAll(month, day, year);
		
		CheckBox alive = new CheckBox("Alive");
		alive.setSelected(true);
		
		Button addchild = new Button("Add Child");
		Button addparent = new Button("Add Parent");
		Button addsibling = new Button("Add Sibling");
		Button addpartner = new Button("Add Partner");
		
		// hbox
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		hbox.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null, BorderStrokeStyle.SOLID,
				BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, null, null, null)));
		
		ComboBox<String> cb = new ComboBox<String>();
		cb.setPromptText("Choose family member");
		Button load = new Button("Load");
		Button exit = new Button("Exit");
		exit.setOnAction(e -> {
			stage.close();
		});
		hbox.getChildren().addAll(cb, load, exit);
		
		// buttons
		Button add = new Button("Add family member");
		add.setOnAction(e -> {
			
			Member member;
			String n = name.getText();
			if (n.equals(""))
				n = "First Last";
			String g = ((RadioButton) tg.getSelectedToggle()).getText().toLowerCase();
			boolean a = alive.isSelected();
			String m = month.getText();
			String d = day.getText();
			String y = year.getText();
			
			if (y.equals(""))
				member = new Member(n, g, a);
			else if (m.equals("") || d.equals(""))
				member = new Member(n, g, Integer.parseInt(y), a);
			else member = new Member(n, g, Integer.parseInt(m), Integer.parseInt(d), Integer.parseInt(y), a);
			
			member.locate();
			member.init();
			members.add(member);
			pane.getChildren().addAll(member, member.text);
			cb.getItems().add(member.getName());
		});
		vbox.getChildren().addAll(nameprompt, name, genderprompt, gender, birthdayprompt, birthday, alive,
				add, addchild, addparent, addsibling, addpartner);
		
		// pane
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		for (Member m : members) {
			pane.getChildren().add(m);
		}
		pane.setOnMousePressed(e -> {
			for (Member m : members) {
				m.offsetx = e.getSceneX();
				m.offsety = e.getSceneY();
			}
		});
		pane.setOnMouseDragged(e -> {
			for (Member m : members) {
				pane.toFront();
				Member member = (Member) m;
				member.setX(member.getX() + e.getSceneX() - member.offsetx);
				member.setY(member.getY() + e.getSceneY() - member.offsety);
				member.text.setX(member.text.getX() + e.getSceneX() - member.offsetx);
				member.text.setY(member.text.getY() + e.getSceneY() - member.offsety);
				member.offsetx = e.getSceneX();
				member.offsety = e.getSceneY();
				vbox.toFront();
				hbox.toFront();
			}
		});
		/*
		pane.setOnScroll(e -> {
			double delta = e.getDeltaY();
			for (Member member : members) {
				Member m = (Member) member;
				m.setScaleX(m.getScaleX());
			}
		});
		*/
		
		// border pane
		BorderPane border = new BorderPane();
		border.setLeft(vbox);
		border.setBottom(hbox);
		border.setCenter(pane);
		
		// stage
		Scene scene = new Scene(border, 1280, 720);
		stage.setTitle("Family Tree");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
