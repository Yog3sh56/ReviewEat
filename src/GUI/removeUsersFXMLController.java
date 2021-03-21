package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.dataSQLite;
import application.InputValidationException;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Controller Class for remove users fxml
 * @author Yogesh Parajuli
 *
 */
public class removeUsersFXMLController implements Initializable{
	private dataSQLite data= dataSQLite.getInstance();
	Alert a =  new Alert(AlertType.NONE);
	

    @FXML
    private ListView<User> usersListView;

    @FXML
    private Button removeUsersButton;

    @FXML
    private Button cancelButton;

    // Takes you back to previous window
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

    }

    //Removes a user from the system
    @FXML
    void removeUser(ActionEvent event) {
    	User selectedUser = usersListView.getSelectionModel().getSelectedItem();
    	
    	if(!(selectedUser == null)) {
    		
    		a.setAlertType(AlertType.CONFIRMATION);
    		a.setHeaderText("Delete Account");
    		a.setContentText("Are you sure you want to remove this user?");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		
    		
    		// Waits for confirmation before deleting the account
    		if(a.showAndWait().get() ==  ButtonType.OK) {
    			usersListView.getItems().remove(selectedUser);
        		data.removeUser(selectedUser.getUsername());
        		
        		a.setAlertType(AlertType.INFORMATION);
    			a.setHeaderText("Delete Account");
    			a.setContentText("Account Successfully Deleted");
    			a.show();
    		}
    		else {
    			a.close();
    		}
    		
    	}
    	else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Please select a user.");
    		a.setTitle("Invalid Input");
    		a.show();
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Fetching all the users data
		try {
			usersListView.getItems().addAll(data.openAllUsers());
		} catch (InputValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
