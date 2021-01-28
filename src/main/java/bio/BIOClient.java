package bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * BIO¿Í»§¶Ë
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/27 4:46 ÏÂÎç
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 18686);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            outputStream.write((new Date().toString() + "\n" + str).getBytes());
        }
        outputStream.close();
        socket.close();
    }
}
