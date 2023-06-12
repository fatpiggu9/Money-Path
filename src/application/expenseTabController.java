package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class expenseTabController {
	
	@FXML
	private TextField expenseAmount;
	@FXML
	private TextField expenseName;
	static int balance;
	static String target;
	int finalBalance;
	int currentBalance;

	public void addExpense() throws IOException{
		balance = Integer.parseInt(expenseAmount.getText());
		finalBalance = currentBalance - balance;
		temp();
	}
	
	public void changeBalance(String name, Label balanceTotal) throws IOException {
		target = name;
		currentBalance = Integer.parseInt(balanceTotal.getText());
	}
	
	public void temp() throws IOException {
		System.out.println(balance + " " + target);

		File myFile = new File("goalList.txt");
		File myObj = new File("temporary.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(myFile));
		PrintWriter writer = new PrintWriter(new FileWriter(myObj));
		String temp;
		int edited = 0;
		while ((temp = reader.readLine()) != null) {
	        if (temp.equals(target)) {
	            writer.println(target);
	            while ((temp = reader.readLine()) != null) {
	    	        if (temp.equals(Integer.toString(currentBalance)) && edited == 0) {
	    	            writer.println(finalBalance);
				edited = 1;
	    	        } else {
	    	            writer.println(temp);
	    	        }
	    	    }
	        } else {
	            writer.println(temp);
	        }
	    }
		reader.close();
		writer.close();
		boolean y = myFile.delete();
		boolean x = myObj.renameTo(myFile);
		if(y == true) {
			System.out.println("deleted");
		}
		if(x == true) {
			System.out.println("renamed");
		}
		currentBalance = finalBalance;
		
		String name = expenseName.getText();
		createFile();
		writeFile(name);
		
	}
	
	private void createFile() {
		 try {
	           File myFile = new File("transactionList.txt");
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
	
	public static void writeFile(String name) {
		try {
			FileWriter myWriter = new FileWriter("transactionList.txt", true);
			LocalDateTime date = java.time.LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			myWriter.write(target + "\n");
			myWriter.write(name + "\n");
			myWriter.write(date.format(myFormatObj) + "\n");
			myWriter.write(balance + "\n\n");
			
			myWriter.close();
			System.out.println("Successfully wrote the file.");
		}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}
