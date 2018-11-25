package GUI; 

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JButton;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font; 
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.StartDocument;

import Algorithm.Algorithm;
import DoublyLinkedList.DoublyLinkList;
import Queue.Queue;  
import Queue.Node;
import Queue.Process; 
import Source.Validate;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator; 
import java.awt.Window.Type; 
import java.awt.Choice; 
import java.awt.event.ItemListener;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.Random;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder; 

public class MainActivity {

	private JFrame frame;
	private JLabel untilization_lbl;
	private JPanel cpu_panel;
	private JPanel process_panel;
	private DefaultTableModel processdtm; 
	private JTable table;
	private JLabel processes_lbl;
	private JLabel cpu_label;
	private JProgressBar cpuBar;
	private JLabel memoryAmount;
	private JProgressBar memoryBar;
	private JLabel uptime_lbl;
	int hour=0;
	int minute=0;
	int second=0;
	int idle=0;
	int util=0;
	int active=0;
	String time;
	Thread cpuCore1;
	Thread cpuCore2;
	Thread cpuCore3;
	Thread cpuCore4;
	Thread cpuCore5;
	int clocktime=0;
	int processCompleted=0;
	boolean Core1isRunning=false;
	boolean Core2isRunning=false;
	boolean Core3isRunning=false;
	boolean Core4isRunning=false;
	boolean Core5isRunning=false;

	private Thread RR;
	private Thread FCFS;
	private Thread setReadyQueue; 
	private JTextField ready3;
	private JTextField ready2;
	private JTextField ready1;
	private JTextField ready4;
	private JTextField ready5;
	private Choice processes;
	private Choice speed; 
	private int simulatorSpeed=1000;
	private JButton btnStart;
	private JButton btnReset;
	private JButton btnStop;
	private JButton btnViewAll;
	private JButton btnContinue;
	private JLabel currentProcess1;
	private JButton btnReport;
	private JLabel currentProcess2;
	private JLabel currentProcess3;
	private JLabel currentProcess4;
	private JLabel currentProcess5;
	private Choice quantum;
	private DoublyLinkList list;
	//	Queue runningQ = null;
	Queue workingQueue=null;
	private JLabel inUseValue;
	private JLabel available;
	private JLabel averageWaitingTime;
	private JLabel averageTurnArroundTime;
	private JLabel speed_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity window = new MainActivity();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainActivity() {
		initialize();  
		runCpuThread();
	}


