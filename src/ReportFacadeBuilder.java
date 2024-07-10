import java.util.ArrayList;
import java.util.List;

public class ReportFacadeBuilder {
private List<Bill> transactionHistory;
private List<ItemDTO> stock;

public ReportFacadeBuilder withTransactionHistory(List<Bill> transactionHistory) {
    this.transactionHistory = transactionHistory;
    return this;
}

public ReportFacadeBuilder withStock(List<ItemDTO> stock) {
    this.stock = stock;
    return this;
}

public ReportFacade build() {
    return new ReportFacade(transactionHistory, stock);
}

}
