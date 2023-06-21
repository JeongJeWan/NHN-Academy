import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input source file name: ");
        String fileName = scanner.nextLine();
        KeywordTokenizer tokenizer = new KeywordTokenizer(fileName);
        scanner.close();

        List<Token> list = tokenizer.getToken();

        IVisitor keywordIVisitor =  new KeywordVisitor();
        IVisitor symbolIVisitor = new SymbolVisitor();
        IVisitor idetifierIVisitor = new IdentifierVisitor();
        IVisitor whiteSpaceIVisitor = new WhitespaceVisitor();
        IVisitor packageIVisitor = new PackageVisitor();

        try {
            File file = new File(fileName.replace(".java", "") + ".html");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("<html>");
            printWriter.println("<header>");
            printWriter.println("<link rel=\"stylesheet\" href=\"ColorTokenizeredSourceFile.css\">");
            printWriter.println("</header>");
            printWriter.println("</html>");
            printWriter.println("<div>");


            for(Token token : list) {
                if (token instanceof KeywordToken) {
                    printWriter.println(keywordIVisitor.visit(token));
                }
                if (token instanceof SymbolToken) {
                    printWriter.println(symbolIVisitor.visit(token));
                }
                if (token instanceof IdentifierToken) {
                    printWriter.println(idetifierIVisitor.visit(token));
                }
                if (token instanceof WhiteSpaceToken) {
                    printWriter.println(whiteSpaceIVisitor.visit(token));
                }
                if (token instanceof PackageToken) {
                    printWriter.println(packageIVisitor.visit(token));
                }
            }

            printWriter.println("</div>");
            printWriter.println("</body>");
            printWriter.println("</html>");
            printWriter.close();
            System.out.println("Color Tokenized Source File is saved to " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
