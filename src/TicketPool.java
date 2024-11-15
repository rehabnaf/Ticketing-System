package src;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private  int maxTicketCapacity;
    private int totalNumOfTickets;
    private Vector<Ticket> ticketPool;
    private static Lock lock = new ReentrantLock();
    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();
    private Configuration configObj;

    public TicketPool(Configuration configObj){
        this.configObj = configObj;
        this.maxTicketCapacity = configObj.getMaximumTicketCapacity();
        this.totalNumOfTickets = configObj.getTotalNumOfTickets();
        ticketPool = new Vector<>(this.maxTicketCapacity);
    }


    public void addTickets(Ticket ticket){ // synchronized method for vendors
        lock.lock();
        /* prevents race conditions, locks the resource when a thread is using the shared data resource so
        another thread cannot use the resource at the same time
         */
        try {
            while(totalNumOfTickets == maxTicketCapacity){
                System.out.println("Vendor should wait, ticket pool is full");
                notFull.await();
                /*  condition object for thread communication, await method allows another thread to run so if the ticket pool is full
                * the vendor should wait and this allows a customer to buy tickets (remove tickets since the ticket pool is full)
                * notFull.await() = Ticket pool is full  */
            }
            ticketPool.add(ticket);
            System.out.println("A ticket was added");
            ++totalNumOfTickets;
            notEmpty.signal();
            /* condition object that signals to notEmpty.await() while notFull.signal() signals to notFull.await()
            * notEmpty.signal() = Ticket pool is not empty hence a customer can purchase ticktets */
        }
        catch (Exception exception){
            exception.getMessage();
        }

        finally {
            lock.unlock(); // unlocks the lock after a thread is done using the shared data resource allowing another thread to use the resource
        }

    }

    public void removeTicket(Ticket ticket){ // synchronized method for customers
        lock.lock();
        try {
            while(totalNumOfTickets == 0){
                System.out.println("No tickets released by vendor, customer should wait");
                notEmpty.await();
                // notEmpty.await() = ticket pool is empty so customer should wait and this allows a vendor to add tickets  //
            }
            ticketPool.remove(0);
            System.out.println("A ticket was removed");
            --totalNumOfTickets;
            notFull.signal();
            // notFull.signal() = ticket pool is not full hence a vendor can add tickets. notFull.signal() => notFull.await()
        }
        catch (InterruptedException exception){
            exception.getMessage();
        }
        finally {
            lock.unlock();
        }
    }
}
