package BaiTap;

import java.util.ArrayList;
import java.util.List;

// HoaDon hiện thực interface Sale
public class HoaDon implements Sale {

    // Thuộc tính
    private String MaHoaDon;
    private List<SanPham> danhSachSanPham;

    // Constructor
    public HoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
        this.danhSachSanPham = new ArrayList<>();
    }

    // Thêm Sản Phẩm vào hóa đơn
    public void themSanPham(SanPham sanPham) {
        this.danhSachSanPham.add(sanPham);
    }

    /**
     * Phương thức hiện thực từ interface Sale.
     * Giả định logic: giảm 10% nếu tổng tiền hóa đơn vượt quá 5,000,000 VNĐ.
     */
    @Override
    public double tinhTienSale() {
        double tongBan = 0;

        // 1. Tính tổng giá bán của hóa đơn trước khi giảm giá
        for (SanPham sp : danhSachSanPham) {
            // Sử dụng phương thức giaBan() và getSL() đã định nghĩa trước đó
            tongBan += sp.giaBan() * sp.getSL();
        }

        // 2. Áp dụng logic sale
        if (tongBan > 5000000) {
            return tongBan * 0.10; // Giảm 10%
        } else if (tongBan > 1000000) {
            return tongBan * 0.05; // Giảm 5%
        } else {
            return 0; // Không giảm giá
        }
    }

    // chiTietHoaDon(): Hiển thị thông tin chi tiết hóa đơn
    public void chiTietHoaDon() {
        System.out.println("--- Chi Tiết Hóa Đơn: " + this.MaHoaDon + " ---");

        double tongThanhTien = 0;
        double tongCongTruocSale = 0;

        if (danhSachSanPham.isEmpty()) {
            System.out.println("Hóa đơn không có sản phẩm nào.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s %-15s %-15s\n",
                "ID", "Tên", "Số lượng", "Giá Bán/SP", "Thành tiền");

        for (SanPham sp : danhSachSanPham) {
            double giaBanSP = sp.giaBan();
            double thanhTien = giaBanSP * sp.getSL();
            tongCongTruocSale += thanhTien; // Tổng tiền chưa giảm

            System.out.printf("%-10s %-20s %-10d %-15.2f %-15.2f\n",
                    sp.getID(), sp.getTen(), sp.getSL(), giaBanSP, thanhTien);
        }

        // Áp dụng giảm giá
        double tienGiamGia = tinhTienSale();
        double tongCongSauSale = tongCongTruocSale - tienGiamGia;

        System.out.println("----------------------------------------------");
        System.out.printf("Tổng tiền chưa giảm:     %.2f\n", tongCongTruocSale);
        System.out.printf("Số tiền giảm giá (Sale): %.2f\n", tienGiamGia);
        System.out.println("----------------------------------------------");
        System.out.printf("**Tổng cộng phải thanh toán: %.2f**\n", tongCongSauSale);
    }

    // Getters and Setters (Giữ nguyên)
    public String getMaHoaDon() {
        return MaHoaDon;
    }
}