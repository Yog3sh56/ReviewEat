package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Database.dataSQLite;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * Controller for Admin View review eat
 * This class is the defauld view for the admin providing access to different actions
 * @author Yogesh Parajuli
 *
 */
public class adminViewReviewEatFXMLController implements Initializable{
	
	
    @FXML
    private ListView<Restaurant> listRestaurants;
    Alert a = new Alert(AlertType.NONE);
    private dataSQLite data = dataSQLite.getInstance();
   
   
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    	// Populate the list view with restaurant
    	// Likewise, open the reviews that are associated with that restaurant
    	TreeSet <Restaurant> restaurants = data.openRestaurants();
		for(Restaurant r: restaurants) {
			r.setReviews(data.openReview(r.getId()));
			
			r.getAverageRating();
		}
		listRestaurants.getItems().addAll(restaurants);
		
		
	}

    

    @FXML
    private Button addRestaurantButton;

    @FXML
    private Button updateRestaurantButton;

    @FXML
    private Button deleteRestaurantButton;
    
    @FXML
    private Button deleteUsersButton;

    @FXML
    private Button logOutButton;

    
    @FXML
    void addRestaurant(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("addRestaurantFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    }
    

    @FXML
    void deleteRestaurant(ActionEvent event) {
    	Restaurant restaurant=listRestaurants.getSelectionModel().getSelectedItem();
    	if(restaurant != null) {
    		listRestaurants.getItems().remove(restaurant);
    		data.removeRestaurant(restaurant);    	}
    	else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Choice");
    		a.setContentText("Please select an Item");
    		a.show();
    	}
    }

    @FXML
    void updateRestaurant(ActionEvent event) throws IOException {
    	Restaurant restaurant=listRestaurants.getSelectionModel().getSelectedItem();
    	if(restaurant == null) {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Choice");
    		a.setContentText("Please select an Item");
    		a.show();
    	}
    	else {
    		
    	
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("updateRestaurantFXML.fxml"));
			Parent root = loader.load();
			
			// Here we are trying to pass restaurant object to the next controller
			 
			updateRestaurantFXMLController nextController = loader.getController();
			
			nextController.setRestaurant(restaurant);
			nextController.setLabel(restaurant.toString());
			
			
			Scene scene = new Scene(root);
			
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			stage.setScene(scene);
			stage.show();
    	}
    }

    // Logs you out of the system
    @FXML
    void logOut(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void removeUsers(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("removeUsersFXML.fxml"));

		Scene scene = new Scene(root);

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    }
    }
	
	

