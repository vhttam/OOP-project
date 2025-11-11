package modal;

import java.util.List;
import java.util.ArrayList; // Cần import để khởi tạo danh sách

public class Kho {
    // Thuộc tính
    private String maKho;
    private String tenKho;
    // Quan hệ lớp: Kho chứa nhiều SanPham
    private List<SanPham> danhSachSanPham;

    // --- Constructor (Hàm Thiết Lập) ---
    public Kho(String maKho, String tenKho) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        // Khởi tạo List để sẵn sàng chứa đối tượng
        this.danhSachSanPham = new ArrayList<>();
    }

    // --- Phương thức Thao tác (Ví dụ: Thêm Sản Phẩm) ---
    public void themSanPham(SanPham sanPham) {
        this.danhSachSanPham.add(sanPham);
    }

    // --- Getters và Setters ---

    // Getters
    public String getMaKho() {
        return maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public List<SanPham> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    // Setters
    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public void setDanhSachSanPham(List<SanPham> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }
}
