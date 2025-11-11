package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modal.HangHoa;
import modal.SanPham;
import modal.PhieuNhapKho;
import modal.HoaDon;

public class QuanLyGiaoDich {
    private List<HangHoa> danhSachGiaoDich = new ArrayList<>();
    // Dùng SimpleDateFormat để xử lý ngày tháng
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public QuanLyGiaoDich() {
        // Khởi tạo (tương đương với nhập n phần tử ban đầu)
        nhapDanhSachBanDau();
    }

    // --- PHẦN HỖ TRỢ: Tìm kiếm Chính xác ---
    public HangHoa timGiaoDichTheoMa(String ma) {
        for (HangHoa hh : danhSachGiaoDich) {
            if (hh.getMaGiaoDich().equalsIgnoreCase(ma)) {
                return hh;
            }
        }
        return null;
    }

    // --- 1. NHẬP N PHẦN TỬ BAN ĐẦU (Data mẫu) ---
    public void nhapDanhSachBanDau() {
        System.out.println("--- Khoi tao du lieu giao dich mau ---");

        // 1. Tạo Sản phẩm mẫu
        SanPham sp1 = new SanPham("SP001", "Bánh Quy", "Banh", 100, 15000);
        SanPham sp2 = new SanPham("SP002", "Coca Cola", "Nuoc", 200, 8000);
        SanPham sp3 = new SanPham("SP003", "Bàn chải", "Do gia dung", 50, 25000);

        // 2. Tạo Phiếu Nhập Kho (PhieuNhapKho)
        PhieuNhapKho pnk1 = new PhieuNhapKho("PNK001", new Date());
        pnk1.themSanPham(sp1); // Thêm 100 Bánh (Giá gốc 15,000)
        pnk1.themSanPham(sp3); // Thêm 50 Đồ gia dụng (Giá gốc 25,000)
        danhSachGiaoDich.add(pnk1);

        // 3. Tạo Hóa Đơn (HoaDon) - Giao dịch lớn
        HoaDon hd1 = new HoaDon("HD001");
        // Giả lập giao dịch lớn để kích hoạt sale (100 * 15000 * 1.3 = 1,950,000)
        // Cần tổng giá trị > 5,000,000
        hd1.themSanPham(new SanPham("SP004", "TV Samsung", "Dien tu", 2, 3000000));
        // 2 TV * (3M * 1.1) = 6,600,000 VNĐ -> Có giảm giá
        danhSachGiaoDich.add(hd1);

        // 4. Tạo Hóa Đơn (HoaDon) - Giao dịch nhỏ
        HoaDon hd2 = new HoaDon("HD002");
        hd2.themSanPham(sp2); // 200 Coca (Giá bán 8000 * 1.2 = 9600) -> 1,920,000 VNĐ
        danhSachGiaoDich.add(hd2);
    }

    // --- 2. XUẤT DANH SÁCH (Sử dụng Đa hình) ---
    public void xuatDanhSach() {
        if (danhSachGiaoDich.isEmpty()) {
            System.out.println("Danh sach giao dich rong.");
            return;
        }
        System.out.println("\n--- DANH SÁCH TOÀN BỘ GIAO DỊCH ---");
        for (HangHoa hh : danhSachGiaoDich) {
            // Giả định inChiTiet() tồn tại trong HangHoa (hoặc các lớp con)
            // HoaDon và PhieuNhapKho đã hiện thực inChiTiet() trong file code
            if (hh instanceof HoaDon) {
                ((HoaDon) hh).inChiTiet();
            } else if (hh instanceof PhieuNhapKho) {
                ((PhieuNhapKho) hh).inChiTiet();
            }
            System.out.println("-----------------------------------");
        }
    }

