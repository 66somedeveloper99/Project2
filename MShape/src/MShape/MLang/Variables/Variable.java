package MShape.MLang.Variables;

public abstract class Variable {
    public final String name;
    public Variable(String name) {
        this.name = name;
    }
    public abstract void SetValue(Object value);
    public abstract Object GetValue();
}
