
public class Joint {

	private String type;
	
	private double x;
	private double y;
	private double z;
	
	public Joint(String jointType)
	{
		this.type = jointType;
	}
	
	public Joint(String jointType, double x, double y, double z)
	{
		this.type = jointType;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
}
