import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Process implements Runnable{
    Scanner in = new Scanner(System.in);
    Random rand = new Random();
    Thread thread;

    private int PID;
    private int Task;
    private int Priority;
    private LocalDateTime CreateTime;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private int Attempts;
    private int SleepTime;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    public Process() {
        thread = null;
        PID = 0;
        Task = 0;
        Priority = 0;
        CreateTime = null;
        StartTime = null;
        EndTime = null;
        Attempts = 0;
        SleepTime = 0;
    }

    public int getPID()
    {
        return PID;
    }

    public void setPID(int pID)
    {
        PID = pID;
    }

    public int getTask()
    {
        return Task;
    }

    public void setTask(int task)
    {
        Task = task;
    }

    public int getPriority()
    {
        return Priority;
    }

    public void setPriority(int priority)
    {
        Priority = priority;
    }

    public LocalDateTime getCreateTime()
    {
        return CreateTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        CreateTime = createTime;
    }

    public LocalDateTime getStartTime()
    {
        return StartTime;
    }

    public void setStartTime(LocalDateTime startTime)
    {
        StartTime = startTime;
    }

    public LocalDateTime getEndTime()
    {
        return EndTime;
    }

    public void setEndTime(LocalDateTime endTime)
    {
        EndTime = endTime;
    }

    public int getAttempts()
    {
        return Attempts;
    }

    public void setAttempts(int attempts)
    {
        Attempts = attempts;
    }

    public int getSleepTime() {
        return SleepTime;
    }

    public void setSleepTime(int sleepTime)
    {
        SleepTime = sleepTime;
    }

    public void displayShort()
    {
        String task = null;
        if (Task == 1) {
            task = "Insert  ";
        } else if (Task == 2) {
            task = "Remove  ";
        } else if (Task == 3) {
            task = "Sort    ";
        } else if (Task == 4) {
            task = "Retrieve";
        } else if (Task == 5) {
            task = "Total   ";
        }
        System.out.println("PID: " + PID + "\tTask: " + task + "\tPriority: " + Priority);
    }

    public void displayFull()
    {
        String task = null;
        if (Task == 1) {
            task = "Insert  ";
        } else if (Task == 2) {
            task = "Remove  ";
        } else if (Task == 3) {
            task = "Sort    ";
        } else if (Task == 4) {
            task = "Retrieve";
        } else if (Task == 5) {
            task = "Total   ";
        }
        System.out.print("\nPID: " + PID + "\t "+"Task: " + task + "\t "+"Priority: " + Priority + "\t " +"Created: "+ dtf.format(CreateTime) + "\t\t" +"Start: "+ dtf.format(StartTime) + "\t\t"+"End: " + dtf.format(EndTime) + "\t	"+"Attempt: " + Attempts + "\t "+"Sleep: " + SleepTime + "\n");
    }

    public void displayList()
    {
        String task = null;
        if (Task == 1) {
            task = "Insert  ";
        } else if (Task == 2) {
            task = "Remove  ";
        } else if (Task == 3) {
            task = "Sort    ";
        } else if (Task == 4) {
            task = "Retrieve";
        } else if (Task == 5) {
            task = "Total   ";
        }
        System.out.print("\nPID: "  + PID + "\t "+"Task: " + task + "\t "+"Priority: " + Priority + "\t " +"Created: "+ dtf.format(CreateTime) + "\t\t	" +"Start: "+ dtf.format(StartTime) + "\t\t	"+"End: " + dtf.format(EndTime) + "\t  "+"Attempt: " + Attempts + "\t "+"Sleep: " + SleepTime + "\n");
    }

    @Override
    public void run()
    {
        int value;
        try {
            Attempts += 1;
            if (Task == 1) {
                System.out.println("\nEnter a number you want to insert: " );
                value = in.nextInt();
                SharedList.insert(value);
                System.out.println("\nInserted value =" + value);

            } else if (Task == 2) {
                System.out.println("\nRemoved value " + SharedList.remove());
                SharedList.remove();

            } else if (Task == 3) {
                System.out.print("\nEnter key value to be retrieved: ");
                int key = in.nextInt();

                if (SharedList.retrieve(key) > -1) {
                    System.out.println("\nRetrieved key =" + key + " Value returned: " + SharedList.retrieve(key));
                } else {
                    System.out.println("\nRetrieve key =" + key + " does not exist amongst data");
                }

            } else if (Task == 4) {
                SharedList.sort();
                System.out.println("\nData Sorted");
            } else if (Task == 5) {
                System.out.println("\nTotal interger elements in list = " + SharedList.calculateTotal());

            }
        } catch (Exception e) {
            System.out.println("/n Error!");
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            StartTime = LocalDateTime.now();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            EndTime = LocalDateTime.now();

            ProcessList.dequeue();
        }
    }
}
