package Model;

public class Order {

    private int id;
    private int idClient;
    private int idProduct;
    private int sum;

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
