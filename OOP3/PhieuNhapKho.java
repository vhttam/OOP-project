package BaiTap;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapKho {
    // Thuộc tính (Attributes)
    private String MaNhap; // Mã Nhập: Import Code
    private Date NgayNhap; // Ngày Nhập: Import Date

    // Mối quan hệ với Sản Phẩm
    private List<SanPham> danhSachSanPham;

    // Constructor
    public PhieuNhapKho(String MaNhap, Date NgayNhap) {
        this.MaNhap = MaNhap;
        this.NgayNhap = NgayNhap;
        this.danhSachSanPham = new ArrayList<>();
    }

    // Thêm Sản Phẩm vào phiếu nhập
    public void themSanPham(SanPham sanPham) {
        this.danhSachSanPham.add(sanPham);
    }

    // Thao tác (Operation)
    // tongGiaNhap(): Tính tổng giá nhập của tất cả sản phẩm trong phiếu
    public double tongGiaNhap() {
        double tongGia = 0;
        for (SanPham sp : danhSachSanPham) {
            // Giả định tổng giá nhập là tổng (Giá * Số lượng) của từng sản phẩm
            tongGia += sp.getGia() * sp.getSL();
        }
        return tongGia;
    }

    // Getters and Setters (omitted for brevity, but should be included in a complete class)
    public String getMaNhap() {
        return MaNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }
}
