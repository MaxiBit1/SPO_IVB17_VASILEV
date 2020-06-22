package com.company.lexer;

import java.util.regex.Pattern;

public enum Lexem {
    VAR("^[a-zA-Z]+$"),
    DIGIT("^(0|([1-9][0-9]*))$"),
    ASSIGN_OP("^=$"),
    MOP("^\\<$"),
    BOP("^\\>$"),
    PLUS("^\\+$"),
    MINUS("^\\-$"),
    MULTIPLY("^\\*$"),
    DIVISION("^\\/$"),
    WHILE("^while$"),
    IF("^if$"),
    FOR("^for$"),
    R_skobka("^\\)$"),
    L_skobka("^\\($"),
    FR_skobka("^\\}$"),
    FL_skobka("^\\{$"),
    logical_and("^\\&$"),
    logical_or("^\\_$"),
    logical_op("^\\@$"),
    END("^;$"),
    ELSE("^else$"),
    DEGREE("^\\^$"),
    NEWLIST("^NLL$"),
    ADDFIRST("^addFirst$"),
    ADDLAST("^addLast$"),
    REMOVEFIRST("^removeFirst$"),
    REMOVELAST("^removaLast$"),
    CHECK("^checkElem$"),
    XZ("^~$");

    private final Pattern pattern;

    Lexem(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
