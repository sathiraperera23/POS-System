import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;



public class Main {
 public static void main(String[]args) {
     ItemFactory itemFactory = new NormalItemFactory(); // or use DiscountedItemFactory

     BillingSystem billingSystem = new BillingSystem(itemFactory);
ReportGenerator reportGenerator = new ReportGenerator(billingSystem.getTransactionHistory(), billingSystem.getStock());


List<Bill> transactionHistory = new ArrayList<>();
List<ItemDTO> stock = new ArrayList<>();
ReportFacade reportFacade = new ReportFacadeBuilder()
.withTransactionHistory(billingSystem.getTransactionHistory())
.withStock(billingSystem.getStock())
.build();
	 Scanner input = new Scanner(System.in);
	 ArrayList<Stock> stocks = new ArrayList<>();
	    
	 
	    int choice;
	    do {
	        System.out.println("1. Add stock123");
	        System.out.println("2. View Reports");
	        System.out.println("3. Add Items to cart and Checkout");
	        System.out.println("4. Exit");
	        System.out.print("Enter your choice: ");
	        choice = input.nextInt();

	        switch (choice) {
	            case 1:
	            	try {
	                
	            	System.out.println("Choose item type:");
	                System.out.println("1. Normal Item");
	                System.out.println("2. Discounted Item");
	                System.out.print("Enter your choice: ");
	               int number = input.nextInt(); // Consume the newline character

	                ItemFactory itemFactorytwo;
	                if (number == 1) {
	                    itemFactorytwo = new NormalItemFactory();
	                } else if (number == 2){
	                	itemFactorytwo = new DiscountedItemFactory();
	                } else {
	                    System.out.println("Invalid choice. Exiting.");
	                    return;
	                    
	                    
	                }
//	            	
	            	
	            	System.out.println("Enter the code");      
	            	String itemcode = input.next();
	            	System.out.println("Enter the name of the item");
	            	String name = input.next();
	            	System.out.println("Enter the price");
	            	double price = input.nextDouble();
	        
	            	System.out.println("Enter the quantity");
	            	int stockquantity = input.nextInt();
	            	
	                System.out.print("Enter expiry date (yyyy-MM-dd): ");
	                String expiryDateString = input.next();
	                Date expiryDate = parseDate(expiryDateString);

	                
	            	double discount = 1.0;
	            	if(number == 2) {
	            		System.out.println("Enter discount: ");
	            		discount = input.nextDouble();
	            	}
	            	billingSystem.addItemsToStocknew(itemcode, name, price,discount,stockquantity, expiryDate);

	            	 } catch (InputMismatchException e) {
	            	        System.out.println("Invalid input. Please enter a valid number.");
	            	        input.next(); 
	            	    }
	                break;
	            	
	            case 2:
                    while (true) {
                    	System.out.println("Enter 1 to display total sales report");
                    	System.out.println("Enter 2 to display reshelved items report");
                    	System.out.println("Enter 3 to display reorder levels report");
                    	System.out.println("Enter 4 to display stock report");
                    	System.out.println("Enter 5 to display bill report");
                    	System.out.println("Enter 6 to display all the reports at once");
                    	System.out.println("Enter 10 when done");


                    	
                    	System.out.println("Enter the report");
    	            	String report = input.next();

                    	if (report.equals("10")) {
        	                break;
        	            }
                    	switch(report) {
                    	case "1":
                    		reportFacade.displayTotalSaleReport();
                    		break;
                    	case "2":
                    		reportFacade.displayReshelvedItemsReport();
                    		break;
                    	case "3":
                    		reportFacade.displayReorderLevelsReport();
                    		break;
                    	case "4":                    		                    		
                    		reportFacade.displayStockReport();
                    		break;
                    	case "5":
                    		reportFacade.displayBillReport();
                    		break;
                    	case "6":
                    		reportFacade.generateReports();
                    		break;
                    	}
        	        }

	    			case 3:           
		                Scanner scanner1 = new Scanner(System.in);
billingSystem.checkout(scanner1);

	        }
	     


	            	

 }while (choice != 4);
	    }
 private static Date parseDate(String dateString) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     try {
         return dateFormat.parse(dateString);
     } catch (ParseException e) {
         System.out.println("Invalid date format. Using current date.");
         return new Date();
     }}}
