abstract public class Product {
    private String  product_ID;
    private String product_name;
    private int available_items;
    private double price;


    public Product(String product_ID, String product_name, int available_items, double price) {
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.available_items = available_items;
        this.price = price;
    }

    public String getProduct_ID(){
      return product_ID;
    }

    public void setProduct_ID(String product_ID){
        this.product_ID = product_ID;
    }


    public String getProduct_name(){
        return product_name;
    }



    public void setProduct_name(){
        this.product_name=product_name;
    }

    public int getAvailable_items() {
        return available_items;
    }

    public void setAvailable_items(int available_items){
        this.available_items=available_items;
    }

     public double getPrice() {
         return price;
     }

     public void setPrice(int price) {
         this.price = price;
     }
 }
