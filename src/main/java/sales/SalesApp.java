package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {

		SalesDao salesDao = new SalesDao();
		SalesReportDao salesReportDao = new SalesReportDao();
		List<String> headers = null;

		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();

		if (salesId == null) {
			return;
		}

		Sales sales = salesDao.getSalesBySalesId(salesId);

		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return;
		}

		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}

		List<SalesReportData> tempList = getTempList(maxRow, reportDataList);
		filteredReportDataList = tempList;

		headers = getHeaders(isNatTrade);

		SalesActivityReport report = this.generateReport(headers, reportDataList);

		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());

	}

	protected List<String> getHeaders(boolean isNatTrade) {
		List<String> headers;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	protected List<SalesReportData> getTempList(int maxRow, List<SalesReportData> salesReportDatas) {
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < salesReportDatas.size() && i < maxRow; i++) {
			tempList.add(salesReportDatas.get(i));
		}
		return tempList;
	}


	private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
