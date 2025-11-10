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

import OOP3.Nguoi.Nguoi.KhachHang;

public class QuanLi_KhachHang extends Luu_Tru_Du_Lieu {
	private List<KhachHang> dsKhachHang = new ArrayList<>();
	
	
	public List<KhachHang> getdsKhachHang(){
		return this.dsKhachHang;
	}
	@Override
	public void readTXT() {
		File txtNV = new File ("src/OOP3/LuuTruDuLieu/DSKhachHang.txt");
		
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
		File txtNV = new File ("../DSKhachHang.txt");
		
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
		System.out.println("Nhap id khach hang can tim: ");
		String id = sc.nextLine().trim();
		KhachHang kh = timKhachHangTheoID(id);
		if (kh != null) {
			kh.xuat();
		}
		else {
			System.out.println("Khong tim thay khach hang co id " + id );
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
	
}
