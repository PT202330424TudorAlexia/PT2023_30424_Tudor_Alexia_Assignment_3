package BusinessLogic.validators;

import DataAccess.AbstractDAO;
import DataAccess.ProductDAO;
import Model.Order;
import Model.Product;

import java.util.List;

/**
 * The AmountValidator class validates the quantity of a product in an Order, such that it doesn't order more than available in stock.
 */
public class AmountValidator  implements Validator<Order> {

    public void validate(Order t) {
        int s = t.getSum();
        int productid = t.getIdProduct();
        Product product;
        ProductDAO dao = new ProductDAO();  // Use the concrete subclass
        product = dao.findById(productid);

        if (product.equals(null)) {
            throw new IllegalArgumentException("Product not found!");
        }

        int productStock = product.getStock();

        if (s > productStock) {
            throw new IllegalArgumentException("There aren't that many available products!");
        }
    }
}