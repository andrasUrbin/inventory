package main.com.codecool;

import java.util.List;
class StoreManager {
    private StorageCapable storage;

    void addStorage(StorageCapable storage) {
        this.storage = storage;

    }

    void addCDProduct(String name, int price, int tracks) {
        storage.storeCDProduct(name, price, tracks);
    }

    void addBookProduct(String name, int price, int pages) {
        storage.storeBookProduct(name, price, pages);
    }

    int getTotalProductPrice() {
        int sumOfPrices = 0;
        List<Product> listOfProducts = storage.getAllProduct();
        for (int i = 0; i < listOfProducts.size(); i++) {
            sumOfPrices += listOfProducts.get(i).getPrice();
        }
        return sumOfPrices;
    }

    String listProducts() {
        List<Product> listOfProducts = storage.getAllProduct();
        String products = "";
        for (int i = 0; i < listOfProducts.size(); i++) {
            if (i == listOfProducts.size() - 1) {
                products += listOfProducts.get(i).getName();
            } else {
                products += listOfProducts.get(i).getName() + ", ";
            }
        }
        return products;
    }

}