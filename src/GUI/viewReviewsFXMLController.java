package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import java.util.ResourceBundle;


import application.Review;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller class to allow users to view reviews.
 * @author Yogesh Parajuli
 *
 */
public class viewReviewsFXMLController implements Initializable{
	

	@FXML
    private Label restaurantLabel;

    @FXML
    private ListView<Review> reviewListView;

    @FXML
    private Button backButton;

    //Takes you back to previous page
    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("userViewReviewEatFXML.fxml"));

		//Set the scene to the new root i.e new fxml file
		Scene scene = new Scene(root);

		//Set the stage information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	//	reviewListView.getItems().setAll(data.openReview(restaurant.getId()).values());
		
	}
	
	/**
	 * Method to set the label
	 * @param restaurant
	 */
	public void setLabel(String restaurant) {
		restaurantLabel.setText(restaurant);
	}
	
	/**
	 * Method to initialize the List View
	 * @param collection
	 */
	public void setListView(Collection<Review> collection) {
		reviewListView.getItems().setAll(collection);
		
	}

}