    // --- 3. THÊM GIAO DỊCH ---
    public void themGiaoDich(Scanner sc) {
        System.out.println("Nhap loai giao dich (HD / PNK): ");
        String loai = sc.nextLine().trim();

        System.out.println("Nhap ma giao dich moi: ");
        String ma = sc.nextLine().trim();

        if (timGiaoDichTheoMa(ma) != null) {
            System.out.println("Loi: Ma giao dich da ton tai.");
            return;
        }

        HangHoa giaoDichMoi = null;
        if (loai.equalsIgnoreCase("HD")) {
            giaoDichMoi = new HoaDon(ma);
        } else if (loai.equalsIgnoreCase("PNK")) {
            try {
                System.out.print("Nhap ngay nhap (dd/MM/yyyy): ");
                Date ngayNhap = dateFormat.parse(sc.nextLine().trim());
                giaoDichMoi = new PhieuNhapKho(ma, ngayNhap);
            } catch (ParseException e) {
                System.out.println("Loi: Dinh dang ngay khong hop le.");
                return;
            }
        } else {
            System.out.println("Loai giao dich khong hop le.");
            return;
        }

        // Bổ sung thêm sản phẩm mẫu
        SanPham spMoi = new SanPham("SP_T", "SP Test", "Khac", 10, 50000);
        giaoDichMoi.themSanPham(spMoi);

        danhSachGiaoDich.add(giaoDichMoi);
        System.out.println("Them giao dich " + ma + " thanh cong!");

        if (giaoDichMoi instanceof HoaDon) {
            ((HoaDon) giaoDichMoi).inChiTiet();
        } else if (giaoDichMoi instanceof PhieuNhapKho) {
            ((PhieuNhapKho) giaoDichMoi).inChiTiet();
        }
    }

    // --- 4. SỬA THEO MÃ ---
    public void suaGiaoDich(Scanner sc) {
        System.out.println("Nhap ma giao dich can sua: ");
        String ma = sc.nextLine().trim();
        HangHoa hhCanSua = timGiaoDichTheoMa(ma);

        if (hhCanSua == null) {
            System.out.println("Khong tim thay giao dich co ma " + ma);
            return;
        }

        System.out.println("--- Thong tin hien tai ---");
        if (hhCanSua instanceof HoaDon) {
            ((HoaDon) hhCanSua).inChiTiet();
        } else if (hhCanSua instanceof PhieuNhapKho) {
            ((PhieuNhapKho) hhCanSua).inChiTiet();
        }
        System.out.println("--------------------------");

        // Chỉ cho phép sửa NgayNhap cho PhieuNhapKho
        if (hhCanSua instanceof PhieuNhapKho) {
            PhieuNhapKho pnk = (PhieuNhapKho) hhCanSua;
            System.out.printf("Ngay Nhap hien tai: %s. Nhap ngay moi (dd/MM/yyyy) (de trong de giu nguyen): ", dateFormat.format(pnk.getNgayNhap()));
            String ngayMoiStr = sc.nextLine().trim();
            if (!ngayMoiStr.isEmpty()) {
                try {
                    pnk.setNgayNhap(dateFormat.parse(ngayMoiStr));
                    System.out.println("Cap nhat Ngay Nhap thanh cong!");
                } catch (ParseException e) {
                    System.out.println("Loi: Dinh dang ngay khong hop le, giu nguyen.");
                }
            }
        } else {
            System.out.println("Giao dich nay khong ho tro sua thong tin co ban.");
        }
    }

    // --- 5. XÓA THEO MÃ ---
    public void xoaGiaoDich(Scanner sc) {
        System.out.println("Nhap ma giao dich can xoa: ");
        String ma = sc.nextLine().trim();
        HangHoa hhCanXoa = timGiaoDichTheoMa(ma);

        if (hhCanXoa == null) {
            System.out.println("Khong tim thay giao dich co ma " + ma);
            return;
        }

        System.out.println("Ban co chac chan muon xoa giao dich nay? (Y/N)");
        String xacNhan = sc.nextLine().trim();

        if (xacNhan.equalsIgnoreCase("Y")) {
            danhSachGiaoDich.remove(hhCanXoa);
            System.out.println("Da xoa giao dich " + ma + ".");
        } else {
            System.out.println("Da huy thao tac xoa.");
        }
    }

