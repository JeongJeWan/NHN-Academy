public class SymbolToken extends Token {
    public SymbolToken(String value) {
        super(value);
    }

    @Override
    String accept(IVisitor visitor) {
        return visitor.visit(this);
    }
}
