package aed.hotel;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class MiHotel2 implements Hotel
{
	  private IndexedList<Habitacion> habitaciones;
	  
	  
	  public MiHotel2()
	  {
	    this.habitaciones = new ArrayIndexedList<Habitacion>();
	    }

	public void anadirHabitacion(Habitacion habitacion) {
		
	}

	public boolean reservaHabitacion(Reserva reserva) {
		return false;
	}

	public boolean cancelarReserva(Reserva reserva) {
		return false;
	}

	public IndexedList<Habitacion> disponibilidadHabitaciones(String diaEntrada, String diaSalida) {
		return null;
	}

	public IndexedList<Reserva> reservasPorCliente(String dniPasaporte) {
		return null;
	}

	public IndexedList<Habitacion> habitacionesParaLimpiar(String hoyDia) {
		return null;
	}

	public Reserva reservaDeHabitacion(String nombreHabitacion, String dia) {
		return null;
	}
	
}