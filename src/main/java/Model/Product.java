package Model;

/**
 * The Product class represents a product with its information and stock details.
 */
public class Product {
    private int id;
    private String name;
    private int stock;
    private int price;
    private String emailRep;


    /**
     * Constructs a Product object with the specified ID, name, stock quantity, price, and email of the representative.
     *
     * @param id        The ID of the product
     * @param name      The name of the product
     * @param stock     The stock quantity of the product
     * @param price     The price of the product
     * @param emailRep  The email of the representative
     */
    public Product(int id,String name,int stock,int price,String emailRep)
    {
        super();
        this.id=id;
        this.name=name;
        this.stock=stock;
        this.price=price;
        this.emailRep=emailRep;
    }

    public Product(String name,int stock,int price,String emailRep)
    {
        super();
        this.name=name;
        this.stock=stock;
        this.price=price;
        this.emailRep=emailRep;
    }

    public Product()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailRep() {
        return emailRep;
    }

    public void setEmailRep(String emailRep) {
        this.emailRep = emailRep;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
