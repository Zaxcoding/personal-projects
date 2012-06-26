package platformer;

public interface MoveableEntity extends Entity 
{
	public double getDX();
	public double getDY();
	public void setDX(double dx);
	public void setDY(double dy);
}
