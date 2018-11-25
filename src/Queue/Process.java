package Queue;


import java.time.LocalTime; 

public class Process {
	
	private int processID;
	private String task;
	private int baseAddress;
	private int serviceTime;
	private String startTime;
	private String endTime;
	private int attempts;
	private int sleepTime;
	private int priority;
	private int burst=0;



	private boolean finish; 
	
	

	String taskArray[]= {"Adding a new record to the list", 
						 "Removing a record from the list", 
						 "Sort the records in ascending order",
						 "Retrieve a record from the list",
						 "Calculate the total of all integer"}; 
 
		
		
public Process() {
 	 	
			this.processID = 1; 
			this.task = taskArray[(int)(Math.random() * ((4 - 0) + 1)) + 0];
			this.baseAddress = 0;
			this.serviceTime=(int)(Math.random() * ((5 - 2) + 1)) + 2;
			this.startTime = "Created";
			this.endTime = "Incomplete";
			this.attempts = 0;
			this.sleepTime = (int)(Math.random() * ((5 - 0) + 1)) + 0;
			this.burst=serviceTime;
			this.finish=false; 
			this.setPriority(this.task);
	}
	
/*
	private String selectTask(int id) {
		
		if(id/2== (int)(Math.random() * ((2 - 1) + 1)) + 1) {
			for(int i=0; i<3;i++) {
				return  taskArray[(int)(Math.random() * ((2 - 0) + 1)) + 0];
						
				}
		}
		
		
	return taskArray[(int)(Math.random() * ((4 - 0) + 1)) + 0];
}
*/

	public int getPriority() {
	return priority;
}

public void setPriority(String task) {
	if(task.equals("Adding a new record to the list"))
		this.priority= 1;
	else if(task.equals("Removing a record from the list"))
		this.priority= 1;
	else if(task.equals("Sort the records in ascending order"))
		this.priority= 1;
	else {
		this.priority= 0;
	}
	
}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(int baseAddress) {
		this.baseAddress = baseAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}


public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	

public String TimeNow() {
		
		String hourPeriod;
		
		if(LocalTime.now().getHour()<12) {
			hourPeriod="am";
			
		}else {
			hourPeriod="pm";
		}
		
		if(LocalTime.now().getMinute()<10) {
			return  String.valueOf(LocalTime.now().getHour()+":0"+LocalTime.now().getMinute()+hourPeriod);
		}
		
		return  String.valueOf(LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+hourPeriod);
		
	}
 


public void Display() {  
		System.out.println(this.getProcessID());
		System.out.println(this.getTask());
		System.out.println(this.getBaseAddress());
		System.out.println(this.getStartTime());
		System.out.println(this.getEndTime());
		System.out.println(this.getAttempts());
		System.out.println(this.getSleepTime()); 
		System.out.println("----------------------------------------------"); 
	 
		
	}
	//temp=temp.getNextNode();
}
	

