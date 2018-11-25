package Queue;

import java.util.ArrayList;

import Algorithm.Algorithm;
import Algorithm.FCFS;
import GUI.MainActivity;

public class Queue  {//extends MainActivity{

	private Node front;
	private Node rear;

	public Queue() {
		this.front=null;
		this.rear=null;
	}

	public Queue(Queue queue) {
		this.front=queue.front;
		this.rear=queue.rear;
	}

	public Node getFront() {
		return front;
	}

	public void setFront(Node front) {
		this.front = front;
	}

	public Node getRear() {
		return rear;
	}

	public void setRear(Node rear) {
		this.rear = rear;
	}

	public boolean isEmpty() { 
		return front==null;
	}




	public void Enqueue(Process data) {
		Node temp = new Node(data); 

		if (temp!=null) {

			if(isEmpty()) {
				front = temp; 
				rear = temp;
			}else {
				rear.setNextNode(temp);
				temp.setPrevNode(rear); 
				rear = temp;

			}
		}
	}


	public Node Dequeue() {
		Node temp=front;

		if (!isEmpty()) { 
			front=front.getNextNode();
		}

		return temp;

	}

	public void Display() {
		Node temp = front; 

		while(temp.getNextNode()!=null ){
			System.out.println("Process Id   : "+temp.getData().getProcessID());
			System.out.println("Task	     : "+temp.getData().getTask());
			System.out.println("Base Address : "+temp.getData().getBaseAddress());
			System.out.println("Service Time : "+temp.getData().getServiceTime());		
			System.out.println("Start Time   : "+temp.getData().getStartTime());
			System.out.println("End Time     : "+temp.getData().getEndTime());
			System.out.println("Attemps      : "+temp.getData().getAttempts());
			System.out.println("Sleep Time   : "+temp.getData().getSleepTime()); 
			System.out.println("Finish	     : "+temp.getData().isFinish()); 
			System.out.println("----------------------------------------------"); 
			temp= temp.getNextNode();

		}	
		System.out.println("Process Id   : "+temp.getData().getProcessID());
		System.out.println("Task	     : "+temp.getData().getTask());
		System.out.println("Base Address : "+temp.getData().getBaseAddress());
		System.out.println("Service Time : "+temp.getData().getServiceTime());
		System.out.println("Start Time   : "+temp.getData().getStartTime());
		System.out.println("End Time     : "+temp.getData().getEndTime());
		System.out.println("Attemps      : "+temp.getData().getAttempts());
		System.out.println("Sleep Time   : "+temp.getData().getSleepTime());
		System.out.println("Finish	     : "+temp.getData().isFinish());  
		System.out.println("***************************************************************************"); 

	}

public int getCount() {
	int count=0;
	Node temp=this.getFront();
	
	while(temp!=null ){ 

		count++;

		temp= temp.getNextNode();

	}	

	return count;
}

	public String getCurrentProcess(String id, Queue runningq) {
		// TODO Auto-generated method stub
		Node temp=runningq.getFront();
		String Idfound="";

		while(temp.getNextNode()!=null ){
			if (temp.getData().getProcessID()== Integer.parseInt(id)) {

				//			System.out.println("Found");
				return Idfound; 

			}
			temp= temp.getNextNode();

		}	



		return null;
	}







}
