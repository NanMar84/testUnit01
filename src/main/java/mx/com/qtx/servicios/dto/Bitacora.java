package mx.com.qtx.servicios.dto;

import java.util.Date;

public class Bitacora {
	
	private Date fechaCalculoComision;
	private String metodo;
	private Venta venta;
	private double importeComision;
	
	public Date getFechaCalculoComision() {
		return fechaCalculoComision;
	}
	public void setFechaCalculoComision(Date fechaCalculoComision) {
		this.fechaCalculoComision = fechaCalculoComision;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public double getImporteComision() {
		return importeComision;
	}
	public void setImporteComision(double importeComision) {
		this.importeComision = importeComision;
	}
	
	public Bitacora(Date fechaCalculoComision, String metodo, Venta venta, double importeComision) {
		super();
		this.fechaCalculoComision = fechaCalculoComision;
		this.metodo = metodo;
		this.venta = venta;
		this.importeComision = importeComision;
	}
	@Override
	public String toString() {
		return "Bitacora [fechaCalculoComision=" + fechaCalculoComision + ", metodo=" + metodo + ", venta=" + venta
				+ ", importeComision=" + importeComision + "]";
	}
	
	

}
