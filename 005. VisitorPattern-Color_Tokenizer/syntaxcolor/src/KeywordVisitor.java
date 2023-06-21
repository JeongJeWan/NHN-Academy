public class KeywordVisitor implements IVisitor {
    @Override
    public String visit(Token token) {
        
        return "<span style=\"color:blue\">" + token.getValue() + "</span>";
    }
}
