import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet(urlPatterns = {"/report"}, asyncSupported = true)
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                processReport(asyncContext);
            } finally {
                asyncContext.complete();
            }
        });
    }

    private void processReport(AsyncContext asyncContext) {
        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        try {
            String reportNumber = request.getParameter("reportNumber");

            if (reportNumber != null && !reportNumber.isEmpty()) {
                ReportFacade reportFacade = (ReportFacade) request.getServletContext().getAttribute("reportFacade");
                if (reportFacade != null) {
                    switch (reportNumber) {
                        case "1":
                            request.setAttribute("totalSaleReport", reportFacade.displayTotalSaleReport());
                            break;
                        case "2":
                            request.setAttribute("reshelvedItemsReport", reportFacade.displayReshelvedItemsReport());
                            break;
                        case "3":
                            request.setAttribute("reorderLevelsReport", reportFacade.displayReorderLevelsReport());
                            break;
                        case "4":
                            request.setAttribute("stockReport", reportFacade.displayStockReport());
                            break;
                        case "5":
                            request.setAttribute("billReport", reportFacade.displayBillReport());
                            break;
                        case "6":
                            reportFacade.generateReports();
                            request.setAttribute("totalSaleReport", reportFacade.displayTotalSaleReport());
                            request.setAttribute("reshelvedItemsReport", reportFacade.displayReshelvedItemsReport());
                            request.setAttribute("reorderLevelsReport", reportFacade.displayReorderLevelsReport());
                            request.setAttribute("stockReport", reportFacade.displayStockReport());
                            request.setAttribute("billReport", reportFacade.displayBillReport());
                            break;
                        default:
                            request.setAttribute("errorMessage", "Invalid report number");
                            break;
                    }
                    request.getRequestDispatcher("reportResult.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Report facade not available");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Report number is required");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
