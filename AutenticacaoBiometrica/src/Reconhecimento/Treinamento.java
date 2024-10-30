/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reconhecimento;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

/**
 *
 * @author work-
 */
public class Treinamento {
    public static void main(String args[]){
        File diretorio = new File("src\\fotos");
        FilenameFilter filtroImagem = new FilenameFilter(){
            @Override
            public boolean accept(File dir,String nome){
                return nome.endsWith(".jpg") || nome.endsWith(".gif") || nome.endsWith(".png");
            }
        };
        
        File[] arquivos = diretorio.listFiles(filtroImagem);
        MatVector fotos = new MatVector(arquivos.length);
        Mat rot = new Mat(arquivos.length,1,CV_32SC1);
        IntBuffer rotBuffer = rot.createBuffer();
        int contator = 0;
        for(File imagem:arquivos){
            Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);
            System.out.println(classe);
            resize(foto,foto,new Size(160,160));
            fotos.put(contator,foto);
            rotBuffer.put(contator,classe);
            contator++;
        }
    FaceRecognizer eigenfaces = createEigenFaceRecognizer();
    FaceRecognizer fisherfaces = createFisherFaceRecognizer();
    FaceRecognizer lbph = createLBPHFaceRecognizer();
    
    eigenfaces.train(fotos, rot);
    eigenfaces.save("src\\reconhecimento\\classificadosEigenFaces.yml");
    fisherfaces.train(fotos,rot);
    fisherfaces.save("src\\reconhecimento\\classificadosFisherFaces.yml");
    lbph.train(fotos, rot);
    lbph.save("src\\reconhecimento\\classificadoslbph.yml");
    
    }

}
