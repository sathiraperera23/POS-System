import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize necessary components here
        ItemFactory itemFactory = new NormalItemFactory(); // Initialize your ItemFactory
        BillingSystem billingSystem = new BillingSystem(itemFactory); // Initialize BillingSystem

        // Initialize the ReportFacade with necessary components
        ReportFacade reportFacade = new ReportFacade(billingSystem.getTransactionHistory(), billingSystem.getStock());

        sce.getServletContext().setAttribute("billingSystem", billingSystem);
        // Store ReportFacade in the servlet context
        sce.getServletContext().setAttribute("reportFacade", reportFacade);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup code if necessary
    }
}
