package repository;

import model.Product;

import java.util.List;

public interface StockRepository {
    List<Product> fetchAll();
    boolean addProduct(Product product);
}
