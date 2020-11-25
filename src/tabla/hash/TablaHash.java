package tabla.hash;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


//CLASE SOLO PARA PRUEBAS DEL FUNCIONAMIENTO
public class TablaHash {
    
    public static void main(String[] args) throws IOException
    {
        Archivos archivo = new Archivos();
        String[] nombres = archivo.nombreArchivos("m");
        Hash tabla = new Hash();
        imprimeSinonimos(tabla, "Marisol");
        //imprimeElementosHash(tabla);
    }
    
    /*Pueden usar este método para guiarse a la hora de
      hacer el evento del botón de búsqueda, adaptándolo
      para formulario.*/
    public static void imprimeSinonimos(Hash tabla, String palabra)
    {
        //Dentro de un try porque el método retorna exceción
        try{
            //Obtenemos los sinónimos
            ArrayList<String> sinonimos = tabla.buscaSinonimos(palabra);
            
            System.out.println("\nImprimiendo sinónimos de " + palabra + "\n");
            
            //Bucle que imprime cada sinónimo
            for (int i = 0; i < sinonimos.size(); i++) {
                System.out.println(sinonimos.get(i));
            }
        } catch(FileNotFoundException e) //Catch por si no existe el archivo
        {System.out.println("\n¡Lo sentimos! No tenemos la palabra " + palabra + " en nuestra base de datos :(");}
        catch (IOException ex) {
            Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void imprimeElementosHash(Hash tabla)
    {   
        for (int i = 0; i < tabla.getTable().length; i++)
        {
            if(!tabla.getTable()[i].isEmpty())
            {
                System.out.println("Imprimiendo palabras de posición " + i);
                for (int j = 0; j < tabla.getTable()[i].size(); j++)
                {
                    System.out.print(tabla.getTable()[i].get(j) + " - ");
                }
                System.out.println("\n");
            }else System.out.println("La posición " + i + " no tiene elementos\n");
        }
    }
}
