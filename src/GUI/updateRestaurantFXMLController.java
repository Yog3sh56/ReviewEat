package GUI;

import javafx.scene.control.Label;
import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;

import Database.dataSQLite;
import application.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Controller class for Update Restaurant
 * @author Yogesh Parajuli
 *
 */
public class updateRestaurantFXMLController implements Initializable{
	private dataSQLite data = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);
	private Restaurant restaurant;


	/**
	 * Getter for restaurant
	 * @return restaurnat
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Setter for restaurant
	 * @param restaurant
	 */
	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}


	@FXML
	private Label restaurantLabel ;  // = new Label(getRestaurant().toString()) 


	@FXML
	private TextField addressTextField;

	@FXML
	private TextField contactTextField;

	@FXML
	private Button updateButton;

	@FXML
	private Button cancelButton;

	@FXML
	private TextArea descriptionTextArea;



	//Takes you back to previous window
	@FXML
	void cancel(ActionEvent event) throws IOException {
		// go back to the adminview thing
		Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
	}

	//Updates the details of the selected restaurant
	@FXML
	void updateRestaurant(ActionEvent event) throws IOException, NullPointerException{
		String address = addressTextField.getText();
		String contact = contactTextField.getText();
		String restaurantDescription = descriptionTextArea.getText();
		System.out.println(getRestaurant());

		// checks that the the textfields are not null
		if(!address.equals(null) && (!contact.equals(null) && (!restaurantDescription.equals(null)))) {

			//checks for input validation
			if(contact.matches("\\d{11}") && address.matches("^(\\d+)\\s(\\w+)\\s(\\w+)$") && restaurantDescription.matches("\\p{ASCII}{4,100}")) {



				data.updateRestaurant(restaurant.getId(), contact, address, restaurantDescription);

				a.setAlertType(AlertType.CONFIRMATION);
				a.setContentText("Restaurant details successfully updated");
				a.show();

				// go back to the adminview fxml
				Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));

				Scene scene = new Scene(root);

				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

				stage.setScene(scene);
				stage.show();
			}
			else {
				a.setAlertType(AlertType.ERROR);
				a.setHeaderText("Invalid Input");
				a.setContentText("Please Check your input and try again. This is caused due to input validation error.");
				a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				a.show();
			}

		}
		else {
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("Invalid Input");
			a.setContentText("Empty values not accepted");
			a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			a.show();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Method to set the label
	 * @param currentRestaurant
	 */
	public void setLabel(String currentRestaurant) {
		restaurantLabel.setText(currentRestaurant);
	}



}

