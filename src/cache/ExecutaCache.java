
/*
Created by: Luan Audibert, Felipe Oliveira


*/
package cache;

import java.io.*;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.StringTokenizer;
import java.util.Scanner;
/**
 *
 * @author Luan
 */
public class ExecutaCache {
    

    
    public static void main(String[] args) {
        int nsetd = -1, bsized = -1, assocd = -1, nseti = -1, bsizei = -1, associ = -1;
        
        StringTokenizer s = new StringTokenizer(args[0], ":");
        if (s.countTokens() == 3) {
            nseti = Integer.parseInt(s.nextToken());
            bsizei = Integer.parseInt(s.nextToken());
            associ = Integer.parseInt(s.nextToken());
        }else{
            out.println("Sintaxe errada!\nSintaxe correta: <nsets_L1d>:<bsize_L1d>:<assoc_L1d> <nsets_L1i>:<bsize_L1i>:<assoc_L1i> arquivo_de_entrada");
            return;
        }
        StringTokenizer x = new StringTokenizer(args[1], ":");
        if (x.countTokens() == 3) {
            nsetd = Integer.parseInt(x.nextToken());
            bsized = Integer.parseInt(x.nextToken());
            assocd = Integer.parseInt(x.nextToken());
        }else{
            out.println("Sintaxe errada!\nSintaxe correta: <nsets_L1d>:<bsize_L1d>:<assoc_L1d> <nsets_L1i>:<bsize_L1i>:<assoc_L1i> arquivo_de_entrada");
            return;
        }
        Cache instrucao = new Cache(((nseti != -1) ? nseti : 1024), ((bsizei != -1) ? bsizei : 4), ((associ != -1) ? associ : 1));
        Cache dados = new Cache(((nsetd != -1) ? nsetd : 1024), ((bsized != -1) ? bsized : 4), ((assocd != -1) ? assocd : 1));
        LerArquivo lerarq = new LerArquivo();
        // TODO code application logic here
        int numeroEntradas=0;
        ArrayList<Integer> ler = new ArrayList<Integer>();

        ler = lerarq.leituraArquivo(args[2]);
        for (Integer i : ler) { 
            
            if (numeroEntradas%2 == 0){ 
                if(i != 0){
                    instrucao.proccessAddress(i); 
                }
            }else{ 
                if(i != 0){
                    dados.proccessAddress(i); }
            }
            numeroEntradas++; 
        }
        System.out.println("Resultados Cache Instrucoes:");
        instrucao.getResposta();
        System.out.println("Resultados Cache Dados:");
        dados.getResposta();
        out.println("Total lidos: "+numeroEntradas);
    }

}
