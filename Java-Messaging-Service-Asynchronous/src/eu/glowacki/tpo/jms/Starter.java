package eu.glowacki.tpo.jms;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Starter {
    public static void main(String[] args) throws InterruptedException {
        //TprMessageListener tprMessageListener = new TprMessageListener();
        ThreadPoolRequestor threadPoolRequestor = new ThreadPoolRequestor();
        ThreadPoolService threadPoolService = new ThreadPoolService();
        threadPoolRequestor.start();
        threadPoolService.start();
    }
}
