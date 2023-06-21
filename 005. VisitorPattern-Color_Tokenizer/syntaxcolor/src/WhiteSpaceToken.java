public class WhiteSpaceToken extends Token{

    public WhiteSpaceToken(String value) {
        super(value);
    }

    @Override
    String accept(IVisitor visitor) {
        return visitor.visit(this);
    }
}
