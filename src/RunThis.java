import CaptureAndProcess.CapPro;
import SendTables.SendNetworkTables;
import Stream.Streamer;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * Created by eliwa on 10/8/2016.
 */
public class RunThis {
    public static CapPro capPro = new CapPro();
//    public static SendNetworkTables sendNetworkTables = new SendNetworkTables();
    public static String IP = "IP ADDRESS";
    public static Streamer streamer = new Streamer();
    public static Socket socket;

    public static void main(String[] args)throws IOException {
    int cnt =0;
         VideoCapture capture = new VideoCapture(0);
       // sendNetworkTables.init(IP);
        capPro.Init();
        socket = Streamer.intiSocket(socket);
       // capPro.ProcessImage(capture);

        while(true) {

                capPro.ProcessImage(capture);
            try {



                streamer.MjpgStream(capPro.GetFrame(),socket);



                System.out.println(cnt);
                cnt++;

            } catch (Exception e){System.out.println("Run This 2 : "+e);}

         //  if (sendNetworkTables.GetActivate()) {

            //   sendNetworkTables.SendInfo(capPro.getAngleX(), capPro.getAngleY());
           // }
        }
        // put network table with image processing


        // stream images in seprate threads

    }

}
