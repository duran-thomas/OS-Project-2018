package Source;

import javax.swing.JOptionPane;

public class Validate{
	
	public boolean Validation( String process, String quantum) {
 
		 if(process.isEmpty() || process.length()==0){
			 	required("Process Amount");
			 	
				return false;
			}
		 if(quantum.isEmpty() || quantum.length()==0){
			 	required("Quantum Amount");
				return false;
			}
 
		return true;
	}
	
	private void required(String input) {
		JOptionPane.showMessageDialog(null, input+" is Required\n","Error Occured", JOptionPane.ERROR_MESSAGE);
		
	}

}
