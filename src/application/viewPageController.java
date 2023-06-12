package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class viewPageController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
	
	@FXML
	private ChoiceBox<String> walletList;
	@FXML
	private Label chooseWalletLabel;
	
	@FXML
	private Label selectWallet;
	@FXML
	private Label noWallet1;
	@FXML
	private Label noWallet2;
	
	@FXML
	private Label detailName;
	@FXML
	private Label detailType;
	@FXML
	private Label detailCurrency;
	@FXML
	private Label detailBalance;
	@FXML
	private Label walletName;
	@FXML
	private Label walletType;
	@FXML
	private Label currencyType;
	@FXML
	private Label balanceTotal;
	@FXML
	private Label targetBalanceLabel;
	@FXML
	private Label targetDateLabel;
	@FXML
	private Label targetBalance;
	@FXML
	private Label targetDate;
	@FXML
	private Label notReachLabel;
	@FXML
	private Label gainLabel;
	@FXML
	private Label balanceReachLabel;
	@FXML
	private ImageView logo;
	
	@FXML
	private Button incomeButton;
	@FXML
	private Button expenseButton;
	@FXML
	private Button transactionButton;

	@FXML
	private void initialize() throws IOException {
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
		
		File basicFile = new File("basicList.txt");
		File goalFile = new File("goalList.txt");
		Scanner basicScan = new Scanner(basicFile);
		Scanner goalScan = new Scanner(goalFile);
		
		boolean counter = false;
		if(basicScan.hasNextLine() || goalScan.hasNextLine()) {
			counter = true;
		}
		if(counter == false) {
			walletList.setDisable(true);
			chooseWalletLabel.setDisable(true);
			selectWallet.setVisible(false);
			noWallet1.setVisible(true);
			noWallet2.setVisible(true);
		}
		else {
			Scanner basicScan2 = new Scanner(basicFile);
			Scanner goalScan2 = new Scanner(goalFile);
			while(basicScan2.hasNextLine()) {
				walletList.getItems().add(basicScan2.nextLine());
				while(!(basicScan2.nextLine().isEmpty())) {}
			}
			while(goalScan2.hasNextLine()) {
				walletList.getItems().add(goalScan2.nextLine());
				while(!(goalScan2.nextLine().isEmpty())) {}
			}
			basicScan2.close();
			goalScan2.close();
		}
		basicScan.close();
		goalScan.close();
		
		walletList.setOnAction(arg0 -> {
			try {
				selectWallet.setVisible(false);
				viewDetail(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void viewDetail(ActionEvent event) throws IOException{
		File basicFile = new File("basicList.txt");
		File goalFile = new File("goalList.txt");
		Scanner basicScan = new Scanner(basicFile);
		Scanner goalScan = new Scanner(goalFile);
		detailName.setVisible(true);
		detailType.setVisible(true);
		detailCurrency.setVisible(true);
		detailBalance.setVisible(true);
		notReachLabel.setVisible(false);
		gainLabel.setVisible(false);
		balanceReachLabel.setVisible(false);
		

		String target = walletList.getValue();
		String tempBasic = basicScan.nextLine();
		String tempGoal = goalScan.nextLine();
		while(!(target.equals(tempBasic)) && basicScan.hasNextLine()) {
			tempBasic = basicScan.nextLine();
		}
		if(target.equals(tempBasic)) {
			walletName.setVisible(true);
			walletType.setVisible(true);
			currencyType.setVisible(true);
			balanceTotal.setVisible(true);
			targetBalanceLabel.setVisible(false);
			targetDateLabel.setVisible(false);
			targetBalance.setVisible(false);
			targetDate.setVisible(false);
			walletName.setText(tempBasic); tempBasic = basicScan.nextLine();
			walletType.setText(tempBasic); tempBasic = basicScan.nextLine();
			currencyType.setText(tempBasic); tempBasic = basicScan.nextLine();
			balanceTotal.setText(tempBasic);
			
			incomeButton.setVisible(false);
			expenseButton.setVisible(false);
			transactionButton.setVisible(false);
		}
		else {
			while(!(target.equals(tempGoal)) && goalScan.hasNextLine()) {
				tempGoal = goalScan.nextLine();
			}
			walletName.setVisible(true);
			walletType.setVisible(true);
			currencyType.setVisible(true);
			balanceTotal.setVisible(true);
			targetBalanceLabel.setVisible(true);
			targetDateLabel.setVisible(true);
			targetBalance.setVisible(true);
			targetDate.setVisible(true);
			walletName.setText(tempGoal); tempGoal = goalScan.nextLine();
			walletType.setText(tempGoal); tempGoal = goalScan.nextLine();
			currencyType.setText(tempGoal); tempGoal = goalScan.nextLine();
			balanceTotal.setText(tempGoal); int currBalance = Integer.parseInt(tempGoal); tempGoal = goalScan.nextLine();
			targetBalance.setText(tempGoal); int targBalance = Integer.parseInt(tempGoal); tempGoal = goalScan.nextLine();
			targetDate.setText(tempGoal);
			SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd"); 
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			Date targDate = null, fromDate = null;
			try {
				targDate = formatter1.parse(tempGoal);
				fromDate = formatter1.parse(currentDate); 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			long diff = targDate.getTime() - fromDate.getTime();
			int daysLeft = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
			
			if(currBalance < targBalance) {
				notReachLabel.setVisible(true);
				if(daysLeft < 0) {
					gainLabel.setText("You have passed the goal date :(");
				}
				else {
					gainLabel.setText(String.valueOf(daysLeft) + " days left to gain " + (targBalance-currBalance));
				}
				gainLabel.setVisible(true);
			}
			else {
				balanceReachLabel.setVisible(true);
			}
			
			incomeButton.setVisible(true);
			expenseButton.setVisible(true);
			transactionButton.setVisible(true);
		}
		basicScan.close();
		goalScan.close();
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
	
	public void incomeClick(ActionEvent event) throws IOException {
//		Parent root1;
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incomeTab.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load(); 
	        
	        String target = walletList.getValue();
	        incomeTabController incomeTabController = fxmlLoader.getController();
	        incomeTabController.changeBalance(target, balanceTotal);
	        
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1)); 
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public void expenseClick(ActionEvent event) throws IOException {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("expenseTab.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load(); 
	        
	        String target = walletList.getValue();
	        expenseTabController expenseTabController = fxmlLoader.getController();
	        expenseTabController.changeBalance(target, balanceTotal);
	        
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1)); 
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

	public void transactionClick(ActionEvent event) throws IOException {
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("transactionTab.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load(); 
	        
	        String target = walletList.getValue();
	        transactionTabController transactionTabController = fxmlLoader.getController();
	        transactionTabController.setList(target);
	        
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1)); 
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
