package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Database.dataSQLite;
import application.InputValidationException;
import application.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller Class for Add Restaurant fxml
 * This class is responsible to provide an interface for adding retaurant to the database
 * @author Yogesh Parajuli
 *
 */
public class addRestaurantFXMLController implements Initializable {

	private dataSQLite data  = dataSQLite.getInstance();
	Alert a = new Alert(AlertType.NONE);

	@FXML
	private TextField nameTextField;

	@FXML
	private ChoiceBox<String> cuisineDropBox = new ChoiceBox<String>();

	@FXML
	private TextField addressTextField;

	@FXML
	private TextField contactTextField;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Button addButton;

	@FXML
	private Button cancelButton;

	@FXML
	void addRestaurant(ActionEvent event) throws IOException, NullPointerException {
		String name = nameTextField.getText();
		String cuisine = cuisineDropBox.getValue();
		String address = addressTextField.getText();
		String contact = contactTextField.getText();
		String restaurantDescription = descriptionTextArea.getText();


		// we need to perform some sort of check so that same restaurant is not added twice
		TreeSet<Restaurant> restaurants = data.openRestaurants();
		boolean check = false;
		for (Restaurant r: restaurants) {
			if (r.getAddress().equals(address) && r.getName().equals(name)) {
				check = true;
			}
		}


		if (!check) {
			//save the restaurant to the database
			try {
				data.saveRestaurant(new Restaurant(name, restaurantDescription, cuisine, contact, address));

				//display an alert saying that the restaurant has been added
				a.setAlertType(AlertType.INFORMATION);
				a.setHeaderText("Restaurant Added");
				a.setContentText("Restaurant has been successfully added");
				a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				a.show();

				// go back to the adminview fxml
				Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));

				Scene scene = new Scene(root);

				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

				stage.setScene(scene);
				stage.show();

			} catch (InputValidationException e) {

				// General Error Alert
				e.printStackTrace();
				a.setAlertType(AlertType.ERROR);
				a.setHeaderText("Input Invalid");
				a.setContentText("Please check the information you have entered.");
				a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				a.show(); 
			}
		}
		else {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Restaurant has already been added.");
			a.show();
		}



	}

	// Takes you back to previous fxml
	@FXML
	void cancel(ActionEvent event) throws IOException {
		// go back to the adminview fxml
		Parent root = FXMLLoader.load(getClass().getResource("adminViewReviewEat.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Since we are using a dropbox, initialising the choice box with items and a default value
		cuisineDropBox.getItems().addAll("British","Chinese", "French","Indian", "Italian", "Jamaican");
		cuisineDropBox.setValue("British");

	}

}
