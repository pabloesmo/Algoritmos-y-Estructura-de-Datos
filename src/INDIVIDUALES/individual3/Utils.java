package aed.filter;

import java.util.Iterator;


import java.util.function.Predicate;

import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;


public class Utils 
{

  public static <E> Iterable<E> filter(Iterable<E> d, Predicate<E> pred) throws IllegalArgumentException
  {
	  if (d == null)
		  throw new IllegalArgumentException();
	  Iterator<E> iterador = d.iterator(); 
      PositionList<E> listaResul = new NodePositionList<E>();      
      
      while (iterador.hasNext())
      {
    	  //almaceno la variable para usarla dentro del bucle
    	  E elem = iterador.next();
    	  //primero compruebo q no sea null y luego q cumpla la cond. de Greater
    	  if (elem != null && pred.test(elem))
    		  listaResul.addLast(elem);
      }
    return listaResul;
  }
}

