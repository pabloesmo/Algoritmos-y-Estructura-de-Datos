package aed.tries;

import java.util.Iterator;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl_copia implements Dictionary {
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl_copia() {
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<Character,Boolean>(null,false));
	}

	// Devuelve el nodo cuyo camino desde la raiz contiene
	// la palabra prefix. Si no existe el metodo devuelve null.
	//LUEGO USO != NULL COMO EN searchChildLabelledBy
	private Position<Pair<Character,Boolean>> findPos(String prefix) 
	{
		Position<Pair<Character,Boolean>> result = null;
		Position<Pair<Character, Boolean>> ultHijo = tree.root();
		boolean exit = false;
		
		for(int i = 0; i < prefix.length() && !exit; i++){
			boolean res = false;
			Iterator<Position<Pair<Character, Boolean>>> it = tree.children(ultHijo).iterator();
			
			while(!res && it.hasNext()){
				Position<Pair<Character, Boolean>> aux = it.next();
				//comparador de chars (comparo si > y salgo)
				if(aux.element().getLeft() > prefix.charAt(i)){
					res = true;
					exit = true;
				}
				else if(aux.element().getLeft().equals(prefix.charAt(i))){
					result = aux;
					ultHijo = aux;
					res = true;
				}				
			}
		}
		if(exit == true) {
			return null;
		}
		else {
			return result;
		}
	}

	private Position<Pair<Character,Boolean>> searchChildLabelledBy(char ch, Position<Pair<Character,Boolean>> nodo)
	  {
		  Position<Pair<Character,Boolean>> hijoRes = null;
		  if(tree.isExternal(nodo)) {
			  return null;
		  }
		  for(Position<Pair<Character,Boolean>> hijos : tree.children(nodo)) {
			  if (hijos.element().getLeft().compareTo(ch) == 0) {
				  hijoRes = hijos;
			  }
		  }
		return hijoRes;
	  }

	//añade un hijo de forma alfabetica
	private Position<Pair<Character,Boolean>> addChildAlphabetically(Pair<Character,Boolean> pair, Position<Pair<Character,Boolean>> pos) 
	{
		boolean añadido = false;
		Iterator<Position<Pair<Character, Boolean>>> it = tree.children(pos).iterator();
		Position<Pair<Character, Boolean>> elem = null;
		Position<Pair<Character, Boolean>> sibling = null;	
		//bucle tipico con boolean para arboles
		while(it.hasNext() && !añadido)
		{
			elem = it.next();
			if(elem != null && elem.element().getLeft() > pair.getLeft()){
				sibling = tree.insertSiblingBefore(elem, pair);
				añadido = true;
			}
		}
		if(añadido == false){
			if(elem != null)
				sibling = tree.insertSiblingAfter(elem, pair);
			else {
				sibling = tree.addChildLast(pos, pair);
			}
		}	
		return sibling;
	}

	
	public void add(String word) throws IllegalArgumentException
	  {
		//misma cond que en los demas
		  if(word == null || word.equals("")) {
			 throw new IllegalArgumentException();
		  }
		  addAux(word,tree.root(),0);
	  }
	
	private void addAux(String word, Position<Pair<Character,Boolean>> nodo, int pos)
	  {
			  Position<Pair<Character,Boolean>> nodohijo;
			  Position<Pair<Character,Boolean>> ultHijo = tree.root();
			  for(int i=0; i< word.length(); i++) {
				  nodohijo = searchChildLabelledBy(word.charAt(i), ultHijo);
				  
				  if(nodohijo == null) {
					  ultHijo = addChildAlphabetically(new Pair<Character, Boolean>(word.charAt(i), i == (word.length() -1)), ultHijo);
				  }
				  else {
					  ultHijo = nodohijo;
					  if(i == word.length()-1)
					  {
						  ultHijo.element().setRight(true);
					  }
				  }
			  }
	  }

	public void delete(String word) throws IllegalArgumentException
	{ 
		//misma cond que en las demás
		if(word == null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		//EVALUAR FINDPOS
		Position<Pair<Character,Boolean>> nodo = findPos(word);	
		if(nodo != null)
		{
			nodo.element().setRight(false);
		}
	}
	
	public boolean isIncluded(String word) throws IllegalArgumentException
	{ 
		//misma cond que en las demás
		if(word == null || word.equals("")) { 
			throw new IllegalArgumentException();
		}
		//EVALUAR FINDPOS
		Position<Pair<Character, Boolean>> nodo = findPos(word);
		if(nodo != null) {
			return nodo.element().getRight();
		}
		else {
			return false;
		}
	}
	
	
	private String sumaChar (String palabra,char c) {
		return palabra + c;
	}
	
	public PositionList<String> wordsBeginningWithPrefix(String prefix) throws IllegalArgumentException
	{
		//misma cond que en las demas
        if (prefix==null) {
            throw new IllegalArgumentException();
        }
        PositionList<String> res = new NodePositionList<>();
        
        //si no me dan nada como palabra "prefijo"
        if(prefix.equals("")){
        	return wordsBeginningWithPrefixAux(prefix,res,tree.root());
        }
        //EVALUAR FINDPOS
        Position<Pair<Character, Boolean>> nodo = findPos(prefix);
        if (nodo==null){
            return res;
        }
        if (nodo.element().getRight() == true){
            res.addLast(prefix);
        }      
        return wordsBeginningWithPrefixAux(prefix,res,nodo);    
    }

    private PositionList<String> wordsBeginningWithPrefixAux(String prefix,PositionList<String> result,Position<Pair<Character, Boolean>> nodo)
    {
        Iterator<Position<Pair<Character, Boolean>>> it = tree.children(nodo).iterator();
        while (it.hasNext()){
            Position<Pair<Character, Boolean>> aux = it.next();
            if (aux.element().getRight() == true){
                result.addLast(sumaChar(prefix,aux.element().getLeft()));
            }
            wordsBeginningWithPrefixAux(sumaChar(prefix,aux.element().getLeft()),result,aux);
        }
        return result;
    }
}
