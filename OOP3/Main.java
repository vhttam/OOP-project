package BaiTap;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // 1. Khởi tạo các sản phẩm (Sử dụng SanPham để tránh lỗi abstract)
        // Giá cơ bản (nhập kho) là 5000 và 25000
        SanPham sp1Nhap = new SanPham("TP001", "Bánh mì", "Bánh", 50, 5000.0);
        SanPham sp2Nhap = new SanPham("TP002", "Sữa tươi", "Nước", 100, 25000.0);

        // --- KIỂM TRA PHIẾU NHẬP KHO (Phần này chỉ để test tongGiaNhap) ---
        PhieuNhapKho phieuNhap = new PhieuNhapKho("PNK001", new Date());
        phieuNhap.themSanPham(sp1Nhap);
        phieuNhap.themSanPham(sp2Nhap);
        System.out.printf("Tổng giá nhập (50*5000 + 100*25000): %.2f VNĐ\n", phieuNhap.tongGiaNhap());

        System.out.println("\n==============================================\n");

        // 2. TẠO HÓA ĐƠN BÁN HÀNG
        // Khi tạo hóa đơn, chúng ta sử dụng lại các đối tượng sản phẩm NHẬP KHO 
        // để đảm bảo giá cơ bản được sử dụng đúng cho công thức giaBan().

        // SẢN PHẨM 1: Bánh mì (Loại "Bánh" -> Lợi nhuận 30%)
        // Giá cơ bản = 5000.0. Giá bán = 5000 * (1 + 0.3) = 6500.0
        // Số lượng bán: 10
        SanPham spHD1 = new SanPham("TP001", "Bánh mì", "Bánh", 10, 5000.0);

        // SẢN PHẨM 2: Sữa tươi (Loại "Nước" -> Lợi nhuận 20%)
        // Giá cơ bản = 25000.0. Giá bán = 25000 * (1 + 0.2) = 30000.0
        // Số lượng bán: 5
        SanPham spHD2 = new SanPham("TP002", "Sữa tươi", "Nước", 5, 25000.0);

        // SẢN PHẨM 3: Để test Sale > 5,000,000
        // Giá cơ bản = 50000.0. Giá bán = 50000 * (1 + 0.1) = 55000.0 (Giả định loại khác 10%)
        // Số lượng bán: 100
        SanPham spHD3 = new SanPham("TP003", "Bột giặt", "Do gia dung", 100, 50000.0);


        HoaDon hoaDon = new HoaDon("HD20251108");
        hoaDon.themSanPham(spHD1);
        hoaDon.themSanPham(spHD2);
        hoaDon.themSanPham(spHD3); // Thêm sản phẩm có giá trị lớn để kích hoạt Sale 10%

        // 3. IN RA CHI TIẾT HÓA ĐƠN
        hoaDon.chiTietHoaDon();

        /* * PHÉP TÍNH KIỂM TRA (TRƯỚC SALE):
         * spHD1 (Bánh mì): 6500.0 * 10 = 65,000.0
         * spHD2 (Sữa tươi): 30000.0 * 5 = 150,000.0
         * spHD3 (Bột giặt): 55000.0 * 100 = 5,500,000.0
         * TỔNG CỘNG TRƯỚC SALE: 5,715,000.0 VNĐ
         * * SALE: Vì Tổng > 5,000,000 -> Giảm 10%
         * Tiền giảm giá: 5,715,000.0 * 0.1 = 571,500.0 VNĐ
         * TỔNG THANH TOÁN: 5,715,000.0 - 571,500.0 = 5,143,500.0 VNĐ
         */
    }
}