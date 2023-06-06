package mx.com.qtx.servicios.dto;

public class Operacion {

	private long idFolio;
	private String emisor;
	private Venta venta;
	
	public long getIdFolio() {
		return idFolio;
	}
	public void setIdFolio(long idFolio) {
		this.idFolio = idFolio;
	}
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}	
	
}
