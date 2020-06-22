package com.company.parser;

import com.company.linkedlist.Linkedlist;
import com.company.token.Token;
import com.company.lexer.Lexem;
import com.company.exception.LangParseException;
import java.util.List;
public class Parser {

    private final List<Token> tokens;
    int schetchik=0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void lang() throws LangParseException {
        try{
            while(schetchik < tokens.size()) {
                expr();
            }
            System.out.println("Parser work great");
        }catch(LangParseException y){
            System.out.println("You are stupid");
            System.out.println(y);
        }
    }

    private void expr() throws LangParseException{
        int step=schetchik;
        try{
            L_expr();
        }catch(LangParseException e){
            schetchik=step;
            try{
                uslovie();
            }catch(LangParseException e2){
                schetchik=step;
                try{
                    chikl();
                } catch(LangParseException e3){
                    schetchik=step;
                    try{
                        increment();
                    }catch(LangParseException e4){
                            schetchik=step;
                            try{
                                polis();
                            }catch (LangParseException e6) {
                                schetchik=step;
                                try {
                                    NEWLL();
                                }catch (LangParseException e7){
                                    schetchik=step;
                                    try{
                                        LinkedLinst();
                                    }catch (LangParseException e8){
                                        schetchik=step;
                                        throw e;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    private void L_expr() throws LangParseException {
        var();
        assignOp();
        assignExpr();
        END_str();

    }

    private void whilee() throws LangParseException{
        match(getCurrentToken(), Lexem.WHILE);
    }

    private void iff() throws LangParseException{
        match(getCurrentToken(), Lexem.IF);
    }

    private void uslovie() throws LangParseException{
        int step=schetchik;
        try {
            iff();
            us_while_head();
            us_while_result();

        }catch (LangParseException e){
            schetchik=step;
            else_result();

        }
    }

    private void chikl() throws LangParseException{
        whilee();
        us_while_head();
        us_while_result();

    }


    private void us_while_head() throws LangParseException{
        l_bracket();
        logivac();
        r_bracket();
    }

    private void us_while_result() throws LangParseException{
        fl_bracket();
        try{
            while(!(tokens.get(schetchik).getLexem().equals(Lexem.FR_skobka)))
                expr();
            fr_bracket();
        }catch(LangParseException e){
            fr_bracket();
        }
    }

    private void else_result() throws LangParseException{
        else_head();
        fl_bracket();
        try{
            while(!(tokens.get(schetchik).getLexem().equals(Lexem.FR_skobka)))
                expr();
            fr_bracket();
        }catch(LangParseException e){
            fr_bracket();
        }
    }

    private void polis() throws LangParseException{
        value();
        op();
        assignExpr();
        END_str();
        expr();
    }

    private void else_head() throws LangParseException{
        match(getCurrentToken(),Lexem.ELSE);
    }

    private void fl_bracket() throws LangParseException{
        match(getCurrentToken(), Lexem.FL_skobka);
    }

    private void fr_bracket() throws LangParseException{
        match(getCurrentToken(), Lexem.FR_skobka);
    }

    private void l_bracket() throws LangParseException{
        match(getCurrentToken(), Lexem.L_skobka);
    }

    private void r_bracket() throws LangParseException{
        match(getCurrentToken(), Lexem.R_skobka);
    }

    private void logivac() throws LangParseException{
        value();
        logical();
        value();
    }

    private void skobki() throws LangParseException{
        l_bracket();
        try{
            while(!(tokens.get(schetchik).getLexem().equals(Lexem.R_skobka)))
                assignExpr();
            r_bracket();
        }catch(LangParseException e){
            r_bracket();
        }
    }

    private void increment() throws LangParseException{
        var();
        PLUS();
        PLUS();
    }

    private void assignOp() throws LangParseException {
        match(getCurrentToken(), Lexem.ASSIGN_OP);
    }

    private void assignExpr() throws LangParseException{
        int step=schetchik;
        try{
            value();
            while (schetchik<tokens.size()) {
                if(tokens.get(schetchik).getLexem().equals(Lexem.END)){
                    break;
                }
                else if(tokens.get(schetchik).getLexem().equals(Lexem.R_skobka)){
                    break;
                }
                op();
                value();
            }
        }catch(LangParseException e){
            schetchik=step;
                skobki();
        }
    }

    private void op() throws LangParseException {
        int step=schetchik;
        try{
            PLUS();
        }catch(LangParseException e){
            schetchik=step;
            try{
                MINUS();
            }catch(LangParseException e2){
                schetchik=step;
                try{
                    MULTIPLY();
                }catch(LangParseException e3){
                    schetchik=step;
                    try {
                        DIVISION();
                    }catch (LangParseException e4){
                        schetchik=step;
                        DEGREE();
                    }
                }
            }
        }
    }

    private void NEWLL() throws LangParseException{
        LLL();
        NAME();
    }

    private void LinkedLinst() throws LangParseException{
        int step=schetchik;
        try{
            addf();
        }catch (LangParseException e1){
            schetchik=step;
            try{
                addl();
            }catch (LangParseException e2){
                schetchik=step;
                try {
                    remf();
                }catch (LangParseException e3){
                    schetchik=step;
                    try {
                        reml();
                    }catch (LangParseException e4){
                        schetchik=step;
                        CHECK();
                    }
                }
            }
        }
    }

    private void addf() throws LangParseException{
        ADDF();
        value();
    }

    private void addl() throws LangParseException{
        ADDL();
        value();
    }

    private void remf() throws LangParseException{
        REMF();
        value();
    }

    private void reml() throws LangParseException{
        REML();
        value();
    }

    private void CHECK() throws LangParseException{
        Check();
        value();
    }

    private void LLL() throws LangParseException{
        match(getCurrentToken(), Lexem.NEWLIST);
    }

    private void NAME() throws LangParseException{
        int step=schetchik;
        try{
        match(getCurrentToken(),Lexem.VAR);
        }
        catch (LangParseException not_name){
            schetchik=step;
            return;
        }
    }

    private void ADDF() throws LangParseException{
        match(getCurrentToken(),Lexem.ADDFIRST);
    }

    private void ADDL() throws LangParseException{
        match(getCurrentToken(),Lexem.ADDLAST);
    }

    private void REMF()throws LangParseException{
        match(getCurrentToken(),Lexem.REMOVEFIRST);
    }

    private void REML() throws LangParseException{
        match(getCurrentToken(),Lexem.REMOVELAST);
    }

    private void Check() throws LangParseException{
        match(getCurrentToken(),Lexem.CHECK);
    }

    private void END_str() throws LangParseException{
        match(getCurrentToken(), Lexem.END);
    }

    private void value() throws LangParseException {
        int step = schetchik;
        try{
            var();
        } catch (LangParseException e) {
            schetchik=step;
            try{
                digit();
            }catch(LangParseException e2){
                schetchik=step;
                skobki();
            }
        }
    }

    private void DEGREE() throws LangParseException{
        match(getCurrentToken(),Lexem.DEGREE);
    }

    private void logical() throws LangParseException{
        int step=schetchik;
        try{
            l_and();
        } catch (LangParseException e){
            schetchik=step;
            try{
                l_or();
            } catch (LangParseException e2){
                schetchik=step;
                try{
                    l_op();
                }catch(LangParseException e3){
                    schetchik=step;
                    sravnenie();
                }
            }
        }
    }


    private void FOR() throws LangParseException{
        match(getCurrentToken(), Lexem.FOR);
    }

    private void sravnenie() throws LangParseException{
        int step=schetchik;
        try{
            match(getCurrentToken(),Lexem.MOP);
        }catch (LangParseException e){
            schetchik=step;
            match(getCurrentToken(),Lexem.BOP);
        }
    }

    private void var() throws LangParseException {
        match(getCurrentToken(), Lexem.VAR);
    }

    private void PLUS() throws LangParseException {
        match(getCurrentToken(), Lexem.PLUS);
    }

    private void MINUS() throws LangParseException {
        match(getCurrentToken(), Lexem.MINUS);
    }

    private void MULTIPLY() throws LangParseException {
        match(getCurrentToken(), Lexem.MULTIPLY);
    }

    private void DIVISION() throws LangParseException {
        match(getCurrentToken(), Lexem.DIVISION);
    }

    private void l_and() throws LangParseException {
        match(getCurrentToken(), Lexem.logical_and);
    }


    private void l_or() throws LangParseException {
        match(getCurrentToken(), Lexem.logical_or);
    }

    private void l_op() throws LangParseException {
        match(getCurrentToken(), Lexem.logical_op);
    }

    private void digit() throws LangParseException {
        match(getCurrentToken(), Lexem.DIGIT);
    }

    private void match(Token token, Lexem lexem) throws LangParseException {
        if (!token.getLexem().equals(lexem)) {
            throw new LangParseException( lexem.name() + " expected " +
                    "but " + token.getLexem().name() + " found");
        }
    }


    private Token getCurrentToken() {
        // TODO
        Token token = null;
        if(schetchik<tokens.size()){
            token = tokens.get(schetchik);
            schetchik++;
        }
        return token;
    }
}
