package tabla.hash;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dany Serrano
 */
public class Hash
{
    private int cant;
    private ArrayList<String>[] table;

    
    //Constructor de la clase Hash
    public Hash()
    {
        try{
            //Asignamos a cant la cantidad de espacios que contendrá nuestra tabla Hash
            this.cant = Hash.calcEspacios();
            System.out.println("Se han generado " + this.cant + " espacios");
            table = new ArrayList[this.cant]; //Inicializa la tabla
            for (int i = 0; i < table.length; i++)
            {  //Inicializa cada lista guardada en la tabla
                table[i] = new ArrayList<String>();
            }
            this.inicializaHash(); //Se llama al método para que inicialice los valores de la tabla Hash
            
        }catch(IOException e)
        {  System.out.println("No hay palabras agregadas.");    }
    }
    
    
    /*Método que inicializa la tabla Hash
      Lee cada archivo del directorio y agrega todas las palabras
      a la tabla Hash*/
    public void inicializaHash()
    {
        char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                      'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u',
                      'v', 'w', 'x', 'y', 'z'}; //Vector con las letras del abecedario
        String[] archivos;
        Archivos file = new Archivos();
        
        /*Bucle que se realiza para cada letra
          Cada letra es una carpeta en el directorio de palabras*/
        for (int i = 0; i < abc.length; i++)
        {
            /*Este foreach guarda el respectivo nombre de cada archivo que se encuentre
              dentro de la carpeta que corresonda en la iteración del bucle*/
            for (String archivo : file.nombreArchivos(Character.toString(abc[i]))) {
                try {
                    //Vamos agregando cada palabra que se encuentre dentro de la carpeta
                    this.agregaPalabra(archivo);
                }catch(IOException e)
                { System.out.println("Ocurrió un error: " + e);   }
            }
        }
        
        System.out.println("Inicialización Completada.");
        
    }
     //Set y Get necesarios
    public int getCant() {
        return cant;
    }

    public ArrayList<String>[] getTable() {
        return table;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    /*Función HASH
      En base a la cadena que se le envía, calcula una clave especial
        HABRÁ QUE HACER PRUEBAS PARA VERIFICAR QUE FUNCIONA PERFECTAMENTE
        SI NO FUNCIONA BIEN SE LE PUEDEN HACER AJUSTES/CAMBIOS */
    public int hashFunction(String definicion)
    {
        int sum = 0; //Se calcula una sumatoria de valores.
        String[] words = definicion.split(" "); //se extraen las palabras de la frase
        
        for (String word : words) {
            /*Se suma el valor absoluto del residuo de dividir el resultado de la
              función hashCode de la librería String, entre el tamaño de toda la frase*/
            sum += Math.abs(word.hashCode() % definicion.length());            
        }
        
        return (sum%cant);
    }
    
    //Agrega una palabra a la tabla hash en base a su definición
    public void agregaPalabra(String palabra) throws IOException
    {
        Archivos archivo = new Archivos();
        int index = this.hashFunction(archivo.leerArchivo(palabra));
        this.table[index].add(palabra);
    }
    
    /*Calcula la cantidad de espacios que debe contener la tabla Hash
      La tabla contendrá la cantidad de espacios igual al siguiente
      número primo después de la cantidad de palabras/definiciones
      que se tiene guardadas, esto para garantizar una menor cantidad
      de colisiones. */
    public static int calcEspacios() throws IOException
    {
        Archivos f = new Archivos();
        return siguientePrimo(f.cuentaPalabras());
    }
    
    
    //Métodos auxiliares
    
    //Retorna el primer número primo que sigue después del parámetro n
    public static int siguientePrimo(int n)
    {
        if( n <= 1 ) return 3;
        if( n % 2 == 0) n++;
        for( ; !esPrimo( n ); n += 2 ) ;
        return n;
    }

    //Verifica si el número pasado por parámetro es primo
    public static boolean esPrimo( long n )
    {
        if( n == 2 ) return true;
        if( n % 2 == 0 ) return false;
        long raiz = ( long ) Math.sqrt( n );
        boolean ok = true;
        for( long div = 3; div <= raiz; div += 2 )
        {
            if( n % div == 0 ) return false;
        }
        return true;
    }

    
}
