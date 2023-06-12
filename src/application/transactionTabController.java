package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class transactionTabController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String target;
	
	@FXML
	private ChoiceBox<String> transactionList;
	@FXML
	private Label selectLabel;
	@FXML
	private Label noTransaction;
	@FXML
	private Label nameLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label amountLabel;
	@FXML
	private Label transactionName;
	@FXML
	private Label transactionTime;
	@FXML
	private Label transactionAmount;
	
	public void getTarget(String target) { 
		this.target = target;
	}
	
	@FXML
	public void setList(String target) throws IOException {
		File myFile = new File("transactionList.txt");
		Scanner scan = new Scanner(myFile);
		
		boolean counter = false;
		if(scan.hasNextLine()) {
			counter = true;
		}
		if(counter == false) {
			transactionList.setDisable(true);
			selectLabel.setVisible(false);
			noTransaction.setVisible(true);
		}
		else {
			Scanner scan2 = new Scanner(myFile);
			System.out.println(target);
			while(scan2.hasNextLine()) {
				if(target.equals(scan2.nextLine())) {
					transactionList.getItems().add(scan2.nextLine());
				}
				while(!(scan2.nextLine().isEmpty())) {}
			}
			scan2.close();
		}
		scan.close();
		getTarget(target);
		
		transactionList.setOnAction(arg0 -> {
			try {
				viewDetail(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	
	
	public void viewDetail(ActionEvent event) throws IOException{
		File myFile = new File("transactionList.txt");
		Scanner scan = new Scanner(myFile);
		String temp = scan.nextLine();
		String choice = transactionList.getValue();
		while(scan.hasNextLine()) {
			if(target.equals(temp)) {
				temp = scan.nextLine();
				if(choice.equals(temp)) {
					break;
				}
			}
			temp = scan.nextLine();
		}
		
		selectLabel.setVisible(false);
		nameLabel.setVisible(true);
		timeLabel.setVisible(true);
		amountLabel.setVisible(true);
		transactionName.setText(temp);temp = scan.nextLine();
		transactionTime.setText(temp);temp = scan.nextLine();
		transactionAmount.setText(temp);
		transactionName.setVisible(true);
		transactionTime.setVisible(true);
		transactionAmount.setVisible(true);
		scan.close();
	}

}
