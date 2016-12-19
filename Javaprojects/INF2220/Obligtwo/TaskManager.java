

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*Most of the activities are implemented in this class.
 * first it reads  the file and and put to task by creating the 
 * tasks class object.The data is again used to create the event 
 * activities.The method for checking if the project has a cycle
 * is implemented here also. finally printing out the task contents  and
 *  the traversing of the project. */

public class TaskManager {
	private int projectSize; // size of the project
	private Task[] tasks; // holds all the tasks
	private LinkedList<Integer> tOrder; // hold the topological order of tasks.
	private graphEvent[] events; // holds the all the events

	/*
	 * This method reads the input from the file and and inserts to tasks and
	 * edges which area also created here.
	 */
	public void readFile(String file) {
		tOrder = new LinkedList<Integer>();
		Scanner s = null;
		int id = 0;
		String name = null;
		int time = 0;
		int staff = 0;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("Error: unknown element ");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println("not a valid integers");
			System.exit(1);
		}

		projectSize = Integer.parseInt(s.nextLine());// Read project size
		s.nextLine(); // to skip the empty line

		tasks = new Task[projectSize];

		for (int i = 0; i < projectSize; i++)
			tasks[i] = new Task(id, name, time, staff);

		for (Task t : tasks) { // Reads each task
			String line = s.nextLine();
			String[] node = line.split("[ \t]+");
			id = Integer.parseInt(node[0]);
			name = node[1];
			time = Integer.parseInt(node[2]);
			staff = Integer.parseInt(node[3]);

			t.setId(id);
			t.setName(name);
			t.setTime(time);
			t.setStaff(staff);

			// Reads dependancies from index 4 of the lines.
			for (int j = 4; j < node.length; j++) {
				int dep = Integer.parseInt(node[j]);
				if (dep != 0)
					t.addEdge(dep, tasks);

			}
		}
	}

	/*
	 * This method does dfs on all the tasks .if a cycle is detected then the
	 * project is not realizable. The topological order of the tasks is done by
	 * this method by calling the visit method.
	 */
	public boolean containsCyclic() {

		LinkedList<Task> list = new LinkedList<Task>();
		for (Task t : tasks) {
			if (!list.contains(t)) {
				list.add(t);
				list = visit(list, t);
				if (list == null) {
					// node in the cycles
					System.out.printf("%s(%2d)-> \n", t.getName(), (t.getId()));

					return true;
				}
			}
		}
		return false;
	}

	/*
	 * This method does dfs recursively. if a circle is found then the recursion
	 * stops and returns null.it also printout the other nodes in the cycle.
	 */
	public LinkedList<Task> visit(LinkedList<Task> list, Task s) {

		s.setActive(true); // task is set as active

		for (Task e : s.getOutEdges()) { // goes through all edges

			if (e.getTask().isActive()) {
				// cycles node
				System.out.printf(" %s(%2d)-> ", e.getTask().getName(),
						(e.getTask().getId()));
				return null;
			}
			if (!list.contains(e.getTask())) {
				list.add(e.getTask());
				list = visit(list, e.getTask()); // recursion
				if (list == null) {
					// other nodes of the cycles node
					System.out.printf(" %s(%2d)-> ", e.getTask().getName(),
							(e.getTask().getId()));
					return null;
				}
			}
		}

		tOrder.add(s.getId());
		s.setActive(false);
		return list;
	}

	/*
	 * This method convert our activity node graph to event-node graph For every
	 * node in the tasks we will have two events ,dummy and real. The edges
	 * between the to events will be the task.The edge from the dummy to the
	 * real will have the same time as the activity task event but edges
	 * pointing the dummy will have 0 time.Lastly dummy event has to constructed
	 * for the completion of the project.
	 */

	public void createEvents() {
		events = new graphEvent[2 * projectSize + 2];
		for (int id = 1; id <= projectSize; id++) {
			events[2 * id - 2] = new graphEvent(id); // the dummy event
			events[2 * id - 1] = new graphEvent(id); // the real event
			getDummy(id).addEventEdges(getTask(id).getTime(), getReal(id));

		}
		for (Task t : tasks)
			for (Task e : t.getOutEdges())
				getReal(e.getId()).addEventEdges(0, getDummy(t.getId()));

		// extra dummy event for completion
		events[2 * projectSize] = new graphEvent(0);
		events[2 * projectSize + 1] = new graphEvent(0);
		getDummy(projectSize + 1).addEventEdges(0, getReal(projectSize + 1));
		for (int i = 0; i < 2 * projectSize; i++)
			events[i].addEventEdges(0, getDummy(projectSize + 1));
		tOrder.add(projectSize + 1);
	}

	// This method returns the dummy event
	public graphEvent getDummy(int id) {
		return events[2 * id - 2];
	}

	// This method returns the real event
	public graphEvent getReal(int id) {
		return events[2 * id - 1];
	}

	// return task of the id given.
	public Task getTask(int id) {
		return tasks[id - 1];
	}

	/*
	 * This method get the earliest time a task can start by going through the
	 * graphevent edges.The calculation of earliest finish we adapt to the
	 * shortest path algorithm. The applicable rule for calculating shortest
	 * finish for node i is EC1 = 0 ECw = max (ECv + cv,w) (v,w)∈E (see
	 * READme.txt for more information). earliest finish times is calculated by
	 * their topological order for vertices
	 */
	public void earlyStart() {

		for (Integer i : tOrder) {
			graphEvent[] events = { getDummy(i), getReal(i) };
			for (graphEvent e : events)
				for (graphEvent ed : e.getOutEdges())
					ed.getEvent().setEarliestFinish(
							max(ed.getEvent().getEarliestFinish(),
									e.getEarliestFinish() + ed.getTime()));
		}
		for (Task t : tasks) {
			t.setEarliestFinish(getReal(t.getId()).getEarliestFinish());
			t.setEarliestStart(t.getEarliestFinish() - t.getTime());
		}
	}

	/*
	 * This method get the latest time a task can start by going through the
	 * graphevent edges.The latest completion time formula is LCn = ECn =
	 * min(LCw − cv,w) (v,w)∈E(see READme.txt for more information)
	 */
	public void lateStart() {

		graphEvent last = getReal(tOrder.get(projectSize));
		last.setLatestFinish(last.getEarliestFinish());
		/*
		 * The topological order has to be reversed when calculating the latest
		 * finish
		 */
		Collections.reverse(tOrder);
		for (Integer i : tOrder) {

			graphEvent[] events = { getReal(i), getDummy(i) };
			for (graphEvent e : events)
				for (graphEvent ed : e.getInEdges())
					ed.getEvent().setLatestFinish(
							min(ed.getEvent().getLatestFinish(),
									e.getLatestFinish() - ed.getTime()));

		}
		for (Task t : tasks) {
			t.setLatestFinish(getReal(t.getId()).getLatestFinish());
			t.setLatestStart(t.getLatestFinish() - t.getTime());
		}
	}

	public int max(int i, int j) {
		if (i > j)
			return i;
		else
			return j;
	}

	public int min(int i, int j) {
		if (i < j)
			return i;
		else
			return j;

	}

	/*
	 * This method starts the tasks,prints out when started or end of every
	 * task.
	 */
	public void startTraversion() {
		int time = 0;
		boolean action = true;
		int completed = 0;
		int currentStaff = 0;
		System.out.println(" ");
		System.out.println("\t  THE GRAPH TRAVERSION    ");
		System.out.println("\t  --------------------");

		do {
			action = true;
			for (Task t : tasks) {
				if (time == t.getEarliestStart()) {
					if (action) {
						System.out.printf("Time:%2d\n", time);
						action = false;
					}
					System.out.printf("\tStart:\t\t%2d\n ", t.getId());
					currentStaff = currentStaff + t.getStaff();
				}
				if (time == t.getEarliestFinish()) {
					if (action) {
						System.out.println("Time: " + time);
						action = false;
					}
					System.out.printf("\tFinish:\t\t%2d\n ", t.getId());
					completed++;
					currentStaff = currentStaff - t.getStaff();
				}
			}
			if (!action)
				System.out.printf(" \tcurrent staff:  %2d\n", currentStaff);
			time++;
		} while (completed < projectSize);
		System.out.printf(
				"\n*** Shortest possible project execution is %2d ***\n\n",
				time - 1);
	}

	/*
	 * print all the tasks content, their earliest start ,latest start, slack
	 * and whether they critical
	 */
	public void printTasks() {
		System.out.println("\tALL THE TASKS ");
		System.out.println("\tNO CIRCLE WAS FOUND\n");
		for (Task t : tasks) {

			System.out
					.printf("Task:%2d\n\tName: %s\n\tTime:\t %2d\n\tStaff:\t %2d\n\tEarlieststart: %2d\n\tLateststart:   %2d\n\tSlack:       %s\n",
							t.getId(), t.getName(), t.getTime(), t.getStaff(),
							t.getEarliestFinish(), t.getLatestFinish(),
							(t.getLatestFinish() - t.getEarliestFinish()));
			// isCritacal(t);
			taskDependacy(t, true);

			System.out.println("\t------------------------\t");

		}
	}

	// prints out the critical
	public void isCritacal() {
		System.out.println();
		System.out.printf("\n\tTHE CRITICAL TASKS ARE\n");
		for (Task t : tasks) {
			if (t.getLatestFinish() - t.getEarliestFinish() == 0)
				System.out.printf("\t%2d\t%s\n", t.getId(), t.getName());

		}
	}

	// finds and print out every Tasks dependencies
	public void taskDependacy(Task td, boolean action) {
		System.out.printf("\tdepedancy ");
		for (Task t : tasks)
			for (Task et : t.getOutEdges()) {
				if (td == et.getTask()) {
					if (action)
						action = false;
					System.out.printf("  %2d", t.getId());
				}
			}
		System.out.println();
		if (action)
			System.out.println("\t none");
	}
}
