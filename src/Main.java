package src;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){
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

        ExecutorService executor = Executors.newCachedThreadPool();
        TicketPool ticketPool = new TicketPool(configObj);
        executor.execute(new Vendor(ticketPool,1,configObj));
        executor.execute(new Vendor(ticketPool,2,configObj));
        executor.execute(new Customer(1,ticketPool,configObj));
        executor.execute(new Customer(2,ticketPool,configObj));
        executor.shutdown();

    }
}