    // --- 6. TÌM KIẾM (Gần đúng/Gần đúng) ---
    public void timKiemGiaoDich(Scanner sc) {
        System.out.println("Nhap tu khoa ma giao dich can tim: ");
        String tuKhoa = sc.nextLine().trim().toLowerCase();

        System.out.println("\n--- KET QUA TIM KIEM ---");
        int count = 0;
        for (HangHoa hh : danhSachGiaoDich) {
            // Tìm kiếm gần đúng theo MaGiaoDich
            if (hh.getMaGiaoDich().toLowerCase().contains(tuKhoa)) {
                if (hh instanceof HoaDon) {
                    ((HoaDon) hh).inChiTiet();
                } else if (hh instanceof PhieuNhapKho) {
                    ((PhieuNhapKho) hh).inChiTiet();
                }
                System.out.println("-----------------------------------");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Khong tim thay giao dich nao voi tu khoa '" + tuKhoa + "'.");
        }
    }

    // --- 7. THỐNG KÊ (Sử dụng Đa hình) ---
    public void thongKeTongHop() {
        double tongDoanhThuTruocGiam = 0;
        double tongTienSale = 0;
        double tongGiaTriNhapKho = 0;
        int soHoaDon = 0;
        int soPhieuNhap = 0;

        for (HangHoa hh : danhSachGiaoDich) {
            // Gọi phương thức đa hình tinhTongGiaTri()
            if (hh instanceof HoaDon) {
                HoaDon hd = (HoaDon) hh;
                tongDoanhThuTruocGiam += hd.tinhTongGiaTri();
                tongTienSale += hd.tinhTienSale(); // Gọi hàm từ Interface Sale
                soHoaDon++;
            } else if (hh instanceof PhieuNhapKho) {
                tongGiaTriNhapKho += hh.tinhTongGiaTri();
                soPhieuNhap++;
            }
        }

        double tongDoanhThuSauGiam = tongDoanhThuTruocGiam - tongTienSale;

        System.out.println("\n--- BÁO CÁO THỐNG KÊ GIAO DỊCH ---");
        System.out.printf("1. Tổng Giá Trị Nhập Kho: %.2f VND (%d phiếu)\n", tongGiaTriNhapKho, soPhieuNhap);
        System.out.printf("2. Tổng Doanh Thu (trước giảm): %.2f VND (%d hóa đơn)\n", tongDoanhThuTruocGiam, soHoaDon);
        System.out.printf("3. Tổng Tiền Giảm Giá (Sale): %.2f VND\n", tongTienSale);
        System.out.printf("**TỔNG DOANH THU THỰC TẾ: %.2f VND**\n", tongDoanhThuSauGiam);
        System.out.println("-----------------------------------");
    }

    // --- 8. LỌC THEO TIÊU CHÍ (Lọc theo loại giao dịch) ---
    public void locGiaoDichTheoLoai(Scanner sc) {
        System.out.println("Nhap loai can loc (HD / PNK): ");
        String loaiLoc = sc.nextLine().trim();

        System.out.printf("\n--- DANH SÁCH GIAO DỊCH LOẠI '%s' ---\n", loaiLoc.toUpperCase());
        int count = 0;
        for (HangHoa hh : danhSachGiaoDich) {
            boolean match = false;
            if (loaiLoc.equalsIgnoreCase("HD") && hh instanceof HoaDon) {
                ((HoaDon) hh).inChiTiet();
                match = true;
            } else if (loaiLoc.equalsIgnoreCase("PNK") && hh instanceof PhieuNhapKho) {
                ((PhieuNhapKho) hh).inChiTiet();
                match = true;
            }

            if (match) {
                System.out.println("-----------------------------------");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Khong co giao dich nao loai '" + loaiLoc + "'.");
        }
    }
}