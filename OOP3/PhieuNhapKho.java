package BaiTap;

// Kế thừa HangHoa: Chỉ quan tâm đến Giá Gốc
public class PhieuNhapKho extends HangHoa {

    public PhieuNhapKho(String maGiaoDich) {
        super(maGiaoDich);
    }

    // Hiện thực phương thức trừu tượng: Tính tổng tiền nhập (dựa trên Giá Gốc)
    @Override
    public double tinhTongGiaTri() {
        double tongGiaNhap = 0;
        for (SanPham sp : danhSachSanPham) {
            tongGiaNhap += sp.getGia() * sp.getSL();
        }
        return tongGiaNhap;
    }

    public void inChiTiet() {
        System.out.printf("--- Phiếu Nhập %s ---\n", getMaGiaoDich());
        System.out.printf("Tổng Giá Trị Nhập Kho: %.2f\n", tinhTongGiaTri());
    }
}