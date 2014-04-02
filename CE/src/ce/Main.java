/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Manoel Afonso Filho
 */
public class Main {
    
    private static final Random r = new Random(System.currentTimeMillis());
    
    private static final double STDV = 0.05;

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
            System.out.println(getFittest(populacao).getFitness());
            populacao = geracao(populacao);
        }
    }
    
    public static double gaussiana(double dp, double media){
        double gaussiana = r.nextGaussian();
        if(gaussiana > 1){
            gaussiana = 1;
        } else if (gaussiana < 1){
            gaussiana = 0;
        }
        gaussiana = gaussiana * dp + media;
        return gaussiana;
    }
    
    public static Individuo[] geracao(Individuo[] populacao){
        //Ordena por fitness (em ordem decrescente)
        ArrayList<Individuo> melhores = new ArrayList<>();
        melhores.addAll(Arrays.asList(populacao));
        Collections.sort(melhores);
        
        //Os cinco melhores são modificados.
        for (int i = 0; i < 5; i++) {
            alterar(melhores.get(i));
        }
        
        //Cria cinco novos indivíduos aleatórios.
        Individuo[] novos5 = new Individuo[5];
        for (int i = 0; i < novos5.length; i++) {
            novos5[i] = criarIndividuo();
        }
        
        //Monta a nova população
        Individuo[] newpopulacao = new Individuo[10];
        //Insere os modificados
        for (int i = 0; i < 5; i++) {
            newpopulacao[i] = melhores.get(i);
        }
        //Insere os novos 5
        for (int i = 5; i < newpopulacao.length; i++) {
            newpopulacao[i] = novos5[i-5];
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
    
    //Sorteia o novo gene até que ele fique no intervalo [0,1]
    public static void alterar(Individuo ind){
        double[] seq = ind.getSequencia();
        //Posição a ser modificada (0 - 4).
        int pos = r.nextInt(seq.length);
        double newgene = gaussiana(STDV, seq[pos]);
        seq[pos] = newgene;
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
