package cn.liuyan.test01;

public class Device {

	private String address;
	private int temp;
	private int humid;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public int getHumid() {
		return humid;
	}
	public void setHumid(int humid) {
		this.humid = humid;
	}
	
	public Device(){
		
	}
	
	public Device(String address, int temp, int humid) {
		super();
		this.address = address;
		this.temp = temp;
		this.humid = humid;
	}
	@Override
	public String toString() {
		return "device [address=" + address + ", temp=" + temp + ", humid="
				+ humid + "]";
	}
	
	
}
