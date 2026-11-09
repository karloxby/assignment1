
import java.awt.*;

public abstract class Car {

	private double currentSpeed;
	private double enginePower;
	private int nrDoors;
	private Color color;
	private String modelName;
	
	public Car(double enginePower, int nrDoors, Color color, String modelName) {
		this.enginePower = enginePower;
		this.nrDoors = nrDoors;
		this.color = color;
		this.modelName = modelName;
	}

	public int getNrDoors() {
		return nrDoors;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color clr) {
		color = clr;
	}

	public void startEngine() {
		currentSpeed = 0.1;
	}

	public void stopEngine() {
		currentSpeed = 0;
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
}

