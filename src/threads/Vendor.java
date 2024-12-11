package src;

import src.config.Configuration;

public class Vendor implements Runnable {
    private Configuration configObj;
    private int vendorId;
    private int ticketsPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;

    public Vendor(TicketPool ticketPool,int vendorId,Configuration configObj){
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketsPerRelease = configObj.getTicketReleaseRate();
        this.releaseInterval = 3500; // Thread sleep duration
    }
    public void run(){
            while (true){
                // Making a vendor release tickets in batches according to the configuration (ticketsPerRelease)
                for (int i = 0; i < ticketsPerRelease;++i){
                    ticketPool.addTickets(new Ticket(this.vendorId));
                }
                try {
                    Thread.sleep(releaseInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
