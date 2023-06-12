import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class OnePiece{
    public static void main(String args[]){
        int tamanho = 0;
        int linhas = 0;
        int colunas = 0;
        String linha = " ";
        String dimensoes = "";
        char valor = ' ';
        char[] portos = new char[10];
                
        File exemplo = new File("mapas/case0.map");
        
        try {
            Scanner leitor = new Scanner(exemplo);  
            dimensoes = leitor.nextLine();
            String[] partes = dimensoes.split("\\D+");
            linhas = Integer.parseInt(partes[0]);
            colunas = Integer.parseInt(partes[1]);
            tamanho = linhas * colunas;
            Graph mapa = new Graph(tamanho);
            
            for(int i = 0; i < linhas; i++){
                linha = leitor.nextLine();
                for(int j = 0; j < colunas; j++) {
                    valor = linha.charAt((i * colunas) + j);
                    if(valor == '.' && ((i * colunas) + j) + 1 != '*') {
                        mapa.addEdge(valor, ((i * colunas) + j) + 1);
                    }

                    if(valor == '.' && (((i+1) * colunas) + j) != '*') {
                        mapa.addEdge(valor, (((i+1) * colunas) + j));
                    }

                    if(valor == '1') portos[1] = valor;
                    if(valor == '2') portos[2] = valor;
                    if(valor == '3') portos[3] = valor;
                    if(valor == '4') portos[4] = valor;
                    if(valor == '5') portos[5] = valor;
                    if(valor == '6') portos[6] = valor;
                    if(valor == '7') portos[7] = valor;
                    if(valor == '8') portos[8] = valor;
                    if(valor == '9') portos[9] = valor;
                }
            }

            leitor.close();
    
        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }

        
        

    }
}