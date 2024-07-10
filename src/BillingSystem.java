import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BillingSystem {
	

	

	
	
	  List<ItemDTO> stock = new ArrayList<>();
	    List<Bill> transactionHistory = new ArrayList<>();
	    ItemFactory itemFactory;

	    public BillingSystem(ItemFactory itemFactory) {
	        this.itemFactory = itemFactory;
	    }
	    
	 
	    
	public void addItemToStock(ItemDTO item) {
		stock.add(item);
	for (ItemDTO itemDTO : stock) {
	System.out.println(itemDTO);
	}
	}
	
	 public void addItemsToStock(List<ItemDTO> items) {
	        stock.addAll(items);
	    }
	 
	 public void addItemsToStocknew(String itemcode, String name, double price, double discount, int stockquantity, Date expiryDate) {
		 ItemDTO item = new ItemDTO(itemcode, name, price, discount, stockquantity, expiryDate);  
		 stock.add(item);
	    }
	 
	 public void addItemsToStocknewtwo(String itemcode, String name, double price, double discount, int stockquantity, Date expiryDate) {
		 ItemDTO item = new ItemDTO(itemcode, name, price, discount, stockquantity, expiryDate);  
		 stock.add(item);
	    }
    

	
	 public void displayReports() {

	        System.out.println("Reports Displayed Successfully.");
	    }
	 
	 public ItemDTO findItemByCode(String code) {
	        for (ItemDTO item : stock) {
	            if (item.getCode().equals(code)) {
	                return item; 
	            }
	        }
	        return null; 
	    }

	 public void checkout(Scanner input) {
	        List<ItemDTO> purchasedItems = new ArrayList<>();
	        List<Integer> quantities = new ArrayList<>();

	        System.out.println("Enter the items to be purchased (type 'done' to finish):");
try {
	        while (true) {
	            System.out.print("Enter item code: ");
	            String code = input.nextLine();

	            if (code.equalsIgnoreCase("done")) {
	                break;
	            }

	            System.out.print("Enter quantity: ");
	            int quantity = input.nextInt();
	            input.nextLine(); 
	            ItemDTO item = findItemByCode(code);
	            if (item != null) {
	                purchasedItems.add(item);
	                quantities.add(quantity);
	                System.out.println("Items added to cart successfully");
	            } 
	            else {
	                System.out.println("Item: '" + code + "' not found.");
	            }
	        }
 
	        
	        // Calculate total dues
	        double totalDues = calculateDueAmount(purchasedItems, quantities);
	        System.out.println("Total Dues: $" + totalDues);

	        // Accept cash tendered
	        System.out.print("Enter cash tendered: ");
	        double cashTendered = input.nextDouble();

	        // Calculate change
	        double changeAmount = cashTendered - totalDues;
	        System.out.println("Change amount: $" + changeAmount);

	        // Generate and save bill
	        Bill bill = generateBill(purchasedItems, quantities, cashTendered, changeAmount);
	        transactionHistory.add(bill);

	        System.out.println("Bill created successfully.");
}catch (InputMismatchException e) {
    System.out.println("Invalid input. Please enter a valid number.");

}
	    }
	 
		public String checkoutother(String[] itemCodes, int[] quantities, double cashTendered) {
			StringBuilder sb = new StringBuilder();
			List<ItemDTO> purchasedItems = new ArrayList<>();
			List<Integer> qtyList = new ArrayList<>();
		
			try {
				for (int i = 0; i < itemCodes.length; i++) {
					String code = itemCodes[i];
					int quantity = quantities[i];
					ItemDTO item = findItemByCode(code);
					if (item != null) {
						purchasedItems.add(item);
						qtyList.add(quantity);
						sb.append("Items added to cart successfully for item code: ").append(code).append("<br>");
					} else {
						sb.append("Item: '").append(code).append("' not found.<br>");
					}
				}
		
				double totalDues = calculateDueAmount(purchasedItems, qtyList);
				sb.append("Total Dues: $").append(totalDues).append("<br>");
		
				double changeAmount = cashTendered - totalDues;
				sb.append("Change amount: $").append(changeAmount).append("<br>");
		
				Bill bill = generateBill(purchasedItems, qtyList, cashTendered, changeAmount);
				transactionHistory.add(bill);
		
				sb.append("Bill created successfully.<br>");
			} catch (Exception e) {
				sb.append("Error during checkout: ").append(e.getMessage()).append("<br>");
			}
		
			return sb.toString();
		}
		
	    // Method to calculate total dues
	    public double calculateDueAmount(List<ItemDTO> purchasedItems, List<Integer> quantities) {
	        double totalDues = 0.0;
	        for (int i = 0; i < purchasedItems.size(); i++) {
	            ItemDTO item = purchasedItems.get(i);
	            int quantity = quantities.get(i);
	            totalDues += item.getPrice() * quantity;
	        }
	        return totalDues;
	    }

	    // Method to generate bill
	    public Bill generateBill(List<ItemDTO> purchasedItems, List<Integer> quantities,
	                              double cashTendered, double changeAmount) {
	        Bill bill = new Bill(purchasedItems, quantities, cashTendered);
	        return bill;
	    }

	    // Method to display transaction history
	    public void displayTransactionHistory() {
	        for (Bill bill : transactionHistory) {
	            System.out.println(bill);
	        }
}
	    public List<Bill> getTransactionHistory() {
	        return transactionHistory;
	    }

	    public List<ItemDTO> getStock() {
	        return stock;
	    }
		public void setItemFactory(ItemFactory itemFactory) {
			this.itemFactory = itemFactory;
		}
	    }
