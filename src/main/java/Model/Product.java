package Model;

public class Product {
    private int id;
    private String name;
    private int stock;
    private int price;
    private String emailRep;


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
