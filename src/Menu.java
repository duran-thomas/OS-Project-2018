import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {
    public Menu() {
    }
    @SuppressWarnings("resource")
    public void processing() {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
        Random rand = new Random();

        final int SHARED_LIST_COUNT = 10;
        final int MAX = 30;
        final int MAX_NUM_PROCESS = 10;
        final int MAX_CONCURRENT_OPERATIONS = 5;

        SharedListValue[] listElement = new SharedListValue[60];
        Integer[] RID = new Integer[MAX_NUM_PROCESS];
        Process[] process;

        int Nprocess = 0;
        int[] RecycleId = new int[MAX_CONCURRENT_OPERATIONS];


        try {
            PrintWriter printwriter;
            FileWriter filewriter = new FileWriter("EndReport.txt", true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            printwriter = new PrintWriter(bufferedwriter);
            PrintWriter slPrintWriter ;
            FileWriter slFileWriter = new FileWriter("SharedListReport.txt", true);
            BufferedWriter slBufferWriter = new BufferedWriter(slFileWriter);
            slPrintWriter = new PrintWriter(slBufferWriter);
            printwriter.println("The Process Report");
            slPrintWriter.println("Shared List Data");

            for (int i = 0; i < SHARED_LIST_COUNT; i++) {
                listElement[i] = new SharedListValue();
                listElement[i].setValue((rand.nextInt(500)) + 1);
                SharedList.insert(listElement[i]);
            }

            SharedListNode temp = SharedList.getHead();
            slPrintWriter.println("KEY\t\tVALUE");
            while (temp != null) {
                slPrintWriter.println(temp.getKey() + "\t\t" + temp.getData().getValue());
                temp = temp.getNextNode();
            }
            slPrintWriter.println("The Before Data - Total items: " + SharedList.getCount() + "\n");


            do {
                System.out.println("Please Enter Number of Processes to be executed (Range: 10 - 30): ");
                Nprocess = in.nextInt();
                if(Nprocess<10 || Nprocess>30){
                    System.out.println("Invalid Number of Processes.");
                    System.out.println("Please try again.\n");
                }
            } while (Nprocess > MAX || Nprocess < MAX_NUM_PROCESS);//check for error with the OR

            process = new Process[Nprocess];

            for (int i = 0; i < RID.length; i++) {
                RID[i] = i;
            }
            Collections.shuffle(Arrays.asList(RID));

            for (int i = 0; i < MAX_NUM_PROCESS; i++) {
                process[i] = new Process();
                process[i].setPID(RID[i]);
                process[i].setTask((rand.nextInt(5) + 1));
                process[i].setPriority((rand.nextInt(5) + 1));
                process[i].setCreateTime(LocalDateTime.now());
                process[i].setSleepTime((rand.nextInt(5) + 1));

                ProcessList.enqueue(process[i]);
            }

            ProcessNode tempprocess = ProcessList.getFront();
            printwriter.println("\nProcesses to be executed");
            printwriter.println("\nPID\t\tTask\t\tPriority");
            while (tempprocess != null) {
                String task = null;
                if (tempprocess.getData().getTask() == 1) {
                    task = "Insert  ";
                } else if (tempprocess.getData().getTask() == 2) {
                    task = "Remove  ";
                } else if (tempprocess.getData().getTask() == 3) {
                    task = "Sort	";
                } else if (tempprocess.getData().getTask() == 4) {
                    task = "Retrieve";
                } else if (tempprocess.getData().getTask() == 5) {
                    task = "Total   ";
                }
                printwriter.println("\nPID: " + tempprocess.getData().getPID() + "\t " +"Task: " + task + "\t " + "Priority: " +tempprocess.getData().getPriority());
                tempprocess = tempprocess.getNextNode();
            }

            System.out.println("\nProcesses to be executed");
            ProcessList.display();

            if (Nprocess > MAX_NUM_PROCESS) {
                for (int i = MAX_NUM_PROCESS; i < Nprocess; i++) {
                    process[i] = new Process();
                    process[i].setTask(rand.nextInt(5) + 1);
                    process[i].setPriority((rand.nextInt(5) + 1));
                    process[i].setCreateTime(LocalDateTime.now());
                    process[i].setSleepTime((rand.nextInt(5) + 1));

                    ProcessWaitingList.enqueue(process[i]);// insert into process waiting list data structure
                }

                ProcessNode tempwaitingprocess = ProcessWaitingList.getFront();
                printwriter.println("\nProcesses in Queue");
                printwriter.println("\nPID \tTask \tPriority");
                while (tempwaitingprocess != null) {
                    String task = null;
                    if (tempwaitingprocess.getData().getTask() == 1) {
                        task = "Insert  ";
                    } else if (tempwaitingprocess.getData().getTask() == 2) {
                        task = "Remove  ";
                    } else if (tempwaitingprocess.getData().getTask() == 3) {
                        task = "Sort    ";
                    } else if (tempwaitingprocess.getData().getTask() == 4) {
                        task = "Retrieve";
                    } else if (tempwaitingprocess.getData().getTask() == 5) {
                        task = "Total   ";
                    }
                    printwriter.println("\nPID: " +tempwaitingprocess.getData().getPID() + "\t\t\t" +"Task: " + task + "\t\t\t" +"Priority: " + tempwaitingprocess.getData().getPriority());
                    tempwaitingprocess = tempwaitingprocess.getNextNode();
                }

                System.out.println("\nQueue");
                ProcessWaitingList.display();

            }

            while (true) {
                if (ProcessList.Count == 0 && ProcessWaitingList.Count == 0) {
                    break;
                } else if (ProcessList.Count >= MAX_CONCURRENT_OPERATIONS) {
                    for (int i = 0; i < MAX_CONCURRENT_OPERATIONS; i++) {
                        RecycleId[i] = ProcessList.getFront().getData().getPID();
                        ProcessList.getFront().getData().start();
                    }
                } else if (ProcessList.Count < MAX_CONCURRENT_OPERATIONS) {
                    int i = 0;
                    while (ProcessList.Count != 0) {
                        RecycleId[i] = ProcessList.getFront().getData().getPID();
                        ProcessList.getFront().getData().start();
                        i++;
                    }
                }
                if (ProcessWaitingList.getCount() != 0) {
                    if (ProcessWaitingList.getCount() >= MAX_CONCURRENT_OPERATIONS) {
                        for (int i = 0; i < MAX_CONCURRENT_OPERATIONS; i++) {
                            ProcessList.enqueue(ProcessWaitingList.dequeue());
                            ProcessList.getBack().getData().setPID(RecycleId[i]);
                        }
                    } else if (ProcessWaitingList.getCount() < MAX_CONCURRENT_OPERATIONS) {
                        int i = 0;
                        while (ProcessWaitingList.getCount() != 0) {
                            ProcessList.enqueue(ProcessWaitingList.dequeue());
                            ProcessList.getBack().getData().setPID(RecycleId[i]);
                            i++;
                        }
                    }
                }
            }

            printwriter.println("\nAfter Processes Details");

            temp = SharedList.getHead();
            slPrintWriter.println("KEY\t\tVALUE");
            while (temp != null) {
                slPrintWriter.println(temp.getKey() + "\t\t" + temp.getData().getValue());
                temp = temp.getNextNode();
            }
            slPrintWriter.println("The After Data - Total items: "  + SharedList.getCount() + "\n");

            printwriter.print("\nPID \tTask \t\t\tPriority \t\tCreate Time \t\t\t\t\tStart Time \t\t\t\t\tEnd Time\t\t\t Attempts\tSleep Time \n");

            System.out.print("\nPID \tTask \t\t  Priority \t\tCreate Time \t\t\t\t\tStart Time \t\t\t\t\tEnd Time\t\t\t Attempts\tSleep Time \n");
            for (int i = 0; i < Nprocess; i++) {
                String task = null;
                if (process[i].getTask() == 1) {
                    task = "Insert  ";
                } else if (process[i].getTask() == 2) {
                    task = "Remove  ";
                } else if (process[i].getTask() == 3) {
                    task = "Sort    ";
                } else if (process[i].getTask() == 4) {
                    task = "Retrieve";
                } else if (process[i].getTask() == 5) {
                    task = "Total   ";
                }
                process[i].displayList();

                printwriter.println("\nPID:" + process[i].getPID() + "\t" +"Task:" + task + "\t " + "Process:"+ process[i].getPriority() + "\t "+"Created:"  + dtf.format(process[i].getCreateTime()) + "\t\t\t\t"+"Start:"  + dtf.format(process[i].getStartTime()) + "\t\t\t\t " +"End:" + dtf.format(process[i].getEndTime()) + "\t\t\t "+"Attempts:"  + process[i].getAttempts() + "\t\t "+"Sleep:"  + process[i].getSleepTime() + "\n");
            }
            printwriter.close();
            slPrintWriter.close();


        } catch (InputMismatchException ime) {
            System.out.println("Invalid Input. Please enter a number");
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error");
            e.printStackTrace();
        }
    }
}





