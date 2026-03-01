import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;

public class App {

   
    public static void main(String[] args) throws IOException {
        
        String filename = "tasks.txt";
        todolist todoList = new todolist(filename);
        Menu menu = new Menu(todoList);
        menu.start();
    }


}
