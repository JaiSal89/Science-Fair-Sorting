import java.util.*;
import java.io.*;

public class StartPairing 
{
	static boolean fieldCommaCheck= false;
	static boolean fieldCheckLoop= false;
	public static String pairedOutputFile, fileLine;
	public static Queue<String> projectQueue = new PriorityQueue<>(); 
	public static Queue<String> judgesQueue = new PriorityQueue<>();
	static LinkedList<String> beSoList = new LinkedList<String>();
	static LinkedList<String> chemList = new LinkedList<String>();
	static LinkedList<String> eaSpList = new LinkedList<String>();
	static LinkedList<String> enviList = new LinkedList<String>();
	static LinkedList<String> lifeList = new LinkedList<String>();
	static LinkedList<String> maPhList = new LinkedList<String>();
	static LinkedList<String> outputList = new LinkedList<String>();
	static LinkedList<String> projectNumList = new LinkedList<String>();
	static LinkedList<String> beSoProjects = new LinkedList<String>();
	static LinkedList<String> chemProjects = new LinkedList<String>();
	static LinkedList<String> eaSpProjects = new LinkedList<String>();
	static LinkedList<String> enviProjects = new LinkedList<String>();
	static LinkedList<String> lifeProjects = new LinkedList<String>();
	static LinkedList<String> maPhProjects = new LinkedList<String>();

	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		PairingUI start = new PairingUI();
	}

	public static void SortFiles(String projectsFile, String judgesFile, String outputFile) throws IOException
	{
		pairedOutputFile = outputFile;
		
		File projFile = new File(projectsFile);
		File judFile = new File(judgesFile);
		
		BufferedReader transText0 = new BufferedReader(new FileReader(projFile));
		
		while ((fileLine = transText0.readLine()) != null)
			projectQueue.add(fileLine);
			
		transText0.close();
		
		BufferedReader transText1 = new BufferedReader(new FileReader(judFile));
		
		while ((fileLine = transText1.readLine()) != null)
			judgesQueue.add(fileLine);
		
		transText1.close();
		
		
		System.out.println(projectQueue.size() + " projects found.");
		System.out.println(judgesQueue.size() + " judges found.");
		
		FieldSorting(projectQueue, judgesQueue, outputFile);
		
	}
	
	public static void FieldSorting(Queue<String> projectQueue, Queue<String> judgesQueue, String outputFile)
	{
		String judgeName = null, judgeFields = null, projectField, projectNumber, line;
		Queue<String> multiFieldQueue = new PriorityQueue<>();
		int count = 0;
		
		while (!judgesQueue.isEmpty())   //starts processing the judges queue.
		{
			fieldCommaCheck = false;
			fieldCheckLoop = false;
			count = 0; 
			line = judgesQueue.poll();
			
			for (int a = 0; a < line.length(); a++) //splits the judges by name and fields. 
			{
				if (line.charAt(a) == ':')
				{
					judgeName = line.substring(0, a);
					judgeName = judgeName.strip();
					
					judgeFields = line.substring(a+1);
					judgeFields = judgeFields.strip();
				}
			}
			
			count = judgeFields.length();
			 
			for (int a = 0; a < count; a++) //starts scanning for fields for ','.
			{
				if (judgeFields.charAt(a) == ',')
				{
					fieldCommaCheck = true;
					multiFieldQueue.add(judgeFields.substring(0, a)); 
					judgeFields = judgeFields.substring(a+1);
					judgeFields = judgeFields.strip();
					a = 0;
					count = judgeFields.length();
				}
			}
			
			if (fieldCommaCheck == false)
			{
				JudgeSorting(judgeName, judgeFields);
			}
			else
			{
				multiFieldQueue.add(judgeFields);
				while (!multiFieldQueue.isEmpty())
				{
					JudgeSorting(judgeName, multiFieldQueue.poll());
				}
			}
		}
		
		System.out.println(beSoList.size() + " BeSo Judges listed.");
		System.out.println(chemList.size() + " Chem Judges listed.");
		System.out.println(eaSpList.size() + " EaSp Judges listed.");
		System.out.println(enviList.size() + " Envi Judges listed.");
		System.out.println(lifeList.size() + " Life Judges listed.");
		System.out.println(maPhList.size() + " maPh Judges listed.");
		
		while (!projectQueue.isEmpty())   //starts processing the judges queue.
		{
			line = projectQueue.poll();
			
			projectNumber = line.replaceAll("[^0-9]", "");
			projectNumber = projectNumber.strip();
			
			projectField = line.replaceAll("[^A-Za-z]", "");
			projectField = projectField.strip();
			
			ProjSorting(projectNumber, projectField);
		}
		
		System.out.println();
		System.out.println(beSoProjects.size() + " BeSo Projects listed.");
		System.out.println(chemProjects.size() + " Chem Projects listed.");
		System.out.println(eaSpProjects.size() + " EaSp Projects listed.");
		System.out.println(enviProjects.size() + " Envi Projects listed.");
		System.out.println(lifeProjects.size() + " Life Projects listed.");
		System.out.println(maPhProjects.size() + " maPh Projects listed.");
		
		try {
			JudgeProjectPairing();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void JudgeSorting(String judge, String judgeField) //takes judges name and given field to sort into list.
	{
		switch(judgeField)
		{
		case "Behavioral/Social Science":
			
			beSoList.add(judge);
			//fieldCheckLoop = true;
			break;
			
		case "Chemistry":
			chemList.add(judge);
			//fieldCheckLoop = true;
			
			break;
			
		case "Earth/Space Science":
			eaSpList.add(judge);
			//fieldCheckLoop = true;
			
			break;
			
		case "Environmental Science":
			enviList.add(judge);
			//fieldCheckLoop = true;
			
			break;
			
		case "Life Science":
			lifeList.add(judge);
			//fieldCheckLoop = true;
			
			break; 
			
		case "Mathematics/Physics":
			maPhList.add(judge);
			//fieldCheckLoop = true;
			
			break;
			
		default: 
			
		}
	}
	
	public static void ProjSorting(String projNum, String projField)
	{
		switch (projField)
		{
		case "BehavioralSocialScience":
			beSoProjects.add(projNum);
			break;
			
		case "Chemistry":
			chemProjects.add(projNum);
			break;
			
		case "EarthSpaceScience":
			eaSpProjects.add(projNum);
			break;
			
		case "EnvironmentalScience":
			enviProjects.add(projNum);
			break;
			
		case "LifeScience":
			lifeProjects.add(projNum);
			break;
			
		case "MathematicsPhysics":
			maPhProjects.add(projNum);
			break;
		}
		
		return;
	}
	
	private static void JudgeProjectPairing() throws FileNotFoundException 
	{
		PrintWriter toFile = new PrintWriter(pairedOutputFile);
		
		if (beSoProjects.size() <= 6)
		{
			toFile.print("Behavioral/Social Science: ");
			toFile.print(beSoList.poll() + ", ");
			toFile.print(beSoList.poll() + ", ");
			toFile.print(beSoList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!beSoProjects.isEmpty())
			{
				toFile.print(beSoProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = beSoProjects.size() / 6;
			for (double a = 0.0; a < repCount; a++)
			{
				toFile.print("Behavioral/Social Science_" + (a + 1.0) + ": ");
				toFile.print(beSoList.poll() + ", ");
				toFile.print(beSoList.poll() + ", ");
				toFile.print(beSoList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int b = 0; b < 6; b++)
				{
					toFile.print(beSoProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		if (chemProjects.size() <= 6)
		{
			toFile.print("Chemistry: ");
			toFile.print(chemList.poll() + ", ");
			toFile.print(chemList.poll() + ", ");
			toFile.print(chemList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!chemProjects.isEmpty())
			{
				toFile.print(chemProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = chemProjects.size() / 6.0;
			for (double b = 0.0; b < repCount; b++)
			{
				toFile.print("Chemistry_" + (b + 1.0) + ": ");
				toFile.print(chemList.poll() + ", ");
				toFile.print(chemList.poll() + ", ");
				toFile.print(chemList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int c = 0; c < 6; c++)
				{
					toFile.print(chemProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		if (eaSpProjects.size() <= 6)
		{
			toFile.print("Earth/Space Science: ");
			toFile.print(eaSpList.poll() + ", ");
			toFile.print(eaSpList.poll() + ", ");
			toFile.print(eaSpList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!eaSpProjects.isEmpty())
			{
				toFile.print(eaSpProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = eaSpProjects.size() / 6.0;
			for (double b = 0.0; b < repCount; b++)
			{
				toFile.print("Earth/Space Science_" + (b + 1.0) + ": ");
				toFile.print(eaSpList.poll() + ", ");
				toFile.print(eaSpList.poll() + ", ");
				toFile.print(eaSpList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int c = 0; c < 6; c++)
				{
					toFile.print(eaSpProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		if (enviProjects.size() <= 6)
		{
			toFile.print("Environmental Science: ");
			toFile.print(enviList.poll() + ", ");
			toFile.print(enviList.poll() + ", ");
			toFile.print(enviList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!enviProjects.isEmpty())
			{
				toFile.print(enviProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = enviProjects.size() / 6.0;
			for (double b = 0.0; b < repCount; b++)
			{
				toFile.print("Environmental Science_" + (b + 1.0) + ": ");
				toFile.print(enviList.poll() + ", ");
				toFile.print(enviList.poll() + ", ");
				toFile.print(enviList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int c = 0; c < 6; c++)
				{
					toFile.print(enviProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		if (lifeProjects.size() <= 6)
		{
			toFile.print("Life Science: ");
			toFile.print(lifeList.poll() + ", ");
			toFile.print(lifeList.poll() + ", ");
			toFile.print(lifeList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!lifeProjects.isEmpty())
			{
				toFile.print(lifeProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = lifeProjects.size() / 6.0;
			for (double b = 0.0; b < repCount; b++)
			{
				toFile.print("Life Science_" + (b + 1.0) + ": ");
				toFile.print(lifeList.poll() + ", ");
				toFile.print(lifeList.poll() + ", ");
				toFile.print(lifeList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int c = 0; c < 6; c++)
				{
					toFile.print(lifeProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		if (maPhProjects.size() <= 6)
		{
			toFile.print("Mathematics/Phyics: ");
			toFile.print(maPhList.poll() + ", ");
			toFile.print(maPhList.poll() + ", ");
			toFile.print(maPhList.poll() + " ");
			toFile.println();
			
			toFile.print("Projects: ");
			while (!maPhProjects.isEmpty())
			{
				toFile.print(maPhProjects.poll() + " ");
			}
			toFile.println();
		}
		else 
		{
			double repCount = maPhProjects.size() / 6.0;
			for (double b = 0.0; b < repCount; b++)
			{
				toFile.print("Mathematics/Phyics_" + (b + 1.0) + ": ");
				toFile.print(maPhList.poll() + ", ");
				toFile.print(maPhList.poll() + ", ");
				toFile.print(maPhList.poll() + " ");
				toFile.println();
			
				toFile.print("Projects: ");
			
				for (int c = 0; c < 6; c++)
				{
					toFile.print(maPhProjects.poll() + " ");
				}
				toFile.println();
			}
		}
		
		PairingUI.statusLabel.setText("OutputFile Updated.");
		toFile.close();
	}
}