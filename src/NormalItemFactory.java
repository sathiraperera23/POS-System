import java.util.Date;

class NormalItemFactory implements ItemFactory {

	@Override
	public ItemDTO createItem(String code, String name, double price, double discount, int quantity, Date expiryDate) {
		// TODO Auto-generated method stub
        return new ItemDTO(code, name, price, discount, quantity, expiryDate);
	}

}
