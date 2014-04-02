/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ce;

/**
 *
 * @author Manoel Afonso Filho
 */
public class Individuo implements Comparable<Individuo> {
    
    private double[] sequencia = new double[5];
    
    private double fitness;
    
    public Individuo(double[] seq){
        this.sequencia = seq;
    }
    
    public void calcularFitness(){
        double sum = 0;
        for (int i = 0; i < this.sequencia.length; i++) {
            sum += this.sequencia[i];
        }
        this.fitness = (sum / this.sequencia.length);
    }

    /**
     * @return the sequencia
     */
    public double[] getSequencia() {
        return sequencia;
    }

    /**
     * @param sequencia the sequencia to set
     */
    public void setSequencia(double[] sequencia) {
        this.sequencia = sequencia;
    }

    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Individuo o) {
        if(this.fitness < o.getFitness()){
            return 1;
        } else if (this.fitness > o.getFitness()){
            return -1;
        } else{
            return 0;
        }
    }

    @Override
    public String toString() {
        String seq = "";
        for (int i = 0; i < this.sequencia.length; i++) {
            seq += this.sequencia[i] + " ";
        }
        
        return  this.fitness + "";
    }
    
}
