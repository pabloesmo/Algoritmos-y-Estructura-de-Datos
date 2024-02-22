package aed.positionlistiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;

public class NIterator<E> implements Iterator<E> 
{
	private PositionList<E> list;
	private Position<E> cursor;
	int n;

  public NIterator(PositionList<E> list, int n)
  {
	  if (list == null)
		  throw new IllegalArgumentException();
	  
	  this.list = list;
	  cursor = list.first();
	  this.n = n;
  }

  public boolean hasNext()
  {
    return cursor != null;
  }

  
  //TODO
  public E next() throws NoSuchElementException
  {
	  if (!hasNext())
		  throw new NoSuchElementException();
	  
	  
	  //mientras sea null el primero que me de el siguiente
	  while(esNull(cursor))
	  {
		  cursor = list.next(cursor);
	  }
	  E e = cursor.element();
	  
	  for(int i=0; i<n; i++)
	  {
		  if (hasNext())
			  cursor = list.next(cursor);
	  }
	  return e;
  }
  
  private boolean esNull (Position<E> cursor)
  {
	  return cursor.element() == null; 
  }
}