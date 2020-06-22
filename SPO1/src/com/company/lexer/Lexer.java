package com.company.lexer;

import com.company.token.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
public class Lexer {
    private final String rawInput;

    public Lexer(final String rawInput) {
        this.rawInput = rawInput+"~";
    }

    public String getRawInput() {
        return rawInput;
    }

    public List<Token> tokens() {
        final String source = rawInput;
        int currentIndex = 0;
        int currentIndexFrom = 0;

        boolean okWaiting = true;
        final List<Token> tokens = new ArrayList<>();
        Lexem prevLexem = null;

        while (currentIndex < source.length()) {
            final String acc = source.substring(currentIndexFrom, currentIndex + 1);
            Lexem currentLexem = null;
            for (final Lexem lexem : Lexem.values()) {
                final Matcher matcher = lexem.getPattern().matcher(acc);
                if (matcher.find()) {
                    currentLexem = lexem;
                }
            }
            if (currentLexem != null) {
                prevLexem = currentLexem;
            }
            if (okWaiting && currentLexem != null) {
                okWaiting = false;
            }
            if (okWaiting && currentLexem == null) {
                throw new RuntimeException("Incorrect source");
            }
            if (!okWaiting && currentLexem == null ) {
                final Token token = new Token(prevLexem, acc.substring(0, acc.length() - 1));
                tokens.add(token);
                okWaiting = true;
                currentIndexFrom = currentIndex;
            }
            else {
                currentIndex = currentIndex + 1;
            }
        }
        return tokens;
    }
}
