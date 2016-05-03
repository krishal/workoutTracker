package workoutTracker;

//GUI
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.opencsv.CSVReader;
//For file
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class addWorkout {
	//For testing purposes 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new addWorkout();
	}
	
	private JTextField tName, tRep, tSet;
	private int count = 0;
	private Dimension maximumSize;
	
	public addWorkout() {
		// TODO Auto-generated constructor stub
		JFrame addWG = new JFrame();
		JPanel buttonP = new JPanel();
		JPanel textFields = new JPanel();
		
		addWG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWG.setTitle("New Workout");
		addWG.setSize(300, 500);
		addWG.setLayout(new BorderLayout());
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.Y_AXIS));
		textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
		
		maximumSize = new Dimension(250,60);
		textFields.setMaximumSize(maximumSize);
		JButton saveWork = new JButton("Save Workout");
		JButton addExer = new JButton("Add Exercise");
		saveWork.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				addToFile();
			}
		});
		
		addExer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				tName = new JTextField();
				tName.setSize(textFields.getWidth(), 3);
				tName.setName("tName"+count);
				textFields.add(tName);
				tRep = new JTextField();
				tRep.setSize(textFields.getWidth(), 10);
				tRep.setName("tRep"+count);
				textFields.add(tRep);
				tSet = new JTextField();
				tSet.setSize(textFields.getWidth(), 15);
				tSet.setName("tSet"+count);
				count++;
				maximumSize = new Dimension(250,60*count);
				textFields.setMaximumSize(maximumSize);
				textFields.add(tSet);
				textFields.revalidate();
				textFields.repaint();
			}
		});
		addExer.setAlignmentX(Component.CENTER_ALIGNMENT);
		saveWork.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonP.add(addExer);
		buttonP.add(saveWork);
		
		addWG.add(textFields, BorderLayout.NORTH);
		addWG.add(buttonP, BorderLayout.SOUTH);
		addWG.setVisible(true);
	}
	
	public void addToFile(){
		List<String[]> workouts = new ArrayList<String[]>();
		String csvFile = "src/Info/log.csv";
		CSVReader br = null;
		String[] nextLine = {""};
		String[] newLine = {"1","2"};
		char csvSplitBy = ',';
		
		try{
			br = new CSVReader(new FileReader(csvFile), csvSplitBy);
			while((nextLine = br.readNext()) != null){
				workouts.add(nextLine);
			}
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try{
					br.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		CSVWriter wr = null;
		printList(workouts);
		try{
			wr = new CSVWriter(new FileWriter(csvFile), csvSplitBy);
			workouts.add(newLine);
			wr.writeAll(workouts);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			System.out.println(new File(".").getAbsoluteFile());
		}
		catch (IOException e){
			e.printStackTrace();
			
		}
		finally{
			if(wr != null){
				try{
					wr.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void printList(List<String[]> t){
		Iterator<String[]> iterator = t.iterator();
		while(iterator.hasNext()) {
			String[] next = iterator.next();
			for(String n : next){
				System.out.print(n+", ");
			}
			System.out.println();
		}
	}
}
