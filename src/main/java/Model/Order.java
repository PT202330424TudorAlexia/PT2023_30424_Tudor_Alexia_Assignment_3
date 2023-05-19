package Model;

/**
 * The Order class represents an order made by a client for a product.
 */
public class Order {

    private int id;
    private int idClient;
    private int idProduct;
    private int sum;

    /**
     * Constructs an Order object with the specified ID, client ID, product ID, and sum.
     *
     * @param id        The ID of the order
     * @param idClient  The ID of the client who made the order
     * @param idProduct The ID of the product in the order
     * @param sum       The sum of the order
     */
    public Order(int id,int idClient,int idProduct,int sum)
    {
        super();
        this.id=id;
        this.idClient=idClient;
        this.idProduct=idProduct;
        this.sum=sum;
    }

    public Order(int idClient,int idProduct,int sum)
    {
        super();
        this.idClient=idClient;
        this.idProduct=idProduct;
        this.sum=sum;
    }

    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
