package aed.urgencias;

/**
 * Un paciente.
 */
public class Paciente implements Comparable<Paciente> {

  // DNI
  private String DNI;
  // Prioridad 
  private int prioridad;
  // Tiempo de admision en las urgencias
  private int tiempoAdmision;
  // Tiempo cuando entro en la prioridad 
  private int tiempoAdmisionEnPrioridad;

  /**
   * Constructor.
   */
  public Paciente(String DNI, int prioridad, int tiempoAdmision, int tiempoAdmisionEnPrioridad)
  {
    this.DNI = DNI;
    this.prioridad = prioridad;
    this.tiempoAdmision = tiempoAdmision;
    this.tiempoAdmisionEnPrioridad = tiempoAdmisionEnPrioridad;
  }

  /**
   * Devuelve el dni.
   * @return el dni.
   */
  public String getDNI() {
    return DNI;
  }

  /**
   * Devuelve la prioridad.
   * @return la prioridad.
   */
  public int getPrioridad() {
    return prioridad;
  }

  /**
   * Devuelve el tiempo de admision.
   * @return el tiempo de admision.
   */
  public int getTiempoAdmision() {
    return tiempoAdmision;
  }

  /**
   * Devuelve el tiempo de admision en la prioridad actual.
   * @return el tiempo de admision en la prioridad actual.
   */
  public int getTiempoAdmisionEnPrioridad() {
    return tiempoAdmisionEnPrioridad;
  }

  /**
   * Asigna una prioridad nueva.
   * @return la prioridad antigua.
   */
  public int setPrioridad(int prioridadNuevo) {
    int oldPrioridad = prioridad;
    prioridad = prioridadNuevo;
    return oldPrioridad;
  }

  /**
   * Asigna un nuevo tiempo de admision en prioridad.
   * @return el tiempo de admision en prioridad antigua.
   */
  public int setTiempoAdmisionEnPrioridad(int tiempoNuevo) {
    int oldTiempo = tiempoAdmisionEnPrioridad;
    tiempoAdmisionEnPrioridad = tiempoNuevo;
    return oldTiempo;
  }

  @Override
  public String toString()
  {
     return "<\""+DNI.toString()+"\","+prioridad+","+tiempoAdmision+","+tiempoAdmisionEnPrioridad+">";
  }

  // ----------------------------------------------------------------------
  // Para terminar

  // Hay que definir compareTo:
  // (prioridad numericamente menor es mas urgente),
  @Override
  public int compareTo(Paciente paciente)
  {
//	  System.out.println("CompareTo" + DNI + " " + paciente.DNI);
	  if(prioridad < paciente.prioridad) {
//		  System.out.println("(-1)CompareTo" + prioridad + " " + paciente.prioridad);
		  return -1; 
	  }
	  else if(prioridad > paciente.prioridad) {
//		  System.out.println("(+1)CompareTo" + prioridad + " " + paciente.prioridad);
		  return 1;
	  }
	  else {
//		  System.out.println("ELSE" + " " + tiempoAdmisionEnPrioridad + " " + paciente.tiempoAdmisionEnPrioridad);
		  if(tiempoAdmisionEnPrioridad < paciente.tiempoAdmisionEnPrioridad) {
//			  System.out.println("(-1)CompareTo" + tiempoAdmisionEnPrioridad + " " + paciente.tiempoAdmisionEnPrioridad);
			  return -1;
		  }
		  else {
//			  System.out.println("(+1)CompareTo" + tiempoAdmisionEnPrioridad + " " + paciente.tiempoAdmisionEnPrioridad);
			  return 1;
		  }
	  }
  }
  
  // Hay que definir equals
  // Usad solo el DNI al comparar pacientes
  public boolean equals(Object obj)
  { 	  
	  boolean iguales = false;
	  if (obj instanceof Paciente){
		  Paciente p = (Paciente) obj;
		  if(DNI == p.DNI) {
			  iguales = true;
		  }
	  }
	  return iguales;
  }
  
  // Hay que definir hashCode
  // Usad solo el DNI al calcular el hashCode
  public int hashCode()
  {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
	  return result;
  }

}
