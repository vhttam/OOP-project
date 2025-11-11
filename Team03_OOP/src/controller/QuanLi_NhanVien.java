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

import modal.NhanVien;
import modal.NhanVien_BanHang;
import modal.NhanVien_QuanLiKho;
import modal.NhanVien_ThuNgan;



public class QuanLi_NhanVien extends Luu_Tru_Du_Lieu {
	private List<NhanVien> dsNhanVien = new ArrayList<>();
	
	public List<NhanVien> getdsNhanVien(){
		return this.dsNhanVien;
	}
	
	@Override
	public void readTXT() {
		File txtNV = new File ("src/data/DSNhanVien.txt");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(txtNV))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}
				
				String data[] = line.split(",");
				NhanVien nv = null;
				
				try {
					String chucvu = data[0].trim();
					String id = data[1].trim();
					String hoten = data[2].trim();
					String gioitinh = data[3].trim();
					String namsinh = data[4].trim();
					int soNgayNghi = Integer.parseInt(data[5].trim());
					float luongThuong = Float.parseFloat(data[6].trim());
					String caLamViec = data[7].trim();
					if (chucvu.equalsIgnoreCase("Thu Ngan") && data.length == 9) {
						float doanhThu = Float.parseFloat(data[8].trim());
						nv = new NhanVien_ThuNgan(chucvu, id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, doanhThu);
					}
					
					else if ((chucvu.equalsIgnoreCase("Quan li kho") || chucvu.equalsIgnoreCase("Quan ly kho")) && data.length == 9) {
						float phuCap = Float.parseFloat(data[8].trim());
						nv = new NhanVien_QuanLiKho(chucvu, id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, phuCap);
					}
					else if (chucvu.equalsIgnoreCase("Ban Hang") && data.length == 9) {
						float doanhSo = Float.parseFloat(data[8].trim());
						nv = new NhanVien_BanHang(chucvu,id, hoten, gioitinh, namsinh, soNgayNghi, luongThuong, caLamViec, doanhSo);
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
		File txtNV = new File ("src/data/DSNhanVien.txt");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtNV))) {
			for (NhanVien nv : dsNhanVien) {
				
				StringBuilder line = new StringBuilder();
					line.append(nv.getchucvu()).append(",");
					line.append(nv.getid()).append(",");
					line.append(nv.gethoten()).append(",");
					line.append(nv.getgioitinh()).append(",");
					line.append(nv.getnamsinh()).append(",");
					line.append(nv.getsoNgayNghi()).append(",");
					line.append(nv.getluongThuong()).append(",");
					line.append(nv.getcaLamViec()).append(",");
					if (nv instanceof NhanVien_ThuNgan) {
						line.append(((NhanVien_ThuNgan) nv).getdoanhThu());
					}
					else if (nv instanceof NhanVien_QuanLiKho) {
						line.append(((NhanVien_QuanLiKho) nv).getphuCap());
					}
					else if (nv instanceof NhanVien_BanHang) {
						line.append(((NhanVien_BanHang) nv).getdoanhSo());
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
		System.out.println("Nhap id/ten nhan vien can tim: ");
		String timkiem = sc.nextLine().trim();
		if (timkiem.isEmpty()) {
			System.out.println("Loi du lieu nhap vao");
			return;
		}
		List<NhanVien> ketQua = new ArrayList<>();
		for (NhanVien nv : dsNhanVien) {
			boolean matchID = nv.getid().equalsIgnoreCase(timkiem);
			
			boolean matchName = nv.gethoten().toLowerCase().contains(timkiem.toLowerCase());
			
			if (matchID || matchName) {
				ketQua.add(nv);
			}
		}
			if (ketQua.isEmpty()) {
			System.out.println("Khong tim thay nhan vien nao phu hop voi tu khoa: '" + timkiem + "'");
		} else {
			System.out.println("Tim thay " + ketQua.size() + " ket qua phu hop:");
			System.out.println("-----------------------------------------");
			for (NhanVien nv : ketQua) {
				nv.xuat();
				System.out.println("-----------------------------------------");
			}
		}
	}
	
	private NhanVien taoNhanVienTheoChucVu(String chucvu) {
		if (chucvu.equalsIgnoreCase("Ban Hang")) {
			return new NhanVien_BanHang();
		}
		else if (chucvu.equalsIgnoreCase("Thu Ngan")) {
			return new NhanVien_ThuNgan();
		}
		else if (chucvu.equalsIgnoreCase("Quan Li Kho") || chucvu.equalsIgnoreCase("Quan Ly Kho")) {
			return new NhanVien_QuanLiKho();
		}
		else {
			return null;
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
			System.out.println("Loi: Phai nhap so luong la mot con so.");
			return;
		}
        
		for (int i = 0; i < soluong; i++) {
			int j = i+1;
			System.out.println("---Moi nhap nhan vien thu " + j + " ---");
            try {
                
                System.out.println("Moi nhap chuc vu (Ban Hang, Thu Ngan, Quan Li Kho): ");
                String chucvu = sc.nextLine().trim();
                
                NhanVien nvm = taoNhanVienTheoChucVu(chucvu);

                if (nvm == null) {
                    System.out.println("Loi: Chuc vu khong hop le. Vui long nhap lai.");
                    i--;
                }
                
                nvm.nhap(sc);
                
                if (nvm.getid() == null || nvm.getid().isEmpty()) {
                    System.out.println("Loi: ID khong khong duoc de trong. Vui long nhap lai.");
                    i--;
                    continue;
                }
                
                if (timNhanVienTheoID(nvm.getid()) != null) {
                    System.out.println("Loi: ID da ton tai. Vui long nhap lai.");
                    i--;
                    continue;
                }
                
                dsNhanVien.add(nvm);
                System.out.println("Them nhan vien moi thanh cong!");

            } catch (NumberFormatException e) {
                System.out.println("Loi dinh dang so. Vui long nhap lai cho nhan vien nay.");
                i--; 
            } catch (Exception e) {
                System.out.println("Loi khong xac dinh. Vui long nhap lai cho nhan vien nay.");
                e.printStackTrace();
                i--; 
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

	public void suaNhanVien(Scanner sc) {
		System.out.println("Moi nhap id nhan vien can sua: ");
		String idNVCanSua = sc.nextLine().trim();
		NhanVien nvCanSua = timNhanVienTheoID(idNVCanSua);

		if (nvCanSua == null) {
			System.out.println("Khong tim thay nhan vien voi id: " + idNVCanSua);
			return;
		}
			System.out.println("Thong tin nhan vien can sua: ");
			nvCanSua.xuat();
			System.out.println("-----------------------------------------");

			NhanVien nvMoi = null;
		try {
			if (nvCanSua instanceof NhanVien_BanHang) {
				nvMoi = new NhanVien_BanHang();
				System.out.println("Moi nhap thong tin moi (Chuc vu: Ban Hang):");
			} else if (nvCanSua instanceof NhanVien_ThuNgan) {
				nvMoi = new NhanVien_ThuNgan();
				System.out.println("Moi nhap thong tin moi (Chuc vu: Thu Ngan):");
			} else if (nvCanSua instanceof NhanVien_QuanLiKho) {
				nvMoi = new NhanVien_QuanLiKho();
				System.out.println("Moi nhap thong tin moi (Chuc vu: Quan Li Kho):");
			} else {
				System.out.println("Loi: Khong xac dinh duoc loai nhan vien.");
				return;
			}
			
			nvMoi.nhap(sc);
			
			if (nvMoi.getid() == null || nvMoi.getid().isEmpty()) {
				System.out.println("Loi: ID khong duoc de trong. Thao tac sua bi huy.");
				return;
			}
			
			if (!nvMoi.getid().equalsIgnoreCase(idNVCanSua)) {
				if (timNhanVienTheoID(nvMoi.getid()) != null) {
					System.out.println("Loi: ID moi (" + nvMoi.getid() + ") da ton tai. Thao tac sua bi huy.");
					return;
				}
				System.out.println("Luu y: ID nhan vien da duoc thay doi tu " + idNVCanSua + " thanh " + nvMoi.getid());
			}

			int index = dsNhanVien.indexOf(nvCanSua);
			if (index != -1) {
				dsNhanVien.set(index, nvMoi);
				System.out.println("Cap nhat thong tin nhan vien thanh cong!");
			} else {
				System.out.println("Loi: Khong tim thay vi tri nhan vien de cap nhat.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Loi dinh dang so. Thao tac sua bi huy.");
		} catch (Exception e) {
			System.out.println("Loi khong xac dinh: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

