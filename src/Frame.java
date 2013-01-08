import java.util.ArrayList;


public class Frame {

	private int number;
	private ArrayList<Joint> joints;
	
	public Frame(int number)
	{
		this.number = number;
		joints = new ArrayList<Joint>();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean addJoint(Joint j)
	{
		return joints.add(j);
	}
	
	public ArrayList<Joint> getJoints() {
		return joints;
	}

	public void setJoints(ArrayList<Joint> joints) {
		this.joints = joints;
	}

}
