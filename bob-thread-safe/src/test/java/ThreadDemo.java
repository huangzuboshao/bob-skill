import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author huangzuboshao
 * @date 2023/11/20 17:10
 */
public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            step1();
            return "step1 over";
        }).thenApplyAsync(r1 -> {
            step2(r1);
            return "step2 over";
        }).thenApplyAsync(r2 -> {
            step3(r2);
            return "step3 over";
        }, Executors.newFixedThreadPool(1));
        if (completableFuture.isDone()) {
            String s = completableFuture.get();
            System.out.println("123"+s);
        }
    }

    private static void step3(String r2) {
        System.out.println("step2->step3");
    }

    private static void step2(String r1) {
        System.out.println("step1->step2");
    }

    private static void step1() {
        System.out.println("step1");
    }
}
