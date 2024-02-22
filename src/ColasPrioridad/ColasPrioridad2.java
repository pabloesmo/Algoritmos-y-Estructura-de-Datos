package ColasPrioridad;
import java.util.*;

import aed.urgencias.Paciente;
import aed.urgencias.PacienteExisteException;
import aed.urgencias.PacienteNoExisteException;
import es.upm.aedlib.*;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.priorityqueue.SortedListPriorityQueue;

public class ColasPrioridad2
{
	private static PriorityQueue<Paciente, Paciente> colaUrgencias = new SortedListPriorityQueue<>();
	private static TreeMap<String,Paciente> arbol = new TreeMap<>();
	private static HashTableMap<String,Paciente> hash = new HashTableMap<>();
	private static HashTableMap<Integer,Paciente> atendidos = new HashTableMap<>();


	public static Paciente admitirPaciente(String DNI, int prioridad, int hora) throws PacienteExisteException 
	{
		if(hash.containsKey(DNI)) {
			throw new PacienteExisteException(); 
		}
		Paciente paciente = new Paciente(DNI, prioridad, hora, hora); 
		hash.put(DNI, paciente);
		arbol.put(DNI, paciente);	
		colaUrgencias.enqueue(paciente, paciente);
		return paciente;
	}

	
	
	public static Paciente salirPaciente(String DNI, int hora) throws PacienteNoExisteException
	{
		Paciente p = hash.get(DNI);
		if(p ==  null) {
			throw new PacienteNoExisteException();
		}
		Iterator<Entry<Paciente, Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente,Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
			p = entry.getKey();
			if(p != null && p.getDNI().equals(DNI)) {			
				colaUrgencias.remove(entry);
				break;
			}
		}
		hash.remove(DNI);
		return p;
	}
	
	public static Paciente cambiarPrioridad(String DNI, int nuevaPrioridad, int hora) throws PacienteNoExisteException
	{
		Paciente p = null;
		Iterator<Entry<Paciente, Paciente>> it = colaUrgencias.iterator();
		Entry<Paciente,Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
			p = entry.getKey();
			if(p != null && p.getDNI().equals(DNI)) {
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
		return p;
	}
	
	public static Paciente atenderPaciente(int hora)
	{
		Paciente p = null; 
		if(!colaUrgencias.isEmpty()) {
			p = colaUrgencias.dequeue().getValue(); 
			atendidos.put(hora,p);
		}
		return p;
	}

	
	public static void aumentaPrioridad(int maxTiempoEspera, int hora) 
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
			
			else if(hora - entry.getValue().getTiempoAdmision() > maxTiempoEspera) {
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
	

	// Devuelve un objeto Iterable ordenado segun el orden en la cola
	public static Iterable<Paciente> pacientesEsperando() 
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

		
	
	public static Paciente getPaciente(String DNI)
	{
		Paciente paciente = null;
		if(hash.containsKey(DNI) && !colaUrgencias.isEmpty()) {
			paciente = new Paciente(DNI, hash.get(DNI).getPrioridad(),
					hash.get(DNI).getTiempoAdmision(),
					hash.get(DNI).getTiempoAdmisionEnPrioridad());
		}
		return paciente;
	}

	
	public static Pair<Integer, Integer> informacionEspera() 
	{
		int sumaEspera = 0;
		int pacientesAtendidos = 0;
		Iterator<Entry<Integer,Paciente>> it = atendidos.iterator();
		Entry<Integer,Paciente> entry;
		while(it.hasNext()) {
			entry = it.next();
			sumaEspera += (entry.getKey() - entry.getValue().getTiempoAdmision());
		}
		pacientesAtendidos = atendidos.size();
		Pair<Integer,Integer> parResul = new Pair<>(sumaEspera, pacientesAtendidos);
		return parResul;
	}
	
	
	
	public static void main (String[] args) throws PacienteExisteException, PacienteNoExisteException 
	{
		//17
//		System.out.println(admitirPaciente("111",2,0));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
//		aumentaPrioridad(1,5);
//		System.out.println(admitirPaciente("222",1,10));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(15));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");	
		
		System.out.println(admitirPaciente("111",5,1));
		System.out.println(admitirPaciente("222",7,2));
		System.out.println(admitirPaciente("333",8,100));
		System.out.println("\n");
		System.out.println("-----------ARBOL-----------");
		System.out.println(colaUrgencias.toString());
		System.out.println("----------------------");
		System.out.println("\n");
		aumentaPrioridad(1500,2000);
		System.out.println("\n");
		System.out.println("-----------ARBOL-----------");
		System.out.println(colaUrgencias.toString());
		System.out.println("----------------------");
		System.out.println("\n");
		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(3000));
		System.out.println("\n");
		System.out.println("-----------ARBOL-----------");
		System.out.println(colaUrgencias.toString());
		System.out.println("----------------------");
		System.out.println("\n");
		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(4000));
		
		
		//18
//		System.out.println(admitirPaciente("111",5,0));
//		System.out.println(admitirPaciente("222",4,1));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
//		System.out.println(cambiarPrioridad("111",4,2));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(3));
		
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(27));
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(35));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
		
		
		
//		PositionList<Paciente> lista = new NodePositionList<>();
//		Paciente p1 = new Paciente("PABLO", 3, 4, 23);
//		Paciente p2 = new Paciente("LUIS", 6, 8, 8);
//		Paciente p3 = new Paciente("JOSE", 9, 17, 17);
//		Paciente p4 = new Paciente("PEDRO", 8, 13, 13);
//		lista.addLast(p1);
//		lista.addLast(p2);
//		lista.addLast(p3);
//		lista.addLast(p4);
		
		
		
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(27));
//		System.out.println("\n");
//		System.out.println("-----------ARBOL-----------");
//		System.out.println(colaUrgencias.toString());
//		System.out.println("----------------------");
//		System.out.println("\n");
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(35));
		
		
//		System.out.println("PACIENTE ATENDIDO => " + atenderPaciente(30));
//		System.out.println(admitirPaciente("74939234Y",1,20));
//		System.out.println("\n");
//		System.out.println(pacientesEsperando()); // [PABLO,MIGUEL,...]
//		System.out.println(atenderPaciente(10));
//		System.out.println(atenderPaciente(7));
//		System.out.println(getPaciente("MARIANGELES"));	
	}
}