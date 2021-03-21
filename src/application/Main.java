package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


/**
 * @author Yogesh Parajuli
 * Graded Unit Project 
 * Class containing main method.
 * It loads loginFXML file
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/loginFXML.fxml"));
			Scene scene = new Scene(root,312,413);
			primaryStage.setTitle("Review Eat");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 * Public Main method to launch the application
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
