public class ProcessWaitingList {
    private static ProcessNode Front;
    private static ProcessNode Back;
    static int Count;


    public ProcessWaitingList() {
        Front = null;
        Back = null;
        Count = 0;
    }

    public ProcessWaitingList(ProcessNode back) {
        Back = back;
        Front = back;
        Count = 0;
    }

    public static ProcessNode getFront() {
        return Front;
    }

    public static void setFront(ProcessNode head) {
        Front = head;
    }

    public static ProcessNode getBack() {
        return Back;
    }

    public static void setBack(ProcessNode back) {
        Back = back;
    }

    public static int getCount() {
        return Count;
    }

    public static void setCount(int count) {
        Count = count;
    }

    public static boolean isEmpty() {

        if (Front != null) {
            return false;
        }
        return true;
    }

    public static void enqueue(Process data) {
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
                if(temp==null){
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("Empty Queue");
        }
    }
}
