package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.AmountValidator;
import BusinessLogic.validators.StockValidator;
import BusinessLogic.validators.Validator;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Order;
import Model.Product;
import Connection.ConnectionFactory;

/**
 * The OrderBLL class provides business logic operations for orders.
 */
public class OrderBLL {

    private List<Validator<Order>> validators;

    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<Validator<Order>>();
        validators.add(new AmountValidator());

        orderDAO = new OrderDAO();

    }

    public Order findOrderById(int id) {
        Order st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return st;
    }

    public Order insertOrder(Order order, int productid) {
        for (Validator<Order> v : validators) {
            v.validate(order);
        }
        int nr=order.getSum();
        //int productid = order.getIdProduct();
        System.out.println(productid);

        Product product;
        ProductDAO dao = new ProductDAO();  // Use the concrete subclass
        product = dao.findById(productid);
        int prodstock=product.getStock();
        int finalnr=prodstock-nr;
        product.setStock(prodstock-nr);
        System.out.println(product.getStock());
        String prodname=product.getName();
        int prodprice=product.getPrice();
        String prodemail=product.getEmailRep();

        String query = "UPDATE product  SET name=? , stock=? , price=? , emailrep=?  WHERE id= ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, prodname);
            statement.setInt(2, finalnr);
            statement.setInt(3, prodprice);
            statement.setString(4, prodemail);
            statement.setInt(5, productid);


            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting object: " + e.getMessage());
        }

        return orderDAO.insert(order);
    }

    public Order deleteOrder(Order order, int productid) {

        int sum=order.getSum();

        //int productid = order.getIdProduct();
        Product product;
        ProductDAO dao = new ProductDAO();  // Use the concrete subclass
        product = dao.findById(productid);
        int prodstock=product.getStock();
        product.setStock(prodstock+sum);
        String prodname=product.getName();
        int prodprice=product.getPrice();
        String prodemail=product.getEmailRep();

        String query = "UPDATE product  SET name=? , stock=? , price=? , emailrep=?  WHERE id= ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, prodname);
            statement.setInt(2, prodstock);
            statement.setInt(3, prodprice);
            statement.setString(4, prodemail);
            statement.setInt(5, productid);


            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting object: " + e.getMessage());
        }

        return orderDAO.delete(order);
    }

    public Order updateOrder(Order order) {
        for (Validator<Order> v : validators) {
            v.validate(order);
        }
        return orderDAO.update(order);

    }
}
