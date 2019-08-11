package sales;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SalesAppTest {

	@Test
	public void testGenerateReport() {

		SalesApp salesApp = new SalesApp();
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);

	}


}
