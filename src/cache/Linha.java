/*
Created by: Luan Audibert, Felipe Oliveira


*/

package cache;

public class Linha {
    public int val;
    public int tag;
    public int blocos[];
    public int bsize;
    Linha(int tam){
        blocos = new int[tam];
        tag = -1;
        bsize = tam;
        val = 0;
        for(int i = 0; i < tam; i++){
         int val = 0;
            blocos[i] = 0;
        }
        
    }
    public boolean checkTag(int tag) {
         if (this.tag == tag) {
            return true;
            }
        return false;
    }

}
