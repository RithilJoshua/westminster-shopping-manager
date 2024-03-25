public class Electronics extends  Product{
   private String brand;
   private String warrenty;



    public Electronics(String product_ID, String product_name, int available_items, double price, String brand, String warrenty ) {
        super(String.valueOf(product_ID), product_name,available_items,price);
        this.brand = brand;
        this.warrenty = warrenty;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String  getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(String  warrenty) {
        this.warrenty = warrenty;
    }
    @Override
    public String toString() {
        return "Electronics - ID: " + getProduct_ID() + ", Name: " + getProduct_name() +
                ", Available Items: " + getAvailable_items() + ", Price:" + getPrice() +
                ", Brand: " + getBrand() + ", Warranty: " + getWarrenty();
    }

}
