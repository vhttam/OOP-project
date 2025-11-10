package BaiTap;

import java.util.List;

// Kế thừa HangHoa VÀ Hiện thực GiamGia
public class HoaDon extends HangHoa implements Sale {

    public HoaDon(String maGiaoDich) {
        super(maGiaoDich);
    }

    // Hiện thực phương thức trừu tượng từ HangHoa: Tính tổng tiền bán (dựa trên Giá Bán)
    @Override
    public double tinhTongGiaTri() {
        double tongGiaBan = 0;
        for (SanPham sp : danhSachSanPham) {
            tongGiaBan += sp.giaBan() * sp.getSL();
        }
        return tongGiaBan;
    }

    // HIỆN THỰC HÀM TỪ INTERFACE GiamGia: Logic giảm giá
    @Override
    public double tinhTienSale() {
        double tongTruocGiam = tinhTongGiaTri();

        // Logic sale: Giảm 10% nếu tổng tiền bán vượt quá 5,000,000 VNĐ
        if (tongTruocGiam > 5000000) {
            return tongTruocGiam * 0.10;
        } else {
            return 0;
        }
    }

    public void inChiTiet() {
        double tongTruocGiam = tinhTongGiaTri();
        double tienGiam = tinhTienSale();
        double tongPhaiThanhToan = tongTruocGiam - tienGiam;

        System.out.printf("--- Hóa Đơn %s ---\n", getMaGiaoDich());
        System.out.printf("Tổng tiền trước giảm: %.2f\n", tongTruocGiam);
        System.out.printf("Số tiền giảm giá: %.2f\n", tienGiam);
        System.out.printf("**TỔNG THANH TOÁN: %.2f**\n", tongPhaiThanhToan);
    }


}