package aed.Clases;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.UndirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.priorityqueue.SortedListPriorityQueue;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.tree.Tree;

public class Examen2 
{
	public static <E> void addBeforeElement(PositionList<E> list, E e1, E e2)
	{
		Position<E> cursor = list.first();
		while(cursor != null) {
			if(cursor.element() == e2) {
				list.addBefore(cursor, e1);
			}
			cursor = list.next(cursor);
		}
	}
	
	public static <E> Position<E> find(PositionList<E> list, E e) 
	{
		Position<E> cursorRes = null;
		Position<E> cursor = list.first();
		while(cursor!=null) {
			if(cursor.element().equals(e)) {
				cursorRes = cursor;
			}
			cursor = list.next(cursor);
		}
		return cursorRes;
	}
	

	public static <E> void replace(IndexedList<E> list, E e1, E e2)
	{
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(e1)) {
				list.set(i, e2);
			}
		}
	}
	
	//METODO ITERATIVO
	public static <E> int getNumApariciones (PositionList<E> lista, E elem)
	{
		int contador = 0;
		Position<E> cursor = lista.first();
		while(cursor!=null) {
			if(elem.equals(cursor.element())) {
				contador++;
			}
			cursor = lista.next(cursor);
		}
		return contador;
	}
	
	public static <E> int getNumAparicionesRec (PositionList<E> lista, E elem)
	{
		if(lista.isEmpty()) {
			return 0;
		}
		
		int contador = 0;
		
		if(elem.equals(lista.first().element())) {
			contador++;
		}
		lista.remove(lista.first());
		return contador + getNumAparicionesRec(lista,elem);
	}
	
	
	public static <E> void eliminarConsecIguales(PositionList<E> lista)
	{
		Position<E> cursor = lista.first();
		while(cursor != null) {
			if(cursor.element().equals(lista.next(cursor).element())) {
				lista.remove(cursor);
			}
		}
		cursor = lista.next(cursor);
	}
	
	public static <E> PositionList<E> copiarHastaElem (PositionList<E> list, E elem) 
	{
		PositionList<E> listaRes = new NodePositionList<>();
		Position<E> cursor = list.first();
		while(cursor != null && cursor.element() != elem) {
			listaRes.addLast(cursor.element());
			cursor = list.next(cursor);
		}
		return listaRes;
	}
	
	public static <E> PositionList<E> invertir (PositionList<E> list)
	{
		PositionList<E> listaRes = new NodePositionList<>();
		Position<E> cursor = list.first();
		while(cursor != null) {
			listaRes.addFirst(cursor.element());
			cursor = list.next(cursor);
		}
		return listaRes;
	}
	
	public static <E> PositionList<E> copyElemsNbyN (PositionList<E> list, int n) throws IllegalArgumentException
	{
		if(n<=0) { throw new IllegalArgumentException(); }
		PositionList<E> listaRes = new NodePositionList<>();
		Position<E> cursor = list.first();
		int pos = 0;
		while(cursor != null) {
			if(pos % n == 0) {
				listaRes.addLast(cursor.element());
			}
			pos++;
			cursor = list.next(cursor);
		}
		return listaRes;
	}
	
	public static <E> void quitarIguales (PositionList<E> list, E elem) throws IllegalArgumentException
	{
		if(list == null) {throw new IllegalArgumentException();}
		Position<E> cursor = list.first();
		while(cursor != null) {
			if(cursor.element().equals(elem)) {
				list.remove(cursor);
			}
			cursor = list.next(cursor);
		}
	}
	
	public static <E> void multiplyAndClean (PositionList<Integer> list, int n)
	{
		Position<Integer> cursor = list.first();
		Integer valor = 0;
		while(cursor != null) {
			if(cursor.element() != null) {
				valor = cursor.element()*n;
				list.set(cursor, valor);
			}
			cursor = list.next(cursor);
		}
	}
	
	
	public static boolean esSerieFibonacci (Iterable<Integer> iterable)
	{
		Iterator<Integer> it = iterable.iterator();
		boolean loEs = true;
		if(!it.hasNext()) {
			return loEs;
		}
		Integer prev = it.next();
		if(!it.hasNext()) {
			return loEs;
		}
		Integer sig = it.next();
		
		while(it.hasNext() && loEs) {
			Integer actual = it.next();
			loEs = actual.equals(prev+sig);
			prev = sig;
			sig = actual;
		}
		return loEs;
	}
	
	public static <E> void eliminarRepeticiones (PositionList<E> list) throws IllegalArgumentException
	{
		if(list==null) {
			throw new IllegalArgumentException();
		}
		Position<E> cursor = list.first();
		int contador = 0;
		while(cursor != null) {
			quitarRepeticionesDesde(list,cursor);
			cursor = list.next(cursor);
		}
	}
	
	
	private static <E> void quitarRepeticionesDesde(PositionList<E> list, Position<E> cursor)
	{
		E e = cursor.element();
		cursor = list.next(cursor);
		while(cursor != null) {
			Position<E> next = list.next(cursor);
			if(eqNull(e,cursor.element())) {
				list.remove(cursor);
			}
			cursor = next;
		}
	}

	private static <E> boolean eqNull (Object o1, Object o2) 
	{
		return o1 == o2 || o1 != null && o1.equals(o2);
	}
	
	
	public static PositionList<Integer> copiarHastaSumN (PositionList<Integer> lista, int n) throws IllegalArgumentException
	{
		if(n<=0) { throw new IllegalArgumentException(); }
		PositionList<Integer> nueva = new NodePositionList<>();
		int suma = 0;
		Position<Integer> cursor = lista.first();
		while(cursor != null && suma < n) {
			if(n==1) {break;}
			suma += cursor.element();
			nueva.addLast(cursor.element());
			cursor = lista.next(cursor);
		}
		return nueva;
	}
	
	public static <E> PositionList<Integer> multiplyAndClean2 (PositionList<Integer> list, int n) throws IllegalArgumentException
	{
		if(list == null || n<=0) { throw new IllegalArgumentException(); }
		PositionList<Integer> nueva = new NodePositionList<>();
		Position<Integer> cursor = list.first();
		while(cursor != null) {
			if(cursor.element() != null) {
				nueva.addLast(cursor.element()*n);
			}
			cursor = list.next(cursor);
		}
		return nueva;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////PRIORITY QUEUES////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public class Alumno
	{
		private Integer dni; 
		private String apellidos;
		
		public Alumno(Integer dni, String apellidos) { 
			this.dni = dni;
			this.apellidos = apellidos;
		}
		
		public Integer getDni() {
			return dni; 
		}
		
		public String getApellidos() { 
			return apellidos;
		}
	}
	
	public static Alumno[] ordenarAlumnos (Alumno[] alumnos)
	{
		Alumno[] nuevo = new Alumno[alumnos.length];
		PriorityQueue<Integer,Alumno> cola = new SortedListPriorityQueue<>();
		for (int i=0; i<alumnos.length; i++) {
			cola.enqueue(alumnos[i].getDni(), alumnos[i]);
		}
		int i=0;
		while(!cola.isEmpty()) {
			nuevo[i] = cola.dequeue().getValue();
			i++;
		}
		return nuevo;
	}
	
	public static <E> PositionList<String> ordenarPorNota (Map<String,Integer> map)
	{
		PositionList<String> nueva = new NodePositionList<>();
		PriorityQueue<String,Integer> cola = new HeapPriorityQueue<>();
		for(Entry<String,Integer> e : map.entries()) {
			cola.enqueue(e.getKey(), e.getValue());
		}
		while(!cola.isEmpty()) {
			nueva.addLast(cola.dequeue().getKey());
		}
		return nueva;
	}
	
	public static <E> PositionList<E> ordenarDescPorApariciones (Map<E,Integer> map)
	{
		PositionList<E> resul = new NodePositionList<>();
		PriorityQueue<Integer,E> cola = new HeapPriorityQueue<>();
		for(Entry<E,Integer> e : map.entries()) {
			cola.enqueue(e.getValue(), e.getKey());
		}
		while(!cola.isEmpty()) {
			resul.addFirst(cola.dequeue().getValue());
		}
		return resul;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////ARBOLES////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static <E> boolean existeHoja (Tree<E> tree, E e)
	{
		return existeHojaAux(tree,e,tree.root());
	}
	
	
	private static<E> boolean existeHojaAux(Tree<E> tree, E e, Position<E> cursor) 
	{
		boolean existe = false;
		if(cursor == null || !tree.isExternal(cursor)) {
			return false;
		}
		if(tree.isExternal(cursor) && eqNull(e,cursor.element())) {
			return true;
		}
		Iterator<Position<E>> it = tree.children(cursor).iterator();
		while(it.hasNext() && !existe) {
			existe = existeHojaAux(tree,e,it.next());
		}
		return existe;
	}
	
	public static boolean estanHijosOrdenadosRec (BinaryTree<Integer> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		return estanHijosOrdenadosRecAux(tree,tree.root());
	}
	
	
	//PARA TODO == TODO HA IDO BIEN /// QUE INCUMPLE LA CONDICION
	private static boolean estanHijosOrdenadosRecAux(BinaryTree<Integer> tree, Position<Integer> nodo) 
	{
		if(tree.isExternal(nodo)) {
			return true;
		}
		if(tree.left(nodo).element() > tree.right(nodo).element()) {
			return false;
		}
		return estanHijosOrdenadosRecAux(tree,tree.left(nodo)) && 
				estanHijosOrdenadosRecAux(tree,tree.right(nodo));
	}

	
	public static <E> void imprimirCaminosHojas (Tree<E> tree)
	{
		imprimirCaminosHojasAux(tree,tree.root(), new ArrayList<E>());
	}
	
	
	private static <E> void imprimirCaminosHojasAux(Tree<E> tree, Position<E> cursor, List<E> camino) 
	{
		if(tree.isExternal(cursor)) {
			camino.add(cursor.element());
			System.out.println(camino);
		}
		else {
			camino.add(cursor.element());
			for(Position<E> hijos : tree.children(cursor)) {
				imprimirCaminosHojasAux(tree,hijos, new ArrayList<E>(camino));	
			}
		}
	}
	
	
	public static double maximoCamino (BinaryTree<Integer> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		return maximoCaminoAux(tree,tree.root());
	}

	private static Integer maximoCaminoAux(BinaryTree<Integer> tree, Position<Integer> cursor) 
	{
		Integer sumLeft = 0;
		Integer sumRight = 0;
		
		if(tree.hasLeft(cursor)) {
			sumLeft = maximoCaminoAux(tree,tree.left(cursor));
		}
		if(tree.hasRight(cursor)) {
			sumLeft = maximoCaminoAux(tree,tree.right(cursor));
		}
		return cursor.element() + Math.max(sumLeft, sumRight);
	}

	
	
	public <E> void inOrder (BinaryTree<E> tree)
	{
		inOrderAux(tree,tree.root());
	}
	
	private <E> void inOrderAux(BinaryTree<E> tree, Position<E> cursor) 
	{
		if(tree.hasLeft(cursor)) {
			inOrderAux(tree,tree.left(cursor));
		}
		
		System.out.println(cursor.element());
		
		if(tree.hasRight(cursor)) {
			inOrderAux(tree,tree.right(cursor));
		}
	}
	
	
	public static boolean member (BinaryTree<Integer> tree, Position<Integer> cursor, Integer elem)
	{
		if(cursor == null) {
			return false;
		}
		else if(elem == cursor.element()) {
			return true;
		}
		else if(elem < cursor.element()) {
			return member(tree,tree.left(cursor),elem);
		}
		else 
			return member(tree,tree.right(cursor),elem);
	}
	
	
	public static void printPreOrder (Tree<Integer> tree)
	{
		if(tree.isEmpty() || tree == null) {
			throw new IllegalArgumentException();
		}
		printPreOrderAux(tree,tree.root());
	}
	
	private static void printPreOrderAux(Tree<Integer> tree,Position<Integer> cursor)
	{
		System.out.println(cursor.element());
		for(Position<Integer> hijos : tree.children(cursor)) {
			printPreOrderAux(tree,hijos);
		}
	}
	
	public static void printPostOrder (Tree<Integer> tree)
	{
		if(tree.isEmpty() || tree == null) {
			throw new IllegalArgumentException();
		}
		printPostOrderAux(tree,tree.root());
	}
	
	private static void printPostOrderAux(Tree<Integer> tree,Position<Integer> cursor)
	{
		for(Position<Integer> hijos : tree.children(cursor)) {
			printPostOrderAux(tree,hijos);
		}
		System.out.println(cursor.element());
	}
	
	public static PositionList<Integer> hojasEnRangoRec (Tree<Integer> tree, int a, int b)
	{
		if(tree == null || tree.isEmpty() || b < a) {
			throw new IllegalArgumentException();
		}
		PositionList<Integer> res = new NodePositionList<>();
		if(tree.isEmpty()) {
			return res;
		}
		hojasEnRangoRecAux(tree,a,b,tree.root(),res);
		return res;
	}
	
	private static void hojasEnRangoRecAux(Tree<Integer> tree, int a, int b, Position<Integer> cursor,
			PositionList<Integer> res) 
	{
		if(tree.isExternal(cursor) && a <= cursor.element() && b >= cursor.element()) {
			res.addLast(cursor.element());
		}
		for(Position<Integer> hijos : tree.children(cursor)) {
			hojasEnRangoRecAux(tree,a,b,hijos,res);
		}
	}
	
	public static boolean hayHojasEnRangoRec(Tree<Integer> tree, int a, int b)
	{
		if(tree == null || b < a) {
			throw new IllegalArgumentException();
		}
		if(tree.isEmpty()) {
			return false;
		}
		return hayHojasEnRangoRecAux(tree,a,b,tree.root());
	}
	
	//REVISAR!!!!!!!
	private static boolean hayHojasEnRangoRecAux(Tree<Integer> tree, int a, int b,Position<Integer> cursor)
	{
		if(tree.isExternal(cursor) && a <= cursor.element() && b >= cursor.element()) {
			return true;
		}
		boolean encontrada = false;
		for(Position<Integer> hijos : tree.children(cursor)) {
			encontrada = hayHojasEnRangoRecAux(tree,a,b,hijos);
			if(encontrada) {
				break;
			}
		}
		return encontrada;
	}
	
	public static <E> void show (BinaryTree<E> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		showAux(tree,tree.root());
	}
	
	public static <E> void showAux (BinaryTree<E> tree, Position<E> cursor)
	{
		if(tree.hasLeft(cursor)) {
			showAux(tree,tree.left(cursor));
		}
		System.out.println(cursor.element());
		
		if(tree.hasRight(cursor)) {
			showAux(tree,tree.right(cursor));
		}
	}
	
	public static <E> int height(BinaryTree<E> tree, Position<E> p) 
	{
	    if (tree.isExternal(p)) {
	        return 0;
	    }
	    int leftHeight = 0;
	    int rightHeight = 0;
	    
	    if(tree.hasLeft(p)) {
	    	leftHeight = height(tree,tree.left(p));
	    }
	    if(tree.hasRight(p)) {
	    	rightHeight = height(tree,tree.left(p));
	    }
	    return 1 + Math.max(leftHeight, rightHeight);
	}
	
	public <E> int numHojas (BinaryTree<E> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		return numHojasAux(tree,tree.root());
	}

	public <E> int numHojasAux (BinaryTree<E> tree, Position<E> cursor)
	{
		int numLeft = 0;
		int numRight = 0;
		
		if(tree.isExternal(cursor)) {
			return 1;
		}
		if(tree.hasLeft(cursor)) {
			numLeft = numHojasAux(tree,tree.left(cursor));
	    }
	    if(tree.hasRight(cursor)) {
	    	numRight = numHojasAux(tree,tree.left(cursor));
	    }
	    return numLeft + numRight;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////REPASO/////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void aumentarPrecio (Map<String,Double> precios, String prod, Double coste)
	{
		Double precio = precios.get(prod);
		precio = (precio != null ? coste + precio : coste);
		precios.put(prod, precio);
	}

	
	//recursivo
	public static <E> int sumaElementosPositivos (PositionList<Integer> list)
	{
		if (list == null) { throw new IllegalArgumentException();}
		return sumaElementosPositivosAux(list,list.first());
	}
	
	
	//Lo primero es CASO BASE
	private static int sumaElementosPositivosAux(PositionList<Integer> list, Position<Integer> cursor) 
	{
		//no quedan elementos por recorrer
		if(cursor == null) {
			return 0;
		}
		//evaluar si es null o no (delante check de seguridad de null y luego ya que sea positivo
		if(cursor.element() != null && cursor.element() > 0) {
			return cursor.element() + sumaElementosPositivosAux(list,list.next(cursor));
		}
		else {
			return sumaElementosPositivosAux(list,list.next(cursor));
		}
	}
	
	//a y b son elementos
	//elems que hay entre [4,8] siendo [a=4,b=8]
	public static PositionList<Integer> cuantosEnRango (PositionList<Integer> list, int a, int b)
	{
		if(list == null || b<a) { throw new IllegalArgumentException();}
		PositionList<Integer> lista = new NodePositionList<>();
		cuantosEnRangoAux(list,a,b,list.first(),lista);
		return lista;
	}
	
	
	private static void cuantosEnRangoAux(PositionList<Integer> list, int a, int b,
			Position<Integer> cursor,PositionList<Integer> lista) 
	{
		//Caso base
		if(cursor == null) {
			return;
		}
		if(cursor.element() >= a && cursor.element() <= b) {
			lista.addLast(cursor.element());
		}
		cuantosEnRangoAux(list,a,b,list.next(cursor),lista);
	}
	
	public static boolean todosEnRango (PositionList<Integer> list, int a, int b)
	{
		if(list == null || b < a) { throw new IllegalArgumentException();}
		return todosEnRangoAux(list,a,b,list.first());
	}
	
	
	
	private static boolean todosEnRangoAux(PositionList<Integer> list, int a, int b, Position<Integer> cursor) 
	{
		//no he terminado la lista
		if(cursor == null) {
			return true;
		}
		//no incumplo propiedad basica
		if(cursor.element() < a || cursor.element() > b) {
			return false;
		}
		return todosEnRangoAux(list,a,b,list.next(cursor));
	}
	
	
	public static <E> void imprimirPorOrdenApariciones (Map<E,Integer> map)
	{
		PriorityQueue<Integer,E> cola = new HeapPriorityQueue<>();
		for(Entry<E,Integer> e : map.entries()) {
			cola.enqueue(e.getValue(), e.getKey());
		}
		while(!cola.isEmpty()) {
			System.out.println(cola.dequeue().getValue());
		}
	}
	
	public static boolean estanHijosOrdenados (BinaryTree<Integer> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		return estanHijosOrdenadosAux(tree,tree.root());
	}
	
	
	//PARA TODO == TODO HA IDO BIEN /// QUE INCUMPLE LA CONDICION
	private static boolean estanHijosOrdenadosAux(BinaryTree<Integer> tree, Position<Integer> cursor) 
	{
		//he llegado al final de la estructura
		if(tree.isExternal(cursor)) {
			return true;
		}
		//ahora sabemos despues de este if que es interno
		//el hijo izquierdo tiene q ser mayor al derecho
		//compruebo si incumple la condicion
		if(tree.left(cursor).element() > tree.right(cursor).element()) {
			return false;
		}
		//sabemos que tenemos hasLeft y hasRight
		return estanHijosOrdenadosAux(tree,tree.left(cursor)) && 
				estanHijosOrdenadosAux(tree,tree.right(cursor));
	}
	
	
	//devuelve el cjto de vertices que sean alcanzables siempre que se pase por vertices con elems positivos
	public static <E> Set<Vertex<Integer>> getVerticesAlcanzables (DirectedGraph<Integer,E> g, Vertex<Integer> n)
	{
		Set<Vertex<Integer>> visited = new HashTableMapSet<>();
		getVerticesAlcanzablesAux(g,n,visited);
		return visited;
	}
	

	private static <E> void getVerticesAlcanzablesAux(DirectedGraph<Integer, E> g, Vertex<Integer> n,
			Set<Vertex<Integer>> visited) 
	{
		//caso en el que es negativo o ya contiene el Set ese vertice se devuelve [nada] 
		if(visited.contains(n) || n.element() < 0) {
			return;
		}
		visited.add(n);
		for (Edge<E> edge : g.outgoingEdges(n)) {
			getVerticesAlcanzablesAux(g,g.endVertex(edge),visited);
		}
	}
	
	public static PositionList<Integer> hojasEnRango (Tree<Integer> tree, int a, int b)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		PositionList<Integer> lista = new NodePositionList<>();
		if(tree.isEmpty()) {
			return lista;
		}
		hojasEnRangoAux(tree,tree.root(),a,b,lista);
		return lista;
	}
	

	private static void hojasEnRangoAux(Tree<Integer> tree, Position<Integer> cursor, int a, int b,
			PositionList<Integer> lista) 
	{
		if(tree.isExternal(cursor) && cursor.element() >= a && cursor.element() <= b) {
			lista.addLast(cursor.element());
		}
		//para todos los hijos del cursor
		for(Position<Integer> hijo : tree.children(cursor)) {
			hojasEnRangoAux(tree,hijo,a,b,lista);
		}
	}
	
	
	public static boolean existeHojaEnRango (Tree<Integer> tree, int a, int b)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		if(tree.isEmpty()) {
			return false;
		}
		return existeHojaEnRangoAux (tree,tree.root(),a,b);
	}
	

	private static boolean existeHojaEnRangoAux (Tree<Integer> tree, Position<Integer> cursor, int a, int b) 
	{
		if(tree.isExternal(cursor) && cursor.element() >= a && cursor.element() <= b) {
			return true;
		}
		//caso de que sea externo y no este en el rango
		//para todos los hijos del cursor
		boolean found = false;
		for(Position<Integer> hijo : tree.children(cursor)) {
			found = existeHojaEnRangoAux(tree,hijo,a,b);
			//ya ha encontrado uno asiq corto
			if(found) {
				break;
			}
		}
		return found;
	}
	
	
	public static Map<Character,Integer> contarApariciones (String texto)
	{
		Map<Character,Integer> mapa = new HashTableMap<>();
		int contador = 0;
		for(int i=0; i < texto.length(); i++) {
			if(mapa.containsKey(texto.charAt(i))) {
				mapa.put(texto.charAt(i), contador++);	
			}
			else {
				mapa.put(texto.charAt(i), 1);
			}
		}
		return mapa;
	}
	
	public static <E> Map<E,Integer> contarInstancias (PositionList<E> input)
	{
		Map<E,Integer> mapa = new HashTableMap<>();
		Position<E> cursor = input.first();
		int contador = 0;
		while(cursor != null) {
			if(mapa.containsKey(cursor.element())) {
				mapa.put(cursor.element(), contador++);
			}
			else {
				mapa.put(cursor.element(), 1);
			}
			cursor = input.next(cursor);
		}
		return mapa;
	}
	
	
	public static Map<String,String> invertir (Map<String,String> map)
	{
		Map<String,String> mapa = new HashTableMap<>();
		for(Entry<String,String> e: map.entries()) {
			mapa.put(e.getValue(), e.getKey());
		}
		return mapa;
	}
	
	
	public static <E> PositionList<E> leaves (Tree<E> tree)
	{
		PositionList<E> lista = new NodePositionList<>();
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		if(tree.isEmpty()) {
			return lista;
		}
		leavesAux(tree,tree.root(),lista);
		return lista;
	}
		
	private static <E> void leavesAux(Tree<E> tree, Position<E> nodo, PositionList<E> lista) 
	{
		if(tree.isExternal(nodo)) {
			lista.addLast(nodo.element());
		}
		for(Position<E> w : tree.children(nodo)) {
			leavesAux(tree,w,lista);
		}
	}
	
	
	public static <E> boolean siblings (Tree<E> tree, Position<E> p1, Position<E> p2)
	{
		return p1 != p2 && !tree.isRoot(p1) && !tree.isRoot(p2) 
				&& tree.parent(p1) == tree.parent(p2);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////GRAFOS/////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static <V,E> boolean isReachable (UndirectedGraph<V,E> g, Vertex<V> from, Vertex<V> to)
	{
		Set<Vertex<V>> visited = new HashTableMapSet<>();
		if(g == null || from == null || to == null || g.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return isReachableAux(g,from,to,visited);
	}
	
	
	private static <V,E> boolean isReachableAux(UndirectedGraph<V, E> g, Vertex<V> from, Vertex<V> to,
			Set<Vertex<V>> visited) 
	{
		//1er cambio
		if(from == to) {
			return true;
		}
		boolean alcanzable = false;		
		visited.add(from);
		
		Iterator<Edge<E>> it = g.edges(from).iterator();
		//2do cambio
		while(it.hasNext() && !alcanzable) {
			Vertex<V> other = g.opposite(from, it.next());
			if(!visited.contains(other)) {
				//3er cambio
				alcanzable = isReachableAux(g,other,to,visited);
			}
		}
		return alcanzable;
	}
	
	
	public static <V,E> Set<Vertex<V>> getVerticesAlcanzables (UndirectedGraph<V,E> g, Vertex<V> n)
	{
		if(g == null) {
			throw new IllegalArgumentException();
		}
		Set<Vertex<V>> vertices = new HashTableMapSet<>();
		getVerticesAlcanzablesRec(g,n,vertices);
		return vertices;	
	}
	

	private static <V,E> void getVerticesAlcanzablesRec(UndirectedGraph<V, E> g, Vertex<V> n, Set<Vertex<V>> vertices) 
	{
		//caso de parada
		if(vertices.contains(n)) {
			return;
		}
		//este es el que hace algo en la funcion si no esta en el hashmap ya metido
		vertices.add(n);
		
		for(Edge<E> e: g.edges(n)) {
			getVerticesAlcanzablesRec(g,g.opposite(n, e),vertices);
		}
	}
	
	public static <V,E> PositionList<Vertex<V>> reachableNodesWithGrade (DirectedGraph<V,E> g, Vertex<V> v, int grado)
	{
		Set<Vertex<V>> visited = new HashTableMapSet<>();
		PositionList<Vertex<V>> res = new NodePositionList<>();
		reachableNodesWithGradeAux(g,v,grado,visited,res);
		return res;
	}

	public static <V,E> void reachableNodesWithGradeAux(DirectedGraph<V,E> g, Vertex<V> v, int grado, 
			Set<Vertex<V>> visited, PositionList<Vertex<V>> res)
	{
		//si ya esta visitado
		if(visited.contains(v)) {
			return;
		}
		
		if(g.outDegree(v) == grado) {
			res.addLast(v);
		}
		visited.add(v);
		
		//vertice alcanzable desde v que tiene mismo grado SALIENTE que "grado"
		for(Edge<E> e : g.outgoingEdges(v)) {
			reachableNodesWithGradeAux(g,g.endVertex(e),grado,visited,res);
		}	
	}
	
	
	public static <V,E> int numReachableInSteps (UndirectedGraph<V,E> g, Vertex<V> v, int steps)
	{
		Set<Vertex<V>> visited = new HashTableMapSet<>();
		visited.add(v);
		numReachableInStepsAux(g,v,visited,steps);
		return visited.size();
	}

	public static <V,E> void numReachableInStepsAux(UndirectedGraph<V,E> g, Vertex<V> v, 
			Set<Vertex<V>> visited, int steps)
	{
		if (steps == 0 || visited.contains(v)) {
			return;
		}
		visited.add(v);
		for(Edge<E> e: g.edges(v)) {
			numReachableInStepsAux(g,g.opposite(v, e),visited,steps-1);
		}
	}
	
	
	//devuelve el cjto de vertices que sean alcanzables siempre que se pase por vertices con elems positivos
	public static <E> Set<Vertex<Integer>> getVerticesAlcanzablesRec (DirectedGraph<Integer,E> g, Vertex<Integer> n)
	{
		Set<Vertex<Integer>> visited = new HashTableMapSet<>();
		getVerticesAlcanzablesRecAux(g,n,visited);
		return visited;
	}
	
	private static <E> void getVerticesAlcanzablesRecAux (DirectedGraph<Integer, E> g, Vertex<Integer> n,
			Set<Vertex<Integer>> visited) 
	{
		//si ya lo contiene o es negativo no se hace nada
		if(visited.contains(n) || n.element() < 0) {
			return;
		}
		visited.add(n);
		
		for(Edge<E> e : g.outgoingEdges(n)) {
			getVerticesAlcanzablesRecAux(g,g.endVertex(e),visited);
		}
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////RECURSIVIDAD///////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public static <E> void showRev (PositionList<E> list)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		showRevAux(list,list.last());
	}
	
	private static <E> void showRevAux(PositionList<E> list, Position<E> cursor) 
	{
		if(cursor != null) {
			showRevAux(list,list.prev(cursor));
			System.out.println(cursor.element());
		}
	}
	
	public static int sumaElems (PositionList<Integer> list)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		return sumaElemsAux(list,list.first());
	}
	
	private static int sumaElemsAux(PositionList<Integer> list, Position<Integer> cursor)
	{
		if(cursor == null) {
			return 0;
		}
		return cursor.element() + sumaElemsAux(list,list.next(cursor));
	}
	
	
	public static<E> void borraElemento (PositionList<E> list, E elem)
	{
		if(list == null || list.isEmpty()) {
			throw new IllegalArgumentException();
		}
		borraElementoAux(list,elem,list.first());
	}
	
	private static <E> void borraElementoAux(PositionList<E> list, E elem,Position<E> cursor)
	{
		if(cursor != null) {
			if(cursor.element() != null && cursor.element().equals(elem)) {
				list.remove(cursor);
			}
			else
			borraElementoAux(list,elem,list.next(cursor));
		}
	}
	
	public static <E> void borraTodos (PositionList<E> list, E elem)
	{
		if(list == null || list.isEmpty()) {
			throw new IllegalArgumentException();
		}
		borraTodosAux(list,elem,list.first());
	}
	

	private static <E> void borraTodosAux(PositionList<E> list, E elem, Position<E> cursor) 
	{
		if(cursor == null) {
			return;
		}
		Position<E> aux = cursor;
		cursor = list.next(cursor);
		if(eqNull(aux.element(),elem)) {
			list.remove(aux);
		}
		borraTodosAux(list,elem,cursor);
	}
	
	
	public static <E> PositionList<E> invertirRec (PositionList<E> list)
	{
		if(list == null) {
			return null;
		}
		PositionList<E> res = new NodePositionList<>();
		invertirRecAux(list,list.first(),res);
		return res;
	}
	

	private static <E> void invertirRecAux (PositionList<E> list, Position<E> cursor, PositionList<E> res) 
	{
		if(cursor == null) {
			return;
		}
		res.addFirst(cursor.element());
		invertirRecAux(list,list.next(cursor),res);
	}
	
	public static double average (PositionList<Integer> list)
	{
		if(list == null || list.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return averageRec(list,list.first()) / list.size();
	}
	
	
	private static double averageRec(PositionList<Integer> list, Position<Integer> cursor) 
	{
		if(cursor == null) {
			return 0;	
		}
		return cursor.element() + averageRec(list,list.next(cursor));
	}
	
	
	public static <E> int countApariciones (PositionList<E> list, E elem)
	{
		if(list == null) {
			return 0;
		}
		return countAparicionesAux(list,elem,list.first(),0);
	}
	

	private static <E> int countAparicionesAux(PositionList<E> list, E elem, Position<E> cursor, int cont) 
	{
		if(cursor == null) {
			return cont;
		} 
		if(eqNull(cursor.element(),elem)) {
			cont++;
		}
		return countAparicionesAux(list,elem,list.next(cursor),cont);
	}
	
	
	public static<E> PositionList<E> copiarHastaElemRec (PositionList<E> list, E elem)
	{
		PositionList<E> res = new NodePositionList<>();
		if(list == null) {
			throw new IllegalArgumentException();
		}
		copiarHastaElemRecAux(list,elem,list.first(),res);
		return res;
	}
	
	private static <E> void copiarHastaElemRecAux (PositionList<E> list, E elem, Position<E> cursor, PositionList<E> res) 
	{
		//caso de parada
		if(cursor == null || elem.equals(cursor.element())) {
			return;
		}
		res.addLast(cursor.element());
		copiarHastaElemRecAux(list,elem,list.next(cursor),res);
	}
	
	public static int sumaElementosPositivosRec (PositionList<Integer> list)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		return sumaElementosPositivosRecAux(list,list.first());
	}
	
	private static int sumaElementosPositivosRecAux (PositionList<Integer> list, Position<Integer> cursor)
	{
		int suma = 0;
		if(cursor == null) {
			return 0;
		}
		//si no es null y es positivo
		if (cursor.element() != null && cursor.element() > 0) {
			suma += cursor.element();
		}
		return suma + sumaElementosPositivosRecAux(list, list.next(cursor));
	}
	
	
	public static <E> PositionList<Integer> multiplyAndCleanRec (PositionList<Integer> list, int n)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		PositionList<Integer> res = new NodePositionList<>();
		multiplyAndCleanRecAux(list,n,list.first(),res);
		return res;
	}
	
	private static void multiplyAndCleanRecAux(PositionList<Integer> list, int n, Position<Integer> cursor, PositionList<Integer> res) 
	{
		if(cursor == null) {
			return;
		}
		if(cursor.element() != null) {
			res.addLast(cursor.element()*n);
		}
		multiplyAndCleanRecAux(list,n,list.next(cursor),res);
	}

	public static int cuantosEnRangoRec (PositionList<Integer> list, int a, int b)
	{
		if(list == null || b < a) {
			return 0;
		}
		return cuantosEnRangoRecAux(list,a,b,list.first());
	}
	
	
	private static int cuantosEnRangoRecAux(PositionList<Integer> list, int a, int b, Position<Integer> cursor) 
	{
		int contador = 0;
		if(cursor == null) {
			return 0;
		}
		//si esta en el rango pedido
		if(cursor.element() >= a && cursor.element() <= b) {
			contador++;
		}
		return contador + cuantosEnRangoRecAux(list,a,b,list.next(cursor));
		
	}
	
	public static boolean hayEnRango (PositionList<Integer> list, int a, int b)
	{
		if(b < a) {
			return false;
		}
		return hayEnRangoAux(list,a,b,list.first());
	}
	

	private static boolean hayEnRangoAux(PositionList<Integer> list, int a, int b, Position<Integer> cursor) 
	{
		if(cursor == null) {
			return false;
		}
		if(a <= cursor.element() && b >= cursor.element()) {
			return true;
		}
		return hayEnRangoAux(list,a,b,list.next(cursor));
	}
	
	
	public static <E> PositionList<E> elementosUnicos (PositionList<E> lista)
	{
		PositionList<E> res = new NodePositionList<>();
		Map<E,Integer> mapaFrec = new HashTableMap<>();
		int count = 0;
		Position<E> cursor = lista.first();
		while(cursor != null) {
			if(mapaFrec.containsKey(cursor.element())) {
				mapaFrec.put(cursor.element(),count++);
			}
			else {
				mapaFrec.put(cursor.element(), 1);
			}
			cursor = lista.next(cursor);
		}
		for(Entry<E,Integer> e : mapaFrec.entries()) {
			if(e.getValue().equals(1)) {
				res.addLast(e.getKey());
			}
		}
		return res;
	}
		
	
	public static <E> int getApariciones (PositionList<Pair<E,Integer>> mset, E elem)
	{
		int res = 0;
		Position<Pair<E,Integer>> cursor = mset.first();
		while(cursor != null) {
			if(cursor.element().getLeft().equals(elem)) {
				res = cursor.element().getRight();
			}
			cursor = mset.next(cursor);
		}
		return res;
	}
	
	
	public static boolean estaOrdenada (PositionList<Integer> list) 
	{
		boolean ordenada = true;
		if(list.isEmpty()) {
			return true;
		}
		Iterator<Integer> it = list.iterator();
		Integer primero = it.next();
		while(it.hasNext() && ordenada) {
			if(primero > it.next()) {
				ordenada = false;
			}
		}
		return ordenada;
	}
	
	
	public static int sumaElementosRec (PositionList<Integer> list)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		return sumaElementosRecAux(list,list.first());
	}
	
	private static int sumaElementosRecAux(PositionList<Integer> list, Position<Integer> cursor) 
	{
		if(cursor == null) {
			return 0;
		}
		return cursor.element() + sumaElementosRecAux(list,list.next(cursor));
	}
	
	
	public static <E> PositionList<E> quitarIgualesRec (PositionList<E> list, E elem)
	{
		if(list==null) {
			return null;
		}
		PositionList<E> res = new NodePositionList<>();
		quitarIgualesRecAux(list,elem,list.first(),res);
		return res;
	}
	
	private static <E> void quitarIgualesRecAux(PositionList<E> list, E elem, Position<E> cursor,PositionList<E> res) 
	{
		if(cursor == null) {
			return;
		}
		if(cursor.element() != elem) {
			res.addLast(cursor.element());
		}
		quitarIgualesRecAux(list,elem,list.next(cursor),res);
	}
	
	
	public static <E> boolean member (Iterable<E> iterable, E elem) 
	{
		boolean found = false;
		if(iterable == null || elem == null) {
			throw new IllegalArgumentException();
		}
		Iterator<E> it = iterable.iterator();
		while(it.hasNext() && !found) {
			if(eqNull(it.next(),elem)) {
				found = true;
			}
		}
		return found;
	}
	
	public static <E> void eliminarRepeticionesV2 (PositionList<E> list)
	{
		if(list == null) {
			throw new IllegalArgumentException();
		}
		Position<E> cursor = list.first();
		while(cursor != null) {
			quitarRepeticionesDesdeV2(list,cursor);
			cursor = list.next(cursor);
		}
	}
	
	private static <E> void quitarRepeticionesDesdeV2(PositionList<E> list,Position<E> cursor)
	{
		E e = cursor.element();
		cursor = list.next(cursor);
		while(cursor != null) {
			Position<E> next = list.next(cursor);
			if(eqNull(e,cursor.element())) {
				list.remove(cursor);
			}
			cursor = next;
		}
	}
	
	
	public static <E> PositionList<E> leavesRec (Tree<E> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		PositionList<E> res = new NodePositionList<>();
		if(tree.isEmpty()) {
			return res;
		}
		leavesRecAux(tree,tree.root(),res);
		return res;
	}
	
	public static <E> void leavesRecAux (Tree<E> tree, Position<E> cursor, PositionList<E> res)
	{
		if(tree.isExternal(cursor)) {
			res.addLast(cursor.element());
		}
		for(Position<E> hijos : tree.children(cursor)) {
			leavesRecAux(tree,hijos,res);
		}
	}
	
	
	//que tienen el mismo nodo padre
	public static <E> boolean siblingsRec (Tree<E> tree, Position<E> p1, Position<E> p2)
	{
		boolean hermanos = false;
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		if(p1 == p2) {
			hermanos = true;
		}
		if(tree.parent(p1) == tree.parent(p2)) {
			hermanos = true;
		}
		return hermanos;
	}
	
	
	public static <E> PositionList<E> ancestros (Tree<E> tree, Position<E> cursor)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		PositionList<E> res = new NodePositionList<E>();
		ancestrosAux(tree,cursor,tree.root(),res);
		return res;
	}
	
	
	//nodo current es ancestro de cursor si [current == cursor]
	//[current es padre de cursor] || [current es ancestro del padre de cursor]
	public static <E> void ancestrosAux (Tree<E> tree, Position<E> cursor, Position<E> current,PositionList<E> res) 
	{
		if(tree.isRoot(cursor)) {
			return;
		}
		if(current == cursor || tree.parent(cursor) == current) {
			res.addLast(current.element());
		}
		ancestrosAux(tree,cursor,tree.parent(current),res);
	}
	
	
	public static <E> PositionList<E> ancestros2 (Tree<E> tree, Position<E> cursor)
	{
		PositionList<E> res = new NodePositionList<E>();
		while(!tree.isRoot(cursor)) {
			cursor = tree.parent(cursor);
			res.addLast(cursor.element());
		}
		return res;
	}
	
	public Iterable<Integer> sumarTrios (Iterable<Integer> iterable)
	{
		PositionList<Integer> lista = new NodePositionList<>();
		Iterator<Integer> it = iterable.iterator();
		if(!it.hasNext()) {
			return lista;
		}
		Integer prev = it.next();
		if(!it.hasNext()) {
			return lista;
		}
		Integer next = it.next();
		while(it.hasNext()) {
			Integer current = it.next();
			lista.addLast(prev + next + current);
			prev = next;
			next = current;
		}
		return lista;
	}
	
	
	public static <E> boolean compruebaSec (PositionList<E> sec, Map<E,E> mapa)
	{
		if(sec.size() < 2) {
			throw new IllegalArgumentException();
		}
		boolean res = true;
		E prev = sec.first().element();
		if(!mapa.containsKey(prev)) {
			res = false;
		}
		Position<E> cursor = sec.next(sec.first());
		while(cursor != null) {
			//el elem E de la derecha no es igual al sig en la lista
			if(!mapa.get(prev).equals(cursor.element())) {
				res = false;
			}
			prev = cursor.element();
			cursor = sec.next(cursor);
		}
		return res;
	}
	
	public static <E> PositionList<E> intercalar (PositionList<E> l1,PositionList<E> l2)
	{
		if(l1.size() != l2.size()) {
			throw new IllegalArgumentException();
		}
		PositionList<E> res = new NodePositionList<>();
		intercalarAux(l1,l2,l1.first(),l2.first(),res);
		return res;
	}
	
	public static <E> void intercalarAux (PositionList<E> l1,PositionList<E> l2,
			Position<E> cursor1, Position<E> cursor2, PositionList<E> res)
	{
		if(cursor1 == null || cursor2 == null) {
			return;
		}
		res.addLast(cursor1.element());
		res.addLast(cursor2.element());
		intercalarAux(l1,l2,l1.next(cursor1),l2.next(cursor2),res);
	}
	
	
	public static boolean esSerieGeometrica (Iterable<Integer> iterable)
	{
		boolean res = true;
		Iterator<Integer> it = iterable.iterator();
		if(!it.hasNext()) {
			return false;
		}
		Integer prev = it.next();
		if(!it.hasNext()) {
			return false;
		}
		while(it.hasNext() && res) {
			Integer sig = it.next();
			res = sig.equals(prev*2);
			prev = sig;
		}
		return res;
	}
	
	
	public static <E> PositionList<E> barajar (PositionList<E> list) 
	{
		if(list.size() < 1 || list.size() % 2 == 0) {
			throw new IllegalArgumentException();
		}
		PositionList<E> res = new NodePositionList<>();
		barajarAux(list,list.first(),list.last(),res);
		return res;
	}
	

	private static <E> void barajarAux(PositionList<E> list, Position<E> c1, Position<E> c2, PositionList<E> res) 
	{
		//cuando llegan al medio los dos (metemos cualquiera y paramos)
		if(c1 == c2) {
			res.addLast(c1.element());
			return;
		}
		res.addLast(c1.element());
		res.addLast(c2.element());
		barajarAux(list,list.next(c1),list.prev(c2),res);
	}
	
	
	public static PositionList<Pair<Integer,Integer>> getDistanciasMayores (Iterable<Integer> iterable, int min)
	{
		if (min < 0) {
			throw new IllegalArgumentException();
		}
		PositionList<Pair<Integer,Integer>> res = new NodePositionList<>();
		Iterator<Integer> it = iterable.iterator(); 
		Integer prev = null;
		if(!it.hasNext()) {
			prev = it.next();
		}
		while(it.hasNext()) {
			Integer sig = it.next();
			Integer distancia = Math.abs(prev - sig);
			if(distancia > min) {
				res.addLast(new Pair<>(prev,sig));
			}
			prev = sig;
		}
		return res;
	}
	
	
	public static boolean hasHeapPropertyGen (Tree<Integer> tree)
	{
		if(tree.isEmpty()) {
			return false;
		}
		return hasHeapPropertyGenAux(tree, tree.root());
	}
	
	public static boolean hasHeapPropertyGenAux(Tree<Integer> tree, Position<Integer> cursor)
	{
		boolean res = true;
		if(tree.parent(cursor) != null && cursor.element() < tree.parent(cursor).element()) {
			return false;
		}
		for(Position<Integer> hijos : tree.children(cursor)) {
			res = hasHeapPropertyGenAux(tree,hijos);
			if(!res) {
				break;
			}
		}
		return res;
	}
	
	public static boolean hasHeapProperty (BinaryTree<Integer> tree)
	{
		if(tree.isEmpty()) {
			return true;
		}
		return hasHeapPropertyAux(tree,tree.root());
	}
	
	public static boolean hasHeapPropertyAux(BinaryTree<Integer> tree, Position<Integer> cursor)
	{
		if(tree.parent(cursor) != null && cursor.element() < tree.parent(cursor).element()) {
			return false;
		}
		boolean res = true;
		if(tree.hasLeft(cursor)) {
			res = hasHeapPropertyAux(tree,tree.left(cursor));
		}
		if(res && tree.hasLeft(cursor)) {
			res = hasHeapPropertyAux(tree,tree.right(cursor));
		}
		return res;
	}
		
	
	class Book{ 
		private String nombre;
		private Double precio;
		
		public Book(String nombre, Double precio) { 
			this.nombre = nombre;
			this.precio = precio;
		}
		
		public Double getPrecio() {
			return precio; 
		}
		
		public String getNombre() { 
			return nombre;
		}
		
		public String toString() {
			return "(" + precio + "," + nombre + ")";
		}
	}

	public static void printCheapestBooks(int nElems, Book[] books)
	{
		PriorityQueue<Double, Book> pq = new HeapPriorityQueue<>();
		for(Book book : books) {
			//1er ERROR
			pq.enqueue(book.getPrecio(), book);
		}
		int i=0;
		//2do ERROR
		while(i < nElems && !pq.isEmpty()) {
			//3er ERROR
			Book book = pq.dequeue().getValue();
			System.out.println("El libro " + book.getNombre() + " vale " + book.getPrecio());
			i++;
		}
	}
	
	public static boolean estanHijosOrdenadosV2 (BinaryTree<Integer> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		if(tree.isEmpty()) {
			return true;
		}
		return estanHijosOrdenadosV2Aux(tree,tree.root());
	}
	
	public static boolean estanHijosOrdenadosV2Aux (BinaryTree<Integer> tree, Position<Integer> cursor)
	{
		if(tree.isExternal(cursor)) {
			return true;
		}
		if(tree.left(cursor).element() > tree.right(cursor).element()) {
			return false;
		}
		return estanHijosOrdenadosV2Aux(tree,tree.left(cursor)) && 
				estanHijosOrdenadosV2Aux(tree,tree.right(cursor));
	}
	
	
	public <E> Set<Vertex<Integer>> getVerticesAlcanzablesV2 (DirectedGraph<Integer,E> g,Vertex<Integer> n)
	{
		Set<Vertex<Integer>> visited = new HashTableMapSet<>();
		visited.add(n);
		getVerticesAlcanzablesV2Aux(g,n,visited);
		return visited;
	}
	
	public <E> void getVerticesAlcanzablesV2Aux (DirectedGraph<Integer,E> g,Vertex<Integer> n, Set<Vertex<Integer>> visited)
	{
		//si esta ya visitado o es negativo salimos
		if(visited.contains(n) || n.element() < 0) {
			return;
		}
		//si no se ha visitado lo visito
		visited.add(n);
		
		//todas las aristas que salen de mi vertice y llegan a otros
		for(Edge<E> e : g.outgoingEdges(n)) {
			//vuelvo a poner los vertices a los que llego en visited llamando
			//recursivo con el vertice que he alcanzado en la arista previa
			getVerticesAlcanzablesV2Aux(g,g.endVertex(e),visited);
		}
	}
	
	public static Integer maximoCaminoV2 (BinaryTree<Integer> tree)
	{
		if(tree == null) {
			throw new IllegalArgumentException();
		}
		return maximoCaminoV2Aux(tree, tree.root());
	}
	

	private static Integer maximoCaminoV2Aux (BinaryTree<Integer> tree, Position<Integer> cursor) 
	{
		Integer sumaD = 0;
		Integer sumaI = 0;
		if(tree.hasLeft(cursor)) {
			sumaI = maximoCaminoV2Aux(tree,tree.left(cursor));
		}
		if(tree.hasRight(cursor)) {
			sumaD = maximoCaminoV2Aux(tree,tree.right(cursor));
		}
		return cursor.element() + Math.max(sumaI, sumaD);
	}
	
	
	public static Map<String,String> invertirV2 (Map<String,String> map)
	{
		Map<String,String> mapa = new HashTableMap<>();
		for(Entry<String,String> entry : map.entries()) {
			mapa.put(entry.getValue(), entry.getKey());
		}
		return mapa;
	}
	
	
	public static boolean esSerieFibonacciV2 (Iterable<Integer> iterable)
	{
		boolean loes = true;
		Iterator<Integer> it = iterable.iterator();
		Integer prev = it.next();
		if(!it.hasNext()) {
			return true;
		}
		Integer sig = it.next();
		while(it.hasNext() && loes) {
			Integer current = it.next();
			Integer suma = prev + sig;
			if(!suma.equals(current)) {
				loes = false;
			}
			prev = sig;
			sig = current;
		}
		return loes;
	}
	
	public static <E> Iterable<E> obtenerSecuencia (Map<E,E> map, E pos)
	{
		PositionList<E> res = new NodePositionList<>();
		Map<E,E> mapAux = new HashTableMap<>();
		//mientras este en el dado y no este ya en el auxiliar
		while(map.containsKey(pos) && !mapAux.containsKey(pos)) {
			mapAux.put(pos, pos);
			res.addLast(pos);
			pos = map.get(pos);
		}
		res.addLast(pos);
		return res;
	}

	
	

	public static void main(String[] args)
	{
		
		class Alumno
		{
			private Integer dni; 
			private String apellidos;
			
			public Alumno(Integer dni, String apellidos) { 
				this.dni = dni;
				this.apellidos = apellidos;
			}
			
			public Integer getDni() {
				return dni; 
			}
			
			public String getApellidos() { 
				return apellidos;
			}
			
			public String toString() {
				return "(" + dni + "," + apellidos + ")";
			}
		}
					
		GeneralTree<Integer> tree = new LinkedGeneralTree<>();
		tree.addRoot(1);
		Position<Integer> n2 = tree.addChildLast(tree.root(), 2);
		Position<Integer> n3 = tree.addChildLast(tree.root(), 3);
		Position<Integer> n4 = tree.addChildLast(n2, 4);
		Position<Integer> n5 = tree.addChildLast(n2, 5);
		Position<Integer> n6 = tree.addChildLast(n2, 6);
		Position<Integer> n7 = tree.addChildLast(n3, 7);
		Position<Integer> n8 = tree.addChildLast(n3, 8);
		Position<Integer> n9 = tree.addChildLast(n8, 9);
//		System.out.println(tree.toString());
//		imprimirCaminosHojas(tree);
//		printPreOrder(tree);
//		System.out.println("\n");
//		printPostOrder(tree);
//		System.out.println(leavesRec(tree));
		
		GeneralTree<Integer> tree2 = new LinkedGeneralTree<>();
		tree2.addRoot(1);
		Position<Integer> nodo2 = tree2.addChildLast(tree2.root(), 2);
		Position<Integer> nodo3 = tree2.addChildLast(tree2.root(), 3);
		Position<Integer> nodo4 = tree2.addChildLast(nodo2, 4);
		Position<Integer> nodo5 = tree2.addChildLast(nodo2, 5);
		Position<Integer> nodo6 = tree2.addChildLast(nodo2, 6);
		Position<Integer> nodo7 = tree2.addChildLast(nodo3, 7);
		Position<Integer> nodo8 = tree2.addChildLast(nodo3, 8);
		Position<Integer> nodo9 = tree2.addChildLast(nodo8, 9);
//		System.out.println(tree2.toString());
//		System.out.println(ancestros(tree2,nodo5));
		
		
		Map<String, Double> mapa = new HashTableMap<>();
		mapa.put("1",10.0);
		mapa.put("3",13.0);
//		aumentarPrecio(mapa,"1",2.0);
//		System.out.println(mapa.toString());
		
		
		
		/*Alumno a1 = new Alumno(100, "Esgueva");
		Alumno a2 = new Alumno(200, "Ponce");
		Alumno a3 = new Alumno(300, "Martinez");
		Alumno a4 = new Alumno(400, "Perez");
		Alumno a5 = new Alumno(500, "Velasco");
		
		Alumno[] alumnos = {a3,a5,a2,a1,a4};
		Alumno[] nuevo = new Alumno[alumnos.length];
		for(int i=0; i<alumnos.length; i++) {
			cola.enqueue(alumnos[i].getDni(), alumnos[i]);
		}
		int i=0;
		while(!cola.isEmpty()) {
			nuevo[i] = cola.dequeue().getValue();
			i++;
		}
		System.out.print("COLA");
		System.out.print("\n");
		System.out.print(cola.toString());
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("ARRAY (ALUMNOS)");
		System.out.print("\n");
		System.out.print(Arrays.toString(alumnos));
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("ARRAY (NUEVO)");
		System.out.print("\n");
		System.out.print(Arrays.toString(nuevo));
		*/
		
		
		
//		PositionList<Integer> lista = new NodePositionList<>();
//		lista.addLast(1);
//		lista.addLast(2);
//		lista.addLast(3);
//		lista.addLast(8);
//		lista.addLast(4);
//		lista.addLast(5);
//		lista.addLast(6);
//		System.out.println(lista.toString());
		
//		System.out.println(sumaElems(lista));
//		borraElemento(lista,3);
//		borraTodos(lista,3);
//		System.out.println(invertirRec(lista));
//		System.out.println(sumaElementosPositivosRec(lista));
//		System.out.println(multiplyAndCleanRec(lista,2));
//		System.out.println(cuantosEnRangoRec(lista,1,4));
//		System.out.println(hayEnRango(lista,7,10));
//		System.out.println(elementosUnicos(lista));
//		System.out.println(estaOrdenada(lista));
//		System.out.println(sumaElementosRec(lista));
//		System.out.println(quitarIgualesRec(lista,8));
//		System.out.println(member(lista,4));
//		System.out.println(barajar(lista));
		

		
//		String texto = "En un lugar de La Mancha";
//		Map<Character,Integer> mapa = new HashTableMap<>();
//		int contador = 0;
//		for(int i=0; i < texto.length(); i++) {
//			if(mapa.containsKey(texto.charAt(i))) {
//				mapa.put(texto.charAt(i), contador++);	
//			}
//			else {
//				mapa.put(texto.charAt(i), 1);
//			}	
//		}
//		System.out.println(mapa.toString());
		
//		double precio = 2;
//		double p1 = 10;
//		double p2 = 13;
//		mapa.put("1", p1);
//		mapa.put("3", p2);
//		aumentarPrecio(mapa,"1",precio);
//		System.out.println(mapa.toString());
//		imprimirPorOrdenApariciones(mapa);
//		System.out.print(ordenarPorNota(mapa));
//		mapa.put("a", 10);
//		mapa.put("b", 3);
//		mapa.put("c", 5);
//		System.out.println(ordenarDescPorApariciones(mapa));
		
		
//		for(Entry<String,Integer> e : mapa.entries()) {
//			cola.enqueue(e.getKey(), e.getValue());
//		}
//		while(!cola.isEmpty()) {
//			res.addLast(cola.dequeue().getKey());
//		}
//		System.out.println("COLA");
//		System.out.println(cola.toString());
//		System.out.println("\n");
//		System.out.println("\n");
//		System.out.println("MAPA");
//		System.out.println(mapa.toString());
//		System.out.println("\n");
//		System.out.println("\n");
//		System.out.println("LISTA (NUEVA)");
//		System.out.println(res.toString());
//		System.out.println("\n");
		
		
//		PositionList<Integer> listaG = new NodePositionList<>();
//		listaG.addLast(2);
//		listaG.addLast(4);
//		listaG.addLast(8);
//		listaG.addLast(16);
//		listaG.addLast(32);
//		System.out.println("EJERCICIO SERIE GEOMETRICA");
//		System.out.println(esSerieGeometrica(listaG));
//		System.out.println(sumaElementos(lista));
		
//		System.out.println(multiplyAndClean2(lista,2));
//		System.out.println(copiarHastaSumN(lista,8));
//		System.out.println(lista.toString());
//		multiplyAndClean(lista,3);
//		System.out.println(lista.toString());
//		quitarIguales(lista,3);
//		System.out.println(copyElemsNbyN(lista,2));

//		PositionList<Character> listaChars = new NodePositionList<>();
//		listaChars.addLast('t');
//		listaChars.addLast('u');		
//		listaChars.addLast('b');
//		listaChars.addLast('o');
//		listaChars.addLast('s');
//		System.out.println(listaChars.toString());
//		System.out.println(contarInstancias(listaChars));
		
		
		
//		System.out.println(listaChars.toString());
//		System.out.println(copyElemsNbyN(listaChars,5));
		
//		System.out.println(invertir(lista));
//		System.out.println(copiarHastaElem(lista,3));
//		System.out.println(lista.toString());
//		addBeforeElement(lista,4,5);
//		System.out.println(find(lista,3));
//		System.out.println(getNumAparicionesRec(lista,3));
		
//		IndexedList<Integer> list = new ArrayIndexedList<>();
//		list.add(0, 1);
//		list.add(1, 2);
//		list.add(2, 3);
//		list.add(3, 3);
//		list.add(4, 3);
//		list.add(5, 5);
//		System.out.println(list.toString());
//		replace(list, 3, 8);
//		System.out.println(list.toString());	
	}
}