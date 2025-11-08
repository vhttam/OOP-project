package Nguoi;

import java.util.Scanner;

public class NhanVien_BanHang extends NhanVien {
	private float hoaHong;
	
	public float gethoaHong() {
		return hoaHong;
	}
	public void sethoaHong(float hoaHong) {
		this.hoaHong = hoaHong;
	}
	
	public NhanVien_BanHang (String id, String hoten, String gioitinh, String namsinh, int soNgayNghi, float luongThuong, String caLamViec, float hoaHong) {
		super(id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec);
		this.hoaHong = hoaHong;
	}
	
	@Override
	public float tinhLuong() {
		return (super.getLuongCB() * super.hesoXepLoai() * super.hesoCa()) + super.getluongThuong() + this.gethoaHong();
	}
	
	@SuppressWarnings("resource")
	public void nhap() {
		Scanner sc = new Scanner (System.in);
		super.nhap();
		System.out.println("Moi nhap tien hoa hong: ");
		this.sethoaHong(sc.nextFloat());
	}
	
	public void xuat() {
		super.xuat();
		System.out.println("Tien hoa hong nhan duoc: " + this.gethoaHong());
		System.out.println("Luong: " + this.tinhLuong());
	}
}
