package modal;

import java.util.Date; // Cần import lớp Date

// Kế thừa HangHoa: Chỉ quan tâm đến Giá Gốc
public class PhieuNhapKho extends HangHoa {

    // 1. Bổ sung thuộc tính riêng
    private Date NgayNhap;

    // 2. Cập nhật Constructor để nhận thêm NgayNhap
    public PhieuNhapKho(String maGiaoDich, Date NgayNhap) {
        super(maGiaoDich);
        this.NgayNhap = NgayNhap; // Khởi tạo thuộc tính mới
    }

    // Hiện thực phương thức trừu tượng: Tính tổng tiền nhập (dựa trên Giá Gốc)
    @Override
    public double tinhTongGiaTri() {
        double tongGiaNhap = 0;
        // Giả định: SanPham có getGia() là giá gốc và getSL() là số lượng
        for (SanPham sp : danhSachSanPham) {
            tongGiaNhap += sp.getGia() * sp.getSL();
        }
        return tongGiaNhap;
    }

    // 3. Phương thức Getters và Setters cho NgayNhap
    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    // 4. Cập nhật inChiTiet() để hiển thị NgayNhap
    public void inChiTiet() {
        System.out.printf("--- Phiếu Nhập %s ---\n", getMaGiaoDich());
        System.out.printf("Ngày Nhập: %s\n", this.NgayNhap.toString()); // Hiển thị ngày
        System.out.printf("Tổng Giá Trị Nhập Kho: %.2f\n", tinhTongGiaTri());
    }
}