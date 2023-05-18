package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.AmountValidator;
import BusinessLogic.validators.StockValidator;
import BusinessLogic.validators.Validator;
import DataAccess.OrderDAO;
import Model.Order;

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

    public Order insertOrder(Order Order) {
        for (Validator<Order> v : validators) {
            v.validate(Order);
        }
        return orderDAO.insert(Order);
    }

    public Order deleteOrder(Order Order) {
        return orderDAO.delete(Order);
    }

    public Order updateOrder(Order Order) {
        for (Validator<Order> v : validators) {
            v.validate(Order);
        }
        return orderDAO.update(Order);

    }
}
