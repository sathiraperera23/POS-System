import java.util.List;

public class StockReportGenerator {
    private List<Bill> transactionHistory;
    private List<ItemDTO> stock;

    public StockReportGenerator(List<Bill> transactionHistory, List<ItemDTO> stock) {
        this.transactionHistory = transactionHistory;
        this.stock = stock;
    }

    public String displayReorderLevelsReports() {
        StringBuilder report = new StringBuilder("Reorder Levels Report:\n");
        for (ItemDTO item : stock) {
            if (item.quantity < 50) {
                report.append("Item: ").append(item.name).append(" (Code: ").append(item.code)
                        .append("), Quantity: ").append(item.quantity).append("\n");
            }
        }
        return report.toString();
    }

    public String displayStockReport() {
        StringBuilder report = new StringBuilder("Stock Report:\n");
        for (ItemDTO item : stock) {
            report.append("Item: ").append(item.name).append(" (Code: ").append(item.code)
                    .append("), Quantity: ").append(item.quantity).append("\n");
        }
        return report.toString();
    }
}
