/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalhocealessandra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Manoel Afonso Filho
 */
public class AG {
    
    private static final Random r = new Random(System.currentTimeMillis());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Individuo[] populacao = new Individuo[10];
        //cria os indivíduos aleatoriamente
        for (int i = 0; i < populacao.length; i++) {
            populacao[i] = criarIndividuo();
        }
        //executa as 100 gerações do algoritmo
        for (int i = 0; i < 100; i++) {
            populacao = geracao(populacao);
            System.out.println(getFittest(populacao).getFitness());
        }
    }
    
    public static Individuo[] geracao(Individuo[] populacao){
        //Ordena por fitness (em ordem crescente)
        ArrayList<Individuo> melhores = new ArrayList<Individuo>();
        melhores.addAll(Arrays.asList(populacao));
        Collections.sort(melhores);

        //Os cinco melhores são modificados.
        for (int i = melhores.size() - 1; i >= melhores.size() - 5; i--) {
            alterar(melhores.get(i));
        }
        
        //Cria cinco novos indivíduos aleatórios.
        Individuo[] novos5 = new Individuo[5];
        for (int i = 0; i < novos5.length; i++) {
            novos5[i] = criarIndividuo();
        }
        
        //Monta a nova população
        Individuo[] newpopulacao = new Individuo[10];
        //Insere os novos 5
        System.arraycopy(novos5, 0, newpopulacao, 0, 5);
        //Insere os modificados
        for (int i = 5; i < newpopulacao.length; i++) {
            newpopulacao[i] = melhores.get(i);
        }
        return newpopulacao;
    }
    
    public static Individuo getFittest(Individuo[] pop){
        Individuo maior = pop[0];
        for (int i = 1; i < pop.length; i++) {
            if(pop[i].getFitness() > maior.getFitness()){
                maior = pop[i];
            }
        }
        return maior;
    }
    
    public static void alterar(Individuo ind){
        double[] seq = ind.getSequencia();
        //Posição a ser modificada (0 - 5).
        int pos = r.nextInt(seq.length);
        
        //calcula a gaussiana (arredonda se cair fora de [0,1].
        double gaussiana = r.nextGaussian();
        if(gaussiana > 1){
            gaussiana = 1;
        } else if (gaussiana < 1){
            gaussiana = 0;
        }
        gaussiana = gaussiana * 0.02 + seq[pos];
        
        seq[pos] = gaussiana;
        ind.calcularFitness();
    }
    
    public static Individuo criarIndividuo(){
        double[] ind = new double[5];
        for (int i = 0; i < ind.length; i++) {
            ind[i] = r.nextDouble();
        }
        Individuo i = new Individuo(ind);
        i.calcularFitness();
        return i;
    }
    
    
}