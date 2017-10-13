/* Changes:
 * @142:42. Inserted (toWithdraw * 1.005).
 * @120:82. Added "=".
 * @174:72. Removed "+ 0.005".
 * @101:32. Added "=".
 */

//Name: Alejandro Alonso
//alexalonso04
//Section: 1149
//Project Number: 3
//Description: Program that performs basic baking tasks: Deposit, Withdraw, Check Balance using various methods based on user input.

import java.util.Scanner;
import java.lang.Math;

public class CurrencyExchange {
	private static double balance = 0;

	public static double getBalance() {
		return balance;
	}
	public static boolean updateBalance (double newBalance) {
		balance = Math.round(newBalance * 100) / 100.0;

		if (balance >= 0) {
			return true;
		} else
		return false;
	}

	public static void main(String [] args) {
		Scanner input = new Scanner (System.in);
		boolean running = true;

		System.out.println("Welcome to Currency Exchange  2.0\n");
		printConversionTable();

		//Menu
		do {
			int userSelection = mainMenuOptionSelector(input);

			//Option 1. Check Balance-------------------------------------------
			if (userSelection == 1) {

				System.out.println("Your current balance is: " + getBalance());
				continue;
			}

			//Option 2. Deposit-------------------------------------------------
			if (userSelection == 2) {
				boolean deposit = true;
				int currency = currencyMenuOptionSelector(input);
				double amount = amountSelection(input, deposit);

				deposit(amount,currency);
				System.out.print(logTransaction(amount, currency, deposit));
				continue;
			}

			//Option 3. Withdraw------------------------------------------------
			if (userSelection == 3) {
				boolean deposit = false;
				int currency = currencyMenuOptionSelector(input);
				double amount = amountSelection(input, deposit);

				if (withdraw(amount, currency)){
					System.out.println(logTransaction(amount, currency, deposit));
				} else {
					if (amount <= 0 || (currency <= 0 || currency > 11)) {
						System.out.println(logTransaction(amount, currency, deposit));
					} else {
					System.out.println("Error: Insufficient funds." + "\nLogging Error");
					}
				}
				continue;
			}

			//Insert log Transaction
			if (userSelection == 4) {
				if (getBalance() == (double) 0) {
					System.out.println("Your remaining balance is " + getBalance() + " U.S. dollars" + "\nGoodbye");
					running = false;
				} else {
					boolean deposit =  false;
					int currency = 1;
					double amount = getBalance();
					System.out.println(logTransaction(amount, currency, deposit) + "\nGoodbye");
					running = false;
				}
			}
		} while(running);
	}

	//Methods Section
	//Deposit
	public static boolean deposit(double amount, int currencyType){
		boolean toUSD = (currencyType == 1) ? false : true;
		double toDeposit = amount;
		boolean sucess = true;
			if (currencyType <= 0 || currencyType > 11 || amount <= 0) {
				sucess = false;
			} else {
				if (toUSD) {
					toDeposit = convertCurrency(amount, currencyType, toUSD);
					// double new balance = toDeposit + getBalance();
					updateBalance(getBalance() + toDeposit);
					sucess = true;
				} else {
					updateBalance(getBalance() + amount);
					sucess = true;
				}
			}
		return sucess;
	}

	//Withdraw
	public static boolean withdraw(double amount, int currencyType) {
		boolean toUSD = (currencyType == 1) ? false : true;
		boolean sucess = true;
		double toWithdraw = (!toUSD) ? amount : convertCurrency(amount, currencyType, toUSD) * 1.005;
		if (amount <= (double) 0 || toWithdraw > getBalance() || (currencyType <= 0 || currencyType > 11)) {
			sucess = false;
		}
		if (toUSD && sucess) {
			toWithdraw = convertCurrency(amount, currencyType, toUSD);
			updateBalance(getBalance() - (toWithdraw * 1.005));
		} else if (sucess) {
			updateBalance(getBalance() - toWithdraw);
		}
		return sucess;
	}

	//Conversion
	public static double convertCurrency(double amount, int currencyType, boolean isConvertToUSD){
		double currency = 0;
		double conversion = 0;

		switch (currencyType) {
		//USD
		case 1: currency = 1.00;
		break;
		//Euro
		case 2: currency = 0.89;
		break;
		//Pound
		case 3: currency = 0.78;
		break;
		//Rupee
		case 4: currency = 66.53;
		break;
		//Australian
		case 5: currency = 1.31;
		break;
		//Canadian
		case 6: currency = 1.31;
		break;
		//Singapore
		case 7: currency = 1.37;
		break;
		//Franc
		case 8: currency = 0.97;
		break;
		//Ringgit
		case 9: currency = 4.12;
		break;
		//Yen
		case 10: currency = 101.64;
		break;
		//Yuan Renminbi
		case 11: currency = 6.67;
		break;
		}

		conversion = (isConvertToUSD) ? conversion = amount / (currency) : amount * currency;

		return conversion;
	}

