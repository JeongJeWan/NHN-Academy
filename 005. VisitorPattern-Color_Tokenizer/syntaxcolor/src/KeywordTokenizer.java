import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KeywordTokenizer {
    private String fileName;

    public KeywordTokenizer(String fileName) {
        this.fileName = fileName;
    }

    public List<Token> getToken() throws IOException {
        List<Token> tokens = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " <>(),.:[];\"",true);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                if (isKeyword(word)) {
                    tokens.add(new KeywordToken(word));
                } else if (isSymbol(word)) {
                    tokens.add(new SymbolToken(word));
                } else if (isWhitespace(word)) {
                    tokens.add(new WhiteSpaceToken(word));
                } else if (isPackage(word)) {
                    tokens.add(new PackageToken(word));
                } else {
                    tokens.add(new IdentifierToken(word));
                }
            }
        }
        reader.close();
        return tokens;
    }


    private final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
            "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while"};

    private final String[] PACKAGE = {"File", "FileWriter", "IOException", "PrintWriter", "List", "Scanner", "Scanner", "System", "KeywordTokenizer",
            "List", "java", "io", "util", "String", "IVisitor", "Token", "KeywordToken", "SymbolToken", "WhiteSpaceToken", "IdentifierToken"};


    private boolean isKeyword(String word) {
        for (String keyword : KEYWORDS) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPackage(String word) {
        for (String keyword : PACKAGE) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSymbol(String word) {
        String symbols = "{}[]();,.+-*/&|<>=~!";
        return symbols.contains(word);
    }

    private boolean isWhitespace(String word) {
        String symbols = " ";
        return symbols.contains(word);
    }
}
