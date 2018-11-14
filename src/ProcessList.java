public class ProcessList {
    private static ProcessNode Front;
    private static ProcessNode Back;
    static int Count;

    public ProcessList()
    {
        Front = null;
        Back = null;
        Count = 0;
    }

    public ProcessList(ProcessNode back)
    {
        Back = back;
        Front = back;
        Count = 0;
    }

    public static ProcessNode getFront()
    {
        return Front;
    }

    public static void setFront(ProcessNode head)
    {
        Front = head;
    }

    public static ProcessNode getBack()
    {
        return Back;
    }

    public static void setBack(ProcessNode back)
    {
        Back = back;
    }

    public static int getCount()
    {
        return Count;
    }

    public static void setCount(int count)
    {
        Count = count;
    }

    public static boolean isEmpty()
    {

        if (Front != null) {
            return false;
        }
        return true;
    }

    public static void enqueue(Process data)
    {
        ProcessNode temp = new ProcessNode(data);
        if (isEmpty()) {
            Front = temp;
        } else {
            if (Back == null) {
                Back = temp;
                Front.setNextNode(Back);
            } else {
                ProcessNode previousTail = Back;
                ProcessNode newTail = temp;
                previousTail.setNextNode(newTail);
                Back = newTail;
            }
        }
        Count++;
    }

    public static Process dequeue() {
        ProcessNode nodeToReturn = Front;
        if (isEmpty()) {
            return null;
        } else {
            if (Back == null) {
                Front = null;
            } else {
                Front = Front.getNextNode();
            }
            Count--;
        }
        return nodeToReturn.getData();
    }

    public static void display() {
        if (isEmpty() == false) {
            ProcessNode temp = Front;
            while (temp != null) {
                temp.getData().displayShort();
                temp = temp.getNextNode();
            }
        } else {
            System.out.println("Process queue is empty");
        }
    }

    public static void displayList(){
        if (isEmpty() == false) {
            ProcessNode temp = Front;

            System.out.print("\nPID \tTask \tPriority \t\t\tCreate Time \t\t\t\t\tStart Time \t\t\t\tEnd Time \t\t\tAttempts \t\t\tSleep Time \n");
            while (temp != null) {
                temp.getData().displayList();
                temp = temp.getNextNode();
            }
        } else {
            System.out.println("Empty Queue");
        }
    }
    public static void displayFull(){
        if (isEmpty() == false) {
            ProcessNode temp = Front;

            System.out.print("\nPID \tTask \tPriority \t\t\tCreate Time \t\t\t\t\tStart Time \t\t\t\tEnd Time \t\t\tAttempts \t\t\tSleep Time \n");
            while (temp != null) {
                temp.getData().displayFull();
                temp = temp.getNextNode();
            }
        } else {
            System.out.println("Empty Queue");
        }
    }

}
