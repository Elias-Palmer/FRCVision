package Stream;


import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.net.*;

/**
 * Created by eliwa on 10/18/2016.
 */
public class Streamer {


    public static Socket initSocket(Socket socket)throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        socket = new Socket();
        socket=serverSocket.accept();
        socket.getOutputStream().write(("HTTP/1.0 200 OK\r\n" +
                "Server: iRecon\r\n" +
                "Connection: close\r\n" +
                "Max-Age: 0\r\n" +
                "Expires: 0\r\n" +
                "Cache-Control: no-store, no-cache, must-revalidate, pre-check=0, post-check=0, max-age=0\r\n" +
                "Pragma: no-cache\r\n" +
                "Content-Type: multipart/x-mixed-replace; " +
                "boundary=" + 5 + "\r\n" +
                "\r\n" +
                "--" + 5 + "\r\n").getBytes());

        return socket;
    }


    public static void MjpgStream(Mat origFrame,Socket socket) throws IOException{






        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg",origFrame,matOfByte);
        byte[] imageByte= matOfByte.toArray();
        socket.getOutputStream().write(("Content-type: image/jpeg\r\n" +
                "\r\n").getBytes());
        socket.getOutputStream().write(imageByte);
        socket.getOutputStream().write(("\r\n--" + 5 + "\r\n").getBytes());
        //socket.close();


    }





}