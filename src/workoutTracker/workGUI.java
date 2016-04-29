package workoutTracker;

import javax.swing.JFrame;

import com.opencsv.CSVReader;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class workGUI {
	public workGUI()
	{
		JFrame workG = new JFrame();
		
		workG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		workG.setTitle("Workout Tracker");
		workG.setSize(300, 500);
		
		JButton newWorkout = new JButton("Add Workout");
		newWorkout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				new addWorkout();
			}
		});
		workG.add(newWorkout,BorderLayout.NORTH);
		
		workG.setVisible(true);
	}
}