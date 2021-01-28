package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO·þÎñ¶Ë
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/27 4:46 ÏÂÎç
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(18686);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, 0, len));
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
