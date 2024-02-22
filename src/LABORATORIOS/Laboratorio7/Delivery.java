package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import java.util.Iterator;

public class Delivery<V> 
{
	//HACER UN HASH MAP PARA VER VERTICES ANTES DE METER
	//POSITIONLIST PARA VER LOS VERTICES ADYACENTES
	
	DirectedGraph<V,Integer> g=new DirectedAdjacencyListGraph<>();
	HashTableMap<V,Vertex<V>> mapav=new HashTableMap<>();
	PositionList <Vertex<V>> lista=new NodePositionList<Vertex<V>>();
	HashTableMap<Edge<Integer>,Integer> aristas=new HashTableMap<>();
	
 
	// Construct a graph out of a series of vertices and an adjacency matrix.
	// There are 'len' vertices. A negative number means no connection. A non-negative
	// number represents distance between nodes.
  public Delivery(V[] places, Integer[][] gmat) 
  {	
	  //bucle para meter los vertices places
	  for (int i=0; i<places.length; i++) {
		  mapav.put(places[i], g.insertVertex(places[i]));
	  }
	  
	  for (int i=0; i<gmat[0].length; i++) {
		//si hay camino entre los nodos (pongo valor int)
		  for (int j=0; j<gmat.length; j++) {
			  
			  if(gmat[i][j]!=null) {
				 g.insertDirectedEdge(mapav.get(places[i]), mapav.get(places[j]), gmat[i][j]);
			  }
		  }
	  }    
  }
  
  //Just return the graph that was constructed
  public DirectedGraph<V, Integer> getGraph() 
  {
    return g;
  }

  //Return a Hamiltonian path for the stored graph, or null if there is none.
 // The list containts a series of vertices, with no repetitions (even if the path
 // can be expanded to a cycle).
 //SOLO PUEDE PASAR UNA VEZ POR UN NODO
  public PositionList <Vertex<V>> tour() 
  {	  
	  if(g.isEmpty()) {
		  return null;
	  }
	  Iterator<Vertex<V>> vertices=g.vertices().iterator();
	  while(vertices.hasNext()) {
		  if(lista.size()==g.numVertices()) {
			  break;
		  }
		  Vertex<V> v=vertices.next();
		  find(v);
	  }
	  if(lista.size()==g.numVertices()) {
		  return lista;
	  }
	  return null;
  }
  
  private void find(Vertex<V> v) 
  {	  
	  if(g.outDegree(v)==0){
		  lista.addLast(v);
	  }else {
		  lista.addLast(v);
		  Iterator<Edge<Integer>> it =g.outgoingEdges(v).iterator();
		  while(it.hasNext()) {
			  Edge<Integer> edg=it.next();
			  
			  if(!(visitado(g.endVertex(edg)))) {
				  aristas.put(edg, edg.element());
				  find(g.endVertex(edg));
			  }
			  if(!(lista.size()==g.numVertices())) {
				  aristas.remove(edg);
			  }
		  }
	  }
	  if(!(lista.size()==g.numVertices())) {
		  lista.remove(lista.last());
	  }  
  }
  
  private boolean visitado(Vertex<V> v)
  {
	boolean check=false;
	  for (Vertex<V> entry : lista) {
		if(entry==v){
			check=true;
			break;
		}
	  }
	  return check;
  }
  
  public int length(PositionList<Vertex<V>> path) 
  {	  
	  int longitud=0;	  
	  for (Entry<Edge<Integer>,Integer> edge : aristas) {
		  longitud=longitud+edge.getValue();
	  }
    return longitud;
  }

  //el toString no se toca
  public String toString() {
    return "Delivery";
  }
}