	//private void setValues() {}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); 
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 1001, 870);
		frame.setLocationRelativeTo(null); 
		frame.setTitle("Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		process_panel = new JPanel();
		process_panel.setBounds(12, 0, 972, 822);
		frame.getContentPane().add(process_panel);
		process_panel.setBackground(Color.WHITE);
		process_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		scrollPane.setBounds(12, 194, 948, 318);
		process_panel.add(scrollPane);


		Object[] title = {"PID", "Task", "Base Address","Start Time", "End Time", "Attemps", "Sleep Time","Service Time", "Algorithm"};
		processdtm = new DefaultTableModel(); 
		processdtm.setColumnIdentifiers(title); 	

		table = new JTable(processdtm);
		table.setBackground(new Color(255, 255, 255));
		table.setShowGrid(true);
		table.setShowVerticalLines(true);
		table.setShowGrid(true);
		table.setGridColor(new Color(0, 191, 255));
		table.setShowVerticalLines(true); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		table.getTableHeader().setFont(new Font("Cambria Math", Font.BOLD, 12));

		scrollPane.setViewportView(table);

		cpu_panel = new JPanel();
		cpu_panel.setBounds(12, 516, 948, 293);
		process_panel.add(cpu_panel);
		cpu_panel.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		cpu_panel.setLayout(null);

		JLabel lblCpu_1 = new JLabel("CPU");
		lblCpu_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 50));
		lblCpu_1.setBounds(413, 9, 177, 58);
		cpu_panel.add(lblCpu_1);

		JLabel cpu_lbl = new JLabel("Intel\u00AE Dual-Core\u2122 i5-8269U Processor");
		cpu_lbl.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		cpu_lbl.setBounds(602, 29, 314, 30);
		cpu_panel.add(cpu_lbl);

		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblSpeed.setBounds(582, 244, 87, 16);
		cpu_panel.add(lblSpeed);

		JLabel lblUtilization = new JLabel("Utilization");
		lblUtilization.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblUtilization.setBounds(582, 184, 87, 16);
		cpu_panel.add(lblUtilization);

		JLabel lblUptime = new JLabel("Uptime");
		lblUptime.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblUptime.setBounds(448, 246, 87, 16);
		cpu_panel.add(lblUptime);

		JLabel lblCores = new JLabel("Cores:");
		lblCores.setForeground(new Color(0, 0, 51));
		lblCores.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblCores.setBounds(758, 184, 158, 16);
		cpu_panel.add(lblCores);

		JLabel lblLogicalProcessors = new JLabel("Logical Processors:");
		lblLogicalProcessors.setForeground(new Color(0, 0, 51));
		lblLogicalProcessors.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblLogicalProcessors.setBounds(758, 264, 158, 16);
		cpu_panel.add(lblLogicalProcessors);

		JLabel lblProcessors = new JLabel("Processes");
		lblProcessors.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblProcessors.setBounds(448, 184, 87, 16);
		cpu_panel.add(lblProcessors);

		JLabel lblSockets = new JLabel("Socket(s):");
		lblSockets.setForeground(new Color(0, 0, 51));
		lblSockets.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblSockets.setBounds(758, 224, 158, 16);
		cpu_panel.add(lblSockets);

		JLabel lblCpus = new JLabel("CPU(s):");
		lblCpus.setForeground(new Color(0, 0, 51));
		lblCpus.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblCpus.setBounds(757, 164, 159, 16);
		cpu_panel.add(lblCpus);

		JLabel lblThreadsPerCore = new JLabel("Thread(s) per core:");
		lblThreadsPerCore.setForeground(new Color(0, 0, 51));
		lblThreadsPerCore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblThreadsPerCore.setBounds(757, 204, 159, 16);
		cpu_panel.add(lblThreadsPerCore);

		JLabel lblCoresPerSocket = new JLabel("CPU(s) per socket:");
		lblCoresPerSocket.setForeground(new Color(0, 0, 51));
		lblCoresPerSocket.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblCoresPerSocket.setBounds(758, 244, 158, 16);
		cpu_panel.add(lblCoresPerSocket);

		untilization_lbl = new JLabel("");
		untilization_lbl.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		untilization_lbl.setForeground(Color.DARK_GRAY);
		untilization_lbl.setBounds(582, 203, 151, 16);
		cpu_panel.add(untilization_lbl);

		processes_lbl = new JLabel("0");
		processes_lbl.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		processes_lbl.setForeground(Color.DARK_GRAY);
		processes_lbl.setBounds(448, 203, 65, 16);
		cpu_panel.add(processes_lbl);

		speed_lbl = new JLabel("");
		speed_lbl.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		speed_lbl.setForeground(Color.DARK_GRAY);
		speed_lbl.setBounds(582, 262, 151, 16);
		cpu_panel.add(speed_lbl);

		uptime_lbl = new JLabel("");
		uptime_lbl.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		uptime_lbl.setForeground(Color.DARK_GRAY);
		uptime_lbl.setBounds(448, 264, 128, 16);
		cpu_panel.add(uptime_lbl);

		JLabel label_2 = new JLabel("2");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_2.setBounds(913, 164, 27, 16);
		cpu_panel.add(label_2);

		JLabel label_3 = new JLabel("4");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_3.setBounds(913, 184, 27, 16);
		cpu_panel.add(label_3);

		JLabel label_4 = new JLabel("2");
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_4.setBounds(913, 244, 27, 16);
		cpu_panel.add(label_4);

		JLabel label_5 = new JLabel("8");
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_5.setBounds(913, 264, 27, 16);
		cpu_panel.add(label_5);

		JLabel label_6 = new JLabel("1");
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_6.setBounds(913, 204, 27, 16);
		cpu_panel.add(label_6);

		JLabel label_7 = new JLabel("1");
		label_7.setForeground(Color.DARK_GRAY);
		label_7.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		label_7.setBounds(913, 224, 27, 16);
		cpu_panel.add(label_7);
		cpuBar = new JProgressBar();
		cpuBar.setBorder(new CompoundBorder(new LineBorder(new Color(112, 128, 144), 2, true), new LineBorder(new Color(255, 255, 255), 2, true)));
		cpuBar.setForeground(new Color(0, 128, 128));
		cpuBar.setFocusable(false);
		cpuBar.setBounds(507, 90, 129, 48);
		cpu_panel.add(cpuBar);
		cpuBar.setBackground(new Color(245, 245, 245));

		JLabel lblCpu = new JLabel("CPU");
		lblCpu.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblCpu.setBounds(448, 95, 56, 16);
		cpu_panel.add(lblCpu);


		cpu_label = new JLabel("");
		cpu_label.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		cpu_label.setBounds(517, 143, 111, 16);
		cpu_panel.add(cpu_label);

		JLabel lblUsage = new JLabel("Usage");
		lblUsage.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblUsage.setBounds(444, 109, 56, 16);
		cpu_panel.add(lblUsage);

		JLabel lblMemory_1 = new JLabel("Memory");
		lblMemory_1.setBounds(12, 3, 193, 70);
		cpu_panel.add(lblMemory_1);
		lblMemory_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 50));

		JLabel lblNewLabel = new JLabel("0.2 GB / 200 MB");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(230, 37, 129, 16);
		cpu_panel.add(lblNewLabel);

		JLabel lblInUse = new JLabel("In use");
		lblInUse.setForeground(new Color(0, 0, 51));
		lblInUse.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblInUse.setBounds(45, 183, 56, 16);
		cpu_panel.add(lblInUse);

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setForeground(new Color(0, 0, 51));
		lblAvailable.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblAvailable.setBounds(45, 226, 65, 16);
		cpu_panel.add(lblAvailable);

		JLabel lblSpeed_1 = new JLabel("Speed:  ");
		lblSpeed_1.setForeground(new Color(0, 0, 51));
		lblSpeed_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblSpeed_1.setBounds(195, 186, 68, 16);
		cpu_panel.add(lblSpeed_1);

		JLabel lblSlotsUsed = new JLabel("Slots used:");
		lblSlotsUsed.setForeground(new Color(0, 0, 51));
		lblSlotsUsed.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblSlotsUsed.setBounds(195, 226, 92, 16);
		cpu_panel.add(lblSlotsUsed);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setForeground(new Color(119, 136, 153));
		separator.setBackground(new Color(112, 128, 144));
		separator.setBounds(383, 3, 2, 293);
		cpu_panel.add(separator);

		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblMemory.setFocusable(false);
		lblMemory.setBounds(45, 96, 68, 16);
		cpu_panel.add(lblMemory);

		JLabel label_1 = new JLabel("Usage");
		label_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(52, 111, 68, 16);
		cpu_panel.add(label_1);

		memoryAmount = new JLabel("");
		memoryAmount.setFont(new Font("Cambria Math", Font.BOLD, 13));
		memoryAmount.setForeground(Color.DARK_GRAY);
		memoryAmount.setBounds(114, 143, 158, 16);
		cpu_panel.add(memoryAmount);

		memoryBar = new JProgressBar();
		memoryBar.setBorder(new CompoundBorder(new LineBorder(new Color(112, 128, 144), 2, true), new LineBorder(new Color(255, 255, 255), 2, true)));
		memoryBar.setForeground(new Color(0, 128, 128));
		memoryBar.setFocusable(false);
		memoryBar.setBounds(114, 90, 129, 48);
		cpu_panel.add(memoryBar);
		memoryBar.setBackground(new Color(245, 245, 245));

		inUseValue = new JLabel("");
		inUseValue.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		inUseValue.setForeground(Color.DARK_GRAY);
		inUseValue.setBounds(45, 203, 137, 16);
		cpu_panel.add(inUseValue);

		available = new JLabel("");
		available.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		available.setForeground(Color.DARK_GRAY);
		available.setBounds(45, 247, 137, 16);
		cpu_panel.add(available);

		JLabel lblOf = new JLabel("1 of 1");
		lblOf.setForeground(Color.DARK_GRAY);
		lblOf.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		lblOf.setBounds(283, 226, 56, 16);
		cpu_panel.add(lblOf);

		JLabel lblMhz = new JLabel("534.2 MHz");
		lblMhz.setBackground(new Color(245, 245, 245));
		lblMhz.setForeground(Color.DARK_GRAY);
		lblMhz.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		lblMhz.setBounds(286, 188, 87, 16);
		cpu_panel.add(lblMhz);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		panel.setBounds(12, 5, 948, 185);
		process_panel.add(panel);
		panel.setLayout(null);




		processes = new Choice();
		processes.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		processes.setBounds(103, 42, 60, 22);
		processes.setFocusable(false);
		processes.add("");
		for(int i=10; i<=30; i++) 
			processes.add(String.valueOf(i));
		panel.add(processes);

		JLabel lblProcesses = new JLabel("Processes");
		lblProcesses.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblProcesses.setBounds(23, 48, 88, 16);
		panel.add(lblProcesses);

		JLabel lblAlgorithm = new JLabel(""
				+ "");
		lblAlgorithm.setBounds(34, 14, 66, 16);
		panel.add(lblAlgorithm);

		btnStart = new JButton("Start");
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnStart.setBackground(new Color(169, 169, 169));
		btnStart.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnStart.setFocusable(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				idle=0;
				
			
				if(new Validate().Validation(processes.getSelectedItem(), quantum.getSelectedItem())) {
					btnStart.setEnabled(false);
					 btnStop.setEnabled(true); 

					//Default
					list = new DoublyLinkList();
					list.CreateFile();				
					//Default
					
					StartSimulation();
				}
			}
		});
		btnStart.setBounds(23, 113, 91, 25);
		panel.add(btnStart);

		btnContinue = new JButton("Continue");
		btnContinue.setForeground(new Color(0, 0, 0));
		btnContinue.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnContinue.setBackground(new Color(169, 169, 169));
		btnContinue.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnContinue.setFocusable(false);
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReset.setEnabled(false);
				setReadyQueue.resume();
				FCFS.resume();
				RR.resume();
				cpuCore1.resume();
				cpuCore2.resume();
				cpuCore3.resume();
				cpuCore4.resume();
				cpuCore5.resume();
			}
		});
		btnContinue.setBounds(126, 113, 102, 25);
		panel.add(btnContinue);

		btnStop = new JButton("Stop");
		btnStop.setForeground(new Color(0, 0, 0));
		btnStop.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnStop.setBackground(new Color(169, 169, 169));
		btnStop.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnStop.setFocusable(false);
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(RR.isAlive())   RR.suspend();
				if(FCFS.isAlive())	FCFS.suspend();
				if(setReadyQueue.isAlive())	setReadyQueue.suspend();

				if(!currentProcess1.getText().isEmpty())	cpuCore1.suspend();
				if(!currentProcess2.getText().isEmpty())	cpuCore2.suspend();
				if(!currentProcess3.getText().isEmpty())	cpuCore3.suspend();
				if(!currentProcess4.getText().isEmpty())	cpuCore4.suspend();
				if(!currentProcess5.getText().isEmpty())	cpuCore5.suspend();

				btnContinue.setEnabled(true);
				btnReset.setEnabled(true);
				
				if (table.getRowCount()==0) {
					btnStop.setEnabled(false);
					btnReset.setEnabled(false);
				}
			}
		});
		btnStop.setBounds(23, 146, 91, 25);
		panel.add(btnStop);

		btnViewAll = new JButton("View All");
		btnViewAll.setForeground(new Color(0, 0, 0));
		btnViewAll.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnViewAll.setBackground(new Color(169, 169, 169));
		btnViewAll.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Thread.sleep(300);
					if(new DoublyLinkList().fileExists())
						Desktop.getDesktop().open(new java.io.File("Scheduling_Reports"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewAll.setFocusable(false);
		btnViewAll.setBounds(240, 146, 97, 25);
		panel.add(btnViewAll);

		btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(0, 0, 0));
		btnReset.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnReset.setBackground(new Color(169, 169, 169));
		btnReset.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnReset.setFocusable(false);
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(cpuCore1.isAlive())  cpuCore1.suspend();
				if(cpuCore2.isAlive())  cpuCore2.suspend();
				if(cpuCore3.isAlive())	cpuCore3.suspend();
				if(cpuCore4.isAlive())	cpuCore4.suspend();
				if(cpuCore5.isAlive())	cpuCore5.suspend();

				clocktime=0;

				RR.suspend();
				FCFS.suspend();

				setReadyQueue.stop();
				btnContinue.setEnabled(false);
				//	ProcessThread.stop();
				int rows = table.getRowCount();
				for(int i=0;i<rows;i++)
					processdtm.removeRow(table.getRowCount()-1); 

				ready1.setText("");
				ready2.setText("");
				ready3.setText("");
				ready4.setText("");
				ready5.setText("");
				currentProcess1.setText("");
				currentProcess2.setText("");
				currentProcess3.setText("");
				currentProcess4.setText("");
				currentProcess5.setText("");
				setActiveProcessColor();

				btnStart.setEnabled(true);

				if (table.getRowCount()==0) {
					btnStop.setEnabled(false);
					btnReset.setEnabled(false);
				}
			}
		});
		btnReset.setBounds(126, 146, 102, 25);
		panel.add(btnReset);

		speed = new Choice();
		speed.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		speed.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String simSpeed= speed.getSelectedItem();
				if(simSpeed.equals("1")) {
					simulatorSpeed=500;
				}else if(simSpeed.equals("2")) {
					simulatorSpeed=1000;
				}else if(simSpeed.equals("3")) {
					simulatorSpeed=1500;
				}else if(simSpeed.equals("4")) {
					simulatorSpeed=2000;
				}else if(simSpeed.equals("5")) {
					simulatorSpeed=3000;
				}


			}
		});
		speed.setBounds(103, 78, 60, 22);
		speed.setFocusable(false); 
		panel.add(speed);

		JLabel lblSpeed_2 = new JLabel("Speed");
		lblSpeed_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblSpeed_2.setBounds(45, 84, 66, 16);
		panel.add(lblSpeed_2);
		for (int i=1; i<=5; i++) 
			speed.add(String.valueOf(i));

		JLabel lblNewLabel_1 = new JLabel("Ready Queue (PID)");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(402, 14, 140, 16);
		panel.add(lblNewLabel_1);

		ready5 = new JTextField();
		ready5.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 2, true)));
		ready5.setEditable(false);
		ready5.setFocusable(false);
		ready5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ready5.setForeground(Color.BLACK);
		ready5.setHorizontalAlignment(SwingConstants.CENTER);
		ready5.setColumns(10);
		ready5.setBounds(408, 41, 116, 22);
		panel.add(ready5);

		ready4 = new JTextField();
		ready4.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 2, true)));
		ready4.setEditable(false);
		ready4.setFocusable(false);
		ready4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ready4.setForeground(Color.BLACK);
		ready4.setHorizontalAlignment(SwingConstants.CENTER);
		ready4.setColumns(10);
		ready4.setBounds(408, 68, 116, 22);
		panel.add(ready4);

		ready3 = new JTextField();
		ready3.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 2, true)));
		ready3.setEditable(false);
		ready3.setFocusable(false);
		ready3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ready3.setForeground(Color.BLACK);
		ready3.setHorizontalAlignment(SwingConstants.CENTER);
		ready3.setBounds(408, 95, 116, 22);
		panel.add(ready3);
		ready3.setColumns(10);

		ready2 = new JTextField();
		ready2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 2, true)));
		ready2.setEditable(false);
		ready2.setFocusable(false);
		ready2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ready2.setForeground(Color.BLACK);
		ready2.setHorizontalAlignment(SwingConstants.CENTER);
		ready2.setColumns(10);
		ready2.setBounds(408, 122, 116, 22);
		panel.add(ready2);

		ready1 = new JTextField();
		ready1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 2, true)));
		ready1.setEditable(false);
		ready1.setFocusable(false);
		ready1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ready1.setForeground(Color.BLACK);
		ready1.setHorizontalAlignment(SwingConstants.CENTER);
		ready1.setColumns(10);
		ready1.setBounds(408, 149, 116, 22);
		panel.add(ready1);

		JLabel lblAverageWaitingTime = new JLabel("Average Waiting ");
		lblAverageWaitingTime.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblAverageWaitingTime.setBounds(575, 99, 140, 16);
		panel.add(lblAverageWaitingTime);

		JLabel lblTask = new JLabel("Processes Running");
		lblTask.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblTask.setBounds(642, 14, 157, 16);
		panel.add(lblTask);

		JLabel lblAverageTurnarroundTime = new JLabel("Average Turnaround ");
		lblAverageTurnarroundTime.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblAverageTurnarroundTime.setBounds(735, 98, 157, 16);
		panel.add(lblAverageTurnarroundTime);

		btnReport = new JButton("Report");
		btnReport.setForeground(new Color(0, 0, 0));
		btnReport.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		btnReport.setBackground(new Color(169, 169, 169));
		btnReport.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 15));
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(400);
					if(new DoublyLinkList().fileExists())
						Desktop.getDesktop().open(new java.io.File("Scheduling_Reports/report.txt"));
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnReport.setFocusable(false);
		btnReport.setBounds(240, 113, 97, 25);
		panel.add(btnReport);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(112, 128, 144), 2, true));
		panel_1.setBounds(557, 42, 358, 45);
		panel.add(panel_1);
		panel_1.setLayout(null);

		currentProcess1 = new JLabel("");
		currentProcess1.setHorizontalTextPosition(SwingConstants.CENTER);
		currentProcess1.setHorizontalAlignment(SwingConstants.CENTER);
		currentProcess1.setBounds(12, 10, 56, 25);
		panel_1.add(currentProcess1);

		currentProcess2 = new JLabel("");
		currentProcess2.setHorizontalTextPosition(SwingConstants.CENTER);
		currentProcess2.setHorizontalAlignment(SwingConstants.CENTER);
		currentProcess2.setBounds(81, 10, 56, 25);
		panel_1.add(currentProcess2);

		currentProcess3 = new JLabel("");
		currentProcess3.setHorizontalTextPosition(SwingConstants.CENTER);
		currentProcess3.setHorizontalAlignment(SwingConstants.CENTER);
		currentProcess3.setBounds(150, 10, 56, 25);
		panel_1.add(currentProcess3);

		currentProcess4 = new JLabel("");
		currentProcess4.setHorizontalTextPosition(SwingConstants.CENTER);
		currentProcess4.setHorizontalAlignment(SwingConstants.CENTER);
		currentProcess4.setBounds(219, 10, 56, 25);
		panel_1.add(currentProcess4);

		currentProcess5 = new JLabel("");
		currentProcess5.setHorizontalTextPosition(SwingConstants.CENTER);
		currentProcess5.setHorizontalAlignment(SwingConstants.CENTER);
		currentProcess5.setBounds(288, 10, 56, 25);
		panel_1.add(currentProcess5);

		quantum = new Choice();
		quantum.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 14));
		quantum.setBounds(260, 42, 56, 22);
		quantum.setFocusable(false);
		quantum.add("");
		for(int i=1; i<=3; i++)
			quantum.add(String.valueOf(i));
		panel.add(quantum);

		JLabel lblQuantum = new JLabel("Quantum");
		lblQuantum.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblQuantum.setBounds(186, 48, 79, 16);
		panel.add(lblQuantum);

		averageWaitingTime = new JLabel("0");
		averageWaitingTime.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 18));
		averageWaitingTime.setHorizontalTextPosition(SwingConstants.CENTER);
		averageWaitingTime.setHorizontalAlignment(SwingConstants.CENTER);
		averageWaitingTime.setBounds(585, 143, 86, 23);
		panel.add(averageWaitingTime);

		averageTurnArroundTime = new JLabel("0");
		averageTurnArroundTime.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 18));
		averageTurnArroundTime.setHorizontalTextPosition(SwingConstants.CENTER);
		averageTurnArroundTime.setHorizontalAlignment(SwingConstants.CENTER);
		averageTurnArroundTime.setBounds(751, 143, 86, 23);
		panel.add(averageTurnArroundTime);

		JLabel lblTime_1 = new JLabel("Time");
		lblTime_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblTime_1.setBounds(788, 116, 49, 16);
		panel.add(lblTime_1);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblTime.setBounds(615, 116, 56, 16);
		panel.add(lblTime);


	} 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void StartSimulation() {

		int processAmount=Integer.valueOf(processes.getSelectedItem());
		Queue batchQueue=new Queue();
		int[] IDs = new int[processAmount];

		for(int i=0; i<processAmount; i++) {

			IDs[i]=new Random().nextInt(600)+1;
		}

		for (int i = 0; i < IDs.length; i++) { 
			for (int j = i + 1 ; j < IDs.length; j++) { 
				if (IDs[i]== (IDs[j])) { 
					IDs[i]=(int)(Math.random() * ((998 - 601) + 1)) + 501;
				}
			}
		}

		for(int i=1; i<=processAmount; i++) {
			Process p =new Process();
			p.setProcessID(IDs[i-1]);
			p.setBaseAddress(i);

			batchQueue.Enqueue(p);
		}
		runProcessThread(batchQueue);
		addAllProcessesToTable(batchQueue);

	}

	public void runCpuThread() {


		Thread autorun1=new Thread() {

			public void run() {

				while(true) {

					try { 

						int memoryValue = active*40;
						int percent= memoryValue*100/200;
						double gigabyteInUse=memoryValue/1000.0;
						double gigabyteAvailable=(200-memoryValue)/1000.0;

						active= checkActive(1)+
								checkActive(2)+
								checkActive(3)+
								checkActive(4)+
								checkActive(5);

						memoryAmount.setText(memoryValue+"/200 MB ("+percent+"%)");
						memoryBar.setValue(percent);
						inUseValue.setText(gigabyteInUse+" GB"+" ("+String.valueOf(memoryValue)+" MB)");
						available.setText(gigabyteAvailable+" GB ("+String.valueOf(200-memoryValue)+" MB)");

						if(util>0) {
							cpu_label.setText(String.valueOf(util)+"%");
							cpuBar.setValue(util);
							untilization_lbl.setText(String.valueOf(util)+"%");

						}else {
							untilization_lbl.setText("0"+"%");
							cpu_label.setText("0"+"%");
						}

						speed_lbl.setText(String.valueOf(active)+"/sec " +active*13.5+"MHz");

						Thread.sleep(100);

					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		};

		autorun1.start();

		Thread autorun2=new Thread() {


			public void run() {

				try {
					while(true) {
						second++;
						if(second==60) {
							minute++;
							second=0;
						}
						if(minute==60 || second ==60) {
							hour++;
							minute=0;
						}
						if(hour<10)
							time=("0"+String.valueOf(hour));
						else 
							time=(":"+String.valueOf(hour));	
						if(minute<10)
							time+=(":0"+String.valueOf(minute) );
						else 
							time+=(":"+String.valueOf(minute));	
						if(second<10)
							time+=(":0"+String.valueOf(second));
						else 
							time+=(":"+String.valueOf(second));	
						uptime_lbl.setText(time);

						Thread.sleep(1000);
					}

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		};

		autorun2.start();
	} 


	public  void addAllProcessesToTable(Queue batchQueue) {
		String[] record = {""};
		Node temp = batchQueue.getFront(); 

		processdtm.getDataVector().removeAllElements(); //Delete All Processses

		while(temp.getNextNode()!=null ){
			record= new String[] { String.valueOf(temp.getData().getProcessID() ),
					String.valueOf(temp.getData().getTask()),
					String.valueOf(temp.getData().getBaseAddress()),
					String.valueOf(temp.getData().getStartTime()),
					String.valueOf(temp.getData().getEndTime()),
					String.valueOf(temp.getData().getAttempts()),
					String.valueOf(temp.getData().getSleepTime()),
					String.valueOf(temp.getData().getServiceTime())};
			processdtm.addRow(record);
			temp= temp.getNextNode();
		}	

		record= new String[] { String.valueOf(temp.getData().getProcessID() ),
				String.valueOf(temp.getData().getTask()),
				String.valueOf(temp.getData().getBaseAddress()),
				String.valueOf(temp.getData().getStartTime()),
				String.valueOf(temp.getData().getEndTime()),
				String.valueOf(temp.getData().getAttempts()),
				String.valueOf(temp.getData().getSleepTime()),
				String.valueOf(temp.getData().getServiceTime())};
		processdtm.addRow(record);

	}

	public void runProcessThread(Queue batchQueue){

		workingQueue = new Queue(batchQueue);

		RR = new Thread() {

			public void run() {

				while(true) {

					if( !workingQueue.isEmpty() ) {
						int sleepTime=workingQueue.getFront().getData().getSleepTime();
						int baseAddress=workingQueue.getFront().getData().getBaseAddress();

						if(sleepTime>0 && !currentProcess1.getText().isEmpty() || !currentProcess3.getText().isEmpty() || !currentProcess5.getText().isEmpty()) {

							sleepTime-=1;
							if(sleepTime<0) {
								workingQueue.getFront().getData().setSleepTime(0); 
							}else {
								if(workingQueue.getFront().getData().getAttempts()<1)
									processdtm.setValueAt("Sleeping", baseAddress-1, 3);
								workingQueue.getFront().getData().setSleepTime(sleepTime);
								processdtm.setValueAt(sleepTime, baseAddress-1, 6);
							}

						}else { 
							if(workingQueue.getFront().getData().getPriority()!=1) { 
								if(currentProcess1.getText().isEmpty()  && !workingQueue.isEmpty()) {  

									Core1(workingQueue.Dequeue()); 
								} 

								if(currentProcess3.getText().isEmpty()  && !workingQueue.isEmpty()) { 
									Core3(workingQueue.Dequeue()); 
								}

								if(currentProcess5.getText().isEmpty()  && !workingQueue.isEmpty()) {  
									Core5(workingQueue.Dequeue()); 
								}
							}
						}
					}

					if (active==0)idle++;

					util=((clocktime+1)-(idle+1))*100 /(clocktime+1);

					if(workingQueue.isEmpty()) {
						util=0;
						cpuBar.setValue(0);
					}
					clocktime++;

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}

			}
		};


		RR.start();

		FCFS = new Thread() {

			public void run() {

				while(true) {


					if(!workingQueue.isEmpty()) {
						int sleepTime=workingQueue.getFront().getData().getSleepTime();
						int baseAddress=workingQueue.getFront().getData().getBaseAddress();

						if(sleepTime>0 && !currentProcess2.getText().isEmpty() || !currentProcess4.getText().isEmpty() ) {

							sleepTime-=1;
							if(sleepTime<0) {
								workingQueue.getFront().getData().setSleepTime(0); 
							}else  {
								if(workingQueue.getFront().getData().getAttempts()<1)
									processdtm.setValueAt("Sleeping", baseAddress-1, 3);

								workingQueue.getFront().getData().setSleepTime(sleepTime);
								processdtm.setValueAt(sleepTime, baseAddress-1, 6);

							}

						}else {
							if(workingQueue.getFront().getData().getPriority()==1) {
								if(currentProcess2.getText().isEmpty()  && !workingQueue.isEmpty()) {  
									Core2(workingQueue.Dequeue()); 

								} 
							}else {


								if(currentProcess2.getText().isEmpty()  && !workingQueue.isEmpty()) {  
									Core2(workingQueue.Dequeue());

								} 

								if(currentProcess4.getText().isEmpty() && !workingQueue.isEmpty()) {  
									Core4(workingQueue.Dequeue()); 

								}
							}
						}
					}

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}

					if(workingQueue.isEmpty() 
							&& currentProcess1.getText().isEmpty()
							&& currentProcess2.getText().isEmpty()
							&& currentProcess3.getText().isEmpty()
							&& currentProcess4.getText().isEmpty()
							&& currentProcess5.getText().isEmpty()
							) { 
						btnReset.setEnabled(true);
						btnStop.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Simulation is Complete");
						RR.suspend();
						FCFS.suspend();
					}
				}
			}
		};


		FCFS.start();


		setReadyQueue = new Thread() {

			public void run() {
				int processCount=0;

				while(true) {
					if(!workingQueue.isEmpty()) {


						if(workingQueue.getCount()>5){
							ready1.setText(String.valueOf(workingQueue.getFront().getData().getProcessID())); 
							ready2.setText(String.valueOf(workingQueue.getFront().getNextNode().getData().getProcessID())); 
							ready3.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getData().getProcessID()));
							ready4.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getNextNode().getData().getProcessID()));
							ready5.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getNextNode().getNextNode().getData().getProcessID()));

						}  
						if(workingQueue.getCount()==4){
							ready1.setText(String.valueOf(workingQueue.getFront().getData().getProcessID()));
							ready2.setText(String.valueOf(workingQueue.getFront().getNextNode().getData().getProcessID()));
							ready3.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getData().getProcessID()));
							ready4.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getNextNode().getData().getProcessID()));
							ready5.setText("");	

						}
						if(workingQueue.getCount()==3){
							ready1.setText(String.valueOf(workingQueue.getFront().getData().getProcessID()));
							ready2.setText(String.valueOf(workingQueue.getFront().getNextNode().getData().getProcessID()));
							ready3.setText(String.valueOf(workingQueue.getFront().getNextNode().getNextNode().getData().getProcessID()));
							ready4.setText("");
							ready5.setText("");	

						}
						if(workingQueue.getCount()==2){
							ready1.setText(String.valueOf(workingQueue.getFront().getData().getProcessID()));
							ready2.setText(String.valueOf(workingQueue.getFront().getNextNode().getData().getProcessID()));
							ready3.setText("");
							ready4.setText("");
							ready5.setText("");	

						}
						if(workingQueue.getCount()==1){
							ready1.setText(String.valueOf(workingQueue.getFront().getData().getProcessID()));
							ready2.setText("");
							ready3.setText("");
							ready4.setText("");
							ready5.setText("");	

						}


					}else {
						ready1.setText(""); 
						ready2.setText("");
						ready3.setText("");
						ready4.setText("");
						ready5.setText("");	
					}

					setActiveProcessColor();
					processes_lbl.setText(String.valueOf(workingQueue.getCount()));

					try {  

						Thread.sleep(100);


					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}
			}


		};


		setReadyQueue.start();
	}

	protected int checkActive(int position) {
		int state=0;
		JLabel processLabel[]= {currentProcess1, currentProcess2, currentProcess3, currentProcess4, currentProcess5};

		if(processLabel[position-1].getText().isEmpty())
			state=0;
		else
			state=1;

		return state;
	}

	protected void setActiveProcessColor() {

		JLabel processLabel[]= {currentProcess1, currentProcess2, currentProcess3, currentProcess4, currentProcess5};

		for(int i=0; i<5; i++) {

			if(processLabel[i].getText().isEmpty()) 
				processLabel[i].setBorder(null);
			else 
				processLabel[i].setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(112, 128, 144), 1, true)));

		}

	}

	//LOGICAL RPOCESSORS/////////////////////////////////////


	public void Core1(Node RunningTask ){


		cpuCore1 = new Thread() {

			public void run() {

				String processID = String.valueOf(RunningTask.getData().getProcessID());  
				int baseAddress = RunningTask.getData().getBaseAddress();
				int serviceTime=RunningTask.getData().getServiceTime();
				String startTime= String.valueOf(clocktime);
				int attempt = RunningTask.getData().getAttempts();
				boolean finish = RunningTask.getData().isFinish(); 
				int timeSlice=0;
				int code=getCode(RunningTask.getData().getTask());

				processdtm.setValueAt("Running", baseAddress-1, 4);

				if(attempt==0 ) {
					processdtm.setValueAt(startTime, baseAddress-1, 3);
					RunningTask.getData().setStartTime(startTime);
				}
				attempt+=1;
				RunningTask.getData().setAttempts(attempt);

				while(true) {

					serviceTime=serviceTime-1; 

					if(!finish) {  
						currentProcess1.setText(processID);

						if(serviceTime<=0 ) {
							finish=true;
							RunningTask.getData().setFinish(finish);
							processdtm.setValueAt(clocktime, baseAddress-1, 4); 
							RunningTask.getData().setEndTime(String.valueOf(clocktime));
							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
							setAlgorithm("RR", baseAddress);
							list.systemCall(code, RunningTask.getData(), "RR");

							setAverageTime(RunningTask.getData());
							currentProcess1.setText("");  

						}
						else {

							timeSlice++;

							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+serviceTime ,baseAddress-1 , 7);
							if(timeSlice==quantum.getSelectedIndex() ) {

								setAlgorithm("RR", baseAddress);

								if(serviceTime>0) {
									processdtm.setValueAt("Preempted", baseAddress-1, 4);
									workingQueue.Enqueue(RunningTask.getData());
									processdtm.setValueAt(attempt ,baseAddress-1 , 5);

								}else {

									finish=true;
									RunningTask.getData().setFinish(finish);
									processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
									RunningTask.getData().setEndTime(String.valueOf(clocktime));
									processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
									list.systemCall(code, RunningTask.getData(), "RR");

									setAlgorithm("RR", baseAddress);

								}

								currentProcess1.setText("");
								RunningTask.getData().setServiceTime(serviceTime); 
								cpuCore1.suspend(); 
							}
						}
						processdtm.setValueAt(attempt ,baseAddress-1 , 5);

					}

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}
				}
			}
		};

		cpuCore1.start();

	}

	protected void setAverageTime(Process process) { 
		processCompleted+=1;
		int turnAround=(Integer.valueOf(process.getEndTime())-Integer.valueOf(process.getStartTime())+Integer.valueOf(averageTurnArroundTime.getText()) );
		averageTurnArroundTime.setText(String.valueOf(turnAround/processCompleted));

		int waiting=(turnAround-process.getBurst()+Integer.valueOf(averageWaitingTime.getText())) ;
		averageWaitingTime.setText(String.valueOf(waiting/processCompleted));

		if(Integer.parseInt(averageTurnArroundTime.getText())<0)
			averageTurnArroundTime.setText("0");

		if(Integer.parseInt(averageWaitingTime.getText())<0)
			averageWaitingTime.setText("0");
	}

	//protected void priorityCheck(int code, int positon) {
	//	Thread coreThread[] = {cpuCore1, cpuCore2, cpuCore3, cpuCore4, cpuCore5};
	//	JLabel processLabel[]= {currentProcess1, currentProcess2, currentProcess3, currentProcess4, currentProcess5};
	//
	//	if(code==1 || code==2 || code==3) {
	//		for(int i=0; i<5; i++) {
	//			if(i!=positon) {
	//				coreThread[i].suspend();
	//				processLabel[i].setText("");
	//			}
	//		}
	//	}else {
	//		for(int i=0; i<5; i++) 
	//			coreThread[i].resume();; 
	//	}
	//
	//}

	protected void setAlgorithm(String type, int baseAddress) {

		if(processdtm.getValueAt(baseAddress-1, 8)==null)
			processdtm.setValueAt(type, baseAddress-1 , 8);
		else
			processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 8)+"->"+type, baseAddress-1 , 8);
	}

	protected int getCode(String task) {

		if(task.equals("Adding a new record to the list"))
			return 1;
		else if(task.equals("Removing a record from the list"))
			return 2;
		else if(task.equals("Sort the records in ascending order"))
			return 3;
		else if(task.equals("Retrieve a record from the list"))
			return 4;
		else if(task.equals("Calculate the total of all integer"))
			return 5;

		return 0;
	}

	public void Core2(Node RunningTask){


		cpuCore2 = new Thread() {
			public void run() {
				String processID = String.valueOf(RunningTask.getData().getProcessID());  
				int baseAddress = RunningTask.getData().getBaseAddress();
				int serviceTime=RunningTask.getData().getServiceTime();
				String startTime= String.valueOf(clocktime); 
				int attempt = RunningTask.getData().getAttempts();
				boolean finish = RunningTask.getData().isFinish(); 
				int code=getCode(RunningTask.getData().getTask());

				if(attempt==0) {
					processdtm.setValueAt(startTime, baseAddress-1, 3);
					RunningTask.getData().setStartTime(startTime);
				}

				attempt+=1;
				processdtm.setValueAt("Running", baseAddress-1, 4);
				RunningTask.getData().setAttempts(attempt);

				while(true) {


					if (!finish) {
						currentProcess2.setText(processID);
						serviceTime=serviceTime-1;

						if(serviceTime==0) {

							processdtm.setValueAt(attempt ,baseAddress-1 , 5);

							finish=true;
							RunningTask.getData().setFinish(finish);
							processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
							RunningTask.getData().setEndTime(String.valueOf(clocktime));
							setAlgorithm("FCFS", baseAddress);
							list.systemCall(code, RunningTask.getData(), "FCFS");


							setAverageTime(RunningTask.getData());
							currentProcess2.setText("");  

						}

						RunningTask.getData().setServiceTime(serviceTime);
						processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+serviceTime ,baseAddress-1 , 7);

					}

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}
			}

		};
		cpuCore2.start();

	}

	public void Core3(Node RunningTask){


		cpuCore3 = new Thread() {
			public void run() {

				String processID = String.valueOf(RunningTask.getData().getProcessID());  
				int baseAddress = RunningTask.getData().getBaseAddress();
				int serviceTime=RunningTask.getData().getServiceTime();
				String startTime= String.valueOf(clocktime); 
				int attempt = RunningTask.getData().getAttempts(); 
				boolean finish = RunningTask.getData().isFinish(); 
				int timeSlice=0;
				int code=getCode(RunningTask.getData().getTask());

				if(attempt==0) {
					processdtm.setValueAt(startTime, baseAddress-1, 3);
					RunningTask.getData().setStartTime(startTime);
				}

				attempt+=1;
				processdtm.setValueAt("Running", baseAddress-1, 4);
				RunningTask.getData().setAttempts(attempt);

				while(true) {

					serviceTime=serviceTime-1; 

					if(!finish) {  
						currentProcess3.setText(processID);


						if(serviceTime<=0 ) {
							finish=true;
							RunningTask.getData().setFinish(finish);
							processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
							RunningTask.getData().setEndTime(String.valueOf(clocktime));

							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
							setAlgorithm("RR", baseAddress);
							list.systemCall(code, RunningTask.getData(), "RR");

							setAverageTime(RunningTask.getData());
							currentProcess3.setText("");  

						}
						else {

							timeSlice++;

							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+serviceTime ,baseAddress-1 , 7);
							if(timeSlice==quantum.getSelectedIndex() ) {

								setAlgorithm("RR", baseAddress);

								if(serviceTime>0) {
									processdtm.setValueAt("Preempted", baseAddress-1, 4);
									workingQueue.Enqueue(RunningTask.getData());
									processdtm.setValueAt(attempt ,baseAddress-1 , 5);

								}else {

									finish=true;
									RunningTask.getData().setFinish(finish);
									processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
									RunningTask.getData().setEndTime(String.valueOf(clocktime));

									processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
									list.systemCall(code, RunningTask.getData(), "RR");

									setAlgorithm("RR", baseAddress);

								}

								currentProcess3.setText("");
								RunningTask.getData().setServiceTime(serviceTime); 
								cpuCore3.suspend(); 
							}
						}
						processdtm.setValueAt(attempt ,baseAddress-1 , 5);
					}
					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}
				}
			}
		};

		cpuCore3.start();

	}

	public void Core4(Node RunningTask){
		cpuCore4 = new Thread() {

			public void run() {
				String processID = String.valueOf(RunningTask.getData().getProcessID());  
				int baseAddress = RunningTask.getData().getBaseAddress();
				int serviceTime=RunningTask.getData().getServiceTime();
				String startTime=String.valueOf(clocktime);
				int attempt = RunningTask.getData().getAttempts(); 
				boolean finish = RunningTask.getData().isFinish();  
				int code=getCode(RunningTask.getData().getTask());

				if(attempt==0) {
					processdtm.setValueAt(startTime, baseAddress-1, 3);
					RunningTask.getData().setStartTime(startTime);	
				}

				attempt+=1;
				
				processdtm.setValueAt("Running", baseAddress-1, 4);
				RunningTask.getData().setAttempts(attempt);

				
				while(true) {
					if (!finish) {
						currentProcess4.setText(processID);
						serviceTime=serviceTime-1;

						if(serviceTime==0) {

							processdtm.setValueAt(attempt ,baseAddress-1 , 5);
							finish=true;
							RunningTask.getData().setFinish(finish);

							processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
							RunningTask.getData().setEndTime(String.valueOf(clocktime));

							setAlgorithm("FCFS", baseAddress);
							list.systemCall(code, RunningTask.getData(), "FCFS");

							setAverageTime(RunningTask.getData());
							currentProcess4.setText("");  
						}

						RunningTask.getData().setServiceTime(serviceTime); 
						processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+serviceTime ,baseAddress-1 , 7);

					}

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}
			}

		};

		cpuCore4.start();

	}

	public void Core5(Node RunningTask){

		cpuCore5 = new Thread() {
			public void run() {

				String processID = String.valueOf(RunningTask.getData().getProcessID());  
				int baseAddress = RunningTask.getData().getBaseAddress();
				int serviceTime=RunningTask.getData().getServiceTime();
				String startTime= String.valueOf(clocktime);
				int attempt = RunningTask.getData().getAttempts();
				boolean finish = RunningTask.getData().isFinish(); 
				int timeSlice=0;
				int code=getCode(RunningTask.getData().getTask());

				if(attempt==0) {
					processdtm.setValueAt(startTime, baseAddress-1, 3);
					RunningTask.getData().setStartTime(startTime);
				}
				attempt+=1;
				processdtm.setValueAt("Running", baseAddress-1, 4);
				RunningTask.getData().setAttempts(attempt);

				while(true) {

					serviceTime=serviceTime-1; 

					if(!finish) {  
						currentProcess5.setText(processID);


						if(serviceTime<=0 ) {
							finish=true;
							RunningTask.getData().setFinish(finish);

							processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
							RunningTask.getData().setEndTime(String.valueOf(clocktime));

							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
							setAlgorithm("RR", baseAddress);
							list.systemCall(code, RunningTask.getData(), "RR");

							setAverageTime(RunningTask.getData());
							currentProcess5.setText("");  

						}
						else {

							timeSlice++;

							processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+serviceTime ,baseAddress-1 , 7);
							if(timeSlice==quantum.getSelectedIndex() ) {

								setAlgorithm("RR", baseAddress);

								if(serviceTime>0) {
									processdtm.setValueAt("Preempted", baseAddress-1, 4);
									workingQueue.Enqueue(RunningTask.getData());
									processdtm.setValueAt(attempt ,baseAddress-1 , 5);

								}else {

									finish=true;
									RunningTask.getData().setFinish(finish); 
									processdtm.setValueAt(clocktime, baseAddress-1, 4);//Set Finsh TIme
									RunningTask.getData().setEndTime(String.valueOf(clocktime));

									processdtm.setValueAt(processdtm.getValueAt(baseAddress-1, 7)+"-"+0 ,baseAddress-1 , 7);
									list.systemCall(code, RunningTask.getData(), "RR");
									setAlgorithm("RR", baseAddress);
								}

								currentProcess5.setText("");
								RunningTask.getData().setServiceTime(serviceTime); 
								cpuCore5.suspend(); 
							}
						}
						processdtm.setValueAt(attempt ,baseAddress-1 , 5);
					}

					try { 
						Thread.sleep(simulatorSpeed);
					}catch (InterruptedException e) { 
						e.printStackTrace();
					}
				}
			}
		};


		cpuCore5.start();

	}

}
