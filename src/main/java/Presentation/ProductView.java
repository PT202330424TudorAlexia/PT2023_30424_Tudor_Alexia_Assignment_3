package Presentation;
import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Connection.ConnectionFactory;
import Model.Client;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductView extends javax.swing.JFrame{

    String name, emailrep;
    int id, stock,price;
    DefaultTableModel model;
    JButton[] functionButtons = new JButton[3];
    JButton addButton, updateButton, deleteButton;
    JFrame frame;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel back;

    private JScrollPane scrollPane;
    private JTable productTable;
    private JTextField txt_id;
    private JTextField txt_name;
    private JTextField txt_stock;
    private JTextField txt_emailrep;
    private JTextField txt_price;


    public ProductView() {
        initComponents();
        setProductDetailsToTable();

    }

    public void setProductDetailsToTable() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product");

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String stock = rs.getString("stock");
                String price = rs.getString("price");
                String email = rs.getString("emailrep");

                Object[] obj = {id, name, stock, price, email};
                model = (DefaultTableModel) productTable.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean addProduct() {
        boolean isAdded;
        id = Integer.parseInt(txt_id.getText());
        name = txt_name.getText();
        stock = Integer.parseInt(txt_stock.getText());
        emailrep = txt_emailrep.getText();
        price = Integer.parseInt(txt_price.getText());
        Product product = new Product(id, name, stock,price,emailrep);
        ProductBLL bll=new ProductBLL();

        bll.insertProduct(product);

        if (product!=null) {
            isAdded = true;
        } else {
            isAdded = false;
        }

        return isAdded;

    }

    public boolean updateProduct() {
        boolean isUpdated;
        id = Integer.parseInt(txt_id.getText());
        name = txt_name.getText();
        stock = Integer.parseInt(txt_stock.getText());
        emailrep = txt_emailrep.getText();
        price = Integer.parseInt(txt_price.getText());
        Product product = new Product(id, name, stock,price,emailrep);
        ProductBLL bll=new ProductBLL();

        bll.updateProduct(product);
        if (product != null) {
            isUpdated = true;
        } else {
            isUpdated = false;
        }

        return isUpdated;
    }

    public boolean deleteProduct() {
        boolean isDeleted;
        id = Integer.parseInt(txt_id.getText());
        Product product = new Product(id, name, stock,price,emailrep);
        ProductBLL bll=new ProductBLL();

        bll.deleteProduct(product);
        if (product !=null ) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }

    public void clearTable() {
        DefaultTableModel table = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
    }

    private void initComponents() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1250, 600);
        frame.setLayout(null);

        txt_id = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        txt_name = new JTextField();
        txt_stock = new JTextField();
        txt_emailrep = new JTextField();
        txt_price = new JTextField();
        jLabel4 = new JLabel();
        jLabel6 = new JLabel();
        jLabel8 = new JLabel();
        jLabel7 = new JLabel();
        jLabel3 = new JLabel();
        back = new JLabel();
        productTable = new JTable();
        deleteButton = new JButton();
        updateButton = new JButton();
        addButton = new JButton();

        setUndecorated(true);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(new Color(131, 144, 250));

        txt_id.setBackground(new Color(249, 233, 236));
        txt_id.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_id.setBounds(40, 70, 360, 40);
        txt_id.setFont(new Font("Tahoma", 0, 17));
        frame.add(txt_id);

        jLabel1.setFont(new Font("Verdana", 0, 17));
        jLabel1.setForeground(new Color(250, 199, 72));
        jLabel1.setBounds(40, 30, 310, 30);
        jLabel1.setText("Enter product Id : ");
        frame.add(jLabel1);

        jLabel2.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel2.setForeground(new Color(250, 199, 72));
        jLabel2.setBounds(40, 150, 310, 30);
        jLabel2.setText("Enter product Name : ");
        frame.add(jLabel2);

        txt_name.setBackground(new Color(249, 233, 236));
        txt_name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_name.setFont(new Font("Tahoma", 0, 17));
        txt_name.setBounds(40, 190, 360, 40);
        frame.add(txt_name);

        jLabel3.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel3.setForeground(new Color(250, 199, 72));
        jLabel3.setBounds(40, 270, 310, 30);
        jLabel3.setText("Enter product stock : ");
        frame.add(jLabel3);

        txt_stock.setBackground(new Color(249, 233, 236));
        txt_stock.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_stock.setFont(new Font("Tahoma", 0, 17));
        txt_stock.setBounds(40, 310, 360, 40);
        frame.add(txt_stock);

        jLabel4.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel4.setForeground(new Color(250, 199, 72));
        jLabel4.setBounds(40, 390, 330, 30);
        jLabel4.setText("Enter product Email of Representant: ");
        frame.add(jLabel4);

        txt_emailrep.setBackground(new Color(249, 233, 236));
        txt_emailrep.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_emailrep.setFont(new Font("Tahoma", 0, 17));
        txt_emailrep.setBounds(40, 440, 360, 40);
        frame.add(txt_emailrep);

        jLabel5.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel5.setForeground(new Color(250, 199, 72));
        jLabel5.setBounds(40, 510, 310, 30);
        jLabel5.setText("Enter product price : ");
        frame.add(jLabel5);

        txt_price.setBackground(new Color(249, 233, 236));
        txt_price.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_price.setFont(new Font("Tahoma", 0, 17));
        txt_price.setBounds(40, 550, 360, 40);
        frame.add(txt_price);


        deleteButton.setBackground(new Color(250, 199, 72));
        deleteButton.setText("Delete");
        deleteButton.setBounds(750, 480, 80, 40);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        frame.add(deleteButton);

        addButton.setBackground(new Color(250, 199, 72));
        addButton.setText("Add");
        addButton.setBounds(500, 480, 80, 40);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        frame.add(addButton);

        updateButton.setBackground(new Color(250, 199, 72));
        updateButton.setText("Update");
        updateButton.setBounds(625, 480, 80, 40);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        frame.add(updateButton);

        back.setFont(new Font("Verdana", 0, 17));
        back.setForeground(new Color(250, 199, 72));
        back.setBounds(10, 20, 50, 20);
        back.setText("<--");
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                backAction(evt);
            }
        });
        frame.add(back);
        productTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "id", "name", "stock", "price", "emailrep"
                }
        ));

        productTable.setGridColor(new Color(250, 199, 72));
        productTable.setFont(new Font("Arial", 0, 25));
        productTable.setRowHeight(40);
        productTable.setFocusable(false);
        productTable.setVisible(true);
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setProductTable(evt);
            }
        });


        scrollPane = new JScrollPane(productTable);
        scrollPane.setAutoscrolls(true);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        scrollPane.setBounds(500, 30, 680, 420);

        frame.add(scrollPane);

        frame.setSize(new Dimension(1250, 680));

        frame.setVisible(true);
    }

    private void setProductTable(java.awt.event.MouseEvent evt) {

        int rowNo = productTable.getSelectedRow();
        DefaultTableModel table = (DefaultTableModel) productTable.getModel();

        txt_id.setText(model.getValueAt(rowNo, 0).toString());
        txt_name.setText(model.getValueAt(rowNo, 1).toString());
        txt_stock.setText(model.getValueAt(rowNo, 2).toString());
        txt_price.setText(model.getValueAt(rowNo, 3).toString());
        txt_emailrep.setText(model.getValueAt(rowNo, 4).toString());

    }

    private void backAction(java.awt.event.MouseEvent evt) {
        frame.dispose();

        View home = new View();
        home.setVisible(true);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (addProduct() == true) {
            JOptionPane.showMessageDialog(this, "Product Added");
            clearTable();
            setProductDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Product Addition Failed");
        }
    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (updateProduct() == true) {
            JOptionPane.showMessageDialog(this, "Product Updated");
            clearTable();
            setProductDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Product Updation Failed");
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (deleteProduct() == true) {
            JOptionPane.showMessageDialog(this, "Product Deleted");
            clearTable();
            setProductDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Product Deletion Failed");
        }
    }

    public static void main(String args[]) {

        ProductView view = new ProductView();
    }

}
