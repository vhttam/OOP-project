package Nguoi;


public class KhachHang extends Nguoi {
	public KhachHang (String id, String hoten, String gioitinh, String namsinh) {
		super(id,hoten,gioitinh,namsinh);
	}
	
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
	
	public void nhap() {
		super.nhap();
	}
	
	public void xuat() {
		this.ktr_id();
		super.xuat();
	}
}
