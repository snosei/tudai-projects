package utils;

import java.util.GregorianCalendar;

public class ParFechas{
	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;

	public ParFechas() {
		// TODO Auto-generated constructor stub
	}

	public GregorianCalendar getFromDate() {
		return fromDate;
	}

	public GregorianCalendar getToDate() {
		return toDate;
	}

	public void setFromDate(GregorianCalendar calendar) {
		this.fromDate = calendar;
	}

	public void setToDate(GregorianCalendar calendar) {
		this.toDate = calendar;
	}
}
