package cashregister;

public class MockPrinter extends Printer{
	private String tempString="";
	
	@Override
	public void print(String printThis) {
		// TODO Auto-generated method stub
		super.print(printThis);
		setTempString(printThis);
	}

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
}
