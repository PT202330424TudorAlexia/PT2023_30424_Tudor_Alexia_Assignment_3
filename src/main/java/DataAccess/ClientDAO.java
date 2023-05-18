package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Client;
import Connection.ConnectionFactory;

public class ClientDAO extends DataAccess.AbstractDAO<Client> {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());


}
