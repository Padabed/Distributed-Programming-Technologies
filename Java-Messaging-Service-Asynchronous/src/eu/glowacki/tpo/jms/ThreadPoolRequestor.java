package eu.glowacki.tpo.jms;

import eu.glowacki.tpo.jms.queue.QueueMessageSender;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolRequestor {

        void start() throws InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of 5 threads
            for (int i = 0; i < 10; i++) {
                Runnable sender = new QueueMessageSender("Sender " + i);
                executor.execute(sender);//calling execute method of ExecutorService
            }
            executor.shutdown();
            while (!executor.isTerminated()) {   }
        }
}
