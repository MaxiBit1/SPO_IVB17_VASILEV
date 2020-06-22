package com.company.token;

import com.company.lexer.Lexem;
public class Token {
    private final Lexem lexem;
    private String value;

    public Token(Lexem type, String value) {
        this.lexem = type;
        this.value = value;
    }

    public Lexem getLexem() {
        return lexem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){this.value=value;}

    @Override
    public String toString() {
        return "Token{" +
                "lexem=" + lexem +
                ", value='" + value + '\'' +
                '}';
    }
}
