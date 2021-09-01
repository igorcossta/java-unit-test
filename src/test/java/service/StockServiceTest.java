package service;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.StockRepository;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    private List<Product> products;

    @BeforeEach
    public void setup() {
        products = Arrays.asList(
                new Product("Teclado", 240.50, 19),
                new Product("Mouse", 140.50, 15),
                new Product("Mesa", 333.34, 1),
                new Product("Cadeira", 1440.34, 24),
                new Product("Monitor", 1240.50, 98)
        );
    }

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private StockService stockService;

    @Test
    void shouldReturnListOfProducts() {
        Mockito.when(stockRepository.fetchAll()).thenReturn(products);
        List<Product> actualResult = stockService.fetchAll();
        Assertions.assertEquals(products, actualResult);
    }

    @Test
    void shouldReturnTrueIfListSizeIsGreaterThanZero() {
        boolean actualResult = stockService.isSizeGreaterThanZero(products);
        Assertions.assertTrue(actualResult);
    }

    @Test
    void shouldReturnFalseIfListSizeIsLessThanZero() {
        List<Product> productList = List.of();
        boolean actualResult = stockService.isSizeGreaterThanZero(productList);
        Assertions.assertFalse(actualResult);
    }

    @Test
    void shouldReturnTotalCashOfProductsOnStock() {
        Double actualResult = stockService.totalCash(products);
        Double expectedResult = 163147.5;
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnZeroIfProductListIsEmpty() {
        List<Product> productList = List.of();
        Double actualResult = stockService.totalCash(productList);
        Double expectedResult = 0.0;
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnTrueIfProductIsAddedToDatabase() {
        Product product = new Product("Phone", 1230.43, 102);
        Mockito.when(stockRepository.addProduct(product)).thenReturn(true);
        boolean expectedResult = stockService.addProduct(product);
        Assertions.assertTrue(expectedResult);
    }

    @Test
    void shouldThrowExceptionIfProductAlreadyExistsOnDatabase() {
        Product product = new Product("Phone", 1230.43, 102);
        Mockito.when(stockRepository.addProduct(product)).thenReturn(false);

        Exception actualResult = Assertions.assertThrows(RuntimeException.class, () -> stockService.addProduct(product));
        String expectedResult = "product already exists on data base";

        Assertions.assertEquals(expectedResult, actualResult.getMessage());
    }
}