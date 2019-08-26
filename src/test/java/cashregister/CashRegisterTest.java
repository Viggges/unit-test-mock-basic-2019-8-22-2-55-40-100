package cashregister;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.concurrent.DetachedThreadLocal.Cleaner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CashRegisterTest {
	private static MockPrinter mockPrinter;
	private static CashRegister cashRegister;

	@BeforeAll
	private static void setup() {
		mockPrinter = new MockPrinter();
		cashRegister = new CashRegister(mockPrinter);
	}

	@BeforeEach
	private void clean() {
		mockPrinter.setTempString("");
	}

	@Test
	public void should_print_the_real_purchase_when_call_process() {
		// given
		Item[] items = { new Item("test product", 1) };
		Purchase purchase = new Purchase(items);

		// when
		cashRegister.process(purchase);
		// then
		assertEquals("test product\t1.0\n", mockPrinter.getTempString());
	}

	@Test
	public void should_print_the_stub_purchase_when_call_process() {
		// given
		StubPurchase stubPurchase = new StubPurchase();
		// when
		cashRegister.process(stubPurchase);
		// then
		assertEquals("test product\t1.0\n", mockPrinter.getTempString());
	}

	@Test
	public void should_verify_with_process_call_with_mockito() {
		// given
		Printer printer = mock(Printer.class);
		Purchase purchase = mock(Purchase.class);
		when(purchase.asString()).thenReturn("test product");
		// when
		CashRegister cashRegister = new CashRegister(printer);
		cashRegister.process(purchase);
		// then
		verify(printer).print("test product");
	}

}
