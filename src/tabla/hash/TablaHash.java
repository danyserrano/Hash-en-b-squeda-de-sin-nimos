
package tabla.hash;

import java.io.IOException;

public class TablaHash {

    
    
    
    public static void main(String[] args) throws IOException
    {
        Archivos archivo = new Archivos();
        String[] nombres = archivo.nombreArchivos("m");
        Hash tabla = new Hash();
        /*
        if(nombres.length == 0)
            System.out.println("No hay archivos");
        else
            for (int i = 0; i < nombres.length; i++) {
                System.out.println(nombres[i]);
            }*/
        imprimeElementosHash(tabla);
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
