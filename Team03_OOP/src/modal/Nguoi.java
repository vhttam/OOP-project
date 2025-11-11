package modal;

import java.util.Scanner;

abstract public class Nguoi {
	protected String id;
	protected String hoten;
	protected String gioitinh;
	protected String namsinh;
	
	public String getid() {
		return id;
	}
	public String gethoten() {
		return hoten;
	}
	public String getgioitinh() {
		return gioitinh;
	}
	public String getnamsinh() {
		return namsinh;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	public void sethoten(String hoten) {
		this.hoten = hoten;
	}
	public void setgioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public void setnamsinh(String namsinh) {
		this.namsinh = namsinh;
	}
	
	public Nguoi(String id, String hoten, String gioitinh, String namsinh) {
		this.id = id;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.namsinh = namsinh;
	}
	
	public Nguoi() {
	}
	
	abstract void ktr_id();
	
	public void ktr_gioitinh() {
		if (!gioitinh.equalsIgnoreCase("Nu") && !gioitinh.equalsIgnoreCase("Nam")) {
			this.gioitinh = "Nam";
			System.out.println("Gioi tinh khong hop le");
			System.out.println("Gioi tinh doi thanh mac dinh: " +this.getgioitinh());
		}
	}
	
	public void nhap(Scanner sc) {
		try {
		System.out.println("Moi nhap id:");
		this.setid(sc.nextLine().trim());
		System.out.println("Moi nhap ho ten:");
		this.sethoten(sc.nextLine().trim());
		System.out.println("Moi nhap gioi tinh:");
		this.setgioitinh(sc.nextLine().trim());
		System.out.println("Moi nhap nam sinh:");
		this.setnamsinh(sc.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		}
	}
	
	public void xuat() {
		ktr_gioitinh();
		System.out.println("ID: " + this.getid());
		System.out.println("Ho ten: " + this.gethoten());
		System.out.println("Gioi tinh: " + this.getgioitinh());
		System.out.println("Nam sinh: " + this.getnamsinh());
	}
}
