package aed.individual5;

import java.util.HashSet;
import java.util.Iterator;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.map.*;

public class Utils
{
	/*Recibe una lista indexada, y devuelve una nueva lista 
	 *donde los elementos repetidos dentro de l han sido borrados.
	 *FUNCIONA!!*/
  public static <E> PositionList<E> deleteRepeated(PositionList<E> l) 
  {
	  PositionList<E> listaSinReps = new NodePositionList<E>();
	  HashSet<E> elementos = new HashSet<E>();
	  Position<E> cursor = l.first();
	  while(cursor != null)
	  {
		  /*si el HashSet no contiene el cursor.element() que lo añada y tmb a la lista sin duplicados*/
		  if(!elementos.contains(cursor.element()))
		  {
			  elementos.add(cursor.element());
			  listaSinReps.addLast(cursor.element());
		  }
		  cursor = l.next(cursor);
	  }
	  return listaSinReps;
  }
  
  private static boolean eqNull (Object o1, Object o2)
  {
	  return o1 == o2 || o1!= null && o1.equals(o2);
  }
  
  public static <E> PositionList<E> compactar (Iterable<E> lista) throws IllegalArgumentException
  {
    PositionList<E> compactada = new NodePositionList<E>();
    if(lista == null) {
    	throw new IllegalArgumentException("La lista no puede ser nula");
    }
    Iterator<E> it = lista.iterator();
    //si esta vacia q me devuelva la lista sin rellenar
    if(!it.hasNext())
    {
    	return compactada;
    }
    //sino q me meta el 1ero
    compactada.addLast(it.next());
    
    //mientras q tiene mas de un elemento
    while(it.hasNext())
    {
    	E elem = it.next();
    	E ultimo = compactada.last().element();
    	if (!eqNull(elem,ultimo)) {
    		compactada.addLast(elem);
    	}
    }
    return compactada;
  }
  
  private static int max(int num1, int num2)
  {
	  if(num1 > num2)
		  return num1;
	  return num2;
  }
  
  
  //SOLO FALTA QUE SE METAN EN EL ORDEN CORRECTO PERO FUNCIONA! (al reves de como vienen en el array)
  public static Map<String,Integer> maxTemperatures(TempData[] tempData)
  {
	  //tiene un entry con nombre d ciudad y valor de temperatura
	  Map<String,Integer> map = new HashTableMap<String,Integer>();
	  for(int i=0; i<tempData.length; i++)
	  {
		  String location = tempData[i].getLocation();
		  //si NO hay alguna key en el map con esa location metida
		  if(!(map.containsKey(location)))
		  {
			  map.put(location, tempData[i].getTemperature());
		  }
		  //si hay alguna ya dentro con la misma location q meta la q tenga max temp. 
		  else {
			  map.put(location, max(tempData[i].getTemperature(),map.get(location)));
		  }
	  }
	  return map;
  }
}


