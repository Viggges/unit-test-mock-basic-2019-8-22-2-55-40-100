package cashregister;

public class StubPurchase extends Purchase{

	public StubPurchase() {
		super(new Item[0]);
		// TODO Auto-generated constructor stub
	}
	public String asString() {
        

        return "test product\t1.0\n";
    }
}
