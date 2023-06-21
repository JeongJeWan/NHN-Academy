public class SymbolVisitor implements IVisitor {
    @Override
    public String visit(Token token) {
        String symbol = "<span style=\"color:red\">" + token.getValue() + "</span>";
        if (token.getValue().equals(";") || token.getValue().equals("}") || token.getValue().equals("{")) {
            symbol = symbol + "</div>\n<div>";
        }
        return symbol;
    }
}
