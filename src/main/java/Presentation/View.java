package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends javax.swing.JFrame {


    String name, address, email;
    int id, age;
    DefaultTableModel model;
    JButton[] functionButtons = new JButton[3];
    JButton clientButton, productButton, orderButton;
    JFrame frame;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel exit;

    private JScrollPane scrollPane;
    private JTable clientTable;
    private JTextField txt_id;

    public View() {
        initComponents();

    }

    private void initComponents() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        orderButton = new JButton();
        productButton = new JButton();
        clientButton = new JButton();
        exit = new JLabel();

        setUndecorated(true);

        getContentPane().setBackground(new Color(131, 144, 250));

        jLabel1.setFont(new Font("Verdana", Font.BOLD, 47));
        jLabel1.setForeground(new Color(250, 199, 72));
        jLabel1.setBounds(30, 70, 710, 60);
        jLabel1.setText("Warehouse Management");
        add(jLabel1);

        exit.setFont(new Font("Arial", 1, 40));
        exit.setForeground(new Color(250, 199, 72));
        exit.setBounds(665, 15, 50, 35);
        exit.setText("X");
        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                exitAction(evt);
            }
        });
        add(exit);

        orderButton.setBackground(new Color(250, 199, 72));
        orderButton.setFont(new Font("Arial",1,21));
        orderButton.setForeground(Color.WHITE);
        orderButton.setText("Orders");

        orderButton.setBounds(215, 550, 280, 60);

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        add(orderButton);

        clientButton.setBackground(new Color(250, 199, 72));
        clientButton.setFont(new Font("Arial",1,21));
        clientButton.setForeground(Color.WHITE);
        clientButton.setText("Clients");
        clientButton.setBounds(215, 400, 280, 60);

        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clientButtonActionPerformed(evt);
            }
        });
        add(clientButton);

        productButton.setBackground(new Color(250, 199, 72));
        productButton.setFont(new Font("Arial",1,21));
        productButton.setForeground(Color.WHITE);
        productButton.setText("Products");
        productButton.setBounds(215, 250, 280, 60);

        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                productButtonActionPerformed(evt);
            }
        });
        add(productButton);

        setSize(new Dimension(700, 700));
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void exitAction(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void clientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        ClientView clientView = new ClientView();
        clientView.setVisible(true);
        setVisible(false);
    }

    private void productButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        ProductView productView = new ProductView();
        productView.setVisible(true);
        setVisible(false);
    }

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        OrderView orderView = new OrderView();
        orderView.setVisible(true);
        setVisible(false);

    }

    public static void main(String args[]) {

        View view = new View();
    }

}

