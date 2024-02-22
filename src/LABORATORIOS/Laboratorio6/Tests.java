package aed.urgencias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

  @Test
  public void testAdmitir() throws PacienteExisteException
  {
    Urgencias u = new UrgenciasAED();
    u.admitirPaciente("111", 5, 1);
    Paciente p = u.atenderPaciente(10);

    // Check expected DNI ("111") == observed DNI (p.getDNI())
    assertEquals("111", p.getDNI());
  }
  
  @Test
  public void test1() throws PacienteExisteException
  {
	  Urgencias u = new UrgenciasAED();
	  u.admitirPaciente("P1", 2, 1);
	  u.admitirPaciente("P2", 2, 2);
	  Paciente p = u.atenderPaciente(3);
	  
	  assertEquals("P1", p.getDNI());
  }
  
  @Test
  public void test2() throws PacienteExisteException
  {
	  Urgencias u = new UrgenciasAED();
	  u.admitirPaciente("P1", 2, 1);
	  u.admitirPaciente("P2", 2, 2);
	  Paciente p1 = u.atenderPaciente(3);
	  Paciente p2 = u.atenderPaciente(4);
	  
	  assertEquals("P1", p1.getDNI());
	  assertEquals("P2", p2.getDNI());
  }
  
  @Test
  public void test3() throws PacienteExisteException
  {
	  Urgencias u = new UrgenciasAED();
	  u.admitirPaciente("P1", 5, 1);
	  u.admitirPaciente("P2", 1, 2);
	  Paciente p1 = u.atenderPaciente(3);
	  
	  assertEquals("P2", p1.getDNI());
  }
  
  @Test
  public void test4() throws PacienteExisteException, PacienteNoExisteException
  {
	  Urgencias u = new UrgenciasAED();
	  u.admitirPaciente("P1", 4, 1);
	  u.admitirPaciente("P2", 4, 2);
	  u.salirPaciente("P1", 3);
	  Paciente p = u.atenderPaciente(5);
	  
	  assertEquals("P2", p.getDNI());
  }
  
  @Test
  public void test5() throws PacienteExisteException, PacienteNoExisteException
  {
	  Urgencias u = new UrgenciasAED();
	  u.admitirPaciente("P1", 5, 1);
	  u.admitirPaciente("P2", 5, 2);
	  u.cambiarPrioridad("P2", 1, 4);
	  Paciente p = u.atenderPaciente(6);
	  
	  assertEquals("P2", p.getDNI());
  }
}