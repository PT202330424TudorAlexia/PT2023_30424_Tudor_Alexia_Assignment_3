package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Order;

public class OrderDAO extends DataAccess.AbstractDAO<Order> {

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

}
