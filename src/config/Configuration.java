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



    public static Configuration loadConfigFile(File file){  // method for deserialization = JSON => object
        Gson gson = new Gson();
        Configuration configObj = new Configuration();
        try (Reader reader = new FileReader(file)) {
            configObj = gson.fromJson(reader,Configuration.class); // converts JSON to object


        } catch (IOException e) {
            e.getMessage();
        }
        return configObj;
    }
    public static void saveConfigFile(File file,Configuration configObj){  // method for serialization = object => JSON
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(file)){
                gson.toJson(configObj,writer); // converts object to JSON
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
        while (this.totalNumOfTickets > this.maximumTicketCapacity){
            System.out.println("Try again, total number of tickets is greater than maximum ticket capacity.");
            this.totalNumOfTickets = validateInput(input,"Enter the total number of tickets: ");
            this.maximumTicketCapacity = validateInput(input,"Enter the maximum ticket capacity: ");
        }
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
