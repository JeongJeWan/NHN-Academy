public class IdentifierVisitor implements IVisitor{

    @Override
    public String visit(Token token) {
        
        return "<span style=\"color:black\">" + token.getValue() + "</span>";
    }

}
