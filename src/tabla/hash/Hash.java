package tabla.hash;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dany Serrano
 */
public class Hash
{
    private int cant; //Cantidad de espacios en la tabla Hash
    private ArrayList<String>[] table;//Tabla Hash
    private Archivos file; /*Variable de tipo Archivos necesaria
                             para las operaciones con los archivos*/
    
    //Constructor de la clase Hash
    public Hash()
    {
        file = new Archivos();
        try{
            //Asignamos a cant la cantidad de espacios que contendrá nuestra tabla Hash
            this.cant = this.calcEspacios();
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
    
    /*Método que busca los sinónimos de la palabra que recibe como parámetro*/
    public ArrayList<String> buscaSinonimos(String palabra) throws IOException, FileNotFoundException
    {
        //Obtenemos la posición de las palabras con la misma deficinión en la tabla Hash
        int index = this.hashFunction(file.leerArchivo(palabra));
        //Obtenemos la lista de sinónimos
        ArrayList<String> sinonimos = this.table[index];
        //Lista auxiliar "palabras"
        ArrayList<String> palabras = new ArrayList<String>();
        int p; String aux; //Variables auxiliares
        
        /*Bucle que elimina la terminación ".txt" de los nombres de las palabras
          debido a que, en realidad, extrae el nombre de cada archivo que contenga
          la misma definición.*/
        for (int i = 0; i < sinonimos.size(); i++) {
            p = sinonimos.get(i).lastIndexOf("."); //Posición del caracter '.'
            aux = sinonimos.get(i).substring(0, p); //Palabra sin la terminación ".txt"
            if(!aux.equalsIgnoreCase(palabra)) //Se verifica que no sea la misma palabra recibida como parámetro
                palabras.add(aux); //Se agrega la palabra a la lista auxiliar
        }
        //Retornamos la lista de todas las palabras sin la terminación ".txt"
        return palabras;
    }
    
    //Agrega una palabra a la tabla hash en base a su definición
    public void agregaPalabra(String palabra) throws IOException
    {
       
        int index = this.hashFunction(file.leerArchivo(palabra));
        this.table[index].add(palabra);
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
    
    
    //Métodos auxiliares
    
    /*Calcula la cantidad de espacios que debe contener la tabla Hash
      La tabla contendrá la cantidad de espacios igual al siguiente
      número primo después de la cantidad de palabras/definiciones
      que se tiene guardadas, esto para garantizar una menor cantidad
      de colisiones. */
    public int calcEspacios() throws IOException
    {
        return siguientePrimo(file.cuentaPalabras());
    }
    
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
