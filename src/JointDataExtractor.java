import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class JointDataExtractor {

	public static void main(String[] args) {
		
		String function = args[0];
		String outputFile = args[1];
		String dataFile = args[2];
		
		ArrayList<String> jointTypes = new ArrayList<String>();
		
		File output = new File(outputFile);
		FileWriter fw = null;
		try {
			fw = new FileWriter(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JointParser jp = new JointParser(dataFile);
		
		for (Joint j : jp.getHandler().getFrames().get(0).getJoints()) {
			if(!jointTypes.contains(j.getType()))
				jointTypes.add(j.getType());
		}
		
		if(function.equalsIgnoreCase("extraction"))
		{
		
			for (String s : jointTypes) {
				try {
					fw.append(s + "\n");
					
					for (Frame f : jp.getHandler().getFrames()) {
						fw.append(f.getNumber() + "\t");
						for (Joint j : f.getJoints()) {
							if(j.getType().equals(s))
								fw.append(j.getX() + "\t" + j.getY() + "\t" + j.getZ() + "\n");
						}
						fw.flush();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		} else if(function.equalsIgnoreCase("vector"))
		{
			Frame previous = null;
			for (Frame f : jp.getHandler().getFrames()) {
				
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
				
				previous = f;
			}
			
		}
	}
	
}
