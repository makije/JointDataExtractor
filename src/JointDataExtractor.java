import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class JointDataExtractor {

	public static void main(String[] args) {
		
		String outputFile = args[0];
		String dataFile = args[1];
		
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
	}
	
}
