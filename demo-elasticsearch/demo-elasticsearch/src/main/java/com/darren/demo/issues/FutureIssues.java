package com.darren.demo.issues;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * {@link java.util.concurrent.FutureTask}
 *
 * @author : darren
 * @date : 2021/8/13
 */
public class FutureIssues {

    public static void main(String[] args) {

        AtomicReference<FutureTask<Integer>> a = new AtomicReference<>();

        Runnable task = () -> {
            while (true) {
                FutureTask<Integer> f = new FutureTask<>(() -> 1);
                a.set(f);
                f.run();
            }
        };

        Supplier<Runnable> observe = () -> () -> {
            while (a.get() == null) ;

            int c = 0;
            int ic = 0;
            while (true) {
                c++;
                FutureTask<Integer> f = a.get();
                while (!f.isDone()) {
                }
                try {
                /*
                Set the interrupt flag of this thread.
                The future reports it is done but in some cases a call to
                "get" will result in an underlying call to "awaitDone" if
                the state is observed to be completing.
                "awaitDone" checks if the thread is interrupted and if so
                throws an InterruptedException.
                 */
                    Thread.currentThread().interrupt();
                    f.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    ic++;
                    System.out.println("InterruptedException observed when isDone() == true " + c + " " + ic + " " + Thread.currentThread());
                }
            }
        };

        CompletableFuture.runAsync(task);
        Stream.generate(observe::get)
                .limit(Runtime.getRuntime().availableProcessors() - 1)
                .forEach(CompletableFuture::runAsync);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
