package Model;

public class Product {
    private int id;
    private String name;
    private int nr;
    private int price;
    private String emailRep;


    public Product(int id,String name,int nr,String emailRep)
    {
        super();
        this.id=id;
        this.name=name;
        this.nr=nr;
        this.emailRep=emailRep;
    }

    public Product(String name,int nr,String emailRep)
    {
        super();
        this.name=name;
        this.nr=nr;
        this.emailRep=emailRep;
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

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
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
}
