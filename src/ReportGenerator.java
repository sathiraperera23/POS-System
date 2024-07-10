import java.util.*;

public class ReportGenerator {
    private List<Bill> transactionHistory;
    private List<ItemDTO> stock;

    public ReportGenerator(List<Bill> transactionHistory, List<ItemDTO> stock) {
        this.transactionHistory = transactionHistory;
        this.stock = stock;
    }

    public String displayTotalSaleReport() {
        StringBuilder report = new StringBuilder("Total Sales Report:\n");
        List<String> itemsSold = new ArrayList<>();
        double totalRevenue = 0.0;

        for (Bill bill : transactionHistory) {
            for (int i = 0; i < bill.purchasedItems.size(); i++) {
                ItemDTO item = bill.purchasedItems.get(i);
                int quantity = bill.quantities.get(i);

                itemsSold.add("Item: " + item.name + "(Code: " + item.code + "), Quantity: " + quantity);
                totalRevenue += item.price * item.discount * quantity;
            }
        }

        for (String itemSold : itemsSold) {
            report.append(itemSold).append("\n");
        }
        report.append("Total Revenue: ").append(totalRevenue).append("\n");

        return report.toString();
    }

    public String displayReshelvedItemsReport() {
        StringBuilder report = new StringBuilder("Reshelved Items Report\n");
        int totalReshelvedItems = 0;

        for (Bill bill : transactionHistory) {
            for (ItemDTO purchasedItem : bill.purchasedItems) {
                totalReshelvedItems += purchasedItem.quantity;
            }
        }

        report.append("Total Reshelved Items: ").append(totalReshelvedItems).append("\n");
        return report.toString();
    }

    public String displayBillReport() {
        StringBuilder report = new StringBuilder("Bill Report:\n");
        for (Bill bill : transactionHistory) {
            report.append("Bill Serial Number: ").append(bill.serialNumber).append("\n");
            report.append("Bill Date: ").append(bill.billDate).append("\n");

            for (int i = 0; i < bill.purchasedItems.size(); i++) {
                ItemDTO item = bill.purchasedItems.get(i);
                int quantity = bill.quantities.get(i);
                report.append(" - ").append(item.name)
                        .append(" (Code: ").append(item.code)
                        .append("), Quantity: ").append(quantity)
                        .append(", Total Price: ").append(item.price * item.discount * quantity).append("\n");
            }

            report.append("Total Amount: ").append(bill.totalAmount).append("\n");
            report.append("Cash Tendered: ").append(bill.cashTendered).append("\n");
            report.append("Change Amount: ").append(bill.change).append("\n");
        }

        return report.toString();
    }
}
