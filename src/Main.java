package src;

import java.util.Scanner;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void start() {

        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to start the Ticketing System (Yes/No) ? ");
        String userChoice = input.next();
        if (userChoice.equals("Yes")) {
            System.out.println("Ticketing system has started");
        }
        else {
            Main.stop();
        }
    }

    public static void stop() {
        System.out.println("Ticketing system has stopped");
        System.exit(0);
    }


    public static void main(String[] args) {
        Main.start();
        File configFile = new File("Config.json");
        Configuration configObj;

        if (configFile.exists()) {
            configObj = Configuration.loadConfigFile(configFile);
            // Loads the configuration file that was saved when the program was run for the first time
        }

        else {
            configObj = new Configuration();
            configObj.getUserInput();
            Configuration.saveConfigFile(configFile,configObj);
            // Saves the configuration file so that we don't have to ask for user input everytime we run the program
        }
        ExecutorService executor = Executors.newCachedThreadPool(); // Creates a thread pool which creates threads as required
        TicketPool ticketPool = new TicketPool(configObj);
        executor.execute(new Vendor(ticketPool,1,configObj));  // Creates a thread for a vendor task and runs the task
        executor.execute(new Vendor(ticketPool,2,configObj));
        executor.execute(new Customer(1,ticketPool,configObj)); // Creates a thread for a consumer task and runs the task
        executor.execute(new Customer(2,ticketPool,configObj));
        executor.shutdown();
    }
}
