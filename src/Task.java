public class Task {

  private String title;
  private double priority;
  private double duration;
  private int TaskId;
  private static int counter = 0;

  public Task(String title , double priority, double duration) {
    this.title = title;
    this.priority = priority;
    this.duration = duration;
    counter++;
    TaskId = counter;
  }

  public int GetTaskId() {
    return TaskId;
  }

  public int getCounter() {
    return counter;
  }
  public String GetTitle() {
    return this.title;
  }
  public double GetPriority() {
    return this.priority;
  }

  public double GetTimeRequired() {
    return this.duration;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public void setPriority(double priority) {
    this.priority = priority;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }


 public void printTask() {
    System.out.printf("%-20s\t %-15.2f\t %-15.2f%n", title, priority, duration); // record
}



}
