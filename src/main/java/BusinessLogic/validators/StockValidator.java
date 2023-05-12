package BusinessLogic.validators;

import Model.Product;

public class StockValidator implements Validator<Product> {

    public void validate(Product t) {
        if (t.getNr()!=0) {
            throw new IllegalArgumentException("Stock can't be 0!");
        }
    }

}
