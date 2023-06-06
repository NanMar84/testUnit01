package mx.com.qtx.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mx.com.qtx.servicios.dto.Operacion;
import mx.com.qtx.servicios.dto.Venta;

public class CalculadoraComisiones implements IGestorPersistencia {
	
	//Solamente debe dar comision a ventas superiores al importe minimo
	private final double importeMinimo = 1000;
	
	//Aplica a ventas normales; tipo 'N'
	private final double porcComisionNormal = 5.0;
	
	//Aplica a ventas extraordinarias: tipo 'X'
	private final double porcComisionExtraordinario = 10;
	
	public double calcularComision(double importeVenta, char tipoVenta) {		
		
		if(importeVenta <= 0) {
			return -2;
		}
		
		if( importeVenta > importeMinimo ) {			
			if( String.valueOf(tipoVenta).toUpperCase().equals("N") ) {
				return importeVenta * (this.porcComisionNormal/100) ;
			}
			
			if( String.valueOf(tipoVenta).toUpperCase().equals("X") ) {
				return importeVenta * (this.porcComisionExtraordinario/100) ;
			}
			
			return -1;
		}
				
		return 0;
	}
	
	public double calcularComision(Venta venta) {	
		if(Objects.nonNull(venta)) {
			return this.calcularComision(venta.getImporte(), venta.getTipo());
		} else {
			return -3;
		}
		
	}
	
	public long calcularComisionOperacion(Venta venta) {	
		if(Objects.nonNull(venta)) {
			double importeComision = this.calcularComision(venta.getImporte(), venta.getTipo());
			return registrarOperacion();
		} else {
			return -3;
		}
		
	}
	
	public Operacion getOperacion(long idFolio) {
		return new Operacion();
	}
	
	public List<Operacion> getOperacionesDesde(long folioInicio, long folioFin) {
		List<Operacion> operaciones = new ArrayList<>();
		
		return operaciones;
	}
	
	public List<Operacion> getOperacionesXEmisor(String emisor) {
		List<Operacion> operaciones = new ArrayList<>();
		
		return operaciones;
	}

	public double getImporteMinimo() {
		return importeMinimo;
	}

	public double getPorcComisionNormal() {
		return porcComisionNormal;
	}

	public double getPorcComisionExtraordinario() {
		return porcComisionExtraordinario;
	}

	@Override
	public long registrarOperacion() {
		return 0;
	}
	
}
