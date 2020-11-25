package tabla.hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivos {
    //declarar la variable archivo de tipo File
    private File archivo = null;
    //decaracion de la variable de lectura del archivo FileReader
    private FileReader fr = null;
    //Variables auxiliares 
    private ArrayList lineas = new ArrayList();
    private String linea;
    
    public String leerArchivo(String palabra) throws FileNotFoundException, IOException{
        //Mando a buscar el archivo a la carpeta que tiene como nombre la primera letra de la palabra
        String direccion = "palabras\\" + palabra.charAt(0) + "\\" + palabra;
        
        //Si la dirección no tiene al final la extensión del archivo, se lo agregamos.
        if (!direccion.endsWith(".txt"))
        { direccion += ".txt"; }
        
        archivo = new File (direccion);
        //inicialización de la variable de lectura
        fr = new FileReader (archivo);
        //buffer que ayuda a la lectura del arcchivo(Esta clase extrae toda la informacion de un texto plano)
        BufferedReader br = new BufferedReader(fr);
        return br.readLine();
    }
    
    //Retorna la cantidad de palabras almacenada en nuestro fichero
    public int cuentaPalabras() throws FileNotFoundException, IOException
    {
        int cuenta = 0; //Contador de palabras
        
        /*En este archivo se guarda una palabra por definición
          y se ha colocado una palabra por línea*/
        File file = new File("palabras\\palabras.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        //Bucle que cuenta la cantidad de líneas del archivo
        while(br.readLine() != null)
        {   cuenta++; }
        
        //Retornamos la cantidad de líneas del archivo,
        //que es igual a la cantidad de palabras
        return cuenta;
    }
    
    //Retorna el nombre de los archivos dentro de la carpeta especificada por parámetro
    public String[] nombreArchivos(String carpeta)
    {
        ArrayList<String> archivos = new ArrayList<String>();
        File file = new File("palabras\\" + carpeta);
        return file.list();
    }
}
