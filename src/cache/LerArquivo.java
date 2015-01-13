/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cache;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class LerArquivo {
    public ArrayList<Integer> leituraArquivo (String nome_arquivo){
        ArrayList<Integer> lido = new ArrayList<Integer>();
        DataInputStream arquivo = null;
        
        try {
        arquivo = new DataInputStream(new FileInputStream(nome_arquivo));
            int aux;
            while ((aux = arquivo.readInt()) != -1) {
                lido.add(aux);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado " + e.getMessage());
        } catch (IOException e) {

        } finally {
            if (arquivo != null) {
                try {
                    arquivo.close();
                } catch (IOException e) {
                }
            }
        }
        
        //Teste
      /*  for (Integer i : lido) {
            System.out.println (i);
        }*/
        
        return lido;
    }
}
