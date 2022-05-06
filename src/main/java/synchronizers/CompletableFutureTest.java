package synchronizers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ʵ��ˮ�ݲ����
 * ����3������:
 * ����1����ϴˮ�����տ�ˮ
 * ����2����ϴ�����ϴ�豭���ò�Ҷ
 * ����3�����ݲ衣��������3Ҫ�ȴ�����1������2����ɺ���ܿ�ʼ
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 10:02
 */
public class CompletableFutureTest {
    public static void main(String[] args) {

        // ����1��ϴˮ��->�տ�ˮ
        CompletableFuture<Void> f1 =
                // û�з���ֵ ʹ��runAsync
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:ϴˮ��...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:�տ�ˮ...");
                    sleep(15, TimeUnit.SECONDS);
                });

        // ����2��ϴ���->ϴ�豭->�ò�Ҷ
        CompletableFuture<String> f2 =
                // �з���ֵ ʹ��supplyAsync
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:ϴ���...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:ϴ�豭...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:�ò�Ҷ...");
                    sleep(1, TimeUnit.SECONDS);
                    return "����";
                });

        // ����3������1������2��ɺ�ִ�У��ݲ�
        CompletableFuture<String> f3 =
                // �������future�Ľ��
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:�õ���Ҷ:" + tf);
                    System.out.println("T1:�ݲ�...");
                    return "�ϲ�:" + tf;
                });

        // �ȴ�����3ִ�н��
        System.out.println(f3.join());
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }
}
