package models;

public class Menu {
    // private int id;
    private String name;
    private double price;
    private String kategori;

    public Menu(String name, double price, String kategori) {

        this.name = name;
        this.price = price;
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return name + " - " + "Rp. " + price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}