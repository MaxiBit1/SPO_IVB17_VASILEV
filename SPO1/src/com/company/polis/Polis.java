package com.company.polis;

import com.company.lexer.Lexem;
import com.company.token.Token;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
public class Polis {
    private static List<Token> tokens;
    private static List<Token> TokenList = new ArrayList<>();
    private static Stack<Token> StackOperations = new Stack<>();
    private static boolean resulttop,resultop;
    private static int  exp1=0;
    private static int int_if=0;
    private static int int_else=0;
    private static int exp2=0;
    private static int while_int=0;
    private static int fr_int=0;
    //private static int ch=0;
    private static HashMap <String,Integer> hashM= new HashMap <>();

    public static List<Token> getPolis(final List<Token> tokensIN){
        tokens = tokensIN;
        TokenList.clear();
        StackOperations.clear();

        for(final Token token : tokens){
            if(token.getValue().equals("if")){
                exp1=1;
            }
            else if(token.getValue().equals("while")){
                exp2=1;
            }

            if(getPriority(token) == 0){
                operandFind(token);
            }else{
                operationFind(token);
            }
        }
        push_From_steak();
        if(exp1==1){
            getPolisIF();
        }
        else if(exp2==1){
            getPolisWhile();
        }
        System.out.println("Polis: "+TokenList);
        return TokenList;
    }

    private static void operandFind(final Token tokenn){
        TokenList.add(tokenn);
    }

    private static void operationFind(final Token tokenn){
        if(exp1==1 ){
            if(tokenn.getLexem().equals(Lexem.L_skobka)||tokenn.getLexem().equals(Lexem.R_skobka) || tokenn.getLexem().equals(Lexem.FR_skobka) || tokenn.getLexem().equals(Lexem.FL_skobka)){
                push_From_steak();
                return;
            }
        }else if(exp2==1){
            if(tokenn.getLexem().equals(Lexem.FL_skobka)) {
                push_From_steak();
                return;
            }

            if(tokenn.getLexem().equals(Lexem.L_skobka)||tokenn.getLexem().equals(Lexem.R_skobka)){
                return;
            }
        }
        if(tokenn.getLexem().equals(Lexem.END)){
            push_From_steak();
        }else if(StackOperations.empty()||tokenn.getLexem().equals(Lexem.L_skobka)||(getPriority(tokenn)>getPriority(StackOperations.peek()))){
            StackOperations.push(tokenn);
        }
        else{
            do{
                TokenList.add(StackOperations.pop());
            }while(!StackOperations.empty() && (getPriority(tokenn)<=getPriority(StackOperations.peek())));
            StackOperations.push(tokenn);
        }
    }

    private static void push_From_steak(){
        while(!StackOperations.empty() && !StackOperations.peek().getLexem().equals(Lexem.L_skobka)){
            Token h1=StackOperations.pop();
            if (h1.getValue().equals("if") || h1.getValue().equals("else") || h1.getValue().equals("while")){
                Token h2=new Token(Lexem.DIGIT,"100000");
                TokenList.add(h2);
            }
            if(exp2==1){
                if(h1.getValue().equals("}")){
                    Token h2=new Token(Lexem.DIGIT,"100000");
                    TokenList.add(h2);
                }
            }

            TokenList.add(h1);
        }if(!StackOperations.empty()){
            StackOperations.pop();
            if(!StackOperations.empty()){
                if(StackOperations.peek().getLexem().equals(Lexem.IF)){
                    getPolisIF();
                }
            }
        }
    }

    private static void getPolisIF(){

        int ind4if=0;
        int ind4else=0;
        for(final Token token : TokenList) {
            if (token.getLexem().equals(Lexem.IF)) {
                int_if=TokenList.indexOf(token);

            }


            if (token.getLexem().equals(Lexem.ELSE)) {
                int_else=TokenList.indexOf(token);

            }
        }
        ind4if=int_else+1;
        ind4else=TokenList.size();
        Token A1= new Token(Lexem.DIGIT,Integer.toString(ind4if));
        Token A2= new Token(Lexem.DIGIT,Integer.toString(ind4else));

        TokenList.set(int_if-1,A1);
        TokenList.set(int_else-1,A2);

    }

    private static void getPolisWhile(){
        int indexWhile=0;
        int indexFR=0;
        for(final Token token:TokenList) {
            if (token.getLexem().equals(Lexem.WHILE)) {
                while_int = TokenList.indexOf(token);
            }
            if (token.getLexem().equals(Lexem.FR_skobka)) {
                fr_int = TokenList.indexOf(token);
            }
        }
        indexWhile=while_int-4;
        indexFR=TokenList.size();
        Token A3=new Token(Lexem.DIGIT,Integer.toString(indexWhile));
        Token A4=new Token(Lexem.DIGIT,Integer.toString(indexFR));

        TokenList.set(while_int-1,A4);
        TokenList.set(fr_int-1,A3);



    }

    private static void getPolisWhileN(List <Token> tokel){
        for(final Token token:tokel) {
            if (token.getLexem().equals(Lexem.WHILE)) {
                while_int = tokel.indexOf(token);
            }
            if (token.getLexem().equals(Lexem.FR_skobka)) {
                fr_int = tokel.indexOf(token);
            }
        }


    }



