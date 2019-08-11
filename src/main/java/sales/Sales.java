package sales;

import java.util.Date;

public class Sales {
	
	private boolean isSupervisor;

	public Date getEffectiveTo() {
		// TODO Auto-generated method stub
		return new Date(new Date().getTime() + 100000);
	}

	public Date getEffectiveFrom() {
		// TODO Auto-generated method stub
		return new Date(new Date().getTime() - 100000);
	}

	public boolean isSupervisor() {
		return isSupervisor;
	}

	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
}
