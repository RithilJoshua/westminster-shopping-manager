import java.io.IOException;

interface shoppingmanager {

    void menu() throws IOException;

    void add_new_product() throws IOException;

    void delete_product() throws IOException;
    void  print_list_of_products();
    void save_in_a_file() throws IOException;


}
