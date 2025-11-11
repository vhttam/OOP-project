package modal;

public class SanPham {
    private String ID;
    private String Ten;
    private String Loai;
    private int SL;
    private double Gia;
    public String  getID() {
        return ID;
    }

    public String getTen() {
        return Ten;
    }

    public String getLoai() {
        return Loai;
    }

    public int getSL() {
        return SL;
    }

    public double getGia() {
        return Gia;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public void setGia(double gia) {
        Gia = gia;
    }
    //constructor
    public SanPham(String ID, String Ten, String Loai, int SL, double Gia ){
        this.ID=ID;
        this.Ten=Ten;
        this.Loai=Loai;
        this.SL=SL;
        this.Gia=Gia;
    }

    public double loiNhuan(){
        // Sử dụng this.Loai để truy cập thuộc tính của chính đối tượng
        if(this.Loai.equalsIgnoreCase("Nuoc"))
            return 0.2; // 20% lợi nhuận
        else if (this.Loai.equalsIgnoreCase("Banh")) {
            return 0.3; // 30% lợi nhuận
        }
        else if(this.Loai.equalsIgnoreCase("Do gia dung")){
            return 0.1; // 10% lợi nhuận
        }
        else return 0.1; // Mặc định 10%
    }
    public double giaBan(){
        return this.Gia * (1 + loiNhuan());
    }
}