/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reconhecimento;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Funcionario;
import model.dao.FuncionarioDAO;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_face;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 *
 * @author work-
 */
public class Reconhecer {
    public void Reconhecer(){
        
    }
    public String Reconhecerfun() {
        OpenCVFrameConverter.ToMat convertMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        try {
            camera.start();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(Reconhecer.class.getName()).log(Level.SEVERE, null, ex);
        }
        String Resul = "Desconhecido";
        
        CascadeClassifier facedetecta = new opencv_objdetect.CascadeClassifier("src\\Reconhecimento\\haarcascade_frontalface_alt.xml");
        FaceRecognizer reconhecedor = createEigenFaceRecognizer();
        reconhecedor.load("src\\Reconhecimento\\classificadosEigenFaces.yml");
        
        CanvasFrame cFrame = new CanvasFrame("Vizualização",CanvasFrame.getDefaultGamma()/camera.getGamma());
        Frame frameCapturado  = null;
        Mat imagemColorida = new Mat();

        try {
            while ((frameCapturado = camera.grab())!= null && Resul == "Desconhecido"){
                imagemColorida = convertMat.convert(frameCapturado);
                Mat imagemCinza = new Mat();
                cvtColor(imagemColorida,imagemCinza,COLOR_BGRA2GRAY);
                RectVector facesDetectadas = new RectVector();
                facedetecta.detectMultiScale(imagemCinza, facesDetectadas,1.1,2,0,new Size(150,150),new opencv_core.Size (500,500));
                for (int i=0; i < facesDetectadas.size();i++){
                    Rect dadosFace = facesDetectadas.get(0);
                    rectangle(imagemColorida,dadosFace, new opencv_core.Scalar(192,217,217,0));
                    Mat faceCapturada = new Mat(imagemCinza,dadosFace);
                    resize(faceCapturada,faceCapturada,new opencv_core.Size(160,160));
                    
                    IntPointer rot = new IntPointer(1);
                    DoublePointer confi = new DoublePointer(1);
                    reconhecedor.predict(faceCapturada, rot, confi);
                    int predicao = rot.get(0);
                    if(predicao == -1){
                        Resul = "Desconhecido";
                    }
                    else{
                        Resul = Integer.toString(predicao);
                    }
                }
                
                if (cFrame.isVisible()){
                    cFrame.showImage(frameCapturado);
                }

            }
              cFrame.dispose();
              camera.stop();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(Reconhecer.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return Resul;
    }
}

 
