package aed.hotel;

import es.upm.aedlib.indexedlist.*;


/**
 * Implementa el interfaz Hotel, para realisar y cancelar reservas en un hotel,
 * y para realisar preguntas sobre reservas en vigor.
 */
public class MiHotel implements Hotel {
  /**
   * Usa esta estructura para guardar las habitaciones creados.
   */
  private IndexedList<Habitacion> habitaciones;

  /**
   * Crea una instancia del hotel. 
   */
  public MiHotel() {
    // No se debe cambiar este codigo
    this.habitaciones = new ArrayIndexedList<>();
}

@Override
public void anadirHabitacion(Habitacion habitacion) throws IllegalArgumentException {
	int i = 0;
	for (Habitacion h : habitaciones) {
		if (h.getNombre().equals(habitacion.getNombre())) {
			throw new IllegalArgumentException("Habitacion ya existe");
		} else if (habitacion.getNombre().compareTo(h.getNombre()) < 0) {
			i++;
		}
	}
	habitaciones.add(i, habitacion);
}


public boolean reservaHabitacion(Reserva reserva) throws IllegalArgumentException {
		boolean reservaDisp = true;
		boolean reservaRealizada = false;
		int i = 0;
			for(Habitacion h : habitaciones) {	
				if(h.getNombre().equals(reserva.getHabitacion())) {
					i++;
					int j = 0;
					for(Reserva r : h.getReservas()) {
						if(conflicto(r,reserva)) {
							reservaDisp = false;
						}
						if(reserva.getDiaSalida().compareTo(r.getDiaEntrada()) <= 0){
							j = 0;
						}else if(reserva.getDiaEntrada().compareTo(r.getDiaSalida()) >= 0) {
							j = h.getReservas().size() - 1;
						}
					}
					if(reservaDisp) {
						h.getReservas().add(j, reserva);
						reservaRealizada = true;	
					}
				}
			}
			if(i<1) {
				throw new IllegalArgumentException("la habitacion de la reserva no existe");
			}
		return reservaDisp && reservaRealizada;
	}
	
	private boolean conflicto(Reserva r1, Reserva r2) {
		boolean conflicto = true;
		if(r1.getDiaEntrada().compareTo(r2.getDiaSalida())>=0) 
		{
			conflicto = false;
		}else if(r2.getDiaEntrada().compareTo(r1.getDiaSalida())>=0) {
			conflicto = false;
		}
		return conflicto;
	}
	
	private boolean conflicto(Reserva r1, String e, String s) {
		boolean conflicto = true;
		if(r1.getDiaEntrada().compareTo(s)>=0) 
		{
			conflicto = false;
		}else if(e.compareTo(r1.getDiaSalida())>=0) {
			conflicto = false;
		}
		return conflicto;
	}
	
	public boolean cancelarReserva(Reserva reserva) throws IllegalArgumentException{
		Reserva reservaParaCancelar = null;
		int posHab = - 1;
		boolean reservaCancelable = false;
		int i = 0;
		for(int j = 0; j < habitaciones.size(); j++) {
			if(habitaciones.get(j).getNombre().equals(reserva.getHabitacion())) {
				posHab = j;
				i++;
				for(Reserva r : habitaciones.get(j).getReservas()) {
					if(r.getDiaEntrada().equals(reserva.getDiaEntrada())
							&& r.getDiaSalida().equals(reserva.getDiaSalida())) {
						reservaCancelable = true;
						reservaParaCancelar = r;
					}
				}
			}
		}
		if(i<1) {
			throw new IllegalArgumentException("la habitacion de la reserva no existe");
		}else if(posHab != -1 && reservaCancelable == true) {
			habitaciones.get(posHab).getReservas().remove(reservaParaCancelar);
		}
		return reservaCancelable;
	}
	
	public IndexedList<Habitacion> disponibilidadHabitaciones(String diaEntrada, String diaSalida) {	
		IndexedList<Habitacion> dispHab = new ArrayIndexedList<Habitacion>();
		int i;
		for(Habitacion h : habitaciones) {
			i = 0;
			if(!h.getReservas().isEmpty()) {
				for(Reserva r : h.getReservas()){
					if(conflicto(r,diaEntrada,diaSalida)){
						i++;
					}
				}
			}
			if(i==0) {
				int j = 0;
				if(!dispHab.isEmpty()) {
					for(Habitacion disp : dispHab) {
						if(h.getPrecio() > disp.getPrecio()) {
							j++;
						}
					}	
				}
				dispHab.add(j, h);
			}
		}
		return dispHab;
	}

	
	public IndexedList<Reserva> reservasPorCliente(String dniPasaporte) {
		IndexedList<Reserva> reservasPorCliente = new ArrayIndexedList<Reserva>();
		int i = 0;
		boolean encontrada = false;
		for(Habitacion h : habitaciones) {
			for(Reserva r : h.getReservas()) {
				if(r.getDniPasaporte().equals(dniPasaporte)) {
					for(int j = 0; j < reservasPorCliente.size() && !encontrada; j++) {
						i++;
						if(r.getDiaEntrada().compareTo(reservasPorCliente.get(j).getDiaEntrada())>=0) {
							encontrada = true;
						}
					}
					reservasPorCliente.add(i, r);
				}
			}
		}
		return reservasPorCliente;
	}
	
