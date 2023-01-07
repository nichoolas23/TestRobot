package frc.robot.CompVision;

import edu.wpi.first.cscore.AxisCamera;

public class ComputerVision {
    public void init() {
       /* Thread visThread = new Thread(
                () -> {
    
                    AxisCamera camera = CameraServer.addAxisCamera("axis-camera.local");
    
                    camera.setResolution(640, 480);
    
    
                    CvSink cvSink = CameraServer.getVideo();
    
                    CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);
    
                    // Mats are very memory expensive. Lets reuse this Mat.
    
    
                    // This cannot be 'true'. The program will never exit if it is. This
                    // lets the robot stop this thread when restarting robot code or
                    // deploying.
                    while (!Thread.interrupted()) {
                        // Tell the CvSink to grab a frame from the camera and put it
                        // in the source mat.  If there is an error notify the output.
                        if (cvSink.grabFrame(mat) == 0) {
                            // Send the output the error.
                            outputStream.notifyError(cvSink.getError());
                            // skip the rest of the current iteration
                            continue;
                        }
                        // Put a rectangle on the image
                        Imgproc.rectangle(
                                mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                        // Give the output stream a new image to display
                        outputStream.putFrame(mat);
                    }
    
                });
        visThread.setDaemon(true);
        visThread.start();*/
    
}
    }