public class SharedListValue 
{
    private int Value;

    public SharedListValue()
    {
        Value=0;
    }

    public SharedListValue(int value)
    {
        Value=value;
    }

    public int getValue()
    {
        return Value;
    }

    public void setValue(int value)
    {
        Value = value;
    }

    public void display()
    {
        System.out.print(Value + "\n");
    }
}
