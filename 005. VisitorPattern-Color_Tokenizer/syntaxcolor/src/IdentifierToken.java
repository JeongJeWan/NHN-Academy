public class IdentifierToken extends Token{

    public IdentifierToken(String value) {
        super(value);
    }

    @Override
    String accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
