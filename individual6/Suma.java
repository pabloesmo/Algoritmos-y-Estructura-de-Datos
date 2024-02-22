package aed.individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;

import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.map.HashTableMap;


public class Suma 
{
	//a la izq en el map va el vertice que estamos observando
	//y a la derecha va la suma de los ints que tengan los nodos a los que llegue (adyacentes)
  public static <E> Map<Vertex<Integer>,Integer> sumVertices(DirectedGraph<Integer,E> g) 
  {
	  //Set<Vertex<Integer>> visitados = new HashTableMapSet<>();
	  Map<Vertex<Integer>,Integer> mapResultado = new HashTableMap<>();
	  Iterable<Vertex<Integer>> iterable = g.vertices();
	  Iterator<Vertex<Integer>> it = iterable.iterator();
	  //mientras haya vertices en el grafo
	  while(it.hasNext()) {
		  PositionList<Vertex<Integer>> visitados = new NodePositionList<>();
		  Vertex<Integer> vertice = it.next();
		  int sumaValor = suma(g,vertice,visitados,vertice.element());
		  mapResultado.put(vertice, sumaValor);
	  }
	  return mapResultado;
  }
  
  //funcion recursiva que calcula la suma
  public static <E> int suma(DirectedGraph<Integer,E> g,Vertex<Integer> vertex, PositionList<Vertex<Integer>> visitados, int suma)
  {
	  if(estaVisitado(visitados,vertex)) {
		  suma = 0;
	  }
	  else {
		  visitados.addLast(vertex);
		  Iterator<Edge<E>> aristas = g.outgoingEdges(vertex).iterator();
		  while(aristas.hasNext()) {
			  Edge<E> edge = aristas.next();
			  suma += suma(g,g.endVertex(edge),visitados,g.endVertex(edge).element());
		  }
	  }
	  return suma;
  }


//	private static <E> int suma(DirectedGraph<Integer,E> g,Vertex<Integer> vertex, PositionList<Vertex<Integer>> visitados, int suma)
//	  {
//		  if(estaVisitado(visitados,vertex)) {
//			  return 0;
//		  }
//		  else {
//			  visitados.addLast(vertex);
//			  suma += vertex.element();
//			  Iterator<Edge<E>> aristas = g.outgoingEdges(vertex).iterator();
//			  while(aristas.hasNext()) {
//				  Edge<E> edge = aristas.next();
//				  suma += suma(g,g.endVertex(edge),visitados,0);
//			  }
//		  }
//		  return suma;
//	  }
  
  public static boolean estaVisitado(PositionList<Vertex<Integer>> visitados, Vertex<Integer> vertex)
  {
	  boolean encontrado = false;
	  Position<Vertex<Integer>> vertice = visitados.first();
	  while(vertice != null && !encontrado) {
		  if(vertice.element() == vertex) {
			  encontrado = true;
		  }
		  vertice = visitados.next(vertice);
	  }
	  return encontrado;
  }
  
  
  
}