package Presentation;

import BusinessLogic.ClientBLL;
import Connection.ConnectionFactory;
import Model.Client;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientView extends javax.swing.JFrame {

    String name, address, email;
    int id, age;
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
    private JTable clientTable;
    private JTextField txt_id;
    private JTextField txt_name;
    private JTextField txt_address;
    private JTextField txt_email;
    private JTextField txt_age;


    public ClientView() {
        initComponents();
        setClientDetailsToTable();

    }

    public void setClientDetailsToTable() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM client");

            // Process the ResultSet and populate the table
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String age = rs.getString("age");

                Object[] obj = {id, name, address, email, age};
                model = (DefaultTableModel) clientTable.getModel();
                model.addRow(obj);
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the ResultSet, Statement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}

        public boolean addClient() {
        boolean isAdded = false;
        id = Integer.parseInt(txt_id.getText());
        name = txt_name.getText();
        address = txt_address.getText();
        email = txt_email.getText();
        age = Integer.parseInt(txt_age.getText());
        Client client = new Client(id, name, address, email, age);
        ClientBLL bll=new ClientBLL();

        bll.insertClient(client);

        if (client!=null) {
            isAdded = true;
        } else {
            isAdded = false;
        }

        return isAdded;

    }

    public boolean updateClient() {
        boolean isUpdated = false;
        id = Integer.parseInt(txt_id.getText());
        name = txt_name.getText();
        address = txt_address.getText();
        email = txt_email.getText();
        age = Integer.parseInt(txt_age.getText());
        Client client = new Client(id, name, address, email, age);
        ClientBLL bll=new ClientBLL();

        bll.updateClient(client);
            if (client != null) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }

        return isUpdated;
    }

    public boolean deleteClient() {
        boolean isDeleted = false;
        id = Integer.parseInt(txt_id.getText());

        Client client = new Client(id, name, address, email, age);
        ClientBLL bll=new ClientBLL();

        bll.deleteClient(client);
            if (client !=null ) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
        return isDeleted;
    }

    public void clearTable() {
        DefaultTableModel table = (DefaultTableModel) clientTable.getModel();
        model.setRowCount(0);
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
        txt_name = new JTextField();
        txt_address = new JTextField();
        txt_email = new JTextField();
        txt_age = new JTextField();
        jLabel4 = new JLabel();
        jLabel6 = new JLabel();
        jLabel8 = new JLabel();
        jLabel7 = new JLabel();
        jLabel3 = new JLabel();
        back = new JLabel();
        clientTable = new JTable();
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
        jLabel1.setText("Enter client Id : ");
        frame.add(jLabel1);

        jLabel2.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel2.setForeground(new Color(250, 199, 72));
        jLabel2.setBounds(40, 150, 310, 30);
        jLabel2.setText("Enter client Name : ");
        frame.add(jLabel2);

        txt_name.setBackground(new Color(249, 233, 236));
        txt_name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_name.setFont(new Font("Tahoma", 0, 17));
        txt_name.setBounds(40, 190, 360, 40);
        frame.add(txt_name);

        jLabel3.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel3.setForeground(new Color(250, 199, 72));
        jLabel3.setBounds(40, 270, 310, 30);
        jLabel3.setText("Enter client Address : ");
        frame.add(jLabel3);

        txt_address.setBackground(new Color(249, 233, 236));
        txt_address.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_address.setFont(new Font("Tahoma", 0, 17));
        txt_address.setBounds(40, 310, 360, 40);
        frame.add(txt_address);

        jLabel4.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel4.setForeground(new Color(250, 199, 72));
        jLabel4.setBounds(40, 390, 310, 30);
        jLabel4.setText("Enter client Email : ");
        frame.add(jLabel4);

        txt_email.setBackground(new Color(249, 233, 236));
        txt_email.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_email.setFont(new Font("Tahoma", 0, 17));
        txt_email.setBounds(40, 440, 360, 40);
        frame.add(txt_email);

        jLabel5.setFont(new Font("Verdana", 0, 17)); // NOI18N
        jLabel5.setForeground(new Color(250, 199, 72));
        jLabel5.setBounds(40, 510, 310, 30);
        jLabel5.setText("Enter client age : ");
        frame.add(jLabel5);

        txt_age.setBackground(new Color(249, 233, 236));
        txt_age.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
        txt_age.setFont(new Font("Tahoma", 0, 17));
        txt_age.setBounds(40, 550, 360, 40);
        frame.add(txt_age);


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
        clientTable.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "id", "name", "address", "email", "age"
                }
        ));

        clientTable.setGridColor(new Color(250, 199, 72));
        clientTable.setFont(new Font("Arial", 0, 25));
        clientTable.setRowHeight(40);
        clientTable.setFocusable(false);
        clientTable.setVisible(true);
        clientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setClientTable(evt);
            }
        });

        scrollPane = new JScrollPane(clientTable);
        scrollPane.setAutoscrolls(true);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        scrollPane.setBounds(500, 30, 680, 420);

        frame.add(scrollPane);

        frame.setSize(new Dimension(1250, 680));

        frame.setVisible(true);
    }

    private void setClientTable(java.awt.event.MouseEvent evt) {

        int rowNo = clientTable.getSelectedRow();
        DefaultTableModel table = (DefaultTableModel) clientTable.getModel();

        txt_id.setText(model.getValueAt(rowNo, 0).toString());
        txt_name.setText(model.getValueAt(rowNo, 1).toString());
        txt_address.setText(model.getValueAt(rowNo, 2).toString());
        txt_email.setText(model.getValueAt(rowNo, 3).toString());
        txt_age.setText(model.getValueAt(rowNo, 4).toString());

    }

    private void backAction(java.awt.event.MouseEvent evt) {
        frame.dispose();
        View home = new View();
        home.setVisible(true);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (addClient() == true) {
            JOptionPane.showMessageDialog(this, "Client Added");
            clearTable();
            setClientDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Client Addition Failed");
        }
    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (updateClient() == true) {
            JOptionPane.showMessageDialog(this, "Client Updated");
            clearTable();
            setClientDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Client Updation Failed");
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (deleteClient() == true) {
            JOptionPane.showMessageDialog(this, "Client Deleted");
            clearTable();
            setClientDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Client Deletion Failed");
        }
    }

    public static void main(String args[]) {

        ClientView view = new ClientView();
    }

}
