package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.StudentAgeValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;
import Model.Client;

public class ClientBLL {

    private List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new StudentAgeValidator());
    }

    public Client findClientById(int id) {
        Client st = ClientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Client with id =" + id + " was not found!");
        }
        return st;
    }

    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insert(client);
    }
}
