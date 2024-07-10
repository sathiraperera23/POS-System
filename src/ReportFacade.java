import java.util.List;

public class ReportFacade {
    private ReportGenerator reportGenerator;
    private StockReportGenerator stockReportGenerator;
    private List<Bill> transactionHistory;
    private List<ItemDTO> stock;

    public ReportFacade(List<Bill> transactionHistory, List<ItemDTO> stock) {
        this.reportGenerator = new ReportGenerator(transactionHistory, stock);
        this.stockReportGenerator = new StockReportGenerator(transactionHistory, stock);
        this.transactionHistory = transactionHistory;
        this.stock = stock;
    }

    public String generateReports() {
        StringBuilder reports = new StringBuilder();
        reports.append(reportGenerator.displayTotalSaleReport()).append("\n");
        reports.append(reportGenerator.displayReshelvedItemsReport()).append("\n");
        reports.append(stockReportGenerator.displayReorderLevelsReports()).append("\n");
        reports.append(stockReportGenerator.displayStockReport()).append("\n");
        reports.append(reportGenerator.displayBillReport()).append("\n");
        return reports.toString();
    }

    public String displayTotalSaleReport() {
        return reportGenerator.displayTotalSaleReport();
    }

    public String displayReshelvedItemsReport() {
        return reportGenerator.displayReshelvedItemsReport();
    }

    public String displayReorderLevelsReport() {
        return stockReportGenerator.displayReorderLevelsReports();
    }

    public String displayStockReport() {
        return stockReportGenerator.displayStockReport();
    }

    public String displayBillReport() {
        return reportGenerator.displayBillReport();
    }
}
