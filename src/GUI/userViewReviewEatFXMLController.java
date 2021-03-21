package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Database.dataSQLite;
import application.Restaurant;

import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * Controller Class for user View Review Eat
 * @author Yogesh Parajuli
 *
 */
public class userViewReviewEatFXMLController implements Initializable{
	//private dataSQLite data = dataSQLite.getInstance();
	private dataSQLite data =dataSQLite.getInstance();
	private static User currentUser;
	Restaurant selectedRestaurant;
	Alert a = new Alert(AlertType.NONE);



	/**
	 * Getter for current user
	 * @return current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Setter for user
	 * @param user
	 */
	public void setUser(User user) {
		userViewReviewEatFXMLController.currentUser = user;
	}

	@FXML
	private TextField searchTextField;
	
    @FXML
    private Button viewReviewButton;

	@FXML
	private ListView<Restaurant> restaurantListView;
	private ObservableList <Restaurant> restData = FXCollections.observableArrayList();



	@FXML
	private MenuButton menuButton;

	@FXML
	private MenuButton menuButton1;

	@FXML
	private Button addReveiwButton;

	@FXML
	private Button logOutButton;
	
	

	@FXML
	void addButton(ActionEvent event) throws IOException {
		selectedRestaurant = restaurantListView.getSelectionModel().getSelectedItem();

		if (selectedRestaurant == null) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Restaurant not selected");
			a.setHeaderText("Please select a restaurnt");
			a.show();
		}
		else {

			// Change the parent i.e base class using fxml loader


			FXMLLoader loader = new FXMLLoader(getClass().getResource("addReviewFXML.fxml"));
			Parent root = loader.load();

			addReviewFXMLController nextController = loader.getController();

			// here i am trying to pass user object to another controller
			nextController.setCurrentUser(getCurrentUser());
			nextController.setSelectedRestaurant(selectedRestaurant);

			//Set the scene to the new root i.e new fxml file
			Scene scene = new Scene(root);

			//Set the stage information
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			stage.setScene(scene);
			stage.show();
		}
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeSet <Restaurant> restaurants = data.openRestaurants();
		for(Restaurant r: restaurants) {
			r.setReviews(data.openReview(r.getId()));
			
			r.getAverageRating();
		}
		
		restData.addAll(restaurants);


		FilteredList <Restaurant> filteredData = new FilteredList<>(restData, b-> true);
		// 2. Set the filter Predicate whenever the filter changes.
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(restaurant -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare restaurant's name with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (restaurant.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches 
				} 
				else  
					return false; // Does not match.
			});
		});

		restaurantListView.setItems(filteredData);



	}

	@FXML
	void defaultFilter(ActionEvent event) {
		filter("all");


	}

	@FXML
	void britishFilter(ActionEvent event) {
		filter("British");
	}

	@FXML
	void chineseFilter(ActionEvent event) {
		filter("Chinese");
	}

	@FXML
	void frenchFilter(ActionEvent event) {
		filter("French");
	}

	@FXML
	void indianFilter(ActionEvent event) {
		filter("Indian");
	}

	@FXML
	void italianFilter(ActionEvent event) {
		filter("Italian");
	}

	@FXML
	void jamaicanFilter(ActionEvent event) {
		filter("Jamaican");
	}

	/**
	 * Method to filer the restaurant based on cuisine
	 * We use lamda expression to get all the restaurants based on selected cuisine
	 * @param cuisine
	 */
	public void filter(String cuisine) {
		restaurantListView.getItems().removeAll();
		FilteredList <Restaurant> filteredData = new FilteredList<>(restData, b-> true);
		
		//Lamda expression to match to the cuisines of the restaurants
		filteredData.setPredicate(restaurant ->{
			if (restaurant.getCuisine().equals(cuisine)) {
				return true;
			}
			else if(cuisine.equals("all")) {
				return true;
			}
			else return false;

		});
		
		//lamda expression for the search box
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(restaurant -> {
				// If filter text is empty, display all values

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (restaurant.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches 
				} 
				else  
					return false; // Does not match.
			});
		});

		restaurantListView.setItems(filteredData);
	}

	//Logs you out 
	@FXML
	void logOut(ActionEvent event) throws IOException {


		Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));

		//Set the scene to the new root i.e new fxml file
		Scene scene = new Scene(root);

		//Set the stage information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();	
	}



	@FXML
	void sortByRating(ActionEvent event) {
		sort("rating");

	}

	@FXML
	void defaultSort(ActionEvent event) {
		sort("default");

	}

	/**
	 * Method to sort the restaurants
	 * Here we are using the lamda expression to manage the comparision of restaurant bases on given parameter
	 * also making sure that we can still use the search box as well
	 * searching uses lambda expression as well
	 * @param str
	 */
	public void sort(String str) {
		restaurantListView.getItems().removeAll();
		//restData.removeAll();



		if (str.equals("rating")){
			restaurantListView.setItems(null);
			

			Collections.sort(restData);			

			FilteredList <Restaurant> filteredData = new FilteredList<>(restData, b-> true);

			// Uses lamda expression to compare
			searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(restaurant -> {
					// If filter text is empty, display all data

					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					// matches the characters input in the search box
					String lowerCaseFilter = newValue.toLowerCase();

					if (restaurant.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
						return true; // Filter matches first name.
					} 
					else  
						return false; // Does not match.
				});
			});

			restaurantListView.setItems(filteredData);
		}
		else {
			restaurantListView.setItems(null);

			ObservableList <Restaurant> newRestData = FXCollections.observableArrayList();
			
			TreeSet <Restaurant> restaurants = data.openRestaurants();
			for(Restaurant r: restaurants) {
				r.setReviews(data.openReview(r.getId()));
				
				r.getAverageRating();
			}
			
			//restData.addAll(restaurants);
			newRestData.addAll(restaurants);


			FilteredList <Restaurant> filteredData = new FilteredList<>(newRestData, b-> true);


			searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(restaurant -> {
					

					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					
					String lowerCaseFilter = newValue.toLowerCase();

					if (restaurant.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
						return true; // Filter matches .
					} 
					else  
						return false; // Does not match.
				});
			});

			restaurantListView.setItems(filteredData);
		}
	}
	
	//Method to allow users to view all reviews of the selected Restaurant
	@FXML
    void viewReview(ActionEvent event) throws IOException {
		selectedRestaurant = restaurantListView.getSelectionModel().getSelectedItem();
		
		if (selectedRestaurant == null) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Restaurant not selected");
			a.setHeaderText("Please select a restaurnt");
			a.show();
		}
		else {

			// Change the parent i.e base class using fxml loader
			FXMLLoader loader= new FXMLLoader(getClass().getResource("viewReviewsFXML.fxml"));
			Parent root = loader.load();
			

			viewReviewsFXMLController nextController = loader.getController();

			// here i am trying to pass user object to another controller
			
			nextController.setLabel(selectedRestaurant.toString());
			nextController.setListView(selectedRestaurant.getReviews().values());

			//Set the scene to the new root i.e new fxml file
			Scene scene = new Scene(root);

			//Set the stage information
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			stage.setScene(scene);
			stage.show();


		
    }



}
}





