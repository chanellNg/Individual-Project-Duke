package ip.src.main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class that deals with loading and updating the tasks in the given file.
 *
 */

public class Storage {
    protected String filePath;
    protected Duke bot;

    Storage (String filePath, Duke bot) {
        this.filePath = filePath;
        this.bot = bot;
    }

    /**
     * Updates the Duke bot with the tasks by loading the tasks in the file.
     *
     * @param taskData The task from the file.
     * @throws FileNotFoundException
     */

    public void loadTask(String taskData) throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Task newTask = new Task("");

        String[] taskDataArr = taskData.split(" \\| ");
        String type = taskDataArr[0];
        String doneStatus = taskDataArr[1];
        if (type.equals("T")) {
            String details = taskDataArr[2];
            newTask = new ToDo(details);
            bot.addToBot(newTask);

        } else if (type.equals("E")) {
            String content = taskDataArr[2];
            String at = taskDataArr[3];
            newTask = new Event(content, at);
            bot.addToBot(newTask);

        } else {
            assert type.equals("D");
            String content = taskDataArr[2];
            String by = taskDataArr[3];
            newTask = new Deadline(content, by);
            bot.addToBot(newTask);
        }

        if (doneStatus == " 1 ") {
            newTask.markDone();
        }

    }

    /**
     * Updates the Duke bot with the tasks stored in the file by calling the loadTask method.
     *
     * @param filePath The file with the tasks.
     * @param bot The duke bot.
     * @throws FileNotFoundException
     */
    public void createBot(String filePath, Duke bot) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Storage storage = new Storage(filePath, bot);

        while (s.hasNext()) {
            storage.loadTask(s.nextLine());
        }

    }

    /**
     * Updates the file with the bot current TaskList.
     *
     * @throws IOException
     */
    public void updateFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task element:this.bot.list.list) {
            fw.write(element.toString() + "\n");

        }
        fw.close();
    }

}
