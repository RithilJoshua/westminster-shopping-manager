import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class westminisitershopppingmanagerTest {
    westminisitershopppingmanager w1 = new westminisitershopppingmanager();

    @Test
    void add_new_product() throws IOException {
        westminisitershopppingmanager.button = 2;
        w1.add_new_product();
        assertEquals(1,w1.getProduct_list().size());
    }

    @Test
    void delete_product() throws IOException {
        westminisitershopppingmanager.button =3;
        w1.delete_product();


    }

    @Test
    void print_list_of_products() {
        w1.print_list_of_products();
        assertEquals(1,w1.product_list.size());

    }

    @Test
    void save_in_a_file() throws IOException {
        w1.save_in_a_file();
        w1.add_new_product();
        w1.save_in_a_file();

    }
}