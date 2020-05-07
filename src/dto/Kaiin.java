package dto;
import java.io.Serializable;
public class Kaiin implements Serializable{
	private String kaiincd;
	private String kaiinname;
	private String password;
	private int account;
	public String getKaiincd() {
		return kaiincd;
	}
	public void setKaiincd(String kaiincd) {
		this.kaiincd = kaiincd;
	}
	public String getKaiinname() {
		return kaiinname;
	}
	public void setKaiinname(String kaiinname) {
		this.kaiinname = kaiinname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Kaiin [kaiincd=" + kaiincd + ", kaiinname=" + kaiinname + ", password=" + password + ", account="
				+ account + "]";
	}
	
	
	
	

}
