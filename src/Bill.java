import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {
    static int billSerialNumberCounter = 1;
     int serialNumber;
     Date billDate;
 List<ItemDTO> items;
     double totalAmount;
     double cashTendered;
     double change;
    List<ItemDTO> purchasedItems = new ArrayList<>();
    List<Integer> quantities; // To store quantities corresponding to each purchased item

    
    public Bill(List<ItemDTO> purchasedItems, List<Integer> quantities, double cashTendered) {
        this.serialNumber = billSerialNumberCounter++;
        this.billDate = new Date();
        this.purchasedItems = purchasedItems;
        this.quantities = quantities;
        this.cashTendered = cashTendered;
        calculateTotalAmount();
        calculateChangeAmount();
    }

//   
    private void calculateTotalAmount() {
        totalAmount = 0.0;
        for (int i = 0; i < purchasedItems.size(); i++) {
        	ItemDTO item = purchasedItems.get(i);
            int quantity = quantities.get(i);
            totalAmount += item.price * item.discount * quantity;
        }
    }

    private void calculateChangeAmount() {
        change = cashTendered - totalAmount;
    }

    public void displayBill() {
        System.out.println("Bill Serial Number: " + serialNumber	);
        System.out.println("Bill Date: " + billDate);
        System.out.println("Items Purchased:");
        for (int i = 0; i < purchasedItems.size(); i++) {
        	ItemDTO item = purchasedItems.get(i);
            int quantity = quantities.get(i);
            System.out.println("  - " + item.name + " (Code: " + item.code + "), Quantity: " + quantity +
                    ", Total Price: " + (item.price * item.discount * quantity));
        }
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Cash Tendered: " + cashTendered);
        System.out.println("Change Amount: " + change);
    }
}