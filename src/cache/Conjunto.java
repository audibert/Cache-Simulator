/*
Created by: Luan Audibert, Felipe Oliveira


*/

package cache;

/**
 *
 * @author Luan
 */
public class Conjunto {
    Linha linhas[];
    int nconjuntos;
    Conjunto(int x, int bsize){
        nconjuntos = x;
        linhas = new Linha[x];
        for(int i=0; i < x; i++){
            linhas[i] = new Linha(bsize);
            
        }
    }
    
   


}
