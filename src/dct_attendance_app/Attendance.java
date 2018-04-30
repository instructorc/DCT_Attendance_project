package dct_attendance_app;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Attendance extends Application {
	
	public static void main(String[] args) throws IOException {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	
		// TODO Auto-generated method stub
		Parent parent = FXMLLoader.load(getClass().getResource("dc_interface_1.fxml"));
		
		//Build the scene graph.
		Scene scene = new Scene(parent);
		
		//Display our window, using the scene graph.
		stage.getIcons().add(new Image("dct_attendance_app/dunwoody_d_logo.PNG"));
		stage.setTitle("Dunwoody Attendance Portal (DAP)");
		stage.setScene(scene);
		stage.show();
		

	}



}
