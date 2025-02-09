package Presentation;

import BusinessLogic.OrderBLL;
import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The OrderView class represents the graphical user interface for the order managment system.
 */
public class OrderView extends javax.swing.JFrame{

    int id, idClient,idProduct,sum;
    DefaultTableModel model;
    JButton[] functionButtons = new JButton[3];
    JButton addButton, updateButton, deleteButton, billButton;
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
    private JTable orderTable;
    private JTextField txt_id;
    private JTextField txt_idClient;

    private JTextField txt_idProduct;
    private JTextField txt_sum;


    public OrderView() {
        initComponents();
        setOrderDetailsToTable();

    }

    public void setOrderDetailsToTable() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `order`");

            while (rs.next()) {
                String id = rs.getString("id");
                String idClient = rs.getString("idClient");
                String idProduct = rs.getString("idProduct");
                String sum = rs.getString("sum");

                Object[] obj = {id, idClient, idProduct, sum};
                model = (DefaultTableModel) orderTable.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean addOrder() {
        boolean isAdded = false;
        id = Integer.parseInt(txt_id.getText());
        idClient = Integer.parseInt(txt_idClient.getText());
        idProduct = Integer.parseInt(txt_idProduct.getText());
        sum = Integer.parseInt(txt_sum.getText());

        Order order = new Order(id, idClient, idProduct,sum);
        OrderBLL bll=new OrderBLL();

        bll.insertOrder(order,idProduct);

        if (order!=null) {
            isAdded = true;
        } else {
            isAdded = false;
        }

        return isAdded;

    }

    public boolean updateOrder() {
        boolean isUpdated = false;
        id = Integer.parseInt(txt_id.getText());
        idClient = Integer.parseInt(txt_idClient.getText());
        idProduct = Integer.parseInt(txt_idProduct.getText());
        sum = Integer.parseInt(txt_sum.getText());

        Order order = new Order(id, idClient, idProduct,sum);
        OrderBLL bll=new OrderBLL();

        bll.updateOrder(order);

        if (order != null) {
            isUpdated = true;
        } else {
            isUpdated = false;
        }

        return isUpdated;
    }

    public boolean deleteOrder() {
        boolean isDeleted = false;
        id = Integer.parseInt(txt_id.getText());
        idClient = Integer.parseInt(txt_idClient.getText());
        idProduct = Integer.parseInt(txt_idProduct.getText());
        sum = Integer.parseInt(txt_sum.getText());
        Order order = new Order(id, idClient, idProduct,sum);
        OrderBLL bll=new OrderBLL();

        bll.deleteOrder(order,idProduct);

        if (order !=null ) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }

    public void clearTable() {
        DefaultTableModel table = (DefaultTableModel) orderTable.getModel();
        table.setRowCount(0);
    }

    private void initComponents() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 600);
        frame.setLayout(null);

        txt_id = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel5 = new JLabel();
        txt_idClient = new JTextField();
        txt_idProduct = new JTextField();
        txt_sum = new JTextField();
        jLabel4 = new JLabel();
        jLabel6 = new JLabel();
        jLabel8 = new JLabel();
        jLabel7 = new JLabel();
        jLabel3 = new JLabel();
        back = new JLabel();
        orderTable = new JTable();
        deleteButton = new JButton();
        updateButton = new JButton();
        addButton = new JButton();
        billButton = new JButton();

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
        jLabel1.setText("Enter order Id : ");
        frame.add(jLabel1);

        jLabel2.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel2.setForeground(new Color(250, 199, 72));
        jLabel2.setBounds(40, 150, 310, 30);
        jLabel2.setText("Enter order id client : ");
        frame.add(jLabel2);

        txt_idClient.setBackground(new Color(249, 233, 236));
        txt_idClient.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_idClient.setFont(new Font("Tahoma", 0, 17));
        txt_idClient.setBounds(40, 190, 360, 40);
        frame.add(txt_idClient);

        jLabel3.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel3.setForeground(new Color(250, 199, 72));
        jLabel3.setBounds(40, 270, 310, 30);
        jLabel3.setText("Enter order id product : ");
        frame.add(jLabel3);

