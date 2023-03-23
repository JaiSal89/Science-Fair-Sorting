import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PairingUI implements ActionListener
{
	JLabel intro, projectInput, judgesInput, outputFile; 
	public static JLabel statusLabel;
	JFrame frame;
	JTextField projectsField, judgesField, outputField;
	public String projectsFile, judgesFile, outputFileName; 
	
	public PairingUI()
	{
		frame = new JFrame ("Project Matching");
		frame.setSize(600, 300); 
		frame.setLocation(900, 500);
		
		intro = new JLabel("Match projects to set judges. Enter the file names and press submit.");
		intro.setBounds(10, 5, 500, 30);
		frame.add(intro);
		
		projectInput = new JLabel("Projects File: ");
		projectInput.setBounds(90, 45, 175, 30);
		frame.add(projectInput);
		
		judgesInput = new JLabel("Judges File: ");
		judgesInput.setBounds(90, 105, 175, 30);
		frame.add(judgesInput);
		
		outputFile = new JLabel("Output File: ");
		outputFile.setBounds(330, 45, 175, 30);
		frame.add(outputFile);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(330, 130, 175, 30);
		frame.add(statusLabel);
		
		projectsField = new JTextField(15);
		projectsField.setBounds(90, 70, 175, 30);
		frame.add(projectsField);
		
		judgesField = new JTextField(15);
		judgesField.setBounds(90, 130, 175, 30);
		frame.add(judgesField);
		
		outputField = new JTextField(15);
		outputField.setBounds(330, 70, 175, 30);
		frame.add(outputField);
		
		JButton submitFile = new JButton("Submit");
		submitFile.setBounds(150, 200, 100, 30);
		frame.add(submitFile);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(350, 200, 100, 30);
		frame.add(clearButton);
		
		submitFile.addActionListener(this);
		clearButton.addActionListener(this);
		
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if (command.equals("Submit"));
		{
			projectsFile = "C:\\Users\\julia\\eclipse-workspace\\Science Fair Pairing\\" + projectsField.getText() + ".txt";
			judgesFile = "C:\\Users\\julia\\eclipse-workspace\\Science Fair Pairing\\" + judgesField.getText() + ".txt";
			outputFileName = "C:\\Users\\julia\\eclipse-workspace\\Science Fair Pairing\\" + outputField.getText() + ".txt";
			
			try {
				StartPairing.SortFiles(projectsFile, judgesFile, outputFileName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				statusLabel.setText("Error.");
				e1.printStackTrace();
			}
		}
		
		if (command.equals("Clear"));
		{
			projectsField.setText("");
			judgesField.setText("");
			outputField.setText("");
		}
	}
}
