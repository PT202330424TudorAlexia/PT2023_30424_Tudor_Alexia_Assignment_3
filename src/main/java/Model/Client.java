package Model;
/**
 * The Client class represents a client with their personal information.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Constructs a Client object with the specified ID, name, address, email, and age.
     *
     * @param id      The ID of the client
     * @param name    The name of the client
     * @param address The address of the client
     * @param email   The email of the client
     * @param age     The age of the client
     */
    public Client(int id,String name,String address,String email,int age)
    {
        super();
        this.id=id;
        this.name=name;
        this.address=address;
        this.email=email;
        this.age=age;
    }

    public Client(String name,String address,String email,int age)
    {
        super();
        this.name=name;
        this.address=address;
        this.email=email;
        this.age=age;
    }

    public Client(){}


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
