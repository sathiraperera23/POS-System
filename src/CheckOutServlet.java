import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                processCheckout(asyncContext);
            } finally {
                asyncContext.complete();
            }
        });
    }

    private void processCheckout(AsyncContext asyncContext) {
        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        BillingSystem billingSystem = (BillingSystem) request.getServletContext().getAttribute("billingSystem");

        String[] itemCodes = request.getParameterValues("itemCode");
        String[] quantitiesStr = request.getParameterValues("quantity");
        double cashTendered = Double.parseDouble(request.getParameter("cashTendered"));

        int[] quantities = new int[quantitiesStr.length];
        for (int i = 0; i < quantitiesStr.length; i++) {
            quantities[i] = Integer.parseInt(quantitiesStr[i]);
        }

        String checkoutMessage = billingSystem.checkoutother(itemCodes, quantities, cashTendered);

        request.setAttribute("checkoutMessage", checkoutMessage);

        try {
            request.getRequestDispatcher("checkoutResult.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
