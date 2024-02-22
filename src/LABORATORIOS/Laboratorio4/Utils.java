package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {

  public static int multiply(int a, int b) {
	  int sign = 1;
	  int sum = 0;
	  if(a < 0)
		sign = -1;
	  sum = op(a,b,sum);
    return sign*sum;
  }
  private static int op(int a, int b, int sum) {
	  if(a != 0) {
		  if(a%2 != 0)
			  sum += b;
		  sum = op(a/2, b*2, sum);
	  }
	  return sum;
  }
  

  public static <E extends Comparable<E>> int findBottom(IndexedList<E> l) {
	  int mid = (l.size()-1)/2;
	  if(l.size() < 2) {
		  return 0;
	  }else if(l.size() == 2) {
		  return l.get(0).compareTo(l.get(1)) < 0 ? 0 : 1;
	  }
	  return hoyo(l,mid);
  }
  
  private static <E extends Comparable<E>> int hoyo(IndexedList<E> l, int mid) {

	  int left = mid -1;
	  int right = mid +1;
	  if(left == -1 || right == l.size()) {
		  return mid;
	  }else if(l.get(left).compareTo(l.get(mid)) > 0 && l.get(mid).compareTo(l.get(right)) < 0) {
		  return mid;
	  }
	  return l.get(left).compareTo(l.get(right)) <= 0 ? hoyo(l,left) : hoyo(l,right);
  }

  
  
  
  public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
    joinMultiSets(NodePositionList<Pair<E,Integer>> l1,
		  NodePositionList<Pair<E,Integer>> l2) 
  {
	  //lista q une las dos de multisets l1,l2
	  NodePositionList<Pair<E,Integer>> joined = new NodePositionList<Pair<E,Integer>>();
	  Position<Pair<E, Integer>> c1 = l1.first();
	  Position<Pair<E, Integer>> c2 = l2.first();
	  return join(l1,l2,joined, c1, c2);
  }
  
  public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>> join (NodePositionList<Pair<E, Integer>> l1, 
		  NodePositionList<Pair<E, Integer>> l2, NodePositionList<Pair<E,Integer>> joined, 
		  Position<Pair<E, Integer>> c1,
		  Position<Pair<E, Integer>> c2) 
  {
	  //SI ALGUNO DE LOS <DOS> ESTA VACIO Q META EL OTRO AL JOINED
	  if(c1 == null && c2 != null) {
		  joined.addLast(c2.element());
	  }
	  else if(c1 != null && c2 == null) {
		  joined.addLast(c1.element());
	  }
	  if(c1 == null || c2 == null)
		  return joined;
	  
	  if(c1.element().getLeft().compareTo(c2.element().getLeft()) == 0) {
		  joined.addLast(new Pair<E, Integer>
		  	(c1.element().getLeft(), 
		  			c1.element().getRight() + c2.element().getRight()));
	  }else if(c1.element().getLeft().compareTo(c2.element().getLeft()) < 0){
		  joined.addLast(c1.element());
		  joined.addLast(c2.element());
	  }else if(c1.element().getLeft().compareTo(c2.element().getLeft()) > 0){
		  joined.addLast(c2.element());
		  joined.addLast(c1.element());
	  }
	  return join(l1,l2,joined,l1.next(c1),l2.next(c2));
	  
  }
}
