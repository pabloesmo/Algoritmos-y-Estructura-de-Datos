package aed.individual1;
public class ArrayCheckSumUtils
{ 
  // arr no es null, podria tener tamaño 0, n>0
  public static int[] arrayCheckSum(int[] arr, int n)
  {
    int nchecksum = (int)arr.length/n;
	  if(arr.length % n != 0) //si la longitud del array no es multiplo de n, se incrementa el checksum
	  {
		  nchecksum++;
	  }
	  int[] arrNuevo = new int[arr.length+nchecksum];
    
    int checksum = 0;
    int posArrNuevo = 0;
    for (int i=0; i<arr.length; i++)
    {
      if ((i>0) && (i % n == 0))
      {
        arrNuevo[posArrNuevo] = checksum;
        posArrNuevo++;
        checksum= 0;
      } //lo siguiente vendria dado por un else
      arrNuevo[posArrNuevo] = arr[i];
      posArrNuevo++;
      checksum += arr[i];
    }
    if (checksum !=0)
	    {
	    	arrNuevo[posArrNuevo] = checksum;
	    }
    return arrNuevo;
  }
}
