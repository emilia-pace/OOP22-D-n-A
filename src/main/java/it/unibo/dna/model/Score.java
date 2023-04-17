package it.unibo.dna.model;

public class Score {
    
    private double total=0;
    
    public double getTotal() {
        return this.total;
    }

    public void setTotal(final double value){
        this.total=value;
    }

    public void addScore(final double value){
        this.total=this.total+value;
    }

    public void resetScore(){
        this.total=0;
    }
}
