import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class ShoppingCart extends JFrame {
    private DefaultTableModel model;

    public ShoppingCart() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Product ID", "Quantity", "Price"});

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        add(scrollPane);
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    public void addProductToCart(String productID, int quantity, double price) {
        model.addRow(new Object[]{productID, quantity, price * quantity});
    }
}