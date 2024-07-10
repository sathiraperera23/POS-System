import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                processAddItem(asyncContext);
            } finally {
                asyncContext.complete();
            }
        });
    }

    private void processAddItem(AsyncContext asyncContext) {
        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        try {
            BillingSystem billingSystem = (BillingSystem) request.getServletContext().getAttribute("billingSystem");

            int itemType = Integer.parseInt(request.getParameter("itemType"));
            ItemFactory itemFactory = (itemType == 1) ? new NormalItemFactory() : new DiscountedItemFactory();
            billingSystem.setItemFactory(itemFactory);

            String itemCode = request.getParameter("itemcode");
            String itemName = request.getParameter("itemname");
            String itemPriceStr = request.getParameter("itemprice");
            String itemQuantityStr = request.getParameter("itemquantity");
            String expiryDateString = request.getParameter("expirydate");
            String itemDiscountStr = request.getParameter("itemdiscount");

            double itemPrice = Double.parseDouble(itemPriceStr);
            int itemQuantity = Integer.parseInt(itemQuantityStr);
            Date expiryDate = parseDate(expiryDateString);
            double itemDiscount = itemType == 2 ? Double.parseDouble(itemDiscountStr) : 0.0;

            billingSystem.addItemsToStocknew(itemCode, itemName, itemPrice, itemDiscount, itemQuantity, expiryDate);

            request.setAttribute("message", "Item added successfully!");
            request.getRequestDispatcher("AddItem.jsp").forward(request, response);
        } catch (NumberFormatException | NullPointerException | ServletException | IOException e) {
            request.setAttribute("message", "Error in form submission: " + e.getMessage());
            try {
                request.getRequestDispatcher("AddItem.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
