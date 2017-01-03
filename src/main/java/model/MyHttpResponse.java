package model;

public class MyHttpResponse {

	private int code;
	private String contenu;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	@Override
	public String toString() {
		return "MyHttpResponse [code=" + code + ", contenu=" + contenu + "]";
	}
	
	
}
