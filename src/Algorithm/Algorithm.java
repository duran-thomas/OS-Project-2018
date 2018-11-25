package Algorithm;
import Queue.Queue;
import Queue.Process;

public class Algorithm {
	//protected Process process;
	protected Queue readyQueue = new Queue();  
	protected boolean busy;
	protected Queue batchList ;// long term Scheduler 
	protected boolean finish;
	
	
	public Algorithm()
    {
        this.readyQueue = new Queue();  
  //      this.process = new Process(7);   
        this.busy = false;   
    //    this.batchList= workQueue ; 
        
    }


	public Queue getBatchList() {
		return batchList;
	}


	public void setBatchList(Queue batchList) {
		this.batchList = batchList;
	}
	
	
	public void excecuteTask( Queue q){
		q.getFront().getData().setAttempts(q.getFront().getData().getAttempts()+1);
		//q.getFront().getData().setFinish(false);
		//q.getFront().getData().setEndTime(endTime);
		
		
	}
	
}
