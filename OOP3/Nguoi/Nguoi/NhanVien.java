package Nguoi;

import java.util.Scanner;

abstract public class NhanVien extends Nguoi {
	public static int LuongCB = 5000000;
	protected int soNgayNghi;
	protected float luongThuong;
	protected String caLamViec;
	
	public int getLuongCB() {
		return NhanVien.LuongCB;
	}
	public int getsoNgayNghi() {
		return this.soNgayNghi;
	}
	public float getluongThuong() {
		return this.luongThuong;
	}
	public String getcaLamViec() {
		return this.caLamViec;
	}
	
	public void setLuongCB(int LuongCB) {
		NhanVien.LuongCB = LuongCB;
	}
	public void setsoNgayNghi(int soNgayNghi) {
		this.soNgayNghi = soNgayNghi;
	}
	public void setluongThuong(float luongThuong) {
		this.luongThuong = luongThuong;
	}
	public void setcaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	
	public NhanVien(String id, String hoten, String gioitinh, String namsinh, int soNgayNghi, float luongThuong, String caLamViec) {
		super(id,hoten,gioitinh,namsinh);
		this.soNgayNghi = soNgayNghi;
		this.luongThuong = luongThuong;
		this.caLamViec = caLamViec;
	}
	
	@Override
	public void ktr_id() {
		if (id.startsWith("NV")) {
			super.id = id;
		}
		else {
			super.id = "NV001";
			System.out.println("ID nhan vien khong hop le");
			System.out.println("Thay doi ID thanh mac dinh la: " + super.getid());
		}
	}
	
	public boolean ktr_soNgayNghi() {
		if (soNgayNghi < 0) return false;
		else return true;
	}
	
	public char xepLoai() {
		if (ktr_soNgayNghi()) {
			if (soNgayNghi <= 2) return 'A';
			else if (soNgayNghi > 2 && soNgayNghi <= 5) return 'B';
			else if (soNgayNghi > 5 && soNgayNghi <= 7) return 'C';
			else return 'D';
		}
		else return 'F';
	}
	
	public float hesoXepLoai() {
		switch (xepLoai()) {
		case 'A':
			return 1.5f;
		case 'B':
			return 1.0f;
		case 'C': 
			return 0.8f;
		case 'D':
			return 0.5f;
		default: return 0.0f;
		}
	}
	
	public void ktr_caLamViec() {
		if (!caLamViec.equalsIgnoreCase("Sang") && !caLamViec.equalsIgnoreCase("Toi")) {
			this.caLamViec = "Sang";
			System.out.println("Ca lam viec khong hop le");
			System.out.println("Thay doi thanh ca lam mac dinh:" + getcaLamViec());
		}
	}
	
	public float hesoCa() {
		if (caLamViec.equalsIgnoreCase("Toi")) {
			return 1.5f;
		}
		else return 1.0f;
	}
	
	abstract float tinhLuong();
	
	@SuppressWarnings("resource")
	public void nhap() {
		Scanner sc = new Scanner (System.in);
		super.nhap();
		System.out.println("Moi nhap so ngay nghi: ");
		this.setsoNgayNghi(sc.nextInt());
		System.out.println("Moi nhap luong thuong: ");
		this.setluongThuong(sc.nextFloat());
		sc.nextLine();
		System.out.println("Moi nhap ca lam viec: ");
		this.setcaLamViec(sc.nextLine());
	}
	
	public void xuat() {
		this.ktr_id();
		super.xuat();
		System.out.println("So ngay nghi: " + this.getsoNgayNghi());
		System.out.println("Luong thuong: " + this.getluongThuong());
		System.out.println("Ca lam viec: " + this.getcaLamViec());
		System.out.println("Xep loai: " + this.xepLoai());
	}
}
