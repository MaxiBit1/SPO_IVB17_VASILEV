package com.company.linkedlist;

public class Linkedlist<E> {

    private NODE<E> fsNode;
    private NODE<E> ltNode;
    private int size=0;

    public Linkedlist(){
        fsNode=new NODE<E>(ltNode,null,null);
        ltNode=new NODE<E>(null,null,fsNode);
    }

    public void AddLAST(E e){
        NODE<E> past=ltNode;
        past.setCurrentNODE(e);
        ltNode=new NODE<E>(past,null,null);
        past.setNextNODE(ltNode);
        size++;
    }

    public void AddFIRST(E e){
        NODE<E> first=fsNode;
        first.setCurrentNODE(e);
        fsNode=new NODE<>(null,null,first);
        first.setPrevNODE(fsNode);
        size++;
    }

    public void REmoveFirst(){
        NODE<E> step=fsNode.getNextNODE();
        fsNode=step;
        step=step.getNextNODE();
        step.setPrevNODE(fsNode);
        size--;
    }

    public void REmoveLast(){
        NODE<E> step=ltNode.getPrevNODE();
        ltNode=step;
        step=step.getPrevNODE();
        step.setNextNODE(ltNode);
        size--;
    }

    public boolean checkELEMBYINDEX(E e){
        boolean res=false;
        int i=0;
        NODE<E> target=fsNode.getNextNODE();
        while (i<size){
            if(e.equals(target.getCurrentNODE())){
                return true;
            }else{
                target=target.getNextNODE();
            }
            i++;
        }
        return res;
    }

}
