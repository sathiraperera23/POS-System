import java.util.Date;

public interface Item {
	String getCode();
    String getName();
    double getPrice();
    double getDiscount();
    Date getExpiryDate();
}
