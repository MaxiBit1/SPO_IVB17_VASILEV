package com.company;

import com.company.token.Token;
import com.company.exception.LangParseException;
import com.company.lexer.Lexer;
import com.company.parser.Parser;
import com.company.polis.Polis;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args)  throws LangParseException,IOException{
        Lexer lexer = new Lexer(readingProgram("F:\\ProjectGit\\SPO1\\src\\com\\company\\CODE.txt"));

        List<Token> tokens = lexer.tokens();
        List<Token> PolisTokens;

        for (Token token: tokens) {
            System.out.println(token);
        }

        Parser parser = new Parser( tokens );

        parser.lang();



        Polis polis1= new Polis();

        PolisTokens=polis1.getPolis(tokens);

        int A=polis1.Stack(PolisTokens);


    }

    private static String readingProgram(String path) throws IOException{
        String value="";
        FileReader fileReader=new FileReader(path);
        Scanner SC=new Scanner(fileReader);
        while (SC.hasNext()){
            value+=SC.nextLine();
        }
        SC.close();
        fileReader.close();
        return value;
    }
}

