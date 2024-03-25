public class Clothing extends Product{
    private int size;
    private String color;

    public Clothing(String product_ID,String product_name, int available_items,double price,int size,String color){
        super(product_ID, product_name,available_items,price);
        this.size=size;
        this.color=color;

    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Clothings - ID: " + getProduct_ID() + ", Name: " + getProduct_name() +
                ", Available Items: " + getAvailable_items() + ", Price:" + getPrice() +
                ", Size: " + getSize() + ", Color: " + getColor();
    }
}
