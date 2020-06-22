package com.company.linkedlist;

public class NODE <E> {
    private E currentNODE;
    private NODE<E> nextNODE;
    private NODE<E> prevNODE;



    public NODE(NODE<E> prevNODE, E currentNODE, NODE<E> nextNODE){
        this.currentNODE=currentNODE;
        this.nextNODE= nextNODE;
        this.prevNODE=prevNODE;
    }


    public E getCurrentNODE(){return currentNODE;}
    public void setCurrentNODE(E currentNODE){this.currentNODE=currentNODE;}
    public NODE<E> getNextNODE(){return nextNODE;}
    public void setNextNODE(NODE<E> nextNODE){this.nextNODE=nextNODE;}
    public NODE<E> getPrevNODE(){return prevNODE;}
    public void setPrevNODE(NODE<E> prevNODE){this.prevNODE=prevNODE;}


}

