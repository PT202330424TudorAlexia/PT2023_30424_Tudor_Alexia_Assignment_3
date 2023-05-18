package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES ( ");
        for (int i = 0; i < type.getDeclaredFields().length; i++) {
            sb.append("?");
            if (i < type.getDeclaredFields().length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("`"+type.getSimpleName()+"`");
        sb.append(" SET ");
        int fieldCount = 0;
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                sb.append(field.getName()).append(" = ?");
                if (fieldCount < type.getDeclaredFields().length - 2) {
                    sb.append(", ");
                }
                fieldCount++;
            }
        }
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            result = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return result;
    }

    public T findById(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(createSelectQuery("id"))) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> objects = createObjects(resultSet);
                if (!objects.isEmpty()) {
                    return objects.get(0);
                } else {
                    throw new NoSuchElementException("No object found with the given ID: " + id);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor<?>[] ctors = type.getDeclaredConstructors();
        Constructor<?> ctor = null;
        for (Constructor<?> constructor : ctors) {
            if (constructor.getParameterCount() == 0) {
                ctor = constructor;
                break;
            }
        }
        if (ctor == null) {
            throw new IllegalStateException("No parameterless constructor found for class: " + type.getName());
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                // setGeneratedId(t, generatedId);
            }
            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error inserting object: " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                }
            }
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            int idValue = (int) idField.get(t);
            statement.setInt(parameterIndex, idValue);
            statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, "Error updating object: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            for (Field field : type.getDeclaredFields()) {
                if (field.getName().equals("id")) {
                    field.setAccessible(true);
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    break;
                }
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                // setGeneratedId(t, generatedId);
            }
            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error deleting object: " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
