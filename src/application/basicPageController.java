package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

//import java.awt.Label;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File; 
import java.io.IOException; 
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.io.FileWriter;   
import java.io.IOException;  

public class basicPageController{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> basicWalletList = new ArrayList<>();
	
	@FXML
	private TextField balanceField;
	
	@FXML
	private TextField basicLabelField;
	
	@FXML
	private Label currencyLabel;
	
	@FXML
	private Label successPrompt;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private ChoiceBox<String> currency;
	private String[] money = {"Rupiah (IDR)", "Ringgit (RM)", "United States Dollar (USD)", "Chinese Yuan (RMB)"};
	
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
	
	public void submitBasicOnClick(ActionEvent event){
		successPrompt.setOpacity(1);
		successPrompt.setText("Basic Wallet has been successfully created");
		int basicBalance = Integer.parseInt(balanceField.getText());
		String basicLabel = basicLabelField.getText();
		
		basicWalletList.add(basicLabel);
		basicWalletList.add(String.valueOf(basicBalance));
		String currencyChoice = currency.getValue();
		basicWalletList.add(currencyChoice);
		
		System.out.println("Basic Wallet List: ");
			
		for(int i=0;i<basicWalletList.size();i+=3) {
			String walletLabel = basicWalletList.get(i);
			String balance = basicWalletList.get(i+1);
			String currency = basicWalletList.get(i+2);
			
			String output = String.format("%s %s %s", walletLabel, currency, balance);
			System.out.println(output);
		}
		System.out.println();
		
		createFile();
		writeFile(basicLabel, currencyChoice, basicBalance);
	}
	
	private void createFile() {
		 try {
	           File myFile = new File("basicList.txt");
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

	public static void writeFile(String basicLabel, String currency, int balance) {
		try {
			FileWriter myWriter = new FileWriter("basicList.txt", true);
/*test1*/	//		myWriter.write(basicLabel + " basic " + currency + " " + balance + "\n");
/*test2*/			myWriter.write(basicLabel + "\nbasic\n" + currency + "\n" + balance + "\n\n");

			myWriter.close();
			System.out.println("Successfully wrote the file.");
		}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
	
}