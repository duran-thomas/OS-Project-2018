package Source;
import java.time.LocalTime;

import Queue.Queue;

public class GetAttributes {
	
	

 

	public int GetProcessID(Queue q) {
		return	q.getRear().getData().getProcessID()+1;
	}

	public String getTask() {
	String taskArray[]= {"Adding a new record to the list", "Removing a record from the list", "Sort the records in ascending order",
						 "Retrieve a record from the list", " Calculate the total of all integer elements in the list"}; 
 
		
		return taskArray[(int)(Math.random() * ((5 - 0) + 1)) + 0];
	}

	public String TimeNow() {
		
		String hourPeriod;
		
		if(LocalTime.now().getHour()<12) 
			hourPeriod="am";
		else
			hourPeriod="pm";
		
		return  String.valueOf(LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+hourPeriod);
		
	}

	public int SleepTime() {
		 
		return (int)(Math.random() * ((5 - 0) + 1)) + 0;
	}

	
	
	
 

 
	
}
