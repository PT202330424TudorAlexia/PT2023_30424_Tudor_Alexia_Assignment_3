package BusinessLogic.validators;

/**
 * The Validator interface defines a contract for validating objects of a specific type.
 *
 * @param <T> The type of object to validate.
 */
public interface Validator<T> {

    public void validate(T t);
}
