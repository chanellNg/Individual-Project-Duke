import java.util.*;

public class Level_1 {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Duke bot = new Duke();

        while(!(input.equals("bye"))){
            bot.echo(input);
            input = sc.next();
        }

        System.out.println("Bye. Hope to se you again soon!");
    }
}
