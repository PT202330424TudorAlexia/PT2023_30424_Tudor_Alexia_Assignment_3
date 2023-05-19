package BusinessLogic.validators;

import Model.Product;

/**
 * A validator that validates the stock of a product.
 */
public class StockValidator implements Validator<Product> {

    public void validate(Product t) {
        if (t.getStock()==0) {
            throw new IllegalArgumentException("Stock can't be 0!");
        }
    }

}
