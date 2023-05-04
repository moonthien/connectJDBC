package entity;

public class SinhVien {
	private String maSV;
	private String hoTen;
	private String email;
	private String diaChi;

	private LopHoc lopHoc;

	public SinhVien() {
		super();
	}

	public SinhVien(String maSV) {
		super();
		this.maSV = maSV;
	}

	public SinhVien(String maSV, String hoTen, String email, String diaChi, LopHoc lopHoc) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.lopHoc = lopHoc;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", hoTen=" + hoTen + ", email=" + email + ", diaChi=" + diaChi + ", lopHoc="
				+ lopHoc + "]";
	}


}
