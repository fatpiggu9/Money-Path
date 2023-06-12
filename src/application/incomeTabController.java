package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class incomeTabController {
	
	@FXML
	private TextField incomeAmount;
	int balance;
	String target;
	int finalBalance;
	int currentBalance;
	
	public void addIncome() throws IOException{
		balance = Integer.parseInt(incomeAmount.getText());
		finalBalance = currentBalance + balance;
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
		while ((temp = reader.readLine()) != null) {
	        if (temp.equals(target)) {
	            writer.println(target);
	            while ((temp = reader.readLine()) != null) {
	    	        if (temp.equals(Integer.toString(currentBalance))) {
	    	            writer.println(finalBalance);
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
		
	}
}
