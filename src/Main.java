import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setBackground(new Background(new BackgroundFill(Color.THISTLE, null, null)));
		
		Button button = new Button("Aye");
		button.setOnAction(e -> {
			Toolkit.getDefaultToolkit().beep();
			stage.close();
		});
		vbox.getChildren().addAll(button);
		
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		
		Pane pane = new Pane();
		//pane.setPrefSize(3000, 3000);
		pane.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		ArrayList<Member> members = new ArrayList<Member>();
		Member m1 = new Member(400, 400, 150, 50);
		Member m2 = new Member(600, 600, 150, 50);
		Member m3 = new Member(800, 800, 150, 50);
		members.add(m1);
		members.add(m2);
		members.add(m3);
		pane.getChildren().addAll(m1, m2, m3);

		pane.setOnMousePressed(e -> {
			for (Member m : members) {
				m.offsetx = e.getSceneX();
				m.offsety = e.getSceneY();
			}
		});
		pane.setOnMouseDragged(e -> {
			for (Member m : members) {
				Member member = (Member) m;
				member.setX(member.getX() + e.getSceneX() - member.offsetx);
				member.setY(member.getY() + e.getSceneY() - member.offsety);
				member.offsetx = e.getSceneX();
				member.offsety = e.getSceneY();
				member.toBack();
			}
		});
		/*
		pane.setOnScroll(e -> {
			double delta = e.getDeltaY();
			for (Member member : members) {
				Member m = (Member) member;
				m.setScaleX(m.getScaleX());
			}
		}); */
		/*
		ScrollPane sp = new ScrollPane();
		sp.setContent(pane);
		sp.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		sp.setPannable(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		*/
		BorderPane border = new BorderPane();
		border.setLeft(vbox);
		border.setBottom(hbox);
		border.setCenter(pane);
		
		Scene scene = new Scene(border, 1920, 1080);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
