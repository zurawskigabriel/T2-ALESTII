import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class OnePiece{
    public static void main(String args[]){
        int combustivel = 0;
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
            // Lê a linha com as dimensões e calcula o total de vértices para criar o grafo do mapa
            dimensoes = leitor.nextLine();
            String[] partes = dimensoes.split("\\D+");
            linhas = Integer.parseInt(partes[0]);
            colunas = Integer.parseInt(partes[1]);
            tamanho = linhas * colunas;
            Graph mapa = new Graph(tamanho);


            // Laço para andar nas linhas
            for(int i = 0; i < linhas; i++){
                linha = leitor.nextLine();  // Salva a linha a ter as colunas percorridas
                // Laço para andar nas colunas
                for(int j = 0; j < colunas; j++) {

                    valor = linha.charAt(j); // Salva o caractere de cada coluna 

                    // Verifica se o caractere a direita é navegavel para criar uma aresta 
                    if(valor == '.' && (j + 1) != '*') {
                        mapa.addEdge(valor, (j + 1));
                    }

                    // Verifica se o caractere abaixo é navegavel para criar uma aresta
                    if(valor == '.' && ((i+1) * j) != '*') {
                        mapa.addEdge(valor, ((i+1) * j));
                    }

                    // Salva os endereços dos portos
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

            System.out.println(mapa);

        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }

        
        

    }
}