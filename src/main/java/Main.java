import repository.impl.StockRepositoryImpl;
import service.StockService;

public class Main {

    public static void main(String[] args) {
        StockService stockService = new StockService(new StockRepositoryImpl());
    }
}
