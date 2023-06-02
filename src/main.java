import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class main{
    public static void main(String args[]){
        int tamanho = 0;
        String dimensoes = "";
        File exemplo = new File("mapas/case0.map");
        
        try {
            Scanner leitor = new Scanner(exemplo);  
            dimensoes = leitor.nextLine();
            String[] partes = dimensoes.split("\\D+");
            tamanho = Integer.parseInt(partes[0]) * Integer.parseInt(partes[1]);
            Graph mapa = new Graph(tamanho);
            


            leitor.close();
    
        } catch(FileNotFoundException e) {
            System.out.println("Deu erro na leitura");
        }

        

        
        
        

    }
}