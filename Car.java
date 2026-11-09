
import java.awt.*;

public abstract class Car implements Movable {
	
	private double enginePower;
	private int nrDoors;
	private Color color;
	private String modelName;
	private double currentSpeed;
	private double x;
	private double y;
	private String direction;

	public Car(double enginePower, int nrDoors, Color color, String modelName) {
		this.enginePower = enginePower;
		this.nrDoors = nrDoors;
		this.color = color;
		this.modelName = modelName;
		this.direction = "north";
	}

	public double getEnginePower() {
		return enginePower;
	}
	
	public int getNrDoors() {
		return nrDoors;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color clr) {
		color = clr;
	}

	public String getModelName() {
		return modelName;
	}

	public void startEngine() {
		currentSpeed = 0.1;
	}
	public void stopEngine() {
		currentSpeed = 0;
	}
	
	public double getCurrentSpeed() {
		return currentSpeed;
	}

	// every subclass must implement this method
	public abstract double speedFactor();

	private void incrementSpeed(double amount) {
		currentSpeed = getCurrentSpeed() + speedFactor() * amount;
	}
	private void decrementSpeed(double amount) {
		currentSpeed = getCurrentSpeed() - speedFactor() * amount;
	}

	public void gas(double amount) {
		incrementSpeed(amount);
	}
	public void brake(double amount) {
		decrementSpeed(amount);
	}

	public double getCurrentX() {
		return x;
	}
	public double getCurrentY() {
		return y;
	}

	public String getCurrentDirection() {
		return direction;
	}

	public void turnLeft() {
		if (direction.equals("north")) {
			direction = "west";
		} else if (direction.equals("west")) {
			direction = "south";
		} else if (direction.equals("south")) {
			direction = "east";
		} else {
			direction = "north";
		}
	}

	public void turnRight() {
		if (direction.equals("north")) {
			direction = "east";
		} else if (direction.equals("east")) {
			direction = "south";
		} else if (direction.equals("south")) {
			direction = "west";
		} else {
			direction = "north";
		}
	}

	public void move() {
		if (direction.equals("north")) {
			y = y + currentSpeed;
		} else if (direction.equals("east")) {
			x = x + currentSpeed;
		} else if (direction.equals("south")) {
			y = y - currentSpeed;
		} else {
			x = x - currentSpeed;
		}
	}
}

