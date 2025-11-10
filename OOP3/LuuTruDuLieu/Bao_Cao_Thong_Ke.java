package OOP3.LuuTruDuLieu;

import java.util.List;
import OOP3.Nguoi.Nguoi.KhachHang;
import OOP3.Nguoi.Nguoi.NhanVien;
import OOP3.Nguoi.Nguoi.NhanVien_BanHang;
import OOP3.Nguoi.Nguoi.NhanVien_QuanLiKho;
import OOP3.Nguoi.Nguoi.NhanVien_ThuNgan;

public class Bao_Cao_Thong_Ke {
	public Bao_Cao_Thong_Ke() {
	}
	public static void inThongKeNhanVien(List<NhanVien> dsNhanVien) {
		if (dsNhanVien == null || dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien rong, khong co gi de thong ke.");
			return;
		}

		int tongSo = dsNhanVien.size();
		int soThuNgan = 0;
		int soBanHang = 0;
		int soQuanLiKho = 0;
		
		float tongLuongPhaiTraThuNgan = 0;
		float tongLuongPhaiTraBanHang = 0;
		float tongLuongPhaiTraQuanLiKho = 0;
		float tongLuongPhaiTra = 0;
		float tongDoanhSoBanHang = 0;
		float tongDoanhThuThuNgan = 0;
		
		for (NhanVien nv : dsNhanVien) {
			if (nv instanceof NhanVien_ThuNgan) {
				soThuNgan++;
				tongDoanhThuThuNgan += ((NhanVien_ThuNgan) nv).getdoanhThu();
				tongLuongPhaiTraThuNgan += ((NhanVien_ThuNgan) nv).tinhLuong();
			} else if (nv instanceof NhanVien_BanHang) {
				soBanHang++;
				tongDoanhSoBanHang += ((NhanVien_BanHang) nv).getdoanhSo();
				tongLuongPhaiTraBanHang += ((NhanVien_BanHang) nv).tinhLuong();
			} else if (nv instanceof NhanVien_QuanLiKho) {
				soQuanLiKho++;
				tongLuongPhaiTraQuanLiKho += ((NhanVien_QuanLiKho) nv).tinhLuong();
			}
		}
		tongLuongPhaiTra = tongLuongPhaiTraThuNgan + tongLuongPhaiTraBanHang + tongLuongPhaiTraQuanLiKho;
		
		System.out.println("\n--- THONG KE NHAN VIEN ---");
		System.out.println("Tong so luong nhan vien: " + tongSo);
		System.out.println("  - So luong Thu Ngan: \t" + soThuNgan);
		System.out.println("  - So luong Ban Hang: \t" + soBanHang);
		System.out.println("  - So luong Quan Li Kho: " + soQuanLiKho);
		System.out.println("---------------------------------");
		System.out.printf("Tong quy luong phai tra: \t%,.0f VND\n", tongLuongPhaiTra);
		System.out.printf("Tong doanh so (tu NV Ban Hang): \t%,.0f VND\n", tongDoanhSoBanHang);
		System.out.printf("Tong doanh thu (tu NV Thu Ngan): %,.0f VND\n", tongDoanhThuThuNgan);
		System.out.println("---------------------------------");
	}
	
	public static void inThongKeKhachHang(List<KhachHang> dsKhachHang) {
		if (dsKhachHang == null || dsKhachHang.isEmpty()) {
			System.out.println("Danh sach khach hang rong, khong co gi de thong ke.");
			return;
		}
		
		int tongSo = dsKhachHang.size();
		
		System.out.println("\n--- THONG KE KHACH HANG ---");
		System.out.println("Tong so luong khach hang da dang ky: " + tongSo);
		System.out.println("---------------------------------");
		
		int soNam = 0;
		int soNu = 0;
		int khongXacDinh = 0;
		
		for(KhachHang kh : dsKhachHang) {
			if(kh.getgioitinh().equalsIgnoreCase("Nam")) {
				soNam++;
			} else if(kh.getgioitinh().equalsIgnoreCase("Nu")) {
				soNu++;
			} else {
				khongXacDinh++; 
			}
		}
		
		System.out.println("Phan bo theo gioi tinh:");
		System.out.println("  - So luong khach Nam: " + soNam);
		System.out.println("  - So luong khach Nu: " + soNu);
		if (khongXacDinh > 0) {
			System.out.println("  - Khong xac dinh: " + khongXacDinh);
		}
		System.out.println("---------------------------------");
	}
}
