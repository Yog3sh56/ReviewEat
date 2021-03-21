package GUI;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import Database.dataSQLite;
import application.InputValidationException;
import application.Restaurant;
import application.Review;

import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller class for add review fxml
 * This class is responsible for providing an interface to the user to add review on a restaurant 
 * @author Yogesh parajuli
 *
 */
public class addReviewFXMLController implements Initializable{
	dataSQLite data = dataSQLite.getInstance();
	
	// we need a user and restaurant object to add a review
	// User is being passed on from previous fxml 
	// Restaurant object is being selected here
	private User currentUser;
	private Restaurant selectedRestaurant;
	Alert a = new Alert(AlertType.NONE);
	
	

	/**
	 * Setter for current user
	 * @param currentUser
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	

	/**
	 * Setter for the restaurant
	 * @param selectedRestaurant
	 */
	public void setSelectedRestaurant(Restaurant selectedRestaurant) {
		this.selectedRestaurant = selectedRestaurant;
	}

	
    @FXML
    private ChoiceBox<Integer> ratingChoiceBox = new ChoiceBox<Integer>();

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button addReveiwButton;

    @FXML
    private Button cancelButton;

    @FXML
    void addReview(ActionEvent event) throws  IOException, NullPointerException{
    	//read in data from the fields
    	int rating = ratingChoiceBox.getValue();
    	String description = descriptionTextArea.getText();

    	
    	try {
			
    		Review review = new Review(description, rating);
    		
    		if(data.checkReview(currentUser.getUsername(), selectedRestaurant.getId())) {
    			//General error alert
    			a.setAlertType(AlertType.ERROR);
    			a.setContentText("User has already reviewed this restaurant.");
    			a.show();

    		}
    		else {
				
    			data.saveReview(currentUser, selectedRestaurant, review);
			
				a.setAlertType(AlertType.CONFIRMATION);
				a.setContentText("Review Saved");
				a.show();
				
				
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("userViewReviewEatFXML.fxml"));
				Parent root = loader.load();
				 
				userViewReviewEatFXMLController nextController = loader.getController();
				nextController.setUser(currentUser);
				

				//Set the scene to the new root i.e new fxml file
				Scene scene = new Scene(root);

				//Set the stage information
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

				stage.setScene(scene);
				stage.show();
			}
		} catch (InputValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// General error alert
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("Invalid Input");
			a.setContentText("Please Check the information you have entered");
			a.show();
		
		}
    }
    	
    	

    
    // Takes you back to previous fxml
    @FXML
    void cancel(ActionEvent event) throws IOException {
    	

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("userViewReviewEatFXML.fxml"));
		Parent root = loader.load();
		 
		userViewReviewEatFXMLController nextController = loader.getController();
		nextController.setUser(currentUser);
		

		//Set the scene to the new root i.e new fxml file
		Scene scene = new Scene(root);

		//Set the stage information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    	
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Populating the choice box with items and giving it a default value
		ratingChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		ratingChoiceBox.setValue(5);
		
	}

}
