package models;

public class Order {
    private Menu menu;
    private int quantity;
    private Double tax;
    private Double priceBeforeTax;

    public Order(Menu menu, Integer quantity) {
        this.setMenu(menu);
        this.setQuantity(quantity);
        this.setPriceBeforeTax(priceBeforeTax);
        this.setTax(tax);
    }

    public Double getTax() {
        return tax;

    }

    public void setTax(Double tax) {
        this.tax = (0.11 * priceBeforeTax);

    }

    public Double getPriceBeforeTax() {
        return priceBeforeTax;
    }

    public void setPriceBeforeTax(Double priceBeforeTax) {
        this.priceBeforeTax = this.quantity * this.menu.getPrice();
    }

    public Order() {

    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\t" + menu.getName() + "\n\t" + quantity + " x Rp " + menu.getPrice() + " = Rp " + priceBeforeTax;
    }

}
