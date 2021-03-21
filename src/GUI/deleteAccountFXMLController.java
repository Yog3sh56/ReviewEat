
package GUI;

import java.io.IOException;

import Database.dataSQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller to delete Account fxml
 * This class is responsible for proving an interface to the user and allow them to delete thier account
 * @author Yogesh Parajuli
 * 
 *
 */
public class deleteAccountFXMLController {
	private dataSQLite data  = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    //Takes user back to previous fxml
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void deleteUser(ActionEvent event) throws IOException {
    	String username = usernameTextField.getText();
    	String password = passwordTextField.getText();
    	
    	// first we validate the username and password entered
    	if(data.validate(username, password)) {
    		a.setAlertType(AlertType.CONFIRMATION);
    		a.setHeaderText("Delete Account");
    		a.setContentText("Are you sure you want to delete your account?");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		
    		// Here we confirm if the user really wants to delete their account
    		if (a.showAndWait().get() ==  ButtonType.OK) {
    			data.removeUser(username);
    			
    			a.setAlertType(AlertType.INFORMATION);
    			a.setHeaderText("Delete Account");
    			a.setContentText("Account Successfully Deleted");
    			a.show();
    			
    			
    			Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

    			Scene scene = new Scene(root);

    			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

    			stage.setScene(scene);
    			stage.show();
    			
    		}
    		else {
    			a.close();
    		}
    		
    	}
    	else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Account Not found");
    		a.setHeaderText("Delete Account");
    		a.show();
    		
    	}
    }

}

