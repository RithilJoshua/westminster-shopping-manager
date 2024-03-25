import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Swing {

    public static JTable table2;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(900, 500);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        Color customclr = new Color(211,211,211);

        panel1.setBackground(Color.lightGray);
        panel2.setBackground(Color.gray);
        panel3.setBackground(customclr);

        panel1.setPreferredSize(new Dimension(100, 80));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 250));

        jFrame.add(panel1, BorderLayout.NORTH);
        jFrame.add(panel2, BorderLayout.CENTER);
        jFrame.add(panel3, BorderLayout.SOUTH);

        Label label1 = new Label("Select product category");
        panel1.add(label1);
        label1.setFont(new Font("Arial", Font.BOLD, 20));

        String[] products = {"All", "Electronics", "Clothings"};
        JComboBox<String> comboBox = new JComboBox<>(products);
        comboBox.setPreferredSize(new Dimension(100, 40));
        panel1.add(comboBox);

        JButton button1 = new JButton("Shopping cart");
        button1.setFocusable(false);
        panel1.add(button1);
        button1.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton button2 = new JButton("Add to shopping cart");
        button2.setFocusable(false);





        JTable table1 = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Product ID", "Name", "Category", "Price", "Info"});





        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);

                String productID = data[0];
                String category = "";

                if (productID.startsWith("E")) {
                    category = "Electronics";
                } else {
                    category = "Clothings";
                }

                model.setValueAt(category, model.getRowCount() - 1, 2);

                String info = "";

                if (category.equals("Electronics")) {
                    String brand = data[4];
                    String warranty = data[5];
                    info = brand + "," + warranty;
                } else if (category.equals("Clothings")) {
                    String color = data[4];
                    String size = data[5];
                    info = color + "," + size;
                }

                model.setValueAt(info, model.getRowCount() - 1, 4);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        JFrame tableFrame = new JFrame("Product Information");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(400, 300);

        // Create a new table for the new window
        table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(new Object[]{"Product ID & Info", "Quantity", "Price"});
        table2.setModel(model2);

        JScrollPane scrollPane2 = new JScrollPane(table2);
        tableFrame.add(scrollPane2);

        JPanel amountpanel = new JPanel();
        amountpanel.setBackground(Color.gray);
        amountpanel.setPreferredSize(new Dimension(100,200));
        amountpanel.setLayout(new BoxLayout(amountpanel, BoxLayout.Y_AXIS));

        JLabel totalla = new JLabel(" Total Amount:0.00");
        JLabel firstpurdis = new JLabel("First purchase discount (10%): 0.00");
        JLabel threepurdis = new JLabel("Three items in same category discount(15%): 0.00");
        JLabel finalamount = new JLabel("Final amount:00");


        totalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstpurdis.setAlignmentX(Component.CENTER_ALIGNMENT);
        threepurdis.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalamount.setAlignmentX(Component.CENTER_ALIGNMENT);




        Font labelFont = new Font("Arial", Font.BOLD, 16); // Change the font size
        totalla.setFont(labelFont);
        finalamount.setFont(labelFont);
        threepurdis.setFont(labelFont);
        firstpurdis.setFont(labelFont);


        Font tableFont = new Font("Arial", Font.PLAIN, 16); // Change the font size
        table2.setFont(tableFont);


        amountpanel.add(Box.createVerticalGlue());//space
        amountpanel.add(totalla);
        amountpanel.add(Box.createVerticalGlue());//space
        amountpanel.add(firstpurdis);
        amountpanel.add(Box.createVerticalGlue());//space
        amountpanel.add(threepurdis);
        amountpanel.add(Box.createVerticalGlue());//space
        amountpanel.add(finalamount);
        amountpanel.add(Box.createVerticalGlue());//space

        tableFrame.add(amountpanel,BorderLayout.SOUTH);





        // ActionListener for button1
        HashMap<String, Integer> cartItems = new HashMap<>();
        button2.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow != -1) {
                String productID = (String) table1.getValueAt(selectedRow, 0); // Get selected product ID from table1
                String price = (String) table1.getValueAt(selectedRow, 3); // Assuming price info is at index 3 in data.txt
                String info = (String) table1.getValueAt(selectedRow, 4); // Assuming info is at index 4 in data.txt

                int quantity = cartItems.getOrDefault(productID, 0);
                quantity++; // Increase quantity

                cartItems.put(productID, quantity); // Update the quantity in the cart map

                // Combine product ID and info into one string
                String productIDAndInfo = productID + " - " + info;

                // Check if the product already exists in the cart
                boolean productExists = false;
                for (int i = 0; i < model2.getRowCount(); i++) {
                    String productInCart = (String) model2.getValueAt(i, 0);
                    if (productInCart.equals(productIDAndInfo)) {
                        // Product already exists, update its quantity and price
                        int existingQuantity = Integer.parseInt(model2.getValueAt(i, 1).toString());
                        model2.setValueAt(existingQuantity + 1, i, 1); // Increment quantity in table2
                        productExists = true;
                        break;
                    }
                }

                // If the product is not in the cart, add it with quantity 1
                if (!productExists) {
                    model2.addRow(new Object[]{productIDAndInfo, 1, price}); // Add new row to table2
                }

                // Show the new window with table2 if not visible
                if (!tableFrame.isVisible()) {
                    tableFrame.setVisible(true);
                }
            }
            double totalamount = 0;
            double firdiscountamount = 0;
            double categorydiscountamount= 0;

            int electronnicscount = 0;
            int clothingscount = 0;

            for (int i = 0; i < model2.getRowCount(); i++) {
                String productinCart = (String) model2.getValueAt(i,0);
                String[] cparts = productinCart.split("-");
                String categoty = cparts[1].trim();

                if (categoty.equalsIgnoreCase("Electronics")) {
                    electronnicscount++;
                } else if (categoty.equalsIgnoreCase("Clothings")) {
                    clothingscount++;

                }


                String priceString = (String) model2.getValueAt(i, 2);
                double price;
                try {
                    String[] parts = priceString.split(":");
                    if (parts.length > 1) {
                        price = Double.parseDouble(parts[1].trim());
                    } else {
                        price = Double.parseDouble(priceString.trim());
                    }

                    int quantity = Integer.parseInt(model2.getValueAt(i, 1).toString());





                    totalamount += price * quantity;
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }
            if (model2.getRowCount()!=51){
                double firstprdisam = 0.10;
                firdiscountamount = totalamount*firstprdisam;
            }
            if (electronnicscount>3){
                double electronicsdis =0.15;
                categorydiscountamount+=totalamount*electronicsdis;
            }
            if (clothingscount>3){
                double clothingdis = 0.15;
                categorydiscountamount += totalamount*clothingscount;
            }

            double finalAmount = totalamount - firdiscountamount -categorydiscountamount;


            totalla.setText("total amount:" + String.format("%.2f", totalamount));
            firstpurdis.setText("First purchase discount (10%): " + String.format("%.2f",firdiscountamount));
            threepurdis.setText("Three items in the same category discount (15%): " + String.format("%.2f", categorydiscountamount));
            finalamount.setText("Final Amount: " + String.format("%.2f", finalAmount));






        });


        table1.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        panel2.add(scrollPane);

        comboBox.addActionListener(e -> {
            String selectedCategory = (String) comboBox.getSelectedItem();

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table1.setRowSorter(sorter);

            if (!selectedCategory.equals("All")) {
                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + selectedCategory, 2);
                sorter.setRowFilter(rowFilter);
            } else {
                table1.setRowSorter(null);
            }
        });

        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    StringBuilder rowData = new StringBuilder();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        rowData.append(model.getColumnName(i)).append(": ").append(model.getValueAt(selectedRow, i)).append("<br>");
                    }

                    JLabel dataLabel = new JLabel("Selected Product - Details");
                    dataLabel.setFont(new Font("Arial", Font.BOLD, 16));


                    // Get the available items information for the selected product
                    String selectedProductID = (String) model.getValueAt(selectedRow, 0); // Assuming product ID is at index 0
                    String availableItemsInfo = getAvailableItemsInfo(selectedProductID);
                    rowData.append("Available Items: ").append(availableItemsInfo); // Append available items information

                    JLabel label = new JLabel("<html>" + rowData.toString() + "</html>");
                    JPanel panelInfo = new JPanel();
                    panelInfo.setBackground(customclr); // Set the background color to light gray
                    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
                    panelInfo.add(dataLabel);
                    panelInfo.add(Box.createVerticalStrut(10));
                    panelInfo.add(label);


                    panelInfo.add(Box.createVerticalStrut(10));
                    panelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

                    panelInfo.add(button2);
                    panel3.removeAll();
                    panel3.add(panelInfo);
                    panel3.revalidate();
                    panel3.repaint();



                }
            }



        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableFrame.setVisible(true);
            }
        });





        jFrame.setVisible(true);
    }

    // Method to retrieve available items information from data.txt based on product ID
    private static String getAvailableItemsInfo(String productID) {
        String availableItemsInfo = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(productID)) {
                    availableItemsInfo = data[2]; // Assuming available items info is at index 2 in data.txt
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availableItemsInfo;
    }





}