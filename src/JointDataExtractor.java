import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class JointDataExtractor {

	public static void extraction(FileWriter fw, ArrayList<Frame> frames, int ignore) {
		ArrayList<String> jointTypes = new ArrayList<String>();
		
		int numberOfFrames = 0;
		
		for (Joint j : frames.get(0).getJoints()) {
			if(!jointTypes.contains(j.getType()))
				jointTypes.add(j.getType());
		}
		
		for (String s : jointTypes) {
			try {
				fw.append(s + "\n");
				
				if(numberOfFrames > ignore) {
				
					for (Frame f : frames) {
						fw.append(f.getNumber() + "\t");
						for (Joint j : f.getJoints()) {
							if(j.getType().equals(s))
								fw.append(j.getX() + "\t" + j.getY() + "\t" + j.getZ() + "\n");
						}
						fw.flush();
					}
					numberOfFrames++;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void vector(FileWriter fw, ArrayList<Frame> frames, int ignore) {
		Frame previous = null;
		int numberOfFrames = 0;
		
		for(Joint j : frames.get(0).getJoints())
		{
			try {
				fw.append(j.getType() + "\t");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fw.append("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (Frame f : frames) {
			if(numberOfFrames > ignore)
			{
				if(previous == null)
				{
					for(int i = 0; i < f.getJoints().size(); i++)
						try {
							fw.append("0.0\t");
						} catch (IOException e) {
							e.printStackTrace();
						}
				} else {
					for(int i = 0; i < f.getJoints().size(); i++)
					{
						try {
							fw.append("" + Math.sqrt(Math.pow(f.getJoints().get(i).getX() - previous.getJoints().get(i).getX(), 2)
									+ Math.pow(f.getJoints().get(i).getY() - previous.getJoints().get(i).getY(), 2)
									+ Math.pow(f.getJoints().get(i).getZ() - previous.getJoints().get(i).getZ(), 2)) + "\t");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				try {
					fw.append("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			previous = f;
			numberOfFrames++;
		}
	}
	
	public static void main(String[] args) {
		
		String function = args[0];
		String outputFile = args[1];
		String dataFile = args[2];
		
		int amountOfFramesToIgnore = Integer.parseInt(args[3]);
		
		File output = new File(outputFile);
		FileWriter fw = null;
		try {
			fw = new FileWriter(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JointParser jp = new JointParser(dataFile);
		
		if(function.equalsIgnoreCase("extraction"))
		{
			extraction(fw, jp.getHandler().getFrames(), amountOfFramesToIgnore);
		} 
		else if(function.equalsIgnoreCase("vector"))
		{
			vector(fw, jp.getHandler().getFrames(), amountOfFramesToIgnore);
		}
	}
	
}
