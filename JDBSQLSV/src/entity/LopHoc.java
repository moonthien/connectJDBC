package entity;

public class LopHoc {
private String maLop;
private String tenLop;
private String giaoVienCN;

public LopHoc() {
	
}

public LopHoc(String maLop) {
	super();
	this.maLop = maLop;
}

public LopHoc(String maLop, String tenLop, String giaoVienCN) {
	super();
	this.maLop = maLop;
	this.tenLop = tenLop;
	this.giaoVienCN = giaoVienCN;
}

public String getMaLop() {
	return maLop;
}

public void setMaLop(String maLop) {
	this.maLop = maLop;
}

public String getTenLop() {
	return tenLop;
}

public void setTenLop(String tenLop) {
	this.tenLop = tenLop;
}

public String getGiaoVienCN() {
	return giaoVienCN;
}

public void setGiaoVienCN(String giaoVienCN) {
	this.giaoVienCN = giaoVienCN;
}

@Override
public String toString() {
	return "LopHoc [maLop=" + maLop + ", tenLop=" + tenLop + ", giaoVienCN=" + giaoVienCN + "]";
}

}
