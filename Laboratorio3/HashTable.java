package aed.hashtable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.InvalidKeyException;


/**
 * A hash table implementing using open addressing to handle key collisions.
 */
public class HashTable<K,V> implements Map<K,V> 
{
	//Array de pares cada uno con clave y valor
	Entry<K,V>[] buckets;
	int size;

  public HashTable(int initialSize)
  {
    this.buckets = createArray(initialSize);
    this.size = 0;
  }

   /* Add here the method necessary to implement the Map api, and
   * any auxilliary methods you deem convient.*/
  // Examples of auxilliary methods: IT IS NOT REQUIRED TO IMPLEMENT THEM
  
  @SuppressWarnings("unchecked") 
  private Entry<K,V>[] createArray(int size) 
  {
   Entry<K,V>[] buckets = (Entry<K,V>[]) new Entry[size];
   return buckets;
  }

  
  public Iterator<Entry<K, V>> iterator()
  {
	  //Creamos nuestro propio iterador de pares (Entries)
	  Iterator<Entry<K, V>> iteradorHash = new Iterator<Entry<K,V>>()
	  {
		  private int x=0;
		public boolean hasNext()
		{
			return buckets.length > x;
		}

		public Entry<K, V> next() throws NoSuchElementException
		{
			if(!hasNext())
				throw new NoSuchElementException();
			
			Entry<K,V> parResultado = null;
			for(int i=0; i<buckets.length; i++)
			{
				parResultado = buckets[i++];
			}
			return parResultado;
		}
	  };
	  return iteradorHash;
  }

  public boolean containsKey(Object arg0) throws InvalidKeyException
  {
	  boolean contenida = false;
	  if(arg0 == null)
		  throw new InvalidKeyException();
	  
	  while(iterator().hasNext())
	  {
		  if(iterator().next().getKey().equals(arg0))
			  contenida = true;
	  }
	  return contenida;
  }

  public Iterable<Entry<K, V>> entries()
  {
	  return null;
  }
  
  public Iterable<K> keys()
  {
	  /*
	  Iterator<Entry<K,V>> it = iterator();
	  while(it.hasNext())
	  {
		  it.next().getKey();  
	  }
	  */
	  return null;
  }
 
  
  public V get(K arg0) throws InvalidKeyException
  {
	  V valorResultado = null;
	  if (arg0 == null)
		  throw new InvalidKeyException();
	  for(int i=0; i<buckets.length; i++)
	  {
		  if(buckets[i].getKey().equals(arg0))
		  {
			  valorResultado = buckets[i].getValue();
		  }
	  }
	  return valorResultado;
  }


  public boolean isEmpty()
  {
  	return buckets.length != 0;
  }


  public V put(K arg0, V arg1) throws InvalidKeyException
  {
	  if (arg0 == null)
		  throw new InvalidKeyException();
	  
	  int key = funcionDeHash(arg0);
	  Entry<K,V> temporal = buckets[key];
	  while(temporal != null)
	  {
		  if ((temporal.getKey() == null && arg0 == null) 
                  || (temporal.getKey() != null && temporal.getKey().equals(arg0)))
		  {
              arg1 = temporal.getValue();
              return arg1;
		  }
		  //temporal = temporal.next;
	  }
	  return arg1;
  }
	  
	/*
	 * Si la llave de ese par en el bucle es igual a la que me dan de arg entonces
	 * metemos el arg1 (value dado) en ese par y devolvemos el que habia previamente
	 * En caso de q no estuviese con value previo, lo metemos y devuelve null
	 */

  public V remove(K arg0) throws InvalidKeyException
  {
  	return null;
  }

  public int size() 
  {
	  return size;
  }
    
  
  /* Formula para ver lugar donde guardar Entry aplicando 
   * funcion de compresion para reducirlo al tamaño del vector:
   *  index(key) ≡ abs(key.hashCode()) mod size(table)*/
  private int funcionDeHash(K arg0)
  {
	  if (arg0 == null) {
		  return 0;
		  }
	  else{
		  return Math.abs(arg0.hashCode()) % buckets.length;
	  }
  }
  
  // Returns the bucket index of an object
  private int index(Object obj)
  {
	  int pos=0;
	  for(int i=0; i<buckets.length; i++)
	  {
		  /*si la entry q este en esa pos es igual
		   * al objeto q nos dan obtener la pos */
		  if(buckets[i].equals(obj))
			  pos = i;
	  }
	  return pos;
  }

  /*Returns the index where an entry with the key is located,
  or if no such entry exists, the "next" bucket with no entry,
  or if all buckets stores an entry, -1 is returned.
  */
  private int search(Object obj) {
    return -1;
  }

  /*Doubles the size of the bucket array, and inserts all entries present
  in the old bucket array into the new bucket array, in their correct
  places. Remember that the index of an entry will likely change in
  the new array, as the size of the array changes.*/
  private void rehash() {
    Entry[] newBuckets = createArray(buckets.length*2);
    Entry[] oldBuckets = buckets;
    buckets = newBuckets;
  }


} 