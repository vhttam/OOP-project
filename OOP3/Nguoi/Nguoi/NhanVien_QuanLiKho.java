package OOP3.Nguoi.Nguoi;

import java.util.Scanner;

public class NhanVien_QuanLiKho extends NhanVien {
	
	private float phuCap;
	
	public float getphuCap() {
		return phuCap;
	}
	public void setphuCap(float phuCap) {
		this.phuCap = phuCap;
	}
	
	public NhanVien_QuanLiKho (String id, String hoten, String gioitinh, String namsinh, int soNgayNghi, float luongThuong, String caLamViec,float phuCap) {
		super(id,hoten,gioitinh,namsinh,soNgayNghi,luongThuong,caLamViec);
		this.phuCap = phuCap;
	}
	public NhanVien_QuanLiKho() {
	}
	
	@Override
	public float tinhLuong() {
		return (LuongCB * super.hesoXepLoai() * super.hesoCa()) + super.getluongThuong() + getphuCap();
	}
	
	@Override
	public void nhap(Scanner sc) {
		super.nhap(sc);
		try {
			System.out.println("Moi nhap phu cap: ");
			this.setphuCap(Float.parseFloat(sc.nextLine()));
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		}
	}
	
	@Override
	public void xuat() {
		super.xuat();
		System.out.println("Phu cap: " + this.getphuCap());
		System.out.printf("Luong: %.1f" , this.tinhLuong());
	}
}
