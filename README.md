# Money Path 
as Object Oriented Programming Final Project

## Manual Guide
Money Path is a JavaFX application that provides a quick and simple wallet and budget manager. It is designed to run on the JavaFX environment and provides intuitive graphical user interface (GUI) for any interactions with application's features


### **<ins>Prerequisites</ins>**

Make sure to have the following installed on your device:
- Java Development Kit (JDK) Version 17.0.6 or above
- JavaFX SDK 20.0.1

### **<ins>Installation</ins>**
- Clone the repository from GitHub by the following command:

  >```"git clone https://github.com/fatpiggu9/Money-Path"```
- Download and Import file on your preferred Java IDE
- Configure the project to use the appropriate JDK and JavaFX SDK
- Build the project to resolve any dependencies

### **<ins>Usage</ins>**
- Launch the application by running the main class: 'Main.java'
- The application GUI should appear, displaying main page of the application
- Navigate through features on the application

## About Us
Money Path, a financial tracker application designed to track your money flow anytime, everywhere. Set your goal of finance, manage it, and exceed your limit with Money Path. Consists of 2 different wallet types users can input, such as Basic Wallet and Goal Wallet. 

##

## Application Snippet

### **<ins>Menu Page</ins>**
![image](https://github.com/fatpiggu9/Money-Path/assets/127531908/8eb6a9fa-99c7-43bc-8a25-76010486979b)

Features available for Money Path:
- Add "Basic Wallet" : Simple wallet manager, including wallet label, currency type, and amount
- Add "Goal Wallet" : More advanced wallet manager, including all features from basic wallet, and target goal given selected end date
- "View Wallet" : View created wallet and details, users could add income and expense here

##

### **<ins>Basic Wallet</ins>**
![image](https://github.com/fatpiggu9/Money-Path/assets/127531908/13dbce1c-5bd4-4c69-bf10-27b6652270f7)

Users are to input from 3 input fields, namely Wallet Label, Currency, and Balance. 
- Wallet Label indicating the wallet name and to be saved as the corresponding wallet's identity.

- Currency choices are either Rupiah (IDR), Ringgit (RM), Chinese Yuan (RMB), or United States Dollar (USD).

- Balance field contains previously selected currency type in short (Ex. "USD") and input of balance amount.

##

### **<ins>Goal Wallet</ins>**
![image](https://github.com/fatpiggu9/Money-Path/assets/127531908/33f888cb-a2bc-4e64-8ab3-a3b050cc23f9)

Several same features from basic wallet, additionally, ssers are to input from 5 input fields, namely Wallet Label, Currency, Balance, and Target Goal

- Wallet Label indicating the wallet name and to be saved as the corresponding wallet's identity.

- Currency choices are either Rupiah (IDR), Ringgit (RM), Chinese Yuan (RMB), and United States Dollar (USD).

- Balance field contains previously selected currency type in short (Ex. "USD") and input of balance amount.

-  Target Goal includes 2 input fields, which are target amount based on selected currency type, and end date.

##

### **<ins>View Wallet</ins>**
![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/33f246d7-3e00-4373-b7e1-921fbfaf3292)

Users can view created wallet in the View Wallet page. Using the choice box near the top of the page, users can view the name of previously created wallet sorted by the wallet's creation date with Basic Wallets taking priority and displayed over Goal Wallets. Within the case where there are no wallet available or was never previously created, the choicebox would be disabled and greyed out, and the "Choose a wallet" label in the middle of the page would be changed into a "no wallet available" label.

After choosing a wallet, the following information would be shown:
![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/83479771-ab70-4be1-bc00-7b791f62eda6)

- Wallet Name : The name of the wallet when it was created.
- Wallet Type : The type of the wallet (basic or goal).
- Currency Type : The currency type assigned to the wallet.
- Balance : The total balance in the wallet.

![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/e40e060c-1e01-4daf-a622-a5fd01480903)

- Target Balance : The target balance assigned to the wallet. (Only for goal wallet)
- End Date : The end date assigned to the wallet. Formated to [yyyy-mm-dd] (Only for goal wallet)

In addition to the extra information, Goal Wallets have extra feature, which are an "Add Income", "Add Expense", and "View Transaction" buttons.

**"Add Income"** button will open a popup window. In this window, the user is prompt to input amount of income that will be added into the wallet and increase it's balance.


![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/67cc40a4-8abe-403e-8bb7-41149459d827)

Similarly, **"Add Expense"** button will open a window that asks the user to input the amount of expense that will be subtracted from the wallet's balance. It will also asks the user to give the expense a name which will help with the transaction later.

![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/62d7b8c9-2b28-497e-a99f-cb68f0a50927)

**"View Transaction"** button will open the "View Transaction" page. In this page there will initially be a choice box. The choice box holds all expenses made for that specific wallet displayed by the expense name set at the expense page. If there are no expense made for that wallet, the choice box would display nothing.
Clicking on the choice box would display the following:

![image](https://github.com/fatpiggu9/Money-Path/assets/127538786/3cca31da-8573-4576-ac08-37ed0402732e)

- Transaction Name : The name of the expense made.
- Transaction Time : The time when the expense was made. Formated to [dd-mm-yyyy hh:mm:ss]
- Transaction Amount : The amount of expense made.

