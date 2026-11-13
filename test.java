public class test {
	public static void main(String[] args) {

		Saab95 saab = new Saab95();

		Saab95 turboSaab = new Saab95();
		turboSaab.setTurboOn();

		Volvo240 volvo = new Volvo240();

		Car[] cars = {saab, turboSaab};

		for (int i = 0; i < cars.length; i++) {
			cars[i].startEngine();
			cars[i].gas(0.5);
		}

		if (turboSaab.getCurrentSpeed() > saab.getCurrentSpeed()) {
			System.out.println("Saab95 with turbo is faster.");
		} else {
			throw new AssertionError("Saab95 without turbo is faster.");
		}

		saab.stopEngine();

		// it's not really laps, moreso a straight line
		double finishLine = 10;
		int laps = 0;
		do {
			turboSaab.move();
			turboSaab.gas(0.25);
			laps += 1;

			if (turboSaab.getCurrentSpeed() > turboSaab.getEnginePower()) {
				throw new AssertionError("Speed can exceed engine power.");
			}
		} while (turboSaab.getCurrentY() < finishLine);

		System.out.println("And it crossed the finish line after " + laps + " laps.");

		do {
			turboSaab.brake(0.25);
			turboSaab.move();

			if (turboSaab.getCurrentSpeed() < 0) {
				throw new AssertionError("Speed can drop below 0.");
			}
		} while (turboSaab.getCurrentSpeed() > 0);

		turboSaab.stopEngine();

		// i made it an int to lazily truncate decimals
		int stoppingPoint = (int) (turboSaab.getCurrentY() - finishLine);
		System.out.println("It managed to stop after " + stoppingPoint + " points from the finish line.");

		volvo.startEngine();
		volvo.gas(0.5);
		volvo.move();

		if (volvo.getCurrentY() != volvo.getCurrentSpeed()) {
			throw new AssertionError("Value of Y is not the same as current speed.");
		} else {
			String direction = volvo.getCurrentDirection();
			System.out.println("Volvo240 is facing " + direction + ".");
			System.out.printf("And it moved by exactly %.2f points%n", volvo.getCurrentSpeed());

			do {
				volvo.brake(1);
			} while (volvo.getCurrentSpeed() > 0);
		}

		String start = volvo.getCurrentDirection();
		for (int i = 0; i < 4; i++) {
			// volvo.turnRight();
			volvo.turnLeft();
			System.out.println("Volvo240 turned left to face " + volvo.getCurrentDirection());
		}

		if (!volvo.getCurrentDirection().equals(start)) {
			throw new AssertionError("Turned 360 but did not end up on same direction.");
		} else {
			System.out.println("Probably shouldn't move the car when it's standing still, but here we are.");
		}

		// check if one left turn from north faces west
		Volvo240 volvo6 = new Volvo240();
		volvo6.turnLeft();
		if (volvo6.getCurrentDirection().equals("west")) {
			System.out.println("Success: Turning left from north faces west.");
		} else {
			throw new AssertionError("Turning left from north should face west.");
		}

		volvo.stopEngine();


		//gas and brake values check

		// check if brake at 0 speed produces negative values in speed
		Volvo240 volvo1 = new Volvo240();
		volvo1.brake(0.5);
		if (volvo1.getCurrentSpeed() == 0) {
			System.out.println("Success: Braking at zero does not produce negative speed.");
		} else {
			throw new AssertionError("Negative values are not accepted.");
		}
		// check negative value for gas
		Volvo240 volvo2 = new Volvo240();
		volvo2.startEngine();
		volvo2.gas(-0.5);
		if (volvo2.getCurrentSpeed() == 0.1) {
			System.out.println("Success: Negative gas input does not change speed.");
		} else {
			throw new AssertionError("Gas values should not be negative.");
		}
		// check if negative values in brake after accelerating change the speed
		Volvo240 volvo3 = new Volvo240();
		volvo3.startEngine();
		volvo3.gas(1);

		double speedBeforeBrake = volvo3.getCurrentSpeed();

		volvo3.brake(-0.5);
		if (volvo3.getCurrentSpeed() == speedBeforeBrake) {
			System.out.println("Success: Negative brake input does not change speed.");
		} else {
			throw new AssertionError("Negative values should not affect the speed.");
		}

		// check if speed can overtake engine power
		Saab95 saab1 = new Saab95();

		saab1.startEngine();
		for (int i = 0; i < 150; i++) {
			saab1.gas(1);
		}
		if (saab1.getCurrentSpeed() <= saab1.getEnginePower()) {
			System.out.println("Success: Saab95 speed does not exceed engine power. ");
		} else {
			throw new AssertionError("Speed should not overtake engine power.");
		}
			// check if values in gas >1 are limited
			Volvo240 volvo4 = new Volvo240();
			Volvo240 volvo5 = new Volvo240();

			volvo4.startEngine();
			volvo5.startEngine();
			volvo4.gas(1.5);
			volvo5.gas(1);

			if (volvo5.getCurrentSpeed() == volvo4.getCurrentSpeed()) {
				System.out.println("Success: Gas input above 1 is correctly limited.");
			} else {
				throw new AssertionError("Speed should be the same for both.");

			}

		}
	}
