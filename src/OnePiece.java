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
        String linhaAbaixo = " ";
        String linhaAcima = " ";
        String dimensoes = "";
        char atual = ' ';
        char proximo = ' ';
        char abaixo = ' ';
        char acima = ' ';
        char[] portos = new char[10];
                
        File exemplo = new File("mapas/case0.map");
        
        try {
            Scanner leitor = new Scanner(exemplo);

            // Lê a linha com as dimensões e calcula o total de vértices para criar o grafo do mapa
            dimensoes = leitor.nextLine();
            linha = dimensoes;
            String[] partes = dimensoes.split("\\D+");
            linhas = Integer.parseInt(partes[0]);
            colunas = Integer.parseInt(partes[1]);
            tamanho = linhas * colunas;
            Graph mapa = new Graph(tamanho);

            // Laço para andar nas linhas
            linha = null;
            linhaAbaixo = leitor.nextLine();
            for(int i = 0; i < linhas - 1; i++){
                linhaAcima = linha;
                linha = linhaAbaixo;  // Salva a linha a ter as colunas percorridas
                if (leitor.hasNextLine())
                linhaAbaixo = leitor.nextLine();
                else linhaAbaixo = null;
                // Laço para andar nas colunas
                for(int j = 0; j < colunas - 1; j++) {
                    atual = linha.charAt(j); // Salva o caractere de cada coluna

                    //Proximo
                    if (j + 1 < colunas) 
                    proximo = linha.charAt(j + 1); 
                    else 
                    proximo = linha.charAt(j);
                    //Teste

                    //Abaixo e acima
                    if (linhaAbaixo != null)
                    abaixo = (linhaAbaixo.charAt(j));
                    else
                    abaixo = '*';

                    System.out.println(abaixo);

                    /* 

                    if (linhaAcima != null)
                    acima = (linhaAcima.charAt(j));
                    else{
                        acima = '*';
                        System.out.println(acima);
                    }
                    */
                    
                     /* 
                    if (((i + 1) * colunas) + j < linhas) 
                    abaixo = linha.charAt(((i + 1) * colunas) + j);
                    else 
                    abaixo = linha.charAt(j);

                    System.out.println(abaixo);
                    */

                    // Verifica se o caractere a direita é navegavel para criar uma aresta 
                    if(atual != '*' && proximo != '*' && j < colunas) {
                        int aux1 = (i * colunas) + j;
                        int aux2 = (i * colunas) + (j + 1);
                        mapa.addEdge(aux1, aux2);
                    }
                    

                    //Comenta esses dois IFs se quiser ver sem o erro

                    
                    if(atual != '*' && abaixo != '*' && j < colunas && i < linhas) {
                        int aux1 = (i * colunas) + j;
                        int aux2 = (((i + 1) * colunas) + j);
                        mapa.addEdge(aux1, aux2);
                    }
                    

                    /*if(atual != '*' && acima != '*' && j < colunas) {
                        int aux1 = (i * colunas) + j;
                        int aux2 = ((i - 1) * colunas) + j;
                        mapa.addEdge(aux1, aux2);
                    }*/

                    //Verifica se o caractere abaixo é navegavel para criar uma aresta

                    /* 
                    if(atual == '.' && abaixo != '*' && j < colunas && i < linhas) {
                        mapa.addEdge(atual, ((i+1) * j));
                    }
                    */

                    // Salva os endereços dos portos
                    
                    if(atual == '1') portos[1] = atual;
                    if(atual == '2') portos[2] = atual;
                    if(atual == '3') portos[3] = atual;
                    if(atual == '4') portos[4] = atual;
                    if(atual == '5') portos[5] = atual;
                    if(atual == '6') portos[6] = atual;
                    if(atual == '7') portos[7] = atual;
                    if(atual == '8') portos[8] = atual;
                    if(atual == '9') portos[9] = atual;
                }
            }

            leitor.close();

            //System.out.println(mapa.toString());
            
            for (int i = 0; i < linhas - 1; i++) {
                System.out.println();
                for (int j = 0; j < colunas; j++) {
                    int vertice = i * colunas + j;
                    System.out.print(mapa.degree(vertice));
                }
            }



        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }

        
        

    }
}