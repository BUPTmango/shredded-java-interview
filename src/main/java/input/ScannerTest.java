package input;

import java.util.Scanner;

/**
 * 测试编程题中的输入输出
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/9 8:27 上午
 */
public class ScannerTest {

    public void testNumber() {
        Scanner scanner = new Scanner(System.in);
        int round = scanner.nextInt();
        while (round-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("a : " + a + ", b : " + b + ", sum = " + (a + b));
        }
        scanner.close();
    }

    public void testStrAndNum() {
        Scanner scanner = new Scanner(System.in);
        int round = scanner.nextInt();
        while (round-- > 0) {
            int num = scanner.nextInt();
            String name = scanner.next();
            System.out.println("num : " + num + "  name : " + name);
        }
        scanner.close();
    }

    public void testDoubles() {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        // 当出现下一个不是double的时候停止 比如string
        while (scanner.hasNextDouble()) {
            sum += scanner.nextDouble();
        }
        System.out.println("sum : " + sum);
        scanner.close();
    }

    public void testSum() {
        Scanner scanner = new Scanner(System.in);
        // hasNextLine 会一直等待用户输入 循环不会终止
        while (scanner.hasNextLine()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("a + b = " + (a + b));
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ScannerTest scannerTest = new ScannerTest();
//        scannerTest.testNumber();
//        scannerTest.testStrAndNum();
//        scannerTest.testDoubles();
        scannerTest.testSum();
    }
}
