package OOP3.Nguoi.Nguoi;

import java.util.Scanner;

public class NhanVien_ThuNgan extends NhanVien implements TinhThuongDoanhThu {
	private float doanhThu;
	
	public float getdoanhThu() {
		return doanhThu;
	}
	public void setdoanhThu(float doanhThu) {
		this.doanhThu = doanhThu;
	}
	
	public NhanVien_ThuNgan(String id, String hoten, String gioitinh, String namsinh,int soNgayNghi, float luongThuong, String caLamViec, float doanhThu) {
		super(id,hoten,gioitinh,namsinh,soNgayNghi,luongThuong,caLamViec);
		this.doanhThu = doanhThu;
	}
	public NhanVien_ThuNgan() {
	}
	
	@Override
	public float tinhLuong() {
		return (LuongCB * super.hesoXepLoai() * super.hesoCa()) + super.getluongThuong() + tinhThuong();
	}
	
	@Override
	public float tinhThuong() {
		return this.getdoanhThu() * 0.02f;
	}
	
	@Override
	public void nhap(Scanner sc) {
		super.nhap(sc);
		try {
			System.out.println("Moi nhap doanh thu: ");
			this.setdoanhThu(Float.parseFloat(sc.nextLine()));
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		}
	}
	
	@Override
	public void xuat() {
		super.xuat();
		System.out.printf("Luong: %.1f\n", this.tinhLuong());
	}
}
