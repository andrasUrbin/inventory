package main.com.codecool;

class CDProduct extends Product {

    private int numOfTracks;

    CDProduct(String name, int price, int numOfTracks) {
        super(name, price);
        this.numOfTracks = numOfTracks;
    }

    int getNumOfTracks() {
        return numOfTracks;
    }
}
