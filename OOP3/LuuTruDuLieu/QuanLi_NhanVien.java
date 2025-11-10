package OOP3.LuuTruDuLieu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import OOP3.Nguoi.Nguoi.NhanVien;
import OOP3.Nguoi.Nguoi.NhanVien_BanHang;
import OOP3.Nguoi.Nguoi.NhanVien_QuanLiKho;
import OOP3.Nguoi.Nguoi.NhanVien_ThuNgan;

public class QuanLi_NhanVien extends Luu_Tru_Du_Lieu {
	private List<NhanVien> dsNhanVien = new ArrayList<>();
	
	public List<NhanVien> getdsNhanVien(){
		return this.dsNhanVien;
	}
	
	@Override
	public void readTXT() {
		File txtNV = new File ("src/OOP3/LuuTruDuLieu/DSNhanVien.txt");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(txtNV))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}
				
				String data[] = line.split(",");
				String chucvu = data[0].trim();
				NhanVien nv = null;
				
				try {
					String id = data[1].trim();
					String hoten = data[2].trim();
					String gioitinh = data[3].trim();
					String namsinh = data[4].trim();
					int soNgayNghi = Integer.parseInt(data[5].trim());
					float luongThuong = Float.parseFloat(data[6].trim());
					String caLamViec = data[7].trim();
					if (chucvu.equalsIgnoreCase("Thu Ngan") && data.length == 9) {
						float doanhThu = Float.parseFloat(data[8].trim());
						nv = new NhanVien_ThuNgan(id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, doanhThu);
					}
					
					else if ((chucvu.equalsIgnoreCase("Quan li kho") || chucvu.equalsIgnoreCase("Quan ly kho")) && data.length == 9) {
						float phuCap = Float.parseFloat(data[8].trim());
						nv = new NhanVien_QuanLiKho(id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, phuCap);
					}
					else if (chucvu.equalsIgnoreCase("Ban Hang") && data.length == 9) {
						float doanhSo = Float.parseFloat(data[8].trim());
						nv = new NhanVien_BanHang(id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, doanhSo);
					}
					
					else {
						System.out.println("Bi loi dinh dang, bo qua" + line);
					}
					
					if (nv != null) {
						dsNhanVien.add(nv);
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
	
	public void xuatDSNV() {
		if (dsNhanVien.isEmpty()) {
			System.out.println("Danh sach nhan vien rong");
			return;
		}
		for (NhanVien nv : dsNhanVien) {
			nv.xuat();
			System.out.println("-----------------------------------------");
		}
	}
	
	@Override
	public void writeTXT() {
		File txtNV = new File ("src/OOP3/LuuTruDuLieu/DSNhanVien.txt");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtNV))) {
			for (NhanVien nv : dsNhanVien) {
				
				StringBuilder line = new StringBuilder();
				
				if (nv instanceof NhanVien_BanHang) {
					NhanVien_BanHang bh = (NhanVien_BanHang) nv;
					line.append("Ban Hang, ");
					line.append(bh.getid()).append(",");
					line.append(bh.gethoten()).append(",");
					line.append(bh.getgioitinh()).append(",");
					line.append(bh.getnamsinh()).append(",");
					line.append(bh.getsoNgayNghi()).append(",");
					line.append(bh.getluongThuong()).append(",");
					line.append(bh.getcaLamViec()).append(",");
					line.append(bh.getdoanhSo());
				}
				else if (nv instanceof NhanVien_ThuNgan) {
					NhanVien_ThuNgan tn = (NhanVien_ThuNgan) nv;
					line.append("Thu Ngan, ");
					line.append(tn.getid()).append(",");
					line.append(tn.gethoten()).append(",");
					line.append(tn.getgioitinh()).append(",");
					line.append(tn.getnamsinh()).append(",");
					line.append(tn.getsoNgayNghi()).append(",");
					line.append(tn.getluongThuong()).append(",");
					line.append(tn.getcaLamViec()).append(",");
					line.append(tn.getdoanhThu());
				}
				else if (nv instanceof NhanVien_QuanLiKho) {
					NhanVien_QuanLiKho qlk = (NhanVien_QuanLiKho) nv;
					line.append("Quan Li Kho, ");
					line.append(qlk.getid()).append(",");
					line.append(qlk.gethoten()).append(",");
					line.append(qlk.getgioitinh()).append(",");
					line.append(qlk.getnamsinh()).append(",");
					line.append(qlk.getsoNgayNghi()).append(",");
					line.append(qlk.getluongThuong()).append(",");
					line.append(qlk.getcaLamViec()).append(",");
					line.append(qlk.getphuCap());
				}
				
				if (line.length() > 0) {
					writer.write(line.toString());
					writer.newLine();
				}
			}
			System.out.println("Da ghi danh sach nhan vien vao file");
		}
		catch (IOException e) {
			System.err.println("Loi khi ghi file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
			
	public NhanVien timNhanVienTheoID(String id) {
		for (NhanVien nv : dsNhanVien) {
			if (nv.getid().equalsIgnoreCase(id)) {
				return nv;
			}
		}
		return null;
	}
			
	public void timNhanVien(Scanner sc) {
		System.out.println("Nhap id nhan vien can tim: ");
		String id = sc.nextLine().trim();
		NhanVien nv = timNhanVienTheoID(id);
		if (nv != null) {
			nv.xuat();
		}
		else {
			System.out.println("Khong tim thay nhan vien co id " + id );
		}
	}
	
	
	public void themNhanVien(Scanner sc) {
		
		int soluong;
		try {
			System.out.println("Moi nhap so luong nhan vien muon them: ");
			String input = sc.nextLine().trim();
			soluong = Integer.parseInt(input);
			
			if (soluong <= 0) {
				System.out.println("Loi so luong phai luon duong");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Loi");
			return;
		}
		for (int i = 0; i < soluong; i++) {
			int j = i+1;
			System.out.println("---Moi nhap nhan vien thu " + j + " ---");
		try {
		NhanVien nvm = null;
		
		System.out.println("Moi nhap chuc vu: ");
		String chucvu = sc.nextLine().trim();
		
		if (chucvu.equalsIgnoreCase("Ban Hang")) {
			nvm = new NhanVien_BanHang();
		}
		else if (chucvu.equalsIgnoreCase("Thu Ngan")) {
			nvm = new NhanVien_ThuNgan();
		}
		else if (chucvu.equalsIgnoreCase("Quan Li Kho") || chucvu.equalsIgnoreCase("Quan Ly Kho")) {
			nvm = new NhanVien_QuanLiKho();
		}
		else {
			System.out.println("Loi");
			return;
		}
		
		nvm.nhap(sc);
		
		if (nvm.getid() == null || nvm.getid().isEmpty()) {
			System.out.println("Loi id khong khong duoc de trong");
			return;
		}
		
		if (timNhanVienTheoID(nvm.getid()) != null) {
			System.out.println("Loi id da ton tai");
			return;
		}
		
		dsNhanVien.add(nvm);
		System.out.println("Them nhan vien moi thanh cong!");
		} catch (NumberFormatException e) {
			System.out.println("Loi du lieu");
		} catch (Exception e) {
			System.out.println("Loi du lieu");
			e.printStackTrace();
		}
		}
	}
	
	public void xoaNhanVien(Scanner sc) {
		System.out.println("Moi ban nhap id nhan vien can xoa: ");
		String id = sc.nextLine().trim();
		NhanVien nvCanXoa = timNhanVienTheoID(id);
		if (nvCanXoa == null) {
			System.out.println("Khong tim thay nhan vien");
			return;
		}
		else {
			nvCanXoa.xuat();
			System.out.println("Ban co chac muon xoa nhan vien khong, nhan Y de xoa:");
			String xacnhan = sc.nextLine().trim();
			if (xacnhan.equalsIgnoreCase("Y")) {
				dsNhanVien.remove(nvCanXoa);
				System.out.println("Da xoa nhan vien");
			}
			else {
				System.out.println("Da huy thao tac xoa");
			}
		}
	}
}

