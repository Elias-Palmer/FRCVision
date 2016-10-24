package CaptureAndProcess;




import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.Iterator;




public class CapPro {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    Mat orig;
    Mat HSV;
    Mat Hierararchy;
    Mat Threshold;
    Mat Original;


    double VerticalFOV = 33.6;
    double HorizontalFOV = 59.7;
    public static final Scalar
            RED = new Scalar(0, 0, 255),
            BLUE = new Scalar(255, 0, 0),
            GREEN = new Scalar(0, 255, 0),
            BLACK = new Scalar(0,0,0),
            YELLOW = new Scalar(0, 255, 255),
            LOWER_BOUNDS = new Scalar(0,0,95),
            UPPER_BOUNDS = new Scalar(100,255,255);

public void Init() {
    //capture.open(0);


    Threshold = new Mat();
    orig = new Mat();
    HSV = new Mat();
    Hierararchy = new Mat();
    Original = new Mat();




}
public void ProcessImage(VideoCapture videoCapture){

    ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    videoCapture.read(orig);


    Imgproc.cvtColor(orig,HSV,Imgproc.COLOR_BGR2HSV);
    Core.inRange(HSV, LOWER_BOUNDS, UPPER_BOUNDS, Threshold);


        Imgproc.findContours(Threshold,contours,Hierararchy,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);


    for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
        MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
        Rect rec = Imgproc.boundingRect(matOfPoint);
        if(rec.height < 40 || rec.width < 40){
            iterator.remove();
            continue;
        }
        float aspect = (float)rec.width/(float)rec.height;
        if(aspect < 1.0)
            iterator.remove();
    }

    if(contours.size()==1) {


        Rect rect = Imgproc.boundingRect(contours.get(0));
        Imgproc.rectangle(orig, rect.br(), rect.tl(), RED, 3);

        Point center = new Point();
        center.x = (rect.br().x - (rect.width / 2));
        center.y = (rect.tl().y + 10);

        Point CenterOfScreen = new Point((640 / 2), (480 / 2));

        double AngleToWinY = 0;
        double AngleToWinX = 0;

        AngleToWinY = (VerticalFOV / 2) * ((640 / 2) / ((640 / 2) - (center.y - (640 / 2))));
        AngleToWinX = (HorizontalFOV / 2) * ((480 / 2) / ((480 / 2) + (center.x + (480 / 2))));

        setAngleX(AngleToWinX);
        setAngleY(AngleToWinY);
        SetFrame(orig);
        //Percentage of a point on a line


    }else {SetFrame(orig);}
  //  Imgcodecs.imwrite("Test.jpg",orig);
    }
    double Anglex=0;
    double Angley=0;
    public void setAngleX(double X){
        Anglex = X;
    }
    public double getAngleX(){
        return Anglex;
    }

    public void setAngleY(double Y){
        Angley = Y;
    }
    public double getAngleY(){
        return Angley;
    }
    Mat frame;
    public Mat GetFrame(){
        return frame;
    }
    public void SetFrame(Mat mat){
        frame=mat;
    }

}













