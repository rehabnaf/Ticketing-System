package src;

import com.google.gson.Gson;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {

    private int totalNumOfTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int  maximumTicketCapacity;

    private static int validateInput(Scanner input, String message) {
      boolean continueUserInput = true;
      int value = 0;
        do {
            try {
                System.out.print(message);
                 value = input.nextInt();
                if (value < 0 || value == 0){
                    throw new IllegalArgumentException("Try again, only positive numbers that are greater than zero are accepted");
                }
                continueUserInput = false;
            } catch (InputMismatchException ex) {
                System.out.println("Try again, invalid value (only numbers are accepted)");
                input.nextLine(); // clears input

            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());

            }
        }
        while (continueUserInput);
        return value;
    }

    public static Configuration loadConfigFile(File file){
        Gson gson = new Gson();
        Configuration configObj = new Configuration();
        try (Reader reader = new FileReader(file)) {
            configObj = gson.fromJson(reader,Configuration.class);


        } catch (IOException e) {
            e.getMessage();
        }
        return configObj;
    }
    public static void saveConfigFile(File file,Configuration configObj){
        Gson gson = new Gson();
        try (Writer writer = new FileWriter("Config.json")){
                gson.toJson(configObj,writer);
        } catch (IOException e){
            e.getMessage();
        }
    }

    public void getUserInput(){
        Scanner input = new Scanner(System.in);
        this.totalNumOfTickets = validateInput(input,"Enter the total number of tickets: ");
        this.ticketReleaseRate = validateInput(input,"Enter the ticket release rate: ");
        this.customerRetrievalRate = validateInput(input,"Enter the customer retrieval rate: ");
        this.maximumTicketCapacity = validateInput(input,"Enter the maximum ticket capacity: ");
    }

    public int getTotalNumOfTickets(){
        return this.totalNumOfTickets;
    }

    public int getTicketReleaseRate(){
        return this.ticketReleaseRate;
    }

    public int getCustomerRetrievalRate(){
        return this.customerRetrievalRate;
    }

    public int getMaximumTicketCapacity(){
        return this.maximumTicketCapacity;
    }


}
