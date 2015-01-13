
/*
Created by: Luan Audibert, Felipe Oliveira


*/
package cache;

import java.io.*;
import java.util.ArrayList;
import static java.lang.System.out;

public class Cache {

    Conjunto  conjs[];
    double noffset, nindice, ntag ;
    int nset, conjuntos, bsize, assoc, miss, hit, mConflito, mCompusorio, mCapacidade;
    public Cache(int nset, int bsize, int assoc){
        this.nset = nset;
        this.conjuntos = nset;
	this.bsize = bsize;
	this.assoc = assoc;
	this.miss = 0;
        this.mConflito = 0;
        this.mCompusorio = 0;
        this.mCapacidade = 0;
	this.hit = 0;
	this.noffset = Math.log(bsize) / Math.log(2);
	this.nindice = Math.log(nset) / Math.log(2);
	this.ntag = 32-nindice-noffset;    
        
        conjs = new Conjunto[assoc];
        for(int i=0; i < assoc; i++){
            conjs[i] = new Conjunto(nset, bsize);
        }
    
    }
    
    public int proccessAddress(Integer end) {
        int indice, tag;
        indice = (end >> (int) noffset) & (nset - 1);
        tag = end >> (int) (noffset + nindice);
        if (checkTag(tag, indice)) {
            this.hit++;
            return -1; // Hit!
        } else {
            
            return setDados(tag, indice);
        }
    }
    
    public boolean checkTag(int tag, int indice) {
        for(int i = 0; i < assoc; i++){
            if(conjs[i].linhas[indice].tag == tag) {
                return true;
            }
            
        }
        return false;
    }
    
    //0 - Miss Conflito;
    //1 - Miss Compulsorio;
    //2 - Miss Capacidade
    public int setDados(int tag, int indice) {  //TODO
        int end, checkFree;
        if (assoc == 1) {
            if (conjs[0].linhas[indice].val == 0) {
                conjs[0].linhas[indice].val = 1;
                conjs[0].linhas[indice].tag = tag;
                mCompusorio++;
                return 1;
            } else {
                conjs[0].linhas[indice].tag  = tag;
                conjs[0].linhas[indice].val = 1;
                mConflito++;
                return 0;
            }
        } else {
            checkFree = checkFreeAddress(indice);
            if (checkFree == -1) {
                end = (int) ((Math.random() * 10)) % assoc;
                conjs[end].linhas[indice].val = 1;
                conjs[end].linhas[indice].tag =  tag;
                mCapacidade++;
                return 2;
            }
            conjs[checkFree].linhas[indice].val = 1;
            conjs[checkFree].linhas[indice].tag =  tag;
            mCompusorio++;
            return 1;
        }

    }
    public int checkFreeAddress(int indice) {
        for (int i = 0; i < assoc; i++) {
            if (conjs[i].linhas[indice].val == 0) {
                return i;
            }
        }
        return -1;
    }

    
    

    public void getResposta() {
        System.out.println("Hit: " + this.hit);

       miss = mCompusorio + mConflito + mCapacidade;
       System.out.println("Miss: " + this.miss);
        System.out.println("Miss Compusorio: " + this.mCompusorio);
        System.out.println("Miss Conflito: " + this.mConflito);
        System.out.println("Miss Capacidade: " + this.mCapacidade);
        System.out.println("Hit Ratio: " + ((float)this.hit/(this.miss+this.hit)*100) + " %");      
        System.out.println("Miss Ratio: " + ((float)this.miss/(this.miss+this.hit)*100) + " %");    
    }


}