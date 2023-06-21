public class KeywordToken extends Token {
    public KeywordToken(String value) {
        super(value);
    }

    @Override
    String accept(IVisitor visitor) {
        return visitor.visit(this);
    }
}
