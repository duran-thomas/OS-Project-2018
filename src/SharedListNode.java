public class SharedListNode {

    private static int counter = 0;
    private int Key;
    private SharedListValue Data;
    private SharedListNode NextNode;

    public SharedListNode()
    {
        Key = 0;
        Data = null;
        NextNode = null;
    }

    public SharedListNode(int value)
    {
        Key = counter;
        counter++;
        Data = new SharedListValue(value);
        NextNode = null;
    }

    public SharedListNode(SharedListValue data)
    {
        Key = counter;
        counter++;
        setData(data);
        NextNode = null;
    }

    public int getKey()
    {
        return Key;
    }

    public void setKey(int key)
    {
        Key = key;
    }

    public SharedListValue getData()
    {
        return Data;
    }

    public void setData(SharedListValue data)
    {
        Data = data;
    }

    public SharedListNode getNextNode()
    {
        return NextNode;
    }

    public void setNextNode(SharedListNode nextNode)
    {
        NextNode = nextNode;
    }

    public void displayNode()
    {
        System.out.println("KEY: "+Key + "\t\tVALUE: " + Data.getValue());
    }
}