	public IndexedList<Habitacion> habitacionesParaLimpiar(String hoyDia) {
		IndexedList<Habitacion> habitacionesPorLimpiar = new ArrayIndexedList<Habitacion>();
		for(Habitacion h : habitaciones) {
			int i = 0;
			if(!h.getReservas().isEmpty()) {	
				for(Reserva r: h.getReservas()) {
					if(r.getDiaEntrada().compareTo(hoyDia)<0 && r.getDiaSalida().compareTo(hoyDia)>=0) {
						i++;
					}
				}	
			}
			if(i>0) {
				habitacionesPorLimpiar.add(0, h);
			}
		}
		return habitacionesPorLimpiar;
	}
	
	public Reserva reservaDeHabitacion(String nombreHabitacion, String dia){
		Reserva reserva = null;
		for(Habitacion h : habitaciones) {
			for(Reserva r : h.getReservas()) {
				if(h.getNombre().equals(nombreHabitacion) 
						&& r.getDiaEntrada().compareTo(dia)<=0
						&& r.getDiaSalida().compareTo(dia)>0)
				{
					reserva = r;
				}
			}
		}
		return reserva;
	}

//	private boolean a�adeReserva(Reservar,Indexed<>)
/*	
	private int buscaIndiceHab(String nombre) {
		int posicion = -1;
		int primera = 0;
		int ultima = habitaciones.size()-1;
		int centro = (habitaciones.size())/2;
		if(habitaciones.get(centro).getNombre().compareTo(nombre) == 0) 
		{
			posicion = centro;
		}
		else if(habitaciones.get(centro).getNombre().compareTo(nombre) < 0)
		{
			primera = centro+1;
			centro = (ultima+primera)/2;
			posicion = buscaIndiceHab(nombre);
		}
		else if(habitaciones.get(centro).getNombre().compareTo(nombre) > 0)
		{
			ultima = centro-1;
			centro = (ultima+primera)/2;
			posicion = buscaIndiceHab(nombre);
		}
		return posicion;
	}
*/
	
	
	/*	private Habitacion buscaHab(String nombre) throws IllegalArgumentException{
		Habitacion habResultado = null;
		if(buscaIndiceHab(nombre) == -1)
			throw new IllegalArgumentException("Habitaci�n ya registrada");
		for(int i = 0; i<habitaciones.size(); i++) 
		{
			if(i==buscaIndiceHab(nombre)) 
			{
				habResultado = habitaciones.get(i);
			}
		}
		return habResultado;
	}
	*/
/*	private boolean hayConflicto(String diaEntrada1,String diaEntrada2,String diaSalida1,String diaSalida2) {
		boolean hayConflicto = false;
		if(diaEntrada2.compareTo(diaSalida1)<0 || diaEntrada1.compareTo(diaSalida2)<0) 
		{
			hayConflicto = true;
		}
		return hayConflicto;

	}
	private boolean anadeReserva(Reserva r, IndexedList<Reserva> rs) {
		boolean res = true;
		for(int i = 0; i < rs.size(); i++)
		{
			if(!hayConflicto(r.getDiaEntrada(),rs.get(i).getDiaEntrada(),r.getDiaSalida(),rs.get(i).getDiaSalida()))
			{
				rs.add(i, r);
			}
			else 
			{
				rs.add(rs.size(), r);
				res = false;
			}
		}
		return res;
	}
	private static <E> boolean insertar(E e, IndexedList<E>list, Comparator<E> cmp) {
		for(int i = 0; i < list.size(); i++) 
		{
	//		list.add(buscaIndiceHab(), e);
		}
		cmp.compare(e, e);
		return false;
	}
*/	
	//Comparator
	//devuelve res <0 => si habitacion1 (101) < habitacion2 (204)
	//devuelve res ==0 => si o1=o2
	//devuelve res >0 => si habitacion1 (205) > habitacion2 (125)
	
}





