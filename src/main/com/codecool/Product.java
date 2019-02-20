package main.com.codecool;

abstract class Product {
    private String name;
    private int price;

    Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }
}
