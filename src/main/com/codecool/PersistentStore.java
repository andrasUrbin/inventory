package main.com.codecool;

public class PersistentStore extends Store {

    public void storeProduct(Product product) {
        getProducts().add(product);
    }
}