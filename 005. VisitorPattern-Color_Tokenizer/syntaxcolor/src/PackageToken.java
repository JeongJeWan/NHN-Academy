public class PackageToken extends Token{

    public PackageToken(String value) {
        super(value);
    }

    @Override
    String accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
