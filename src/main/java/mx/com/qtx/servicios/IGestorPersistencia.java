package mx.com.qtx.servicios;

import java.util.List;

import mx.com.qtx.servicios.dto.Operacion;

public interface IGestorPersistencia {
	
	/*public void getCantOperaciones();
	public Operacion getOperacion(long id);
	public List<Operacion> getOperaciones(String emisor);
	public Operacion getUltimaOperacion(); */
	public long registrarOperacion();

}
