package sales;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {

	@Mock
	SalesDao salesDao;
	@Mock
	SalesReportDao salesReportDao;
	@InjectMocks
	SalesApp salesApp = new SalesApp();


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

	@Test
	public void should_return_local_time_is_not_contains_when_isNatTrade_is_true(){
		//given
		boolean isNatTrade=true;
		SalesApp spySalesApp = spy(new SalesApp());
		//when
		List<String> headers = spySalesApp.getHeaders(isNatTrade);
		//then
		Assert.assertEquals(false,headers.contains("Local Time"));
	}

	@Test
	public void should_return_local_time_is_contains_when_isNatTrade_is_false(){
		//given
		boolean isNatTrade=false;
		SalesApp spySalesApp = spy(new SalesApp());
		//when
		List<String> headers = spySalesApp.getHeaders(isNatTrade);
		//then
		Assert.assertEquals(true,headers.contains("Local Time"));
	}

	@Test
	public void should_return_filteredReportDataList_when_add_filteredReportDataList(){
		//given
		Sales spySales = spy(new Sales());
		SalesReportData spySalesReportData = spy(new SalesReportData());
		when(spySalesReportData.getType()).thenReturn("SalesActivity");
		List<SalesReportData> reportDataList = Arrays.asList(new SalesReportData(),spySalesReportData);
		when(salesReportDao.getReportData(spySales)).thenReturn(reportDataList);
		List<SalesReportData> filteredReportDataList = new ArrayList<>();
		//when
		filteredReportDataList = salesApp.addFilteredReportDataList(true,filteredReportDataList,reportDataList);
		//then
		Assert.assertEquals(1, filteredReportDataList.size());
		Assert.assertEquals("SalesActivity", filteredReportDataList.get(0).getType());
	}
}
