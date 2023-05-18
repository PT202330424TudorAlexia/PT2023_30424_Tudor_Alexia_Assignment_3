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
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new StudentAgeValidator());

        clientDAO = new ClientDAO();
    }

    public Client findStudentById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Client with id =" + id + " was not found!");
        }
        return st;
    }

    public Client insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client);

    }

    public Client deleteClient(Client client) {
        return clientDAO.delete(client);
    }

    public Client updateClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.update(client);

    }

}
