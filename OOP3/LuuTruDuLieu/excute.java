package OOP3.LuuTruDuLieu;

import java.util.List;
import java.util.Scanner;

import OOP3.LuuTruDuLieu.QuanLi_NhanVien;
import OOP3.LuuTruDuLieu.QuanLi_KhachHang;
import OOP3.LuuTruDuLieu.Bao_Cao_Thong_Ke;

import OOP3.Nguoi.Nguoi.NhanVien;
import OOP3.Nguoi.Nguoi.KhachHang;

public class excute {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			QuanLi_NhanVien qlnv = new QuanLi_NhanVien();
			QuanLi_KhachHang qlkh = new QuanLi_KhachHang();
			
			System.out.println("Dang tai du lieu tu file...");
			qlnv.readTXT();
			qlkh.readTXT();
			System.out.println("Tai du lieu thanh cong!");

			int luaChon = -1;
			
			do {
				System.out.println("\n--- MENU QUAN LY CUA HANG ---");
				System.out.println("--- Quan Li Nhan Vien ---");
				System.out.println("1. Them Nhan Vien moi");
				System.out.println("2. Xoa Nhan Vien");
				System.out.println("3. Tim kiem Nhan Vien (theo ID)");
				System.out.println("4. Xuat danh sach Nhan Vien");
				System.out.println("--- Quan Li Khach Hang ---");
				System.out.println("5. Them Khach Hang moi");
				System.out.println("6. Tim kiem Khach Hang (theo ID)");
				System.out.println("7. Xuat danh sach Khach Hang");
				System.out.println("--- Bao Cao & Thong Ke ---");
				System.out.println("8. In Thong Ke Nhan Vien");
				System.out.println("9. In Thong Ke Khach Hang");
				System.out.println("---------------------------------");
				System.out.println("0. Thoat va Luu Du Lieu");
				System.out.print("Moi ban chon chuc nang: ");
				
				try {
					luaChon = Integer.parseInt(sc.nextLine());
					
					switch (luaChon) {
						case 1:
							System.out.println("--- 1. THEM NHAN VIEN ---");
							qlnv.themNhanVien(sc);
							break;
						case 2:
							System.out.println("--- 2. XOA NHAN VIEN ---");
							qlnv.xoaNhanVien(sc);
							break;
						case 3:
							System.out.println("--- 3. TIM KIEM NHAN VIEN ---");
							qlnv.timNhanVien(sc);
							break;
						case 4:
							System.out.println("--- 4. DANH SACH NHAN VIEN ---");
							qlnv.xuatDSNV();
							break;
							
						case 5:
							System.out.println("--- 5. THEM KHACH HANG ---");
							qlkh.themKhachHang(sc);
							qlkh.writeTXT();
							break;
						case 6:
							System.out.println("--- 6. TIM KIEM KHACH HANG ---");
							qlkh.timKhachHang(sc);
							break;
						case 7:
							System.out.println("--- 7. DANH SACH KHACH HANG ---");
							qlkh.xuatDSKH();
							break;
							
						case 8:
							System.out.println("--- 8. THONG KE NHAN VIEN ---");
							List<NhanVien> dsNV = qlnv.getdsNhanVien();

							Bao_Cao_Thong_Ke.inThongKeNhanVien(dsNV);
							break;
						case 9:
							System.out.println("--- 9. THONG KE KHACH HANG ---");
							List<KhachHang> dsKH = qlkh.getdsKhachHang();
							Bao_Cao_Thong_Ke.inThongKeKhachHang(dsKH);
							break;
							
						case 0:
							System.out.println("Dang thuc hien luu du lieu...");
							qlnv.writeTXT();
							System.out.println("Da luu thanh cong. Tam biet!");
							break;
							
						default:
							System.out.println("Loi: Lua chon khong hop le. Vui long chon tu 0 den 9.");
					}
					
				} catch (NumberFormatException e) {
					System.out.println("Loi: Ban phai nhap mot con so. Vui long thu lai.");
					luaChon = -1;
				} catch (Exception e) {
					System.out.println("Da co loi khong xac dinh xay ra: " + e.getMessage());
					e.printStackTrace();
				}

			} while (luaChon != 0);
			
			sc.close();
		}
}
