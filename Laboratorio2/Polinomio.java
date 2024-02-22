package aed.polinomios;

import java.util.Arrays;
import java.util.function.BiFunction;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


/**
 * Operaciones sobre polinomios de una variable con coeficientes enteros.
 */
public class Polinomio {

  // Una lista de monomios
  PositionList<Monomio> terms;

  /**
   * Crea el polinomio "0".
   */
  public Polinomio() {
    terms = new NodePositionList<>();
  }

  /**
   * Crea un polinomio definado por una lista con monomios.
   * @param terms una lista de monomios
   */
  public Polinomio(PositionList<Monomio> terms) {
    this.terms = terms;
  }

  /**
   * Crea un polinomio definado por un String.
   * La representación del polinomio es una secuencia de monomios separados
   * por '+' (y posiblemente con caracteres blancos).
   * Un monomio esta compuesto por tres partes,
   * el coefficiente (un entero), el caracter 'x' (el variable), y el exponente
   * compuesto por un un caracter '^' seguido por un entero.
   * Se puede omitir multiples partes de un monomio, 
   * ejemplos:
   * <pre>
   * {@code
   * new Polinomio("2x^3 + 9");
   * new Polinomio("2x^3 + -9");
   * new Polinomio("x");   // == 1x^1
   * new Polinomio("5");   // == 5x^0
   * new Polinomio("8x");  // == 8x^1
   * new Polinomio("0");   // == 0x^0
   * }
   * </pre>
   * @throws IllegalArgumentException si el argumento es malformado
   * @param polinomio - una secuencia de monomios separados por '+'
   */
  public Polinomio(String polinomio) {
    throw new RuntimeException("no esta implementado todavia");
  }

  /**
   * Suma dos polinomios.
   * @param p1 primer polinomio.
   * @param p2 segundo polinomio.
   * @return la suma de los polinomios.
   */
   
  public static Polinomio suma(Polinomio p1, Polinomio p2) {
	Polinomio solucion = new Polinomio();  
	Position<Monomio> c1 = p1.terms.first();
	Position<Monomio> c2 = p2.terms.first();
	int suma;
	if(p1.terms.first() == null) {
		solucion = p2;
	}else if(p2.terms.first() == null) {
		solucion = p1;
	}else {
		while(c1 != null && c2 != null) {
			
			if(c1.element().getExponente() == c2.element().getExponente()) 
			{
				suma = c1.element().getCoeficiente() + c2.element().getCoeficiente();
				solucion.terms.addLast(new Monomio(suma, c1.element().getExponente()));
				c2 = p2.terms.next(c2);
				c1 = p1.terms.next(c1);
			} 
			else if(c1.element().getExponente() > c2.element().getExponente()) 
			{
				solucion.terms.addLast(c1.element());
				c1 = p1.terms.next(c1);
			} 
			else if(c1.element().getExponente() < c2.element().getExponente()) 
			{
				solucion.terms.addLast(c2.element());
				c2 = p2.terms.next(c2);
			}
		}
		if(c1 == null) {
			while(c2 != null) {
				solucion.terms.addLast(c2.element());
				c2 = p2.terms.next(c2);
			}
		} else if(c2 == null) {
			while(c1 != null) {
				solucion.terms.addLast(c1.element());
				c1 = p1.terms.next(c1);
			}
		}
	}
	return solucion;
  }
  
