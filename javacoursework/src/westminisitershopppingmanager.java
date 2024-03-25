import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;

public class westminisitershopppingmanager implements shoppingmanager{
    public static int button;
    ArrayList<Product> product_list = new ArrayList<>();

    public ArrayList<Product> getProduct_list(){
        return product_list;
    }



    @Override
    public void menu() throws IOException {
            // defining the menu

        while (true){
            System.out.println("............Welcome to Westminster shopping cart...........");
            System.out.println("press 1 to show the menu");
            System.out.println("press 2 to add a new product");
            System.out.println("press 3 to delete a product");
            System.out.println("press 4 to print a list of products");
            System.out.println("press 5 to save details in a file");
            System.out.println("press 6 to open the GUI");
            System.out.println("press 7 to Exit");
            //getting the input and connect with relevant methods
            Scanner input = new Scanner(System.in);
            try {
                button = input.nextInt();

                switch (button) { //exception for the menu
                    case 1:
                        menu();
                        break;
                    case 2:
                        add_new_product();
                        break;
                    case 3:
                        delete_product();
                        break;
                    case 4:
                        print_list_of_products();
                        break;
                    case 5:
                        save_in_a_file();
                        break;
                    case 6:
                        Swing swingApp = new Swing();
                        SwingUtilities.invokeLater(() -> {
                            swingApp.main(null);
                                                            // running the GUI

                        });
                        System.out.println("GUI is now open");
                        System.out.println("lets add more products");
                        menu();
                        break;

                    case 7:
                        System.out.println("Thank you !!!!!!!");
                        return;
                    default:
                        System.out.println("Wrong input! Please enter a number between 1 and 7.");
                        break;
                }

                if (button == 6) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine(); // consume the invalid input
            }
        }
    }

    @Override
    public void add_new_product() throws IOException {
        Scanner input = new Scanner(System.in);
        if (product_list.size()<50){//check if produtts are less than 50 if yes goes down
            System.out.println("let's add new products");
            System.out.println("what's the type of the product (press (e) for electronic,press (c) for clothing)");
            String type = input.nextLine();
            if (type.equalsIgnoreCase("e")){
                System.out.println("let's add Electronics");
                System.out.println("enter the product ID");
                String  ID = input.nextLine();
                System.out.println("enter the name of the product");
                String product_name = input.nextLine();
                System.out.println("enter the available items");
                int available_items = input.nextInt();
                input.nextLine();
                System.out.println("price of the product");
                double price = input.nextDouble();
                input.nextLine();
                System.out.println("brand of the products");
                String brand = input.nextLine();
                System.out.println("warrenty of the products");
                String  warrenty = String.valueOf(input.nextInt());
                input.nextLine();
                Product electronics = new Electronics(ID,product_name,available_items,price,brand,warrenty);
                product_list.add(electronics);
                System.out.println("Added successfully");
            }
            else if (type.equalsIgnoreCase("c")) {
                System.out.println("lets add Cloths");
                System.out.println("enter the product ID");
                String ID = input.nextLine();
                System.out.println("enter the name of the product");
                String productname = input.nextLine();
                System.out.println("enter the available items");
                int available_items = input.nextInt();
                input.nextLine();
                System.out.println("price of the product");
                double price = input.nextDouble();
                input.nextLine();
                int size;
                while (true) {
                    try {
                        System.out.println("Enter the size of the product");
                        size = Integer.parseInt(input.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong size! Please enter a valid integer size.");
                    }
                }
                System.out.println("color of the product");
                String color = input.nextLine();
                Product clothing = new Clothing(ID,productname,available_items,price,size,color);
                product_list.add(clothing);
                System.out.println("Added successfully");


            }else{
                System.out.println("Wrong product type.. Please try again");
                menu();
            }
        }else {
            System.out.println("cant add more products");
        }
    }

    @Override
    public void delete_product() throws IOException {
        System.out.println("Let's delete a product");
        System.out.println("Enter the ID of the product that you want to delete:");
        Scanner input = new Scanner(System.in);
        String PID = input.nextLine();

        boolean found = false;
        for (Product product : product_list) {
            if (product.getProduct_ID().equals(PID)) {// cheling the id is valid or not
                found = true;
                System.out.println("Product found! Do you want to remove it? (Y/N)");
                String decision = input.nextLine();
                if (decision.equalsIgnoreCase("Y")) {
                    product_list.remove(product);
                    System.out.println("Product removed");
                    System.out.println("Type of Product: " + (product instanceof Electronics ? "Electronic" : "Clothing"));
                    System.out.println("Total number of products that are left: " + product_list.size());
                } else {
                    System.out.println("Decision cancelled");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("There's no product with the relevant ID. Please try again.");
        }
    }


    @Override
    public void print_list_of_products() {
        Collections.sort(product_list, Comparator.comparing(Product::getProduct_ID));
        for (Product product:product_list){
            System.out.println(product);
        }
    }

    @Override
    public void save_in_a_file() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));//saving data in the data.txt file
        for (Product product:product_list){
            writer.write(product.toString());
            writer.newLine();
        }
        writer.close();

        try {
                BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
