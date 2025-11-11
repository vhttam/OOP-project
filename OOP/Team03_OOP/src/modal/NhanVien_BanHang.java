package modal;

import java.util.Scanner;

public class NhanVien_BanHang extends NhanVien implements TinhThuongDoanhThu {
	private float doanhSo;
	
	public float getdoanhSo() {
		return doanhSo;
	}
	public void setdoanhSo(float doanhSo) {
		this.doanhSo = doanhSo;
	}
	
	public NhanVien_BanHang (String chucvu, String id, String hoten, String gioitinh, String namsinh, int soNgayNghi, float luongThuong, String caLamViec, float doanhSo) {
		super(id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec);
		this.doanhSo = doanhSo;
		super.setchucvu("Ban Hang");
	}
	
	public NhanVien_BanHang() {
		super.setchucvu("Ban Hang");
	}
	
	@Override
	public float tinhLuong() {
		return (super.getLuongCB() * super.hesoXepLoai() * super.hesoCa()) + super.getluongThuong() + this.tinhThuong();
	}
	
	@Override
	public float tinhThuong() {
		return doanhSo * 0.05f;
	}
	
	@Override
	public void nhap(Scanner sc) {
		super.nhap(sc);
		try {
		System.out.println("Moi nhap doanh so: ");
		this.setdoanhSo(Float.parseFloat(sc.nextLine()));
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		}
	}
	
	@Override
	public void xuat() {
		super.xuat();
		System.out.println("Doanh so kiem duoc: " + this.getdoanhSo());
		System.out.printf("Luong: %.1f\n" ,this.tinhLuong());
	}
}
