package Model;

/**
 * The Bill class represents a bill for an order with detailed information.
 */
public class Bill {

    private int orderId;
    private String product;
    private String name;
    private int nr;
    private int price;
    private int sum;
    private String address;


    /**
     * Constructs a Bill object with the specified order ID, product name, buyer's name,
     * quantity, price, and shipping address.
     *
     * @param orderId  The ID of the order
     * @param product  The name of the product
     * @param name     The name of the buyer
     * @param nr       The quantity of the product
     * @param price    The price per unit of the product
     * @param address  The shipping address
     */
    public Bill(int orderId,String product,String name,int nr,int price,String address)
    {
        super();
        this.orderId=orderId;
        this.product=product;
        this.name=name;
        this.nr=nr;
        this.price=price;
        this.sum=nr*price;
        this.address=address;
    }

    public Bill(){

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String printBill(Bill bill){
        StringBuilder sb = new StringBuilder();
        sb.append("Bill order: "+ bill.orderId+"\n");
        sb.append("Product "+bill.product+"\n");
        sb.append("Price "+bill.price+"\n");
        sb.append("Quantity: "+bill.nr+"\n");
        sb.append("Total Sum: "+bill.sum+"\n");
        sb.append("Bought by: "+bill.name+"\n");
        sb.append("Shipping to: "+bill.address+"\n");

        return sb.toString();
    }
}
