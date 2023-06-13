import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main{
    public static void main(String args[]){
        int proximoPorto = 0;
        int ultimoPorto = 9;
        int combustivelTotal = 0;
        int combustivel = 0;
        int tamanho = 0;
        int linhas = 0;
        int colunas = 0;
        String linha = " ";
        String linhaAbaixo = " ";
        String dimensoes = "";
        char atual = ' ';
        char proximo = ' ';
        char abaixo = ' ';
        int[] portos = new int[10];
        boolean fim = false;
                
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
                linha = linhaAbaixo;  // Salva a linha a ter as colunas percorridas

                if (leitor.hasNextLine()) linhaAbaixo = leitor.nextLine();
                else linhaAbaixo = null;

                // Laço para andar nas colunas
                for(int j = 0; j < colunas; j++) {
                    atual = linha.charAt(j); // Salva o caractere de cada coluna

                    // Proximo
                    if (j + 1 < colunas) proximo = linha.charAt(j + 1); 
                    else proximo = linha.charAt(j);

                    //Teste
                    if (linhaAbaixo != null) abaixo = (linhaAbaixo.charAt(j));
                    else abaixo = '*';
                    
                    // Verifica se o caractere a direita é navegavel para criar uma aresta 
                    if(atual != '*' && proximo != '*' && j < colunas - 1) {
                        int aux1 = (i * colunas) + j;
                        int aux2 = (i * colunas) + (j + 1);
                        mapa.addEdge(aux1, aux2);
                    } 

                    // Verifica se o caractere a baixo é navegavel para criar uma aresta
                    if(atual != '*' && abaixo != '*' && i < linhas - 2) {
                        int aux1 = (i * colunas) + j;
                        int aux2 = (((i + 1) * colunas) + j);
                        mapa.addEdge(aux1, aux2);
                    }

                    // Salva os endereços dos portos
                    if(atual == '1') portos[1] = (i * colunas) + j;
                    if(atual == '2') portos[2] = (i * colunas) + j;
                    if(atual == '3') portos[3] = (i * colunas) + j;
                    if(atual == '4') portos[4] = (i * colunas) + j;
                    if(atual == '5') portos[5] = (i * colunas) + j;
                    if(atual == '6') portos[6] = (i * colunas) + j;
                    if(atual == '7') portos[7] = (i * colunas) + j;
                    if(atual == '8') portos[8] = (i * colunas) + j;
                    if(atual == '9') portos[9] = (i * colunas) + j;
                }
            }

            leitor.close();

            /////////////////////////////////// Navegação ///////////////////////////////////

            for(int i = 1; i < 9; i++){
                CaminhamentoEmLargura jornada = new CaminhamentoEmLargura(mapa, portos[i]);

                if(jornada.hasPath(portos[i + 1])) {
                    for(int distancia: jornada.pathTo(portos[i + 1])) {    
                        combustivel++;
                        combustivelTotal++;
                    }
                    combustivelTotal--;
                    combustivel--;
                    System.out.printf("Combustivel gasto do porto %d até o porto %d: %d\n", i, (i+1), combustivel);
                    combustivel = 0;
                } else {
                    System.out.printf("Porto %d inacessível\n", (i+1));
                    proximoPorto = i + 1;
                    while(true){
                        proximoPorto += 1;
                        if(proximoPorto == 10){
                            ultimoPorto = i;
                            fim = true;
                            break;
                        }

                        if(jornada.hasPath(portos[proximoPorto])) {
                            for(int distancia: jornada.pathTo(portos[proximoPorto])) {    
                                combustivel++;
                                combustivelTotal++;
                            }
                            combustivelTotal--;
                            combustivel--;
                            System.out.printf("Combustivel gasto do porto %d até o porto %d: %d\n", i, proximoPorto, combustivel);
                            combustivel = 0;
                            i = proximoPorto - 1;
                            break;
                        } else {
                            System.out.printf("Porto %d inacessível\n", proximoPorto);
                        }
                    }

                    if(fim == true){
                        break;
                    }
                }
            }

            /////////////////////////////////// Retorno ///////////////////////////////////

            //System.out.println(ultimoPorto);

            CaminhamentoEmLargura jornada = new CaminhamentoEmLargura(mapa, portos[ultimoPorto]);
            for(int distancia: jornada.pathTo(portos[1])) {    
                combustivel++;
                combustivelTotal++;
            }
            combustivelTotal--;
            combustivel--;
            System.out.printf("Combustivel gasto do porto %d até o porto %d: %d\n", ultimoPorto, 1, combustivel);
            combustivel = 0;
            System.out.println("O minimo de combustível para realizar essa viagem é: " +combustivelTotal);



        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }

        
        

    }
}