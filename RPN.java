import java.util.Scanner;

// Node class
class StackNode {
	private StackNode underneath;
	private double data;
	
	public StackNode(double data, StackNode underneath) {
		this.setData(data);
		this.setUnderneath(underneath);
	}
	private void setData(double data) {
		this.data = data;
	}
	private void setUnderneath(StackNode underneath) {
		this.underneath = underneath;
	}
	public double getData() { // return data
		return data;
	}
	public StackNode getUnderneath() { // return underneath
		return underneath;
	}
}

class RPN {
	private String command;
	private StackNode top;
	
	public RPN(String command) {
		top = null;
		this.command = command;
	}
	// Creating new node 
	private void into(double new_data) {
		StackNode new_node = new StackNode(new_data, top);
		top = new_node;
	}
	// Retrieving data from node
	private double outof() {
		double top_data = top.getData();
		top = top.getUnderneath();
		return top_data;
	}

	public double getResult() {
		calculation(); // performing calculation
		double resultVal = outof(); // retrieving result from stack

		if (top != null) {
			throw new IllegalArgumentException();
		}

		return resultVal;
	}

	// Reverse-Polish Notation calculator
	private void calculation() {
		double stackNum2, stackNum1;
		int j;

		for (int i = 0; i < command.length(); i++) {
			// if it's a digit
			if (Character.isDigit(command.charAt(i))) {
				// get a string of the number
				String temp = "";
				for (j = 0; (j < 100)
						&& (Character.isDigit(command.charAt(i)) || (command.charAt(i) == '.')); j++, i++) {
					temp = temp + String.valueOf(command.charAt(i));
				}
				// convert to double and add to the stack
				double number = Double.parseDouble(temp);
				into(number);
			// Performing operations for different conditions and adding to stack
			} else if (command.charAt(i) == '+') {
				stackNum1 = outof();
				stackNum2 = outof();
				into(stackNum2 + stackNum1);
			} else if (command.charAt(i) == '-') {
				stackNum1 = outof();
				stackNum2 = outof();
				into(stackNum2 - stackNum1);
			} else if (command.charAt(i) == '*') {
				stackNum1 = outof();
				stackNum2 = outof();
				into(stackNum2 * stackNum1);
			} else if (command.charAt(i) == '/') {
				stackNum1 = outof();
				stackNum2 = outof();
				into(stackNum2 / stackNum1);
			} else if (command.charAt(i) == '^') {
				stackNum1 = outof();
				stackNum2 = outof();
				into(Math.pow(stackNum2, stackNum1));
			} else if (command.charAt(i) != ' ') {
				throw new IllegalArgumentException();
			}
		}
	}

	// main method
	public static void main(String args[]) {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter RPN expression or \"quit\".");
			String line = input.nextLine();
			if (line.equals("quit")) {
				break;
			} else {
				RPN calc = new RPN(line);
				// printing to 6 decimals 
				System.out.printf("Answer is %f\n", calc.getResult());
			}
		}
	}
}