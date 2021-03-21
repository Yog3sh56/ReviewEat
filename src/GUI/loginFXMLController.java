package GUI;
import java.io.IOException;

import Database.dataSQLite;
import application.Admin;
import application.InputValidationException;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * Controller for loginFXML
 * This is the first fxml that can be viewed
 * This fxml provides access to vaious other fxmls
 * @author Yogesh Parajuli
 *
 */
public class loginFXMLController {
	User currentUser;
	
	private dataSQLite data  = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button registerButton;

	@FXML
	private Button updatePasswordButton;

	@FXML
	private Button loginButton;

	@FXML
	private Button deleteAccountButton;

	// takes you to delete accoutn page
	@FXML
	void deleteAccount(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("deleteAccountFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

	}
	
	//Logs you in 
	@FXML
	void login(ActionEvent event) throws InputValidationException, IOException {
		String username = usernameField.getText();
		String password = passwordField.getText();

		if(data.validate(username, password)) {
			currentUser =  data.openUser(username);
				if (!currentUser.areYouAdmin()) {
					// Change the parent i.e base class using fxml loader
										
					FXMLLoader loader = new FXMLLoader(getClass().getResource("userViewReviewEatFXML.fxml"));
					Parent root = loader.load();
					 
					// Trying to pass on a User object to next controller
					userViewReviewEatFXMLController nextController = loader.getController();
					nextController.setUser(currentUser);
					
		
					//Set the scene to the new root i.e new fxml file
					Scene scene = new Scene(root);
		
					//Set the stage information
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
					stage.setScene(scene);
					stage.show();	
				}	

		}
		
		//Admin details are not stored in the database
		//instead they are set as private static attribute in admin class
		
		else if(username.equals(Admin.getAdminUsername()) && password.equals(Admin.getAdminPassword())) {
			// Change the parent i.e base class using fxml loader
			Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));
		

			//Set the scene to the new root i.e new fxml file
			Scene scene = new Scene(root);

			//Set the stage information
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			
			

			stage.setScene(scene);
			
			stage.show();
			
			
		}
		else {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Login Unsuccessful. Please check your detail and try again");
			a.show();		 
		}


	}

	// Takes you to register page
	@FXML
	void register(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("registerUserFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

	}
	
	// Takes you to update password page
	@FXML
	void updatePassword(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("updatePasswordFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

	}	

}
