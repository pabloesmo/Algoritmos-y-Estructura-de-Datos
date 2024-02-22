package ColasPrioridad;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import java.util.*;
import es.upm.aedlib.priorityqueue.PriorityQueue;

public class Book
{
	String nombre;
	double precio;
	
	public Book(Double p,String t) {
		precio = p;
		nombre = t;
	}
	
	public Double getPrice() {
		return precio;
	}
	
	public String getTitle() {
		return nombre;
	}
	
	public void setPrice(Double precio) {
		this.precio = precio;
	}
	
	public void setTitle(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return "Libro<"
				+ nombre + ", " + precio + ">";
	}
		
	
	public static void main (String[] args) {
		Book b =  new Book (8.25,"El asedio");
		Book b2 =  new Book (5.10,"El lazarillo de Tormes");
		Book b3 =  new Book (6.50,"Rebelion en la granja");
		
//		PriorityQueue<Double,Book> pq = new HeapPriorityQueue<>(new BookNameComparator());
//		pq.enqueue(b,b);
//		pq.enqueue(b2,b2);
//		pq.enqueue(b3,b3);
		
	}

}