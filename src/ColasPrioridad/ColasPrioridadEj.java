package ColasPrioridad;
import es.upm.aedlib.*;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.priorityqueue.SortedListPriorityQueue;

public class ColasPrioridadEj
{		
	public static void main(String[] args)
	{
		//la prioridad va de 0 a n
		PriorityQueue<Integer,String> cola = new SortedListPriorityQueue<>();
		//ENQUEUE CON COMPLJ O(n)
		cola.enqueue(1, "TV");
		cola.enqueue(2, "Lampara");
		cola.enqueue(4, "Mesa");
		cola.enqueue(0, "Reloj");
		cola.enqueue(3,"Cuadro");
		Entry<Integer,String> entry = cola.enqueue(8, "Silla");
		cola.replaceKey(entry, 10);
//		while(!cola.isEmpty()) {
//			System.out.println(cola.dequeue());
//		}
		
		System.out.println(cola.toString());
		
		//DEQUEUE CON COMPLJ O(1)
		
		//FIRST CON COMPLJ O(1)
//		System.out.println("Primero de la cola => " + cola.first());
		
		
		
//		System.out.println("Tamaño de la cola => " + cola.size());
//		System.out.println("\n");
		
//		while(!cola.isEmpty()){
//			System.out.println(cola.dequeue());
//		}
		//ARRAY COMO ARBOL CASI COMPLETO
		//Con esta implementacion conseguimos que la inserccion y el borrado 
		//tengan complejidad O(log(n))
	}
}