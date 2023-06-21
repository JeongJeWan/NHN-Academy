public class PackageVisitor implements IVisitor{

    @Override
    public String visit(Token token) {

        return "<span style=\"color:green\">" + token.getValue() + "</span>";
    }

}
