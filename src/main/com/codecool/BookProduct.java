package main.com.codecool;

class BookProduct extends Product {

    private int numOfPages;

    BookProduct(String name, int price, int numOfPages) {
        super(name, price);
        this.numOfPages = numOfPages;
    }

    int getNumOfPages() {
        return numOfPages;
    }
}