	//Log Transaction
	private static String logTransaction(double amount, int currencyType, boolean isDeposit){

		String operation = (isDeposit) ? "deposited" : "withdrew";
		String currency = null;
		String result = null;;

			switch (currencyType) {
				//US Dollars
				case 1: currency = "U.S. Dollars";
				break;
				//Euro
				case 2: currency = "Euros";
				break;
				//Pound
				case 3: currency = "British Pounds";
				break;
				//Rupee
				case 4: currency = "Indian Rupees";
				break;
				//Australian
				case 5: currency = "Australian Dollars";
				break;
				//Canadian
				case 6: currency = "Canadian Dollars";
				break;
				//Singapore
				case 7: currency = "Singapore Dollars";
				break;
				//Franc
				case 8: currency = "Swiss Francs";
				break;
				//Ringgit
				case 9: currency = "Malaysian Ringgits";
				break;
				//Yen
				case 10: currency = "Japanese Yen";
				break;
				//Yuan Renminbi
				case 11: currency = "Chinese Yuan Renminbi";
				break;
				}

		//Checks for valid amount
		if (amount <= (double) 0 || (currencyType <= 0 || currencyType > 11)){
			result = "Logging Error";

		//Deposit Output
		}else if (isDeposit) {
			 result = "You successfully " + operation + " " + amount + " " + currency;
	    //Withdrawal Output
		} else if (!isDeposit) {
			result = "You successfully " + operation + " " + amount + " " + currency;
		}
		return result;
	}

	//Selection
	private static int mainMenuOptionSelector(Scanner input){
		boolean running = true;
		int userInput;

		do {
			//Prompt
			System.out.println("\nPlease select an option from the list below:");

			//List of Options
			String [] options = {
					"1. Check the balance of your account",
					"2. Make a deposit",
					"3. Withdraw an amount in a specific currency",
					"4. End your session (and withdraw all remaining currency in U.S. Dollars)"
			};

			//Prints Options
			for (String value: options){
			System.out.println(value);
			}

			//Ask for input
			userInput = input.nextInt();

			//Input Validation
			if (userInput <= 0 || userInput > 4) {
				errorMessage();
				continue;
			}
			running = false;
		} while(running);
		return userInput;
	}

	//Table
	private static void printConversionTable() {
		System.out.println("Current rates are as follows:\n");

		//Array for Table
		String [] conversion = {
				"1 - U.S. Dollar - 1.00",
				"2 - Euro - 0.89",
				"3 - British Pound - 0.78",
				"4 - Indian Rupee - 66.53",
				"5 - Australian Dollar - 1.31",
				"6 - Canadian Dollar - 1.31",
				"7 - Singapore Dollar - 1.37",
				"8 - Swiss Franc - 0.97",
				"9 - Malaysian Ringgit - 4.12",
				"10 - Japanese Yen - 101.64",
				"11 - Chinese Yuan Renminbi - 6.67"
		};

		//Prints Table
		for(String value: conversion) {
			System.out.println(value);
		}
	}

	//Currency Selector
	private static int currencyMenuOptionSelector (Scanner input){
		boolean running = true; //Controls the flow of the while loop
		int currencyType = 0;

		//Currency List
		String [] currency = {
				"1. U.S. Dollars",
				"2. Euros",
				"3. British Pounds",
				"4. Indian Rupees",
				"5. Australian Dollars",
				"6. Canadian Dollars ",
				"7. Singapore Dollars",
				"8. Swiss Francs ",
				"9. Malaysian Ringgits ",
				"10. Japanese Yen",
				"11. Chinese Yuan Renminbi"
		};

		//Prompts for selection
		while (running) {
		System.out.println("\nPlease select the currency type:");
			//Prints the Currency List
			for (String value: currency) {
			System.out.println(value);
			}
		currencyType = input.nextInt();

		//Selection Validation
		if (currencyType <= 0 || currencyType > 11) {
			errorMessage();
			continue;
			}
		running = false;
		}
		//Returns currency selected
		return currencyType;
	}

	//Amount Selection
	private static double amountSelection(Scanner input, boolean isDeposit) {
		boolean running = true;
		String operation = (isDeposit) ? "deposit" : "withdraw";
		double selection = 0;
		while (running) {
			System.out.print("Please enter the " + operation +  " amount: ");
			selection = input.nextDouble();
			System.out.println("");
		running =  false;
		}
		return selection;
	}

	//Error Message
	private static void errorMessage(){
		System.out.println("Input failed validation. Please try again.");
	}
}
