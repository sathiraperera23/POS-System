import java.util.Date;
import java.util.List;

public class ItemDTO {
     String code;
     String name;
     double price;
     double discount;
     int quantity;
     Date expiryDate;
     
     private static ItemDTO temp;

    public ItemDTO(String code, String name, double price, double discount, int quantity, Date expiryDate) {
        this.code = code;                                                                                                    
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
    public static synchronized ItemDTO getInstance(String code, String name, double price, double discount, int quantity, Date expiryDate) {
        if (temp == null) {
            temp = new ItemDTO(code, name, price, discount, quantity, expiryDate);
        }
        return temp;
    }
    
    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                '}';
    }
    
    public static ItemDTO createItem(ItemFactory factory, String code, String name, double price, int quantity, double discount, Date expiryDate) {
        return factory.createItem(code, name, price, discount,quantity, expiryDate);
    }	

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
