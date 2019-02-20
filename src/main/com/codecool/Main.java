package main.com.codecool;

public class Main {

    public static void main(String[] args) {
        PersistentStore ps = new PersistentStore();
        StoreManager manager = new StoreManager();
        manager.addStorage(ps);
        manager.addBookProduct("Golden Compass", 2700, 376);
        manager.addCDProduct("Skillet Best Of", 2500, 20);
        ps.store("Products.xml");
        ps.loadProducts();
        System.out.println("Products from xml: " +  manager.listProducts());
        System.out.println("Total price: " + manager.getTotalProductPrice());
    }
}