    private static int getPriority(final Token token){
        switch(token.getLexem()){
            case L_skobka: return 1;
            case R_skobka: return 1;
            case FR_skobka: return 2;
            case FL_skobka: return 1;
            case IF: return 1;
            case WHILE: return 1;
            case ELSE: return 1;
            case ASSIGN_OP: return 1;
            case MOP: return 2;
            case BOP: return 2;
            case PLUS: return 3;
            case MINUS: return 3;
            case MULTIPLY: return 4;
            case DIVISION: return 4;
            case DEGREE: return 4;
            case END: return 1;

            default: return 0;

        }
    }
    public static int GetValue (Token toka){
        int aa=0;
        if (toka.getLexem().equals(Lexem.VAR))
        {
            return (hashM.get(toka.getValue()));

        }
        else {
            return Integer.parseInt(toka.getValue());
        }

    }


    public static int Stack(final List<Token> TokensID){
        int operand1=0;
        int operand2=0;
        int result=0;
        boolean resulttop;
        int ch=0;
        int k=0;
        int t=1;
        String value_first;
        String operators="";

        for(Token token:TokensID){
            if(exp1==1){
                if ((ch==20 && TokensID.indexOf(token)< int_else+1)||(ch==15 &&  TokensID.indexOf(token)< int_else+1) ){
                    continue;
                } else if ((ch==10 && TokensID.indexOf(token)>int_else-1)||(ch==5 && TokensID.indexOf(token)>int_else-1)) {
                    break;
                }
            }
            if(exp2==1) {
                if ((ch == 10 && TokensID.indexOf(token) < while_int+1)||(ch==5 && TokensID.indexOf(token) < while_int+1)) {
                    continue;
                } else if (ch == 20 || ch==15 ) {
                    break;
                }
                else if(TokensID.indexOf(token)==fr_int){
                    List<Token> TokenList1 = new ArrayList<>();
                    TokenList1= TokensID.subList(while_int-4,fr_int+1);
                    getPolisWhileN(TokenList1);
                    int b=Stack(TokenList1);
                    int aaa=0;

                }
            }

            if(token.getLexem().equals(Lexem.VAR)){
                if(!hashM.containsKey(token.getValue())) {
                    hashM.put(token.getValue(),0);

                }
                StackOperations.push(token);
            }
            if(token.getLexem().equals(Lexem.ASSIGN_OP)){
                Token A1=StackOperations.pop();
                Token B1= StackOperations.pop();
                hashM.put(B1.getValue(), Integer.parseInt(A1.getValue()));
                System.out.println("Variable is '"+B1.getValue()+"' Result is "+hashM.get(B1.getValue()));

            }


            if(token.getLexem().equals(Lexem.DIGIT)){
                StackOperations.push(token);
            }else if(token.getLexem().equals(Lexem.PLUS)){
                operators= token.getValue();
                operand1 = GetValue(StackOperations.pop());
                operand2 = GetValue(StackOperations.pop());
                result= operand1+operand2;
                StackOperations.push(new Token(Lexem.DIGIT, Integer.toString(result)));
            }else if(token.getLexem().equals(Lexem.MINUS)){
                operators = token.getValue();
                operand1=GetValue(StackOperations.pop());
                operand2=GetValue(StackOperations.pop());
                result= operand2-operand1;
                StackOperations.push(new Token(Lexem.DIGIT, Integer.toString(result)));
            }else if(token.getLexem().equals(Lexem.MULTIPLY)){
                operators = token.getValue();
                operand1=GetValue(StackOperations.pop());
                operand2=GetValue(StackOperations.pop());
                result= operand1*operand2;
                StackOperations.push(new Token(Lexem.DIGIT, Integer.toString(result)));
            }else if(token.getLexem().equals(Lexem.DIVISION)){
                operators = token.getValue();
                operand1=GetValue(StackOperations.pop());
                operand2=GetValue(StackOperations.pop());
                result= operand2/operand1;
                StackOperations.push(new Token(Lexem.DIGIT, Integer.toString(result)));
            }else if(token.getLexem().equals(Lexem.MOP)){
                operators= token.getValue();
                operand1=GetValue(StackOperations.pop());
                operand2=GetValue(StackOperations.pop());
                resulttop=operand2<operand1;
                if (resulttop) {
                    ch=10;
                }
                else {
                    ch=20;
                }
            }else if(token.getLexem().equals(Lexem.BOP)){
                operators= token.getValue();
                operand1=GetValue(StackOperations.pop());
                operand2=GetValue(StackOperations.pop());
                resulttop=operand2>operand1;
                if(resulttop){
                    ch=5;
                }else {
                    ch=15;
                }

            }else if(token.getLexem().equals(Lexem.DEGREE)) {
                operators = token.getValue();
                operand1 = GetValue(StackOperations.pop());
                operand2 = GetValue(StackOperations.pop());
                while (k < operand1) {
                    t = t * operand2;
                    k++;
                }
                result = t;
                StackOperations.push(new Token(Lexem.DIGIT, Integer.toString(result)));
            }else if(token.getLexem().equals(Lexem.NEWLIST)){
              


            }

        }
        System.out.println(result);
        return result;
    }
}
