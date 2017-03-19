package Concurrency.ProdCons;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerBlockingQueue {

   public static void main(String args[]){
 
    //Creating shared object
//    BlockingQueue sharedQueue = new LinkedBlockingQueue();
    BlockingQueue sharedQueue = new ArrayBlockingQueue(4);

    //Creating Producer and Consumer Thread
    Thread prodThread = new Thread(new ProducerBQ(sharedQueue));
    Thread consThread = new Thread(new ConsumerBQ(sharedQueue));

    //Starting producer and Consumer thread
    prodThread.start();
    consThread.start();

    // ===================================
//       new ProducerConsumerWaitNotify.Drop();
   }

}

//Producer Class in java
class ProducerBQ implements Runnable {

   private final BlockingQueue sharedQueue;

   public ProducerBQ(BlockingQueue sharedQueue) {
       this.sharedQueue = sharedQueue;
   }

   @Override
   public void run() {
       for(int i=1; i<21; i++){
           try {
               System.out.println("+Produced: " + i + ", SIZE="+ sharedQueue.size());
               sharedQueue.put(i);
           } catch (InterruptedException ex) {
               Logger.getLogger(ProducerBQ.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }

}

//Consumer Class in Java
class ConsumerBQ implements Runnable{

   private final BlockingQueue sharedQueue;

   public ConsumerBQ (BlockingQueue sharedQueue) {
       this.sharedQueue = sharedQueue;
   }
 
   @Override
   public void run() {
       while(true){
           try {
               System.out.println("Consumed: "+ sharedQueue.take() + ", SIZE="+ sharedQueue.size());
               Thread.sleep(100);
           } catch (InterruptedException ex) {
               Logger.getLogger(ConsumerBQ.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
 
 
}