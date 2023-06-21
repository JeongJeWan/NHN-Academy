public class WhitespaceVisitor implements IVisitor{

    @Override
    public String visit(Token token) {
        return "&nbsp;";
    }

}
