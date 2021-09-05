package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程处理任务统一框架
 * 适用于批量处理数据
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/5 23:16
 */
public class MultiThreadTask {
    public static void main(String[] args) {
        List<Object> list = new ArrayList();
        for (int i = 0; i < 8014; i++) {
            list.add(i + "test.png");
        }
        new MultiThreadTask().handleListMutiSchedule(list, 5);
        new MultiThreadTask().handleListMultiThread(list, 10);
    }

    /**
     * 多线程定时处理
     */
    public void handleListMutiSchedule(List list, int taskCount) {
        System.out.println("begin====================================");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(taskCount);
        int start = 0;
        int listSize = list.size();
        int remainder = listSize % taskCount;
        int taskDataSize = listSize / taskCount;
        //平均分配task任务
        for (int i = 0; i < taskCount; i++, start += taskDataSize) {
            int end = start + taskDataSize;
            //最后如果有分配不均的，多余部分交给最后一个任务处理
            if (i == taskCount - 1) {
                if (remainder != 0) {
                    end = listSize;
                }
            }
            executorService.schedule(new Task(list, start, end), 0, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

    /**
     * 普通线程池有返回处理
     */
    public void handleListMultiThread(List list, int taskCount) {
        int start = 0;
        ExecutorService ex = Executors.newFixedThreadPool(taskCount);
        int listSize = list.size();
        int remainder = listSize % taskCount;
        int taskDataSize = listSize / taskCount;
        List<Future> futures = new ArrayList(taskCount);
        // 平均分配task任务
        for (int i = 0; i < taskCount; i++, start += taskDataSize) {
            int end = start + taskDataSize;
            // 最后如果有分配不均的，多余部分交给最后一个任务处理
            if (i == taskCount - 1) {
                if (remainder != 0) {
                    end = listSize;
                }
            }
            Future future = ex.submit(new Task(list, start, end));
            futures.add(future);
        }
        try {
            // 处理
            for (Future future : futures) {
                Object f = future.get();
                // 自定义对返回值f的处理
            }
            ex.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Task任务执行单元
     */
    private static class Task implements Callable<List<Object>> {
        private List<Object> list;
        private int start;
        private int end;

        public Task(List<Object> list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Object> call() throws Exception {
            Object obj = null;
            List<Object> retList = new ArrayList();
            for (int i = start; i < end; i++) {
                obj = list.get(i);
                Thread.sleep(10);
                System.out.println(Thread.currentThread() + "running: " + obj);
            }
            // 返回处理结果
            return retList;
        }
    }
}
