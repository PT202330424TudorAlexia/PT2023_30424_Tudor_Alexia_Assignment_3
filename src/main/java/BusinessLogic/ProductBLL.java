package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.EmailRepresentantValidator;
import BusinessLogic.validators.StockValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ProductDAO;
import Model.Product;

public class ProductBLL {

    private List<Validator<Product>> validators;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new EmailRepresentantValidator());
        validators.add(new StockValidator());
    }

    public Product findProductById(int id) {
        Product st = ProductDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    public int insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insert(product);
    }
}