        txt_idProduct.setBackground(new Color(249, 233, 236));
        txt_idProduct.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_idProduct.setFont(new Font("Tahoma", 0, 17));
        txt_idProduct.setBounds(40, 310, 360, 40);
        frame.add(txt_idProduct);

        jLabel5.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel5.setForeground(new Color(250, 199, 72));
        jLabel5.setBounds(40, 390, 310, 30);
        jLabel5.setText("Enter order sum : ");
        frame.add(jLabel5);

        txt_sum.setBackground(new Color(249, 233, 236));
        txt_sum.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_sum.setFont(new Font("Tahoma", 0, 17));
        txt_sum.setBounds(40, 440, 360, 40);
        frame.add(txt_sum);


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

        billButton.setBackground(new Color(250, 199, 72));
        billButton.setText("Bill");
        billButton.setBounds(875, 480, 80, 40);

        billButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                billButtonActionPerformed(evt);
            }
        });
        frame.add(billButton);


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
        orderTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "id", "idClient", "idProduct", "sum"
                }
        ));

        orderTable.setGridColor(new Color(250, 199, 72));
        orderTable.setFont(new Font("Arial", 0, 25));
        orderTable.setRowHeight(40);
        orderTable.setFocusable(false);
        orderTable.setVisible(true);
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setOrderTable(evt);
            }
        });

        scrollPane = new JScrollPane(orderTable);
        scrollPane.setAutoscrolls(true);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        scrollPane.setBounds(500, 30, 680, 420);

        frame.add(scrollPane);

        frame.setSize(new Dimension(1250, 680));

        frame.setVisible(true);
    }

    private void setOrderTable(java.awt.event.MouseEvent evt) {

        int rowNo = orderTable.getSelectedRow();
        DefaultTableModel table = (DefaultTableModel) orderTable.getModel();

        txt_id.setText(model.getValueAt(rowNo, 0).toString());
        txt_idClient.setText(model.getValueAt(rowNo, 1).toString());
        txt_idProduct.setText(model.getValueAt(rowNo, 2).toString());
        txt_sum.setText(model.getValueAt(rowNo, 3).toString());

    }

    private void backAction(java.awt.event.MouseEvent evt) {
        frame.dispose();

        View home = new View();
        home.setVisible(true);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (addOrder() == true) {
            JOptionPane.showMessageDialog(this, "Order Added");
            clearTable();
            setOrderDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Order Addition Failed");
        }
    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (updateOrder() == true) {
            JOptionPane.showMessageDialog(this, "Order Updated");
            clearTable();
            setOrderDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Order Updation Failed");
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (deleteOrder() == true) {
            JOptionPane.showMessageDialog(this, "Order Deleted");
            clearTable();
            setOrderDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Order Deletion Failed");
        }
    }

    private void billButtonActionPerformed(java.awt.event.ActionEvent evt) {

            FileWriter writeinfile;
            try {
                writeinfile = new FileWriter("BillGenerated.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {

                OrderDAO dao=new OrderDAO();
                java.util.List<Order> orderList = new ArrayList<>();
                orderList= dao.findAll();
                for (Order o: orderList) {
                    int productid= o.getIdProduct();
                    Product product;
                    ProductDAO daoP = new ProductDAO();
                    product = daoP.findById(productid);
                    int prodstock=product.getStock();
                    product.setStock(prodstock+sum);
                    String prodname=product.getName();
                    int prodprice=product.getPrice();

                    Client client;
                    ClientDAO daoC = new ClientDAO();
                    client = daoC.findById(productid);
                    String clientName=client.getName();
                    String clientAddress=client.getAddress();


                    Bill bill=new Bill(o.getId(),prodname,clientName,o.getSum(),prodprice,clientAddress);
                    String orderBill=bill.printBill(bill);
                    writeinfile.write(orderBill+"\n");

                }


            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Bill Generating Failed");

            }

            try {
                writeinfile.close();
                JOptionPane.showMessageDialog(this, "Bill Generated");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

}
