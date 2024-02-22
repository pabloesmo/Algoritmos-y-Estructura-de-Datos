package aed.urgencias;
import java.util.Iterator;
import java.util.TreeMap;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.priorityqueue.*;

public class UrgenciasAED implements Urgencias
{
	//creo lista sobre la que iterar los paciente
	private PriorityQueue<Paciente, Paciente> colaUrgencias = new SortedListPriorityQueue<>();
	private HashTableMap<String,Paciente> hash = new HashTableMap<>();
	private HashTableMap<Integer,Paciente> atendidos = new HashTableMap<>();
	private int acumTiempo = 0;
	
	
	//COMPLEJIDAD MAXIMA (log(n))
	/* Paciente entra en las urgencias. (add)
	 * @returns el paciente nuevo
	 * @throws  la excepcion PacienteExisteException si
	 * ya hay una paciente con este DNI admitido, que no ha sido atendido, ni ha salido.
	 */
	public Paciente admitirPaciente(String DNI, int prioridad, int hora) throws PacienteExisteException 
	{
		if(hash.containsKey(DNI)) {
			throw new PacienteExisteException(); 
		}
		Paciente paciente = new Paciente(DNI, prioridad, hora, hora); 
		hash.put(DNI, paciente);
		colaUrgencias.enqueue(paciente, paciente);
		return paciente;
	}

	
	/* Paciente sale de las urgencias. Borra el paciente de
	 * las estructuras de datos de las urgencias.
	 * @returns el paciente
	 * @throws la excepcion PacienteNoExisteException si
	 * no hay una paciente con este DNI admitido.
	 */
	public Paciente salirPaciente(String DNI, int hora) throws PacienteNoExisteException
	{
		Paciente p = hash.get(DNI);
		if(p == null) {
			throw new PacienteNoExisteException();
		}
		Iterator<Entry<Paciente, Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente,Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
			p = entry.getKey();
			if(p != null && p.getDNI().equals(DNI)) {			
				colaUrgencias.remove(entry);
				hash.remove(DNI);
				break;
			}
		}
		return p;
	}

	
	/* La prioridad del paciente cambia a nuevaPrioridad. 
	 * @returns el paciente
	 * @throws la excepcion PacienteNoExisteException si
	 * no hay una paciente con este DNI admitido.
	 */
	public Paciente cambiarPrioridad(String DNI, int nuevaPrioridad, int hora) throws PacienteNoExisteException
	{
		Paciente p = hash.get(DNI);
		boolean existe = false;
		Iterator<Entry<Paciente, Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente,Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
			p = entry.getKey();
			if(p != null && p.getDNI().equals(DNI)) {
				existe = true;
				if(p.getPrioridad() == nuevaPrioridad) {
					break;
				}
				colaUrgencias.remove(entry);
				p.setPrioridad(nuevaPrioridad);
				p.setTiempoAdmisionEnPrioridad(hora);
				colaUrgencias.enqueue(p, p);
				break;
			}
		}
		if(!existe) {
			throw new PacienteNoExisteException();
		}
		return p;
	}

	
	//COMPLEJIDAD MAXIMA (log(n))
	/* Atende a un paciente (el primero en la cola), 
	 * y borra el paciente de las estructuras de datos 
	 * de las urgencias.
	 * @returns el paciente, o null si no hay ningun paciente admitido.
	 * METO EL PACIENTE QUE ESTE EL PRIMERO EN LA COLA DE PRIORIDAD EN EL HASHMAP DE hash
	 *
	 * PUEDO RECORRER LA COLA BUSCANDO EL QUE TENGA MAS PRIORIDAD Y DAR ESE Y HACER EL REMOVE DE ESE
	 */
	public Paciente atenderPaciente(int hora)
	{
		Paciente p = null; 
		if(!colaUrgencias.isEmpty()) {
			p = colaUrgencias.dequeue().getValue(); 
			atendidos.put(hora,p);
			acumTiempo += (hora - p.getTiempoAdmision());
			hash.remove(p.getDNI());
		}
		return p;
	}

	
	//COMPLEJIDAD MAXIMA (n * log(n))
	/*Aumenta la prioridad de los pacientes que han 
	 * esperado mas que maxTiempoEspera
	 * en su prioridad actual. 
	 */
	public void aumentaPrioridad(int maxTiempoEspera, int hora) 
	{
		Paciente p = null;
		Iterator<Entry<Paciente,Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente, Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
				
			if(entry.getValue().getPrioridad() == 0) {
				entry.getValue().setPrioridad(0);
				entry.getValue().setTiempoAdmisionEnPrioridad(0);
			}
			
			else if(hora - entry.getValue().getTiempoAdmisionEnPrioridad() > maxTiempoEspera) {
				p = entry.getKey();
				if(p != null) {
					colaUrgencias.remove(entry);
					p.setPrioridad(p.getPrioridad() - 1);
					p.setTiempoAdmisionEnPrioridad(hora);
					colaUrgencias.enqueue(p, p);
				}				
			}
		}
	}

	
	//----------------FUNCIONAA----------------
	//COMPLEJIDAD MAXIMA (n * log(n))
	/*Devuelve un objeto Iterable ordenado segun el 
	 * orden en que los pacientes
	 * serian hash.
	 */
	public Iterable<Paciente> pacientesEsperando() 
	{
		PositionList<Paciente> lista = new NodePositionList<>();
		Iterator<Entry<Paciente,Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente,Paciente> entry;
		Paciente paciente = null;
		while(it.hasNext()) 
		{
			entry = it.next();
			paciente = entry.getValue();
			lista.addLast(paciente);
		}	
		return lista;
	}
	

	//COMPLEJIDAD MAXIMA (1)
	/*Devuelve el paciente. Si no hay un paciente 
	 * con este DNI devuelve null.
	 */
	public Paciente getPaciente(String DNI)
	{
		Paciente paciente = null;
		if(hash.containsKey(DNI) && !colaUrgencias.isEmpty()) {
			paciente = new Paciente(DNI, hash.get(DNI).getPrioridad(),
					hash.get(DNI).getTiempoAdmision(),
					hash.get(DNI).getTiempoAdmisionEnPrioridad());
		}
		return paciente;
	}

	
	//COMPLEJIDAD MAXIMA (1)
	/*Devuelve un par de enteros donde el primer entero 
	 * es la suma de los tiempos de espera de los pacientes 
	 * que han sido atendidos, y el segundo es el numero 
	 * de pacientes que han sido atentidos.
	 * Se devuelve null si ningun paciente ha sido atendido.
	 * @returns un par con la sumas de esperas y el numero de 
	 * pacientes admitidos.
	 */
	public Pair<Integer, Integer> informacionEspera() 
	{
		int pacientesAtendidos = 0;
		pacientesAtendidos = atendidos.size();
		Pair<Integer,Integer> parResul = new Pair<>(acumTiempo, pacientesAtendidos);
		return parResul;
	}
}
