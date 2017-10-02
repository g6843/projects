package web;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfImageObject;

public class GetImages{
 

 //allow pdf file selection for extracting
 public static void selectPDF(String src, String destination){

   File file= new File(src);    
              extractImage(file.toString(), destination);
   System.out.println("Extraction complete");
                }
   
 
 private static void extractImage(String src, String destination){

  try{
   
  //create pdf reader object 
  PdfReader pr=new PdfReader(src);
  PRStream pst;
  PdfImageObject pio;
  PdfObject po;
  int n=pr.getXrefSize(); //number of objects in pdf document
  for(int i=0;i<n;i++){
   po=pr.getPdfObject(i); //get the object at the index i in the objects collection
   if(po==null || !po.isStream()) //object not found so continue
    continue;
   pst=(PRStream)po; //cast object to stream
   PdfObject type=pst.get(PdfName.SUBTYPE); //get the object type
   //check if the object is the image type object
   if(type!=null && type.toString().equals(PdfName.IMAGE.toString())){
    pio=new PdfImageObject(pst); //get the image  
    BufferedImage bi=pio.getBufferedImage(); //convert the image to buffered image
    
    for (int k=0;k<bi.getWidth();k++)
        for (int j=0;j<bi.getHeight()/2;j++)
        {
            int tmp = bi.getRGB(k, j);
            bi.setRGB(k, j, bi.getRGB(k, bi.getHeight()-j-1));
            bi.setRGB(k, bi.getHeight()-j-1, tmp);
        }
    
    
    ImageIO.write(bi, "jpg", new File(destination+i+".jpg")); //write the buffered image
    //to local disk
    pr.close();
   }
  
  }
  
  
  }catch(Exception e){e.printStackTrace();}
  
 }

 
}