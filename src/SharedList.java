public class SharedList {
    private static SharedListNode Head;
    private static int Count;

    public SharedList()
    {
        Head = null;
        Count = 0;
    }

    public static SharedListNode getHead()
    {
        return Head;
    }

    public void setHead(SharedListNode head)
    {
        Head = head;
    }

    public static int getCount()
    {
        return Count;
    }

    public static boolean isEmpty()
    {
        if (Head != null) {
            return false;
        }
        return true;
    }

    public synchronized static void insert(int value)
    {
        SharedListNode temp = new SharedListNode(value);
        if (temp != null) {
            if (isEmpty()) {
                Head = temp;
            } else {
                temp.setNextNode(Head);
                Head = temp;
            }
            Count++;
        }
    }

    public synchronized static void insert(SharedListValue data)
    {
        SharedListNode temp = new SharedListNode(data);
        if (temp != null) {
            if (isEmpty()) {
                Head = temp;
            } else {
                temp.setNextNode(Head);
                Head = temp;
            }
            Count++;
        }
    }

    public synchronized static int remove()
    {
        int value=0;
        if (!isEmpty()) {
            value=Head.getData().getValue();
            Head = Head.getNextNode();
            Count--;
        } else {
            System.out.println("Shared list is empty");
            return -1;
        }
        return value;
    }

    public synchronized static int retrieve(int key)
    {
        int value = -1;
        if (isEmpty() == false) {
            SharedListNode temp = Head;

            while (temp != null) {
                if (temp.getKey() == key) {
                    value = temp.getData().getValue();
                    break;
                } else {
                    temp = temp.getNextNode();
                }
            }
        }
        return value;
    }
    public synchronized static void sort()
    {
        if (!isEmpty()) {
            boolean wasChanged;

            do {
                SharedListNode current = Head;
                SharedListNode previous = null;
                SharedListNode next = Head.getNextNode();
                wasChanged = false;

                while (next != null) {
                    if (current.getData().getValue() > next.getData().getValue()) {
                        wasChanged = true;

                        if (previous != null) {
                            SharedListNode sig = next.getNextNode();

                            previous.setNextNode(next);
                            next.setNextNode(current);
                            current.setNextNode(sig);
                        } else {
                            SharedListNode sig = next.getNextNode();

                            Head = next;
                            next.setNextNode(current);
                            current.setNextNode(sig);
                        }

                        previous = next;
                        next = current.getNextNode();
                    } else {
                        previous = current;
                        current = next;
                        next = next.getNextNode();
                    }
                }
            } while (wasChanged);
        }else{
            System.out.println("List is empty");
        }
    }

    public synchronized static int calculateTotal()
    {
        int total = 0;
        if (isEmpty() == false) {
            SharedListNode temp = Head;

            while (temp != null) {
                total += temp.getData().getValue();
                temp = temp.getNextNode();
            }
        } else {
            System.out.println("Shared list is empty");
            return -1;
        }
        return total;
    }

    public static void display()
    {
        if (isEmpty() == false) {
            SharedListNode temp = Head;
            while (temp != null) {
                temp.displayNode();
                temp = temp.getNextNode();
            }
            System.out.println("\nNumber of items in Shared List: "+Count +"\n");
        } else {
            System.out.println("Shared list is empty");
        }
    }
}
