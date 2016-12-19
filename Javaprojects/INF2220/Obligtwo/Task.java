

import java.util.HashSet;

/*This class represent the information of the activity node 
 * in the graph . it has two constructors. one that 
 * represents the edges and the other the node.most of the methods
 * are  straight forward.**/

public class Task {
	private Task task;
	private int id, time, staff;
	private String name;
	private HashSet<Task> outEdges;
	private boolean active;
	private int earliestStart, latestStart, earliestFinish, latestFinish;

	// constructor for the edges of the node
	public Task(int id, Task[] tasks) {
		setId(id);
		task = getTask(id, tasks);

	}

	// constructor for the nodes
	public Task(int id, String name, int time, int staff) {

		this.setId(id);
		this.setName(name);
		this.setTime(time);
		this.setStaff(staff);
		outEdges = new HashSet<Task>();
		setActive(false);

	}

	// return task of the given id
	public Task getTask(int id, Task[] tasks) {
		return tasks[id - 1];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getStaff() {
		return staff;
	}

	public void setStaff(int staff) {
		this.staff = staff;
	}

	// add edges to the hashset.
	public void addEdge(int id, Task[] tasks) {

		outEdges.add(new Task(id, tasks));

	}

	public HashSet<Task> getOutEdges() {
		return outEdges;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getEarliestFinish() {
		return earliestFinish;
	}

	public void setEarliestFinish(int earliestFinish) {
		this.earliestFinish = earliestFinish;
	}

	public int getEarliestStart() {
		return earliestStart;
	}

	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}

	public void setLatestFinish(int latestFinish) {
		this.latestFinish = latestFinish;

	}

	public int getLatestFinish() {
		return latestFinish;
	}

	public int getLatestStart() {
		return latestStart;
	}

	public void setLatestStart(int latestStart) {
		this.latestStart = latestStart;
	}

	public Task getTask() {
		return task;
	}

}
