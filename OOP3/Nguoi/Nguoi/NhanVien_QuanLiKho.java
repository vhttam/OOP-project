package Nguoi;

public class NhanVien_QuanLiKho extends NhanVien {
	
	public NhanVien_QuanLiKho (String id, String hoten, String gioitinh, String namsinh, int soNgayNghi, float luongThuong, String caLamViec) {
		super(id,hoten,gioitinh,namsinh,soNgayNghi,luongThuong,caLamViec);
	}
	
	@Override
	public float tinhLuong() {
		return (LuongCB * super.hesoXepLoai() * super.hesoCa()) + super.getluongThuong();
	}
	
	public void nhap() {
		super.nhap();
	}
	
	public void xuat() {
		super.xuat();
		System.out.println("Luong: " + this.tinhLuong());
	}
}
