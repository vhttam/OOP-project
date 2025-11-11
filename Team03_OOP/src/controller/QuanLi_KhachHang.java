package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import modal.KhachHang;

public class QuanLi_KhachHang extends Luu_Tru_Du_Lieu {
	private List<KhachHang> dsKhachHang = new ArrayList<>();
	
	
	public List<KhachHang> getdsKhachHang(){
		return this.dsKhachHang;
	}
	@Override
	public void readTXT() {
		File txtNV = new File ("src/data/DSKhachHang.txt");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(txtNV))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}
				
				String data[] = line.split(",");
				KhachHang kh = null;
				
				try {
					if (data.length == 4) {
					String id = data[0].trim();
					String hoten = data[1].trim();
					String gioitinh = data[2].trim();
					String namsinh = data[3].trim();
					kh = new KhachHang(id, hoten, gioitinh, namsinh);
					}
					else {
						System.out.println("Bi loi dinh dang, bo qua" + line);
					}
					
					if (kh != null) {
						dsKhachHang.add(kh);
					}
				}	catch (NumberFormatException e) {
					System.out.println("Loi dinh dang, bo qua: " + line);
			}	catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Dong thieu du lieu, bo qua: " + line);
				}
			}
		}	catch (IOException e) {
	            System.err.println("Loi khi doc file: " + e.getMessage());
	            e.printStackTrace();
	        }
		}
	
	public void xuatDSKH() {
		if (dsKhachHang.isEmpty()) {
			System.out.println("Danh sach khach hang rong");
			return;
		}
		for (KhachHang kh : dsKhachHang) {
			kh.xuat();
			System.out.println("-----------------------------------------");
		}
	}
	
	@Override
	public void writeTXT() {
		File txtNV = new File ("src/data/DSKhachHang.txt");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtNV))) {
			for (KhachHang kh : dsKhachHang) {
				
				StringBuilder line = new StringBuilder();
				
					line.append(kh.getid()).append(",");
					line.append(kh.gethoten()).append(",");
					line.append(kh.getgioitinh()).append(",");
					line.append(kh.getnamsinh());
				if (line.length() > 0) {
					writer.write(line.toString());
					writer.newLine();
				}
			}
			System.out.println("Da ghi danh sach khach hang vao file");
		}
		catch (IOException e) {
			System.err.println("Loi khi ghi file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public KhachHang timKhachHangTheoID(String id) {
		for (KhachHang kh : dsKhachHang) {
			if (kh.getid().equalsIgnoreCase(id)) {
				return kh;
			}
		}
		return null;
	}
			
	public void timKhachHang(Scanner sc) {
		System.out.println("Nhap id/ten khach hang can tim: ");
		String timkiem = sc.nextLine().trim();
		if (timkiem.isEmpty()) {
			System.out.println("Loi du lieu nhap vao");
			return;
		}
		List<KhachHang> ketQua = new ArrayList<>();
		for (KhachHang kh : dsKhachHang) {
			boolean matchID = kh.getid().equalsIgnoreCase(timkiem);
			
			boolean matchName = kh.gethoten().toLowerCase().contains(timkiem.toLowerCase());
			
			if (matchID || matchName) {
				ketQua.add(kh);
			}
		}
			if (ketQua.isEmpty()) {
			System.out.println("Khong tim thay khach hang nao phu hop voi tu khoa: '" + timkiem + "'");
		} else {
			System.out.println("Tim thay " + ketQua.size() + " ket qua phu hop:");
			System.out.println("-----------------------------------------");
			for (KhachHang kh : ketQua) {
				kh.xuat();
				System.out.println("-----------------------------------------");
			}
		}
	}
	
	
	public void themKhachHang(Scanner sc) {
		try {
		KhachHang khm = new KhachHang();
		
		System.out.println("Moi ban nhap thong tin khach hang moi: ");
		khm.nhap(sc);
		
		if (khm.getid() == null || khm.getid().isEmpty()) {
			System.out.println("Loi id khong khong duoc de trong");
			return;
		}
		
		if (timKhachHangTheoID(khm.getid()) != null) {
			System.out.println("Loi id da ton tai");
			return;
		}
		
		dsKhachHang.add(khm);
		System.out.println("Them khach hang moi thanh cong!");
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		} catch (Exception e) {
			System.out.println("Loi du lieu");
			e.printStackTrace();
		}
	}
	
	public void suaKhachHang(Scanner sc) {
		System.out.println("Moi ban nhap id khach hang can sua: ");
		String idCanSua = sc.nextLine().trim();
		KhachHang khCanSua = timKhachHangTheoID(idCanSua);

		if (khCanSua == null) {
			System.out.println("Khong tim thay khach hang voi id: " + idCanSua);
			return;
		}

		System.out.println("Tim thay! Thong tin hien tai cua khach hang:");
		khCanSua.xuat();
		System.out.println("---------------------------------");
		
		KhachHang khMoi = null;
		try {
			khMoi = new KhachHang();
			System.out.println("Moi nhap thong tin moi cho khach hang:");
			
			khMoi.nhap(sc); 
			
			if (khMoi.getid() == null || khMoi.getid().isEmpty()) {
				System.out.println("Loi: ID khong duoc de trong. Thao tac sua bi huy.");
				return;
			}
			
			if (!khMoi.getid().equalsIgnoreCase(idCanSua)) {
				if (timKhachHangTheoID(khMoi.getid()) != null) {
					System.out.println("Loi: ID moi (" + khMoi.getid() + ") da ton tai. Thao tac sua bi huy.");
					return;
				}
				System.out.println("Luu y: ID khach hang da duoc thay doi tu " + idCanSua + " thanh " + khMoi.getid());
			}
			
			int index = dsKhachHang.indexOf(khCanSua);
			if (index != -1) {
				dsKhachHang.set(index, khMoi);
				System.out.println("Cap nhat thong tin khach hang thanh cong!");
			} else {
				System.out.println("Loi: Khong tim thay vi tri khach hang de cap nhat.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Loi dinh dang so. Thao tac sua bi huy.");
		} catch (Exception e) {
			System.out.println("Loi khong xac dinh: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void xoaKhachHang(Scanner sc) {
		System.out.println("Moi ban nhap id khach hang can xoa: ");
		String id = sc.nextLine().trim();
		KhachHang khCanXoa = timKhachHangTheoID(id);
		if (khCanXoa == null) {
			System.out.println("Khong tim thay khach hang voi id: " + id);
			return;
		}
		else {
            System.out.println("Tim thay khach hang:");
			khCanXoa.xuat();
			System.out.println("---------------------------------");
			System.out.println("Ban co chac muon xoa khach hang nay khong? (Nhan 'Y' de xac nhan xoa):");
			String xacnhan = sc.nextLine().trim();
			
			if (xacnhan.equalsIgnoreCase("Y")) {
				dsKhachHang.remove(khCanXoa);
				System.out.println("Da xoa khach hang thanh cong!");
			}
			else {
				System.out.println("Da huy thao tac xoa.");
			}
		}
	}
}
