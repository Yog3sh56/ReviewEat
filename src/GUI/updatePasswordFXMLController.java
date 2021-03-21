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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * 
 * Controller class for update password
 * This class is responsible for updating password if valid credentials are provided
 * @author Yogesh Parajuli
 *
 */
public class updatePasswordFXMLController {
	
	private dataSQLite data = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);
	
	

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField currentPasswordTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private PasswordField reNewPasswordTextField;

    // clicking cancel button would take you back to login page
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    }

    //Updates the password
    @FXML
    void updatePassword(ActionEvent event) throws IOException {
    	String username = usernameTextField.getText();
    	String curPassword = currentPasswordTextField.getText();
    	String newPassword = newPasswordTextField.getText();
    	String reNewPassword = reNewPasswordTextField.getText();
    	
    	//Validates the details first
    	if(data.validate(username, curPassword)) {
    		
    		//checks if the passwords matches
    		if(newPassword.equals(reNewPassword)) {
    			
    			// checks for validation
    			if(newPassword.matches("^[a-zA-Z0-9]{6,20}$")) {


    				data.updatePassword(username, newPassword);

    				a.setAlertType(AlertType.CONFIRMATION);
    				a.setHeaderText("Successful");
    				a.setContentText("Password successfully updated");
    				a.show();


    				Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

    				Scene scene = new Scene(root);

    				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

    				stage.setScene(scene);
    				stage.show();
    			}
    			else {
    				
    				
    				a.setAlertType(AlertType.ERROR);
    	    		a.setHeaderText("Invalid password");
    	    		a.setContentText("Please check the details and try again. This is caused due to password constrains. Refer to 'How to register?' section of the manual.");
    	    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    	    		a.show();
    			}
    			
    			
    		}
    		else {
    			a.setAlertType(AlertType.ERROR);
        		a.setHeaderText("Passwords do not match");
        		a.setContentText("Please try again");
        		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        		a.show();
    		}
    		
    	}
    	else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid username or password");
    		a.setContentText("Please check your credential and try again.");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		a.show();
    	}

    }

}
