package DoublyLinkedList;
import Queue.Process; 

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
  


public class DoublyLinkList {
	private LinkNode head; 

	
	public void systemCall(int code,Process process, String algorithm  ) {

		if(code==1)
			this.Insert(process, algorithm);
		else if(code==2)
			this.RemoveRandomElement(process, algorithm);
		else if(code==3)
			this.bubbleSort(process, algorithm);
		else if(code==4)
			this.RetrieveRandom(process, algorithm);
		else if(code==5)
			this.getTotal(process, algorithm);
		
		
	}
	public void Initialize() {
		
		this.head= new LinkNode();  
		for(int i=0; i<9; i++) {
			LinkNode temp= new LinkNode();

			temp.getData().setKey(this.getHead().getData().getKey()+1);

			temp.setNext(this.getHead()); 
			temp.setPrev(null);  

			if (head != null) 
				this.getHead().setPrev(temp);   

			this.setHead(temp); 
		}
	}



	public void Insert(Process process, String algorithm ) {
		LinkNode temp= new LinkNode();
 
		temp.getData().setKey(this.getHead().getData().getKey()+1);
 
		temp.setNext(this.getHead()); 
		temp.setPrev(null);  

		if (head != null) 
			this.getHead().setPrev(temp);   

		this.setHead(temp); 
		
		this.addText(process.getProcessID()+"	Added a New Record +	"+head.getData().getKey()+"        "+head.getData().getValue()+
				  "		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);

	}


	public void Display() {
		if(!isEmpty()) {
			LinkNode temp=head;

			while(temp!=null) {
				System.out.println("\nKey   : "+ temp.getData().getKey());
				System.out.println("Value : "+ temp.getData().getValue());
				System.out.println("----------------------");
				temp=temp.getNext();

			}

		}
	}


	public int getTotal(Process process, String algorithm ) {
		int total=0;
		LinkNode temp=this.getHead();
		if(!isEmpty()) {
			

			while (temp!= null) 
			{ 
				total+=temp.getData().getValue(); 
				temp = temp.getNext(); 
			} 

		}
	
		this.addText(process.getProcessID()+"	Calculated the Total	"+"Sum:"+"     "+total+
				  "		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);
		return total;
	}

	public int getCount() {
		int count=0;
		if(!isEmpty()) {
			LinkNode temp=this.getHead();

			while (temp!= null) 
			{ 
				count++; 
				temp = temp.getNext(); 
			} 
			return count; 

		}
		return count;
	}

	public void bubbleSort(Process process, String algorithm )
	{
		 boolean swap = true;
        LinkNode temp = head;
        LinkNode temp2 = new LinkNode();
        //Check if there swap occured in the sort
        while (swap) {
            swap = false;
            temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getValue() > temp.getNext().getData().getValue()) {
                    temp2.setData(temp.getData());
                    temp.setData(temp.getNext().getData());
                    temp.getNext().setData(temp2.getData());
                    swap = true;
                }
                temp = temp.getNext();
            }
        } 
    

		this.addText(process.getProcessID()+"	Sorted The Records \t\t"+
				"		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);
		this.AddEntireListToTheFile();

	}


	private void AddEntireListToTheFile() {
		if(!isEmpty()) {
			LinkNode temp=head;

			while(temp!=null) {
				this.addText("\t\t\t\tKey:"+temp.getData().getKey()+"\tValue:"+temp.getData().getValue());
				temp=temp.getNext();

			}

		}	
	}
		public void RemoveRandomElement(Process process, String algorithm ) {
 
		if(!isEmpty()) {
			int key=head.getData().getKey();
			int value=head.getData().getValue();
			head=head.getNext();
			
			this.addText(process.getProcessID()+"	Removed a Record   -	"+key+"        "+value +
					  "		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);
	
		}
		 
	}




	public void RetrieveRandom( Process process, String algorithm ) {
		int search=(int)(Math.random() * ((this.getCount()-1 - 1) + 1)) + 1;

		if(!isEmpty()) {
			LinkNode temp=this.getHead();
			
			while (temp!= null) 
			{ 
				if(temp.getData().getKey()==search) {
					
					this.addText(process.getProcessID()+"	Retrieved a Record	"+search+"        "+temp.getData().getValue()+
							  "		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);
					break;
				}
				temp = temp.getNext(); 
			} 
			
			
		} 
 
	}

	public boolean isEmpty() { 
		return head==null;
	}

	public void RemoveValue( Process process , String algorithm) {
		if(!isEmpty()) {
			this.addText(process.getProcessID()+"	Removed a Record	"+head.getData().getKey()+"        "+head.getData().getValue()+
					  "		"+process.getAttempts()+"	    "+process.getStartTime()+"	       \t"+process.getEndTime()+"		 "+process.getSleepTime()+"\t   "+algorithm);
 
			head=head.getNext();
			head.setPrev(null);
 
		}
	}

	public void addText(String execution) {

		final File FileName = new File("Scheduling_Reports/report.txt");

		try{
			FileWriter fw = new FileWriter(FileName,true);

			PrintWriter out = new PrintWriter(new BufferedWriter(fw));
			out.println(execution);
			out.flush(); 
			out.close();

			fw.close();
			//	System.out.println(execution);	

		}catch(IOException ioex){
//			JOptionPane.showMessageDialog(null,"Sorry File Does Not Exist Please Try Again","Message", JOptionPane.ERROR_MESSAGE);


		}

	}

	public void RenameFile() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");


		File oldFIle = new File("Scheduling_Reports/report.txt"); 
		try {

			oldFIle.renameTo(new File("Scheduling_Reports/report"+sdf.format(cal.getTime())+".txt"));

		}catch(Exception e){
			e.printStackTrace();
		}


	}
	
	public boolean fileExists() {
		File file = new File("Scheduling_Reports/report.txt"); 
		
		return file.exists();
	}


	public void CreateFile() {

		this.Initialize();
		RenameFile();  

		File fileName = null;
		String path="Scheduling_Reports/report.txt"; 
		try {
			File dir= new File("Scheduling_Reports");
			fileName= new File(path);
			
			setDefaultStart(fileName);

			dir.mkdir();
			fileName.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

	public void setDefaultStart(File FileName) {//Set Report's Header
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
		String state="";

		if(cal.getTime().getHours()>=12)
			state="pm";
		else
			state="am";


		try{
			FileWriter fw = new FileWriter(FileName,true);
			PrintWriter out = new PrintWriter(new BufferedWriter(fw));
			out.println("OPERATING SYSTEM (CIT3002)");	
			out.println("Simulation Date       : "+dft.format(cal.getTime()));	
			out.println("Simulation Start Time : "+sdf.format(cal.getTime())+state);
			out.println("");
			out.println("\t\tInteger Elements :"); 
			out.flush();
			out.close();
			fw.close();
			
			AddEntireListToTheFile();
			this.addText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			this.addText("PID		TASK		KEY      VALUE	     ATTEMPTS    START TIME	END TIME    SLEEP TIME	   Algorithm");
			this.addText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					
	 
		}catch(IOException ioex){
//			JOptionPane.showMessageDialog(null,"Sorry File Does Not Exist Please Try Again","Message", JOptionPane.ERROR_MESSAGE);


		}

	}



	public LinkNode getHead() {
		return head;
	}

	public void setHead(LinkNode head) {
		this.head = head;
	}



}
 