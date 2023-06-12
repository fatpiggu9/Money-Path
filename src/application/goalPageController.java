package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

//import java.awt.Label;
import javafx.scene.control.Label;

//import java.awt.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class goalPageController{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> goalWalletList = new ArrayList<>();
	
	@FXML
	private TextField goalLabelField;
	
	@FXML
	private TextField goalBalanceField;
	
	@FXML
	private TextField goalTarget;
	
	@FXML
	private Label currencyLabel;
	
	@FXML
	private Label currencyLabel1;
	
	@FXML
	private Label currencyLabel2;
	
	@FXML
	private Label successPrompt;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private ChoiceBox<String> currency;
	private String[] money = {"Rupiah (IDR)", "Ringgit (RM)", "United States Dollar (USD)", "Chinese Yuan (RMB)"};

	@FXML
	private String[] categories = {"Food and Beverages", "Transportation", "Entertainment", "Groceries", "Utility Bills", "Other"};
	
	@FXML
	private ChoiceBox<String> category1;
	
	@FXML
	private void initialize() {
		currency.getItems().addAll(money);
		currency.setOnAction(this::getCurrency);
		logo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	         event.consume();
	     });
	}
	
	public void getCurrency(ActionEvent event) {
		String currencyChoice = currency.getValue();
		
		if(currencyChoice == "Chinese Yuan (RMB)") {
			currencyChoice = "RMB";
		}
		
		else if(currencyChoice == "Rupiah (IDR)") {
			currencyChoice = "IDR";
		}
		
		else if(currencyChoice == "United States Dollar (USD)") {
			currencyChoice = "USD";
		}
		
		else {
			currencyChoice = "RM";
		}
		
		currencyLabel.setText(currencyChoice);
		currencyLabel2.setText(currencyChoice);
	}
	
	
	public void homeOnClick(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void basicOnClick(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("basicPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}

	public void goalOnClick(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("goalPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void testAction(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("viewPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void submitGoalOnClick(ActionEvent event){
		successPrompt.setOpacity(1);
		String goalLabel = goalLabelField.getText();
		int goalBalance = Integer.parseInt(goalBalanceField.getText());
		int target = Integer.parseInt(goalTarget.getText());
		LocalDate targetDate = datePicker.getValue();
		
//		goalWalletList.add(goalLabel);
//		goalWalletList.add(String.valueOf(goalBalance));
//		goalWalletList.add(String.valueOf(target));
//		goalWalletList.add(String.valueOf(limit));
//		goalWalletList.add(String.valueOf(targetDate));
		String currencyChoice = currency.getValue();
//		goalWalletList.add(currencyChoice);
//		String categoryChoice = category.getValue();
//		goalWalletList.add(categoryChoice);
		
		System.out.println("Goal Wallet List: ");
		
		createFile();
		writeFile(goalLabel, currencyChoice, goalBalance, target, targetDate);
	}
	
	private void createFile() {
		 try {
	           File myFile = new File("goalList.txt");
	           if (myFile.createNewFile()) {
	                System.out.println("File created: " + myFile.getName());
	           } else {
	                System.out.println("File already exists.");
	           }
	    } catch (IOException e) {
	              System.out.println("An error occurred.");
	              e.printStackTrace();
	         }
	}

	public static void writeFile(String goalLabel, String currency, int balance, int target, LocalDate targetDate) {
		try {
			FileWriter myWriter = new FileWriter("goalList.txt", true);
			myWriter.write(goalLabel + "\n");
			myWriter.write("Goal\n");
			myWriter.write(currency + "\n");
			myWriter.write(balance +"\n");
			myWriter.write(target +"\n");
			myWriter.write(targetDate +"\n\n");
			
			myWriter.close();
			System.out.println("Successfully wrote the file.");
		}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}