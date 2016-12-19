

public class Oblig2 {

	public static void main(String[] args) {
   //the program takes two arguments
		if (args.length < 2) {
			System.out.println("Run with: java Oblig2 <projectName.txt> manpower");
			System.out.println("For more info, see README.txt");
			return;
		}

		String input = args[0];

		TaskManager tm = new TaskManager();
		tm.readFile(input);

		// check if the graph a circle
		if (tm.containsCyclic()) {
			System.out
					.println("The cirle above was found,so the program is not realizable. ");
			return;
		} else {

			tm.createEvents();
			tm.earlyStart();
			tm.lateStart();
			tm.printTasks();
			tm.isCritacal();
			tm.startTraversion();
		}
	}
}
