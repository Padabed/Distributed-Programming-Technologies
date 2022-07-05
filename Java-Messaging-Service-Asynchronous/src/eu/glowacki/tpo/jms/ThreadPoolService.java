package eu.glowacki.tpo.jms;

import eu.glowacki.tpo.jms.queue.QueueMessageReceiver;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolService {

  void start() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);//creating a pool of 2 threads
        for (int i = 0; i < 5; i++) {
            Runnable receiver = new QueueMessageReceiver("Receiver " + i);
            executor.execute(receiver);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
    }
}
