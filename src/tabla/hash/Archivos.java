
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
        //Mando a buscar el archivo al disco D(AHI LO TENGO; LE PONES LA DIRECCION DE DONDE LO GUARDES)
        String direccion = "palabras\\" + palabra.charAt(0) + "\\" + palabra;
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
        int cuenta = 0;
        File file = new File("palabras\\palabras.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        while(br.readLine() != null)
        {   cuenta++; }
        
        return cuenta;
    }
    
    //Retorna el nombre de los archivos dentro de la carpeta del parámetro
    public String[] nombreArchivos(String carpeta)
    {
        ArrayList<String> archivos = new ArrayList<String>();
        File file = new File("palabras\\" + carpeta);
        return file.list();
    }
    
    
    public void separarr(){
        //Este metodo es el que separa las palabras
         System.out.print ("Documento: \n");
         //teniendo la informacuib del documento en un array es mas facil manipularlo
            for (int j = 0 ; j < lineas.size() ; j++){
                //Saco las lineas del arrayList y se las agno una por una a linea
                linea = (String)lineas.get(j);
                //System.out.println(linea + " " + Hash.hashFunction(linea));
                //creo un array del metodo para que almacene cada una de las palabras por linea
                /*String separar[] = linea.split(" ");
                for(String p : separar){
                    //imprimo para demostrar que si funciona 
                    System.out.println(p + " " + Math.abs(p.hashCode())%21);
                }*/
            }
    }
    
    //y bueno las buenas practicas del programador
    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public ArrayList getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList lineas) {
        this.lineas = lineas;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
    
}
