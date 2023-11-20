import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDemo {
    // 商品库存
    private int inventory = 0;

    // 商品库存锁
    private Lock inventoryLock = new ReentrantLock();

    // 生产者线程
    private Thread producerThread;

    // 消费者线程
    private Thread consumerThread;

    public static void main(String[] args) {
        ProducerConsumerDemo demo = new ProducerConsumerDemo();
        demo.start();
    }

    // 启动生产者和消费者线程
    public void start() {
        producerThread = new Thread(() -> {
            while (true) {
                try {
                    // 等待库存发生变化
                    inventoryLock.lock();
                    // 模拟生产过程
                    inventory++;
                    System.out.println("生产者生产了1个商品，库存为" + inventory);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    inventoryLock.unlock();
                }
            }
        });

        consumerThread = new Thread(() -> {
            while (true) {
                try {
                    // 等待库存发生变化
                    inventoryLock.lock();
                    // 模拟消费过程
                    inventory--;
                    System.out.println("消费者消费了1个商品，库存为" + inventory);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    inventoryLock.unlock();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
