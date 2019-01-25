package main.com.codecool;

import java.util.*;

public interface StorageCapable {

     List<Product> getAllProduct();

     void storeCDProduct(String name, int price, int size);
     void storeBookProduct(String name, int price, int size);
}
