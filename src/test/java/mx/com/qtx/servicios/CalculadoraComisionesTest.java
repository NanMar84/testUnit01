package mx.com.qtx.servicios;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import mx.com.qtx.servicios.dto.Bitacora;
import mx.com.qtx.servicios.dto.Operacion;
import mx.com.qtx.servicios.dto.Venta;

public class CalculadoraComisionesTest {
	
	IGestorPersistencia gestorPersistencia;
	
	
	/*
	 * El importe de venta normal es menor al importe minimo, debe regresar comision 0
	 */
	@Test
	public void testCalcularComisionVentaNormalMenorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMenorMinimo = calculadora.getImporteMinimo() - 1;
		char tipoVenta = 'N';
		
		//entonces
		double comision = calculadora.calcularComision(importeMenorMinimo, tipoVenta);
		
		//validar
		assertEquals(comision, 0);
	}
	
	/*
	 * El importe de venta extraordinaria es menor al importe minimo, debe regresar comision 0
	 */
	@Test
	public void testCalcularComisionVentaExtraordinariaMenorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMenorMinimo = calculadora.getImporteMinimo() - 1;
		char tipoVenta = 'X';
		
		//entonces
		double comision = calculadora.calcularComision(importeMenorMinimo, tipoVenta);
		
		//validar
		assertEquals(comision, 0);
	}

	/*
	 * El importe de venta normal es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta normal
	 */
	@Test
	public void testCalcularComisionVentaNormalMayorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMayorMinimo = calculadora.getImporteMinimo() + 50.65;
		double porcComisionVentaNormal = calculadora.getPorcComisionNormal();
		char tipoVenta = 'N';
		
		//entonces
		double comision = calculadora.calcularComision(importeMayorMinimo, tipoVenta);
		double porComision = (comision/importeMayorMinimo) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaNormal);
	}
	
	/*
	 * El importe de venta extraordinaria es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta extraordinaria
	 */
	@Test
	public void testCalcularComisionVentaExtMinusculaMayorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMayorMinimo = calculadora.getImporteMinimo() + 4.654;
		double porcComisionVentaExtraordinaria = calculadora.getPorcComisionExtraordinario();
		char tipoVenta = 'x';
		
		//entonces
		double comision = calculadora.calcularComision(importeMayorMinimo, tipoVenta);
		double porComision = (comision/importeMayorMinimo) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaExtraordinaria);
	}
	
	/*
	 * El importe de venta normal es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta normal
	 */
	@Test
	public void testCalcularComisionVentaNormalMinusculaMayorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMayorMinimo = calculadora.getImporteMinimo() + 50.65;
		double porcComisionVentaNormal = calculadora.getPorcComisionNormal();
		char tipoVenta = 'n';
		
		//entonces
		double comision = calculadora.calcularComision(importeMayorMinimo, tipoVenta);
		double porComision = (comision/importeMayorMinimo) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaNormal);
	}
	
	/*
	 * El importe de venta extraordinaria es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta extraordinaria
	 */
	@Test
	public void testCalcularComisionVentaExtraordinariaMayorMinimo(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMayorMinimo = calculadora.getImporteMinimo() + 4.654;
		double porcComisionVentaExtraordinaria = calculadora.getPorcComisionExtraordinario();
		char tipoVenta = 'X';
		
		//entonces
		double comision = calculadora.calcularComision(importeMayorMinimo, tipoVenta);
		double porComision = (comision/importeMayorMinimo) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaExtraordinaria);
	}
	
	/*
	 * Tipo de venta inexistente, debe regresar valor -1
	 */
	@Test
	public void testCalcularComisionVentaInexistente(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeMayorMinimo = calculadora.getImporteMinimo() + 4.654;
		char tipoVenta = 'Z';
		
		//entonces
		double comision = calculadora.calcularComision(importeMayorMinimo, tipoVenta);
		//validar
		assertEquals(comision, -1);
	}
	
	/*
	 * Importe de venta igual a cero, debe regresar valor -2
	 */
	@Test
	public void testCalcularComisionIgualCero(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeCero = 0;
		char tipoVenta = 'Z';
		
		//entonces
		double comision = calculadora.calcularComision(importeCero, tipoVenta);
		//validar
		assertEquals(comision, -2);
	}
	
	/*
	 * Importe de venta menor a cero, debe regresar valor -2
	 */
	@Test
	public void testCalcularComisionMenorIgualCero(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		double importeCero = -243;
		char tipoVenta = 'Z';
		
		//entonces
		double comision = calculadora.calcularComision(importeCero, tipoVenta);
		//validar
		assertEquals(comision, -2);
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * El importe de venta normal es menor al importe minimo, debe regresar comision 0
	 */
	@Test
	public void testCalcularComisionVentaNormalMenorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() - 1);
		venta.setTipo('N');
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		
		//validar
		assertEquals(comision, 0);
	}
	
	/*
	 * El importe de venta extraordinaria es menor al importe minimo, debe regresar comision 0
	 */
	@Test
	public void testCalcularComisionVentaExtraordinariaMenorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() - 1);
		venta.setTipo('X');
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		
		//validar
		assertEquals(comision, 0);
	}

	/*
	 * El importe de venta normal es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta normal
	 */
	@Test
	public void testCalcularComisionVentaNormalMayorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() + 50.65);
		venta.setTipo('N');
		double porcComisionVentaNormal = calculadora.getPorcComisionNormal();
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		double porComision = (comision/venta.getImporte()) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaNormal);
	}
	
	/*
	 * El importe de venta extraordinaria es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta extraordinaria
	 */
	@Test
	public void testCalcularComisionVentaExtMinusculaMayorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() + 4.654);
		venta.setTipo('x');
		double porcComisionVentaExtraordinaria = calculadora.getPorcComisionExtraordinario();
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		double porComision = (comision/venta.getImporte()) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaExtraordinaria);
	}
	
	/*
	 * El importe de venta normal es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta normal
	 */
	@Test
	public void testCalcularComisionVentaNormalMinusculaMayorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() + 50.65);
		venta.setTipo('n');
		double porcComisionVentaNormal = calculadora.getPorcComisionNormal();
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		double porComision = (comision/venta.getImporte()) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaNormal);
	}
	
	/*
	 * El importe de venta extraordinaria es mayor al importe minimo, debe regresar comision diferente de 0
	 * y el porcentaje aplicado debe ser el mismo al porcentaje para venta extraordinaria
	 */
	@Test
	public void testCalcularComisionVentaExtraordinariaMayorMinimoV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() + 4.654);
		venta.setTipo('X');
		double porcComisionVentaExtraordinaria = calculadora.getPorcComisionExtraordinario();
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		double porComision = (comision/venta.getImporte()) * 100;
				
		//validar
		assertNotEquals(comision, 0);
		assertEquals(porComision, porcComisionVentaExtraordinaria);
	}
	
	/*
	 * Tipo de venta inexistente, debe regresar valor -1
	 */
	@Test
	public void testCalcularComisionVentaInexistenteV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(calculadora.getImporteMinimo() + 4.654);
		venta.setTipo('Z');
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		//validar
		assertEquals(comision, -1);
	}
	
	/*
	 * Importe de venta igual a cero, debe regresar valor -2
	 */
	@Test
	public void testCalcularComisionIgualCeroV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(0);
		venta.setTipo('Z');
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		//validar
		assertEquals(comision, -2);
	}
	
	/*
	 * Importe de venta menor a cero, debe regresar valor -2
	 */
	@Test
	public void testCalcularComisionMenorIgualCeroV2(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = new Venta();
		venta.setImporte(-243);
		venta.setTipo('Z');
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		//validar
		assertEquals(comision, -2);
	}

	/*
	 * Venta nula, regresa valor -3
	 */
	@Test
	public void testCalcularComisionVengtaNula(){
		//Dados
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		Venta venta = null;
		
		//entonces
		double comision = calculadora.calcularComision(venta);
		//validar
		assertEquals(comision, -3);
	}
	
	//Test Development Driven (TDD)
	@Test
	public void testBitacora(){
		
		List<Bitacora> bitacoraAuditoria = new ArrayList<>();
		List<Bitacora> bitacoraAuditoriaEsperada = new ArrayList<>();
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		
		Venta venta = new Venta();
		venta.setImporte(5453.43);
		venta.setTipo('X');
		
	/*	Bitacora bitacora1 = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(double importeVenta, char tipoVenta)", null, 121.7);
		Bitacora bitacora2 = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(double importeVenta, char tipoVenta)", null, 121.7);
		Bitacora bitacora3 = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(Venta venta)", venta, 545.3430000000001);
		Bitacora bitacora4 = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(Venta venta)", venta, 545.3430000000001);
		
		bitacoraAuditoriaEsperada.add(bitacora1);
		bitacoraAuditoriaEsperada.add(bitacora2);
		bitacoraAuditoriaEsperada.add(bitacora3);
		bitacoraAuditoriaEsperada.add(bitacora4);
		*/
		for (int i=0; i<2; i++) {
			double importeComision = calculadora.calcularComision(2434, 'N');
			Bitacora bitacora = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(double importeVenta, char tipoVenta)", null, importeComision);
			bitacoraAuditoria.add(bitacora);
		}
		
		for (int i=0; i<2; i++) {
			double importeComision = calculadora.calcularComision(venta);
			Bitacora bitacora = new Bitacora(new GregorianCalendar().getTime(), "Desde método calcularComision(Venta venta)", venta, importeComision);
			bitacoraAuditoria.add(bitacora);
		}
		
		assertTrue(!bitacoraAuditoria.isEmpty());
		
	//	assertArrayEquals(bitacoraAuditoria.toArray(), bitacoraAuditoriaEsperada.toArray(), "Las bitacoras son iguales");	
		
	}
	
	@Test
	public void registroOperaciones() {
		
		List<Operacion> operaciones = new ArrayList<>();
		List<Operacion> operacionesRealizadas = new ArrayList<>();
		CalculadoraComisiones calculadora = new CalculadoraComisiones();
		int totalOperaciones = 4;
		
		Operacion operacion1 = new Operacion();
		operacion1.setIdFolio(100);
		operacion1.setEmisor("E");
		operacion1.setVenta(null);
		
		Operacion operacion2 = new Operacion();
		operacion2.setIdFolio(101);
		operacion2.setEmisor("E");
		operacion2.setVenta(null);
		
		Operacion operacion3 = new Operacion();
		operacion3.setIdFolio(102);
		operacion3.setEmisor("E");
		operacion3.setVenta(null);
		
		Operacion operacion4 = new Operacion();
		operacion4.setIdFolio(103);
		operacion4.setEmisor("E");
		operacion4.setVenta(null);
		
		operaciones.add(operacion1);
		operaciones.add(operacion2);
		operaciones.add(operacion3);
		operaciones.add(operacion4);

		assertEquals(operaciones.size(), totalOperaciones);
		
		
	}
}
