package src;

import src.config.Configuration;

public class Customer implements Runnable {
    private int customerID;
    private int retrievalInterval;
    private TicketPool ticketPool;
    private Configuration configObj;
    private int customerRetrievalRate;

    public Customer(int customerID,TicketPool ticketpool,Configuration configObj){
        this.customerID = customerID;
        this.retrievalInterval = 4000;
        this.ticketPool = ticketpool;
        this.customerRetrievalRate = configObj.getCustomerRetrievalRate();
    }

    public void run(){
        while(true){
            for (int i = 0; i < customerRetrievalRate; ++i){
                ticketPool.removeTicket(this.customerID);
            }
            try {
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
