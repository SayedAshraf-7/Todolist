import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class todolist {

    private List<Task> tasks;
    private String filename;
    private Scanner scanner; // Reuse a single Scanner

    public todolist(String filename) {
        this.tasks = new ArrayList<>();
        this.filename = filename;
        this.scanner = new Scanner(System.in);
        loadTasksFromFile();
    }

    // ===================== LOAD TASKS ===================== 
    private void loadTasksFromFile() {
        File file = new File(filename);
        if (!file.exists()) return; // No file yet

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // Expect format: title|priority|time
                if (parts.length == 3) {
                    String title = parts[0];
                    double priority = Double.parseDouble(parts[1]);
                    double timeRequired = Double.parseDouble(parts[2]);
                    tasks.add(new Task(title, priority, timeRequired));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tasks file: " + e.getMessage());
        }
    }

    // ===================== SAVE TASKS =====================
    private void saveTasksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task t : tasks) {
                bw.write(t.GetTitle() + "|" + t.GetPriority() + "|" + t.GetTimeRequired());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks file: " + e.getMessage());
        }
    }

    // ===================== READ TASK FROM USER =====================
    public Task readTask() {
        System.out.println("Enter task title: ");
        String title = scanner.nextLine();
        System.out.println("Enter task priority: ");
        double priority = scanner.nextDouble();
        System.out.println("Enter task time required: ");
        double timeRequired = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return new Task(title, priority, timeRequired);
    }

    // ===================== ADD TASK =====================
    public void addTask() {
        Task t = readTask();
        tasks.add(t);
        sortTasks();
        saveTasksToFile();
    }

    // ===================== UPDATE TASK =====================
    public boolean updateTask(int taskId) {
        if(taskId < 1 || taskId > tasks.size()) return false;
        Task t = tasks.get(taskId - 1);
        t.printTask();
        Task updated = readTask();
        t.setTitle(updated.GetTitle());
        t.setPriority(updated.GetPriority());
        t.setDuration(updated.GetTimeRequired());
        sortTasks();
        saveTasksToFile();
        return true;
    }

    // ===================== DELETE TASK =====================
    public boolean deleteTask(String title) {
        for (Task t : tasks) {
            if (t.GetTitle().equalsIgnoreCase(title)) {
                tasks.remove(t);
                saveTasksToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int taskIdx) {
        if(taskIdx < 1 || taskIdx > tasks.size()) return false;
        tasks.remove(taskIdx - 1);
        saveTasksToFile();
        return true;
    }

    public void deleteAllTasks() {
        tasks.clear();
        saveTasksToFile();
    }

    // ===================== SORT TASKS =====================
    private void sortTasks() {
        tasks.sort(Comparator.comparingDouble(Task::GetPriority).reversed()
                             .thenComparingDouble(Task::GetTimeRequired));
    }

    // ===================== PRINT ALL TASKS =====================
    public void printAllTasks() {
        System.out.println("=============================================================");
        System.out.println("Title\t\t      Priority\t\t      Time Required ");
        System.out.println("=============================================================");
        for (Task t : tasks) {
            t.printTask();
        }
    }

    // ===================== GET TASKS (Optional) =====================
    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // return a copy to avoid external modification
    }
}