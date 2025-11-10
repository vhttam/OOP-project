package OOP3.Nguoi.Nguoi;

import java.util.ArrayList;
import java.util.List;

public class excute {

    public static void main(String[] args) {

        System.out.println("==========================================================");
        System.out.println("=== TEST 1: KIỂM TRA LOGIC VÀ TÍNH ĐA HÌNH (HARD-CODE) ===");
        System.out.println("==========================================================");

        List<NhanVien> dsNhanVien = new ArrayList<>();

        dsNhanVien.add(new NhanVien_BanHang("NV001", "Trần Văn Bán Hàng", "Nam", "1990", 
                                            2, 1000000, "Sang", 500000));

        dsNhanVien.add(new NhanVien_ThuNgan("NV002", "Lê Thị Thu Ngân", "Nu", "1995", 
                                            6, 700000, "Toi"));

        dsNhanVien.add(new NhanVien_QuanLiKho("NV003", "Phạm Văn Kho", "Nam", "1988", 
                                            9, 2000000, "Sang"));

        dsNhanVien.add(new NhanVien_BanHang("NV004", "Ngô Thị B", "Nu", "1992", 
                                            4, 300000, "Toi", 200000));

        dsNhanVien.add(new NhanVien_ThuNgan("SAI_ID_NV", "Nhân Viên Lỗi ID", "Khac", "2000", 
                                            1, 0, "Sang"));

        dsNhanVien.add(new NhanVien_QuanLiKho("NV005", "Nhân Viên Lỗi Ca", "Nam", "1999", 
                                            3, 100000, "Chieu"));

        System.out.println("\n---[ BẮT ĐẦU XUẤT THÔNG TIN NHÂN VIÊN ]---\n");
        for (NhanVien nv : dsNhanVien) {

            nv.ktr_caLamViec();
            
            nv.xuat(); 
            System.out.println("----------------------------------------");
        }


        System.out.println("\n---[ BẮT ĐẦU XUẤT THÔNG TIN KHÁCH HÀNG ]---\n");
        
        KhachHang kh1 = new KhachHang("KH001", "Khách Hàng A", "Nam", "1980");
        kh1.xuat(); 
        System.out.println("----------------------------------------");

        KhachHang kh2 = new KhachHang("SAI_ID_KH", "Khách Hàng B", "Nu", "1990");
        kh2.xuat();
        System.out.println("----------------------------------------");


        System.out.println("\n\n========================================================");
        System.out.println("=== TEST 2: KIỂM TRA HÀM NHAP() TỪ BÀN PHÍM ===");
        System.out.println("========================================================");

        NhanVien_BanHang nvTestNhap = new NhanVien_BanHang("tmp", "tmp", "tmp", "tmp", 0, 0, "tmp", 0);
        
        System.out.println("\n---[ MỜI NHẬP THÔNG TIN NHÂN VIÊN BÁN HÀNG ]---");
        nvTestNhap.nhap(); 
        
        System.out.println("\n---[ KẾT QUẢ CỦA NHÂN VIÊN VỪA NHẬP ]---");
        nvTestNhap.ktr_caLamViec(); 
        nvTestNhap.xuat();
        System.out.println("----------------------------------------");

        KhachHang khTestNhap = new KhachHang("tmp", "tmp", "tmp", "tmp");
        
        System.out.println("\n---[ MỜI NHẬP THÔNG TIN KHÁCH HÀNG ]---");
        khTestNhap.nhap();
        
        System.out.println("\n---[ KẾT QUẢ CỦA KHÁCH HÀNG VỪA NHẬP ]---");
        khTestNhap.xuat();
        System.out.println("----------------------------------------");

        System.out.println("\n---[ KẾT THÚC TEST ]---");
    }
}