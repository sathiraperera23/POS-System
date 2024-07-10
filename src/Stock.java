import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stock {
	 private List<StockItem> stockItems;

	    public Stock() {
	        this.stockItems = new ArrayList<>();
	    }

	    public void addItem(StockItem item) {
	        stockItems.add(item);
	    }

	    public StockItem findStockItem(String itemCode) {
	        for (StockItem item : stockItems) {
	            if (item.getItemCode().equals(itemCode)) {
	                return item;
	            }
	        }
	        return null; // Item not found
	    }

}