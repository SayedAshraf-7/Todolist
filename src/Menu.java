import java.util.Scanner;

public class Menu {

    private todolist todoList;
    private Scanner scanner;

    public Menu(todolist todoList) {
        this.todoList = todoList;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        
        while (!exit) {
            printMenu();
            System.out.print("> ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    todoList.addTask();
                    break;
                case "2":
                    updateTask();
                    break;
                case "3":
                    deleteTask();
                    break;
                case "4":
                    todoList.printAllTasks();
                    break;
                case "5":
                    deleteAllTasks();
                    break;
                case "6":
                    System.out.println("Exiting. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("========== TODO LIST MENU ==========");
        System.out.println("1. Add Task");
        System.out.println("2. Update Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Show All Tasks");
        System.out.println("5. Delete All Tasks");
        System.out.println("6. Exit");
        System.out.println("===================================");
    }

    private void updateTask() {
        todoList.printAllTasks();
        System.out.print("Enter the task number to update: ");
        try {
            int taskId = Integer.parseInt(scanner.nextLine());
            boolean success = todoList.updateTask(taskId);
            if (success) {
                System.out.println("Task updated successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private void deleteTask() {
        todoList.printAllTasks();
        System.out.print("Enter the task number to delete (or enter 0 to delete by title): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            boolean success;
            if (choice == 0) {
                System.out.print("Enter the task title to delete: ");
                String title = scanner.nextLine();
                success = todoList.deleteTask(title);
            } else {
                success = todoList.deleteTask(choice);
            }

            if (success) {
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private void deleteAllTasks() {
        System.out.print("Are you sure you want to delete all tasks? (y/n): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            todoList.deleteAllTasks();
            System.out.println("All tasks deleted.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }
  }