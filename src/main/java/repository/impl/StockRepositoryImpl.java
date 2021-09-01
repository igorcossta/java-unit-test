package repository.impl;

import model.Product;
import repository.StockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockRepositoryImpl implements StockRepository {

    private final List<Product> products = Arrays.asList(
            new Product("Teclado", 240.50, 19),
            new Product("Mouse", 140.50, 15),
            new Product("Mesa", 333.34, 1),
            new Product("Cadeira", 1440.34, 24),
            new Product("Monitor", 1240.50, 98)
    );

    @Override
    public List<Product> fetchAll() {
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        List<Product> productList = new ArrayList<>(products);
        boolean exists = productList.contains(product);
        if (exists) {
            return false;
        }
        productList.add(product);
        return true;
    }
}
