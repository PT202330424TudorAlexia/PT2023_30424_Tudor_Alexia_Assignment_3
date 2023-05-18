package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Product;

public class ProductDAO extends DataAccess.AbstractDAO<Product>{

    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

}
