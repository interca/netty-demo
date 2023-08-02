package cn.itcast.netty.c3;

import java.util.concurrent.*;

public class JdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        //提交任务
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(2);
                Thread.sleep(1000);
                return 50;
            }
        });
        System.out.println(1);
        //主线程通过future获取结果  这是一个阻塞的过程
        Integer integer = future.get();
        System.out.println(integer);
        System.out.println(3);
    }
}
