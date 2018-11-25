package Source;

import java.util.Date;

import GUI.MainActivity;

public class CpuThreads  {
	
	/*
	 * 

	public void runProcesses() {

		Thread autorun1=new Thread() {
			 
			public void run() {
				
				while(true) {
				
				try { 
						int cpuValue = (int)(Math.random() * ((100 - 0) + 1)) + 0;
						 
						progressBar.setValue(cpuValue);  
						cpu_label.setText( String.valueOf(cpuValue));
						cpu_label.setText(String.valueOf(cpuValue)+"%");
						
						int memoryValue = (int)(Math.random() * ((200 - 0) + 1)) + 0;
						 
						int percent= memoryValue*100/200;
						
						memoryAmount.setText(memoryValue+"/200 MB ("+percent+"%)");
						memoryBar.setValue(percent);
						
						processes_lbl.setText(String.valueOf(table.getRowCount()));
						 
						Thread.sleep(1500);
				
					
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		};

	autorun1.start();



		Thread autorun2=new Thread() {
		 
		@SuppressWarnings("deprecation")
		public void run() {
			
			
			try {
				while(true) {
					Date today = new Date();
				
					uptime_lbl.setText( today.getHours()+":"  +today.getMinutes()+":" +today.getSeconds());
		
					Thread.sleep(1000);
			}
				
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		};

	autorun2.start();



		
	}


	 */
}
