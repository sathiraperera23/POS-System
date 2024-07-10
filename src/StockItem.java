import java.util.Date;

public class StockItem {
	 private ItemDTO itemCode;
	    private String Itemname;
	    private int quantity;
	    private Date dateOfPurchase;
	    private int quantityReceived;

	    public StockItem(ItemDTO item, Date dateOfPurchase, int quantityReceived) {
	        this.itemCode = item;
	        this.dateOfPurchase = dateOfPurchase;
	        this.quantityReceived = quantityReceived;
	    }

	    // Getters and setters
	    public ItemDTO getItemCode() {
	        return itemCode;
	    }

	    public void setItem(ItemDTO item) {
	        this.itemCode = item;
	    }

	    public Date getDateOfPurchase() {
	        return dateOfPurchase;
	    }

	    public void setDateOfPurchase(Date dateOfPurchase) {
	        this.dateOfPurchase = dateOfPurchase;
	    }

	    public int getQuantityReceived() {
	        return quantityReceived;
	    }

	    public void setQuantityReceived(int quantityReceived) {
	        this.quantityReceived = quantityReceived;
	    }

		public String getItemname() {
			return Itemname;
		}

		public void setItemname(String itemname) {
			Itemname = itemname;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
}
