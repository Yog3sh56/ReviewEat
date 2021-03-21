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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Register User fxml controller
 * 
 * This class is responsible for registering a user
 * @author Yogesh Parajuli
 *
 */
public class registerUserFXMLController {
	User user;
	private dataSQLite data  = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);
	
	@FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField fNameTextfield;

    @FXML
    private TextField sNameTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private PasswordField rePasswordTextfield;

    //Takes you back to preious page
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();


    }
    
    
    //regiseters the user
    @FXML
    void registerUser(ActionEvent event) throws  IOException, NullPointerException{
    	String fName = fNameTextfield.getText();
    	String sName = sNameTextfield.getText();
    	String email = emailTextfield.getText();
    	String username = usernameTextfield.getText();
    	String password = passwordTextfield.getText();
    	String rePassword = rePasswordTextfield.getText();
    	
    	//check the user name is available
    	//i.e. if the user with same name is already registered 
    	if (data.checkUsername(username)) {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Username not available");
    		a.setContentText("Please choose a different username because current username has already been taken.");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		a.show();
    	}
    	
    	// Prevents using admin as username
    	else if (username.equals(Admin.getAdminUsername())) {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Reserved Username");
    		a.setContentText("You cannot use reserved username. Please choose a different username");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		a.show();
    	}
    	//check the passwords match
    	else if(!password.equals(rePassword)) {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Passwords do not match.");
    		a.setContentText("Please type the passwords again");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		a.show();
    	}
    	else {
    	//add to the database
	    	try {
				user = new User(username, password, fName, sName, email);
			} catch (InputValidationException e) {
				e.printStackTrace();
				a.setAlertType(AlertType.ERROR);
	    		a.setHeaderText("Input Invalid");
	    		a.setContentText("Please check the details you have entered.");
	    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	    		a.show();
			}
	    	data.saveUser(user);
	    	a.setAlertType(AlertType.CONFIRMATION);
	    	a.setHeaderText("Registration successful");
	    	a.setContentText("Now you can login to Revew Eat");
	    	a.show();
	    	
	    	// send the user back to login page
	    	Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));
	
			Scene scene = new Scene(root);
	
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	
			stage.setScene(scene);
			stage.show();
    	}

    }

}

