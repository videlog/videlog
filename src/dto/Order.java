package dto;
import java.io.Serializable;
import java.sql.Timestamp;
public class Order implements Serializable{
	private String ordercd;
	private String kaiincd;
	private String meigaracd;
	private int ordernumber;
	private int orderprice;
	private String ordertype;
	private String orderstatus;
	private String ordercondition;
	private String executioncondition;
	private Timestamp orderdate;
	private String meigaraname;
	private String marketcd;
	private String marketname;
	private String businesscd;
	private String businessname;
	private int stockprice;
	private String startprice;
	private String highprice;
	private String unit;
	
	private String lowprice;
	private String urivalue;
	
	private String kaivalue;
	private String yearhigh;
	private String yearlow;
	
	public String getOrdercd() {
		return ordercd;
	}
	public void setOrdercd(String ordercd) {
		this.ordercd = ordercd;
	}
	public String getKaiincd() {
		return kaiincd;
	}
	public void setKaiincd(String kaiincd) {
		this.kaiincd = kaiincd;
	}
	public String getMeigaracd() {
		return meigaracd;
	}
	public void setMeigaracd(String meigaracd) {
		this.meigaracd = meigaracd;
	}
	public int getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	public int getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getOrdercondition() {
		return ordercondition;
	}
	public void setOrdercondition(String ordercondition) {
		this.ordercondition = ordercondition;
	}
	public String getExecutioncondition() {
		return executioncondition;
	}
	public void setExecutioncondition(String executioncondition) {
		this.executioncondition = executioncondition;
	}
	public Timestamp getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	public String getMeigaraname() {
		return meigaraname;
	}
	public void setMeigaraname(String meigaraname) {
		this.meigaraname = meigaraname;
	}
	public String getMarketcd() {
		return marketcd;
	}
	public void setMarketcd(String marketcd) {
		this.marketcd = marketcd;
	}
	public String getMarketname() {
		return marketname;
	}
	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}
	public String getBusinesscd() {
		return businesscd;
	}
	public void setBusinesscd(String businesscd) {
		this.businesscd = businesscd;
	}
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public int getStockprice() {
		return stockprice;
	}
	public void setStockprice(int stockprice) {
		this.stockprice = stockprice;
	}
	public String getStartprice() {
		return startprice;
	}
	public void setStartprice(String startprice) {
		this.startprice = startprice;
	}
	public String getHighprice() {
		return highprice;
	}
	public void setHighprice(String highprice) {
		this.highprice = highprice;
	}
	public String getLowprice() {
		return lowprice;
	}
	public void setLowprice(String lowprice) {
		this.lowprice = lowprice;
	}
	public String getUrivalue() {
		return urivalue;
	}
	public void setUrivalue(String urivalue) {
		this.urivalue = urivalue;
	}
	public String getKaivalue() {
		return kaivalue;
	}
	public void setKaivalue(String kaivalue) {
		this.kaivalue = kaivalue;	
	}
	public String getYearhigh() {
		return yearhigh;
	}
	public void setYearhigh(String yearhigh) {
		this.yearhigh = yearhigh;
	}
	public String getYearlow() {
		return yearlow;
	}
	public void setYearlow(String yearlow) {
		this.yearlow = yearlow;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		return "Order [ordercd=" + ordercd + ", kaiincd=" + kaiincd + ", meigaracd=" + meigaracd + ", ordernumber="
				+ ordernumber + ", orderprice=" + orderprice + ", ordertype=" + ordertype + ", orderstatus="
				+ orderstatus + ", ordercondition=" + ordercondition + ", executioncondition=" + executioncondition
				+ ", orderdate=" + orderdate + ", meigaraname=" + meigaraname + ", marketcd=" + marketcd
				+ ",marketname" + marketname + ", businesscd=" + businesscd + ".businessname" + businessname
				+ ", stockprice=" + stockprice + ",startprice="+startprice
				+ ",highprice="+ highprice +",lowprice" + lowprice +",urivalue"+ urivalue 
				+ ",kaivalue"+ kaivalue + ", yearhigh=" + yearhigh + ", yearlow="
				+ yearlow + ",unit" + unit +"]";
	}	

}
