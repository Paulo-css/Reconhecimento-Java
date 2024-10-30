/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reconhecimento;

import org.bytedeco.javacv.Frame;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 *
 * @author work-
 */
public class CapturaFoto {
    
    public void CapturaFoto(){
        
    }
    
    public void CameraInicia(String cpf) throws FrameGrabber.Exception, InterruptedException {
        OpenCVFrameConverter.ToMat convertMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();
        
        CascadeClassifier facedetecta = new CascadeClassifier("src\\Reconhecimento\\haarcascade_frontalface_alt.xml");
        
        CanvasFrame cFrame = new CanvasFrame("Vizualização",CanvasFrame.getDefaultGamma()/camera.getGamma());
        Frame frameCapturado  = null;
        Mat imagemColorida = new Mat();
        int amostrasfoto = 25;//número minimo alertado na documentação do openCV para funcionamento adequado. 
        int amostra = 1;//contator das fotos até 25.
        while ((frameCapturado = camera.grab())!= null){
            imagemColorida = convertMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorida,imagemCinza,COLOR_BGRA2GRAY);
            RectVector facesDetectadas = new RectVector();
            facedetecta.detectMultiScale(imagemCinza, facesDetectadas,1.1,1,0,new Size(150,150),new Size (500,500));
            for (int i=0; i < facesDetectadas.size();i++){
                Rect dadosFace = facesDetectadas.get(0);
                rectangle(imagemColorida,dadosFace, new Scalar(192,217,217,0));
                Mat faceCapturada = new Mat(imagemCinza,dadosFace);
                resize(faceCapturada,faceCapturada,new Size(160,160));
                if(amostra <= amostrasfoto){
                            imwrite("src\\fotos\\pessoa."+cpf+"."+amostra+".jpg",faceCapturada);
                            System.out.println("Foto "+amostra+" capturada\n");
                            amostra++;
                        }

            cFrame.waitKey(20);
            }
            if (cFrame.isVisible()){
                cFrame.showImage(frameCapturado);
            }
            if(amostra > amostrasfoto){
                break;
            }
        }
        cFrame.dispose();
        camera.stop();      
    }
}