  /**
   * Substraccion de dos polinomios.
   * @param p1 primer polinomio.
   * @param p2 segundo polinomio.
   * @return la resta de los polinomios.
   */
  public static Polinomio resta(Polinomio p1, Polinomio p2) {
	  Polinomio solucion = new Polinomio();  
		Position<Monomio> c1 = p1.terms.first();
		Position<Monomio> c2 = p2.terms.first();
		int resta;
		if(p1.terms.first() == null) {
			while(c2 != null) {		
				solucion.terms.addLast(new Monomio(-c2.element().getCoeficiente(),c2.element().getExponente()));
				c2 = p2.terms.next(c2);
			}
		}else if(p2.terms.first() == null) {
			solucion = p1;
		}else {
			while(c1 != null && c2 != null) {
				
				if(c1.element().getExponente() == c2.element().getExponente()) 
				{
					if(c1.element().getCoeficiente() != c2.element().getCoeficiente()) {
						resta = c1.element().getCoeficiente() - c2.element().getCoeficiente();
						solucion.terms.addLast(new Monomio(resta, c1.element().getExponente()));
						c2 = p2.terms.next(c2);
						c1 = p1.terms.next(c1);
					}else {
						c2 = p2.terms.next(c2);
						c1 = p1.terms.next(c1);
					}
				} 
				else if(c1.element().getExponente() > c2.element().getExponente()) 
				{
					solucion.terms.addLast(c1.element());
					c1 = p1.terms.next(c1);
				} 
				else if(c1.element().getExponente() < c2.element().getExponente()) 
				{
					solucion.terms.addLast(new Monomio(-c2.element().getCoeficiente(),c2.element().getExponente()));
					c2 = p2.terms.next(c2);
				}
			}
			if(c1 == null) {
				while(c2 != null) {
					solucion.terms.addLast(new Monomio(-c2.element().getCoeficiente(),c2.element().getExponente()));
					c2 = p2.terms.next(c2);
				}
			} else if(c2 == null) {
				while(c1 != null) {
					solucion.terms.addLast(c1.element());
					c1 = p1.terms.next(c1);
				}
			}
		}
		return solucion;

  }
    
  /**
   * Calcula el producto de dos polinomios.
   * @param p1 primer polinomio.
   * @param p2 segundo polinomio.
   * @return el producto de los polinomios.
   */
  public static Polinomio multiplica(Polinomio p1, Polinomio p2) {
	  	Polinomio solucion = new Polinomio();
		Position<Monomio> cursor = p1.terms.first();
		while(cursor != null){
			solucion = suma(solucion,multiplica(cursor.element(),p2));
			cursor = p1.terms.next(cursor);
		}
		return solucion;
  }

  /**
   * Calcula el producto de un monomio y un polinomio.
   * @param m el monomio
   * @param p el polinomio
   * @return el producto del monomio y el polinomio
   */
  public static Polinomio multiplica(Monomio m, Polinomio p) {
		Polinomio solucion = new Polinomio();
		Position<Monomio> cursor = p.terms.first();
		while(cursor != null){
			solucion.terms.addLast(new Monomio(m.getCoeficiente() * cursor.element().getCoeficiente(),
					m.getExponente() + cursor.element().getExponente()));
			cursor = p.terms.next(cursor);
		}
		return solucion;
  }
    
  /**
   * Devuelve el valor del polinomio cuando su variable es sustiuida por un valor concreto.
   * Si el polinomio es vacio (la representacion del polinomio "0") entonces
   * el valor devuelto debe ser -1.
   * @param valor el valor asignado a la variable del polinomio
   * @return el valor del polinomio para ese valor de la variable.
   */
  public long evaluar(int valor) {
	  Position<Monomio> cursor = terms.first();
	  long solucion = 0; 
		  while(cursor != null) {
			  solucion = (long) (solucion + cursor.element().getCoeficiente()* Math.pow(valor,cursor.element().getExponente())); 
			  cursor = terms.next(cursor);  
		  }  
	return solucion;
  }
  
//  private int pow(long mantisa, int exponente) {
//	  
//  }
  

  /**
   * Devuelve el exponente (grado) del monomio con el mayor grado
   * dentro del polinomio
   * @return el grado del polinomio
   */
  public int grado() {
	int grado = -1;
	Position<Monomio> cursor = terms.first();
	while(cursor != null) {
		if(grado < cursor.element().getExponente()) {
			grado = cursor.element().getExponente();
		}
		cursor = terms.next(cursor);
	}
    return grado;
  }

  @Override
  public String toString() {
    if (terms.isEmpty()) return "0";
    else {
      StringBuffer buf = new StringBuffer();
      Position<Monomio> cursor = terms.first();
      while (cursor != null) {
        Monomio p = cursor.element();
        int coef = p.getCoeficiente();
        int exp = p.getExponente();
        buf.append(new Integer(coef).toString());
        if (exp > 0) {
          buf.append("x");
          buf.append("^");
          buf.append(new Integer(exp)).toString();
        }
        cursor = terms.next(cursor);
        if (cursor != null) buf.append(" + ");
      }
      return buf.toString();
    }
  }
}
