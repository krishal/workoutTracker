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
	private JTextField tfield;
	private int count = 0;
	
	public addWorkout() {
		// TODO Auto-generated constructor stub
		JFrame addWG = new JFrame();
		
		addWG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWG.setTitle("New Workout");
		addWG.setSize(300, 500);
		addWG.setLayout(new BoxLayout(addWG.getContentPane(), BoxLayout.Y_AXIS));
		
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
				tfield = new JTextField();
				tfield.setName("tField"+count);
				count++;
				addWG.add(tfield);
				addWG.revalidate();
				addWG.repaint();
			}
		});
		addWG.add(addExer);
		addWG.add(saveWork);
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
