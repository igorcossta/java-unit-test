package service;

import model.Product;
import repository.StockRepository;

import java.util.List;

public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Product> fetchAll() {
        return stockRepository.fetchAll();
    }

    public boolean isSizeGreaterThanZero(List<Product> products) {
        return products.size() > 0;
    }

    public Double totalCash(List<Product> products) {
        return products.stream().mapToDouble(x -> x.getPrice() * x.getQuantity()).sum();
    }

    public boolean addProduct(Product product) {
        boolean success = stockRepository.addProduct(product);
        if (success) return true;
        throw new RuntimeException("product already exists on data base");
    }
}
