import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {

    private int totalNumOfTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int  maximumTicketCapacity;

    private static int validation(Scanner input, String message) {
      boolean continueUserInput = true;
      int value = 0;
        do {
            try {
                System.out.print(message);
                 value = input.nextInt();
                if (value < 0){
                    throw new IllegalArgumentException("Try again, only positive numbers are accepted");
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

    public void getUserInput(){
        Scanner input = new Scanner(System.in);
        this.totalNumOfTickets = validation(input,"Enter the total number of tickets: ");
        this.ticketReleaseRate = validation(input,"Enter the ticket release rate: ");
        this.customerRetrievalRate = validation(input,"Enter the customer retrieval rate: ");
        this.maximumTicketCapacity = validation(input,"Enter the maximum ticket capacity: ");
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
