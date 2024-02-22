package aed.Clases;
import java.awt.Cursor;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;


public class Recursion
{
	public static <E> void show (PositionList<E> list)
	{
		if(list == null)
			throw new IllegalArgumentException();
		showAux(list,list.first());
	}
	
	public static <E> void showAux (PositionList<E> list, Position<E> cursor)
	{
		if (cursor != null){
		System.out.println(cursor.element());
		showAux(list, list.next(cursor));
		}
	}
	
	
	public static <E> int numApar (PositionList<E> list, E elem)
	{
		return numAparAux(list, elem,list.first(),0);
	}

		

	private static <E> int numAparAux(PositionList<E> list, E elem, Position<E> cursor, int acumulado) 
	{
		if (cursor == null) {
			return acumulado;
		}
		if (eqNull(elem, cursor.element())) {
			return numAparAux(list,elem,list.next(cursor),acumulado);
		}
		else {
			return numAparAux(list,elem,list.next(cursor), acumulado);
		}
	}

	private static <E> boolean eqNull(E elem, E element) {
		return false;
	}

	public static <E> boolean iguales(PositionList<E> l1, PositionList<E> l2)
	{
		if (l1.size() != l2.size()) {
			return false;
		}
		return igualesAux(l1,l2,l1.first(),l2.first());
	}
		
	private static <E> boolean igualesAux(PositionList<E> l1, PositionList<E> l2, Position<E> c1, Position<E> c2)
	{
		if (c1 == null) {
			return true;
		}
		if(!eqNull(c1.element(),c2.element())){
			return false;
		}
		return igualesAux(l1,l2,l1.next(c1), l2.next(c2));
	}
	
	
	public static <E> boolean estaOrdenada(PositionList<Comparable<E>> list, E elem)
	{
		if(list.size() <= 1){
			return true;
		}
		return false;
	}

	/*estaOrdenadaAux(list,elem,list.next(list.first()))*/
	private static <E> boolean estaOrdenadaAux(PositionList<Comparable<E>> list, Position<E> cursor) 
	{
		if (cursor == null) {
			return true;
		}
		/*
		if (list.prev(cursor).element().compareTo(cursor.element()) < 0) {
			return false;
		}*/
		return false;
	}
	
	public static <E> void insertBeforeElem(PositionList<E> list, E elem, E toAdd)
	{
		insertBeforeElem(list,elem,toAdd,list.first());
	}
		
	private static <E> void insertBeforeElem(PositionList<E> list, E elem, E toAdd, Position<E> cursor)
	{
		if (cursor == null) {
			return;
		}
		if(eqNull(elem, cursor.element())){
			list.addBefore(cursor, toAdd);
			return;
		}
		insertBeforeElem(list,elem,toAdd,list.next(cursor));
	}
	

	
	public static void main (String[] args)
	{
		PositionList<Integer> list = new NodePositionList<Integer>(new Integer[] {1,2,3,4,5});
		PositionList<Integer> list2 = new NodePositionList<Integer>(new Integer[] {});
		
		show(list);
//		show(list2);
	}
}