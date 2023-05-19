package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.EmailRepresentantValidator;
import BusinessLogic.validators.StockValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

/**
 * The ProductBLL class provides business logic operations for products.
 */
public class ProductBLL {

    private List<Validator<Product>> validators;

    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new EmailRepresentantValidator());
        validators.add(new StockValidator());

        productDAO = new ProductDAO();

    }

    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    public Product insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDAO.insert(product);
    }

    public Product deleteProduct(Product product) {
        return productDAO.delete(product);
    }

    public Product updateProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDAO.update(product);

    }
}
