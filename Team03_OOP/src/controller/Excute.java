package controller;

import java.util.List;
import java.util.Scanner;


import controller.Bao_Cao_Thong_Ke;
import controller.QuanLi_KhachHang;
import controller.QuanLi_NhanVien;

import modal.NhanVien;
import modal.KhachHang;

public class Excute {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			// Khoi tao cac doi tuong quan li
			QuanLi_NhanVien qlnv = new QuanLi_NhanVien();
			QuanLi_KhachHang qlkh = new QuanLi_KhachHang();
			// Khoi tao doi tuong thong ke (vi ham locNhanVien khong phai static)
			Bao_Cao_Thong_Ke bctk = new Bao_Cao_Thong_Ke();
			
			System.out.println("Dang tai du lieu tu file...");
			qlnv.readTXT();
			qlkh.readTXT();
			System.out.println("Tai du lieu thanh cong!");

			int luaChon = -1;
			
			do {
				System.out.println("\n--- MENU QUAN LY CUA HANG ---");
				System.out.println("--- Quan Li Nhan Vien ---");
				System.out.println("1.  Them Nhan Vien moi");
				System.out.println("2.  Sua thong tin Nhan Vien"); // Chuc nang moi
				System.out.println("3.  Xoa Nhan Vien");
				System.out.println("4.  Tim kiem Nhan Vien (theo ID/Ten)"); // Cap nhat mo ta
				System.out.println("5.  Xuat danh sach Nhan Vien");
				
				System.out.println("\n--- Quan Li Khach Hang ---");
				System.out.println("6.  Them Khach Hang moi");
				System.out.println("7.  Sua thong tin Khach Hang"); // Chuc nang moi
				System.out.println("8.  Xoa Khach Hang"); // Chuc nang moi
				System.out.println("9.  Tim kiem Khach Hang (theo ID/Ten)"); // Cap nhat mo ta
				System.out.println("10. Xuat danh sach Khach Hang");
				
				System.out.println("\n--- Bao Cao & Thong Ke ---");
				System.out.println("11. In Thong Ke Nhan Vien (So luong, Tong luong...)");
				System.out.println("12. In Thong Ke Khach Hang (So luong, Gioi tinh...)");
				System.out.println("13. Loc danh sach Nhan Vien theo chuc vu"); // Chuc nang moi
				
				System.out.println("---------------------------------");
				System.out.println("0.  Thoat va Luu Du Lieu");
				System.out.print("Moi ban chon chuc nang (0-13): ");
				
				try {
					luaChon = Integer.parseInt(sc.nextLine());
					
					switch (luaChon) {
						// --- QUAN LI NHAN VIEN ---
						case 1:
							System.out.println("--- 1. THEM NHAN VIEN ---");
							qlnv.themNhanVien(sc);
							break;
						case 2:
							System.out.println("--- 2. SUA NHAN VIEN ---");
							qlnv.suaNhanVien(sc);
							break;
						case 3:
							System.out.println("--- 3. XOA NHAN VIEN ---");
							qlnv.xoaNhanVien(sc);
							break;
						case 4:
							System.out.println("--- 4. TIM KIEM NHAN VIEN ---");
							qlnv.timNhanVien(sc);
							break;
						case 5:
							System.out.println("--- 5. DANH SACH NHAN VIEN ---");
							qlnv.xuatDSNV();
							break;
							
						// --- QUAN LI KHACH HANG ---
						case 6:
							System.out.println("--- 6. THEM KHACH HANG ---");
							qlkh.themKhachHang(sc);
							// Da xoa writeTXT() o day de luu tap trung tai Case 0
							break;
						case 7:
							System.out.println("--- 7. SUA KHACH HANG ---");
							qlkh.suaKhachHang(sc);
							break;
						case 8:
							System.out.println("--- 8. XOA KHACH HANG ---");
							qlkh.xoaKhachHang(sc);
							break;
						case 9:
							System.out.println("--- 9. TIM KIEM KHACH HANG ---");
							qlkh.timKhachHang(sc);
							break;
						case 10:
							System.out.println("--- 10. DANH SACH KHACH HANG ---");
							qlkh.xuatDSKH();
							break;
							
						// --- BAO CAO & THONG KE ---
						case 11:
							System.out.println("--- 11. THONG KE NHAN VIEN ---");
							List<NhanVien> dsNV = qlnv.getdsNhanVien();
							Bao_Cao_Thong_Ke.inThongKeNhanVien(dsNV);
							break;
						case 12:
							System.out.println("--- 12. THONG KE KHACH HANG ---");
							List<KhachHang> dsKH = qlkh.getdsKhachHang();
							Bao_Cao_Thong_Ke.inThongKeKhachHang(dsKH);
							break;
						case 13:
							System.out.println("--- 13. LOC NHAN VIEN THEO CHUC VU ---");
							// Goi ham locNhanVienTheoChucVu (khong phai static)
							bctk.locNhanVienTheoChucVu(qlnv.getdsNhanVien());
							break;

						// --- THOAT & LUU ---
						case 0:
							System.out.println("Dang thuc hien luu du lieu...");
							qlnv.writeTXT();
							qlkh.writeTXT(); // SUA LOI: Them luu file Khach Hang
							System.out.println("Da luu thanh cong. Tam biet!");
							break;
							
						default:
							System.out.println("Loi: Lua chon khong hop le. Vui long chon tu 0 den 13.");
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