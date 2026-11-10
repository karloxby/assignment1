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

		volvo.stopEngine();
	}
}

