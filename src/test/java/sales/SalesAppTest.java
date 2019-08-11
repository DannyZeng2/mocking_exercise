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

	@Test
	public void should_is_not_sale_return_false_when_today_is_sale_day(){
		//given
		SalesApp spySalesApp = spy(new SalesApp());
		Sales spySales = spy(new Sales());
		when(spySales.getEffectiveTo()).thenReturn(new Date(new Date().getTime()+99999));
		when(spySales.getEffectiveFrom()).thenReturn(new Date(new Date().getTime()-99999));
		//when
		boolean isNotSale = spySalesApp.isNotSaleDay(spySales);
		//then
		Assert.assertEquals(false,isNotSale);
	}


}
