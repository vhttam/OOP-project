package OOP3.Nguoi.Nguoi;

import java.util.Scanner;

public class KhachHang extends Nguoi {
	
	@Override
	public void ktr_id() {
		if (id.startsWith("KH")) {
			super.id = id;
		}
		else {
			super.id = "KH001";
			System.out.println("Id khach hang khong hop le");
			System.out.println("Id se duoc thay doi thanh mac dinh la:" + super.getid());
		}
	}
	
	public KhachHang(String id, String hoten, String gioitinh, String namsinh) {
		super(id,hoten,gioitinh,namsinh);
	}
	public KhachHang() {
	}
	
	@Override
	public void nhap(Scanner sc) {
		super.nhap(sc);
		this.ktr_id();
	}
	
	@Override
	public void xuat() {
		super.xuat();
	}
}
