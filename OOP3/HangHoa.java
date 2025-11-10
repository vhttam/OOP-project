package BaiTap;

import java.util.ArrayList;
import java.util.List;

// Abstract Class: Đại diện cho bản chất giao dịch
public abstract class HangHoa {
    protected String MaGiaoDich;
    protected List<SanPham> danhSachSanPham;

    public HangHoa(String maGiaoDich) {
        this.MaGiaoDich = maGiaoDich;
        this.danhSachSanPham = new ArrayList<>();
    }

    // Phương thức chung
    public void themSanPham(SanPham sanPham) {
        this.danhSachSanPham.add(sanPham);
    }

    // Phương thức TRỪU TƯỢNG (Bắt buộc): Tính tổng giá trị của giao dịch
    public abstract double tinhTongGiaTri();

    public String getMaGiaoDich() {
        return MaGiaoDich;
    }

    public List<SanPham> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        MaGiaoDich = maGiaoDich;
    }

    public void setDanhSachSanPham(List<SanPham> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }
}