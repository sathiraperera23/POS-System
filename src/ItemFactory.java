import java.util.ArrayList;
import java.util.Date;
import java.util.List;

	
public interface ItemFactory {
    ItemDTO createItem(String code, String name, double price, double discount,int quantity, Date expiryDate);

}
