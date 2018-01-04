package com.check.utils;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.awt.image.BufferedImage;


public final class MatrixToImageWriter {

  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;

  private MatrixToImageWriter() {}
  
  
  public static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }

  
  public static void writeToFile(BitMatrix matrix, String format, File file)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, file)) {
      throw new IOException("Could not write an image of format " + format + " to " + file);
    }
  }

  
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, stream)) {
      throw new IOException("Could not write an image of format " + format);
    }
  }
  
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static String createQrImage(String content){
	  Logger logger = Logger.getLogger("CheckLogger"); 
	  String result = "";
	  try {
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		     String prevName=sdf.format(new Date());
		     String fileName=UUID.randomUUID().toString();
		     String path =MatrixToImageWriter.class.getResource("/").getPath();
		     path= path.split("WEB-INF")[0]+"QRImages/"+prevName+"/";
		     
		     //System.out.println(path);
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     File file = new File(path);
		     if (!file.exists())
			 {
				file.mkdir();
			 }
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错率
		     hints.put(EncodeHintType.MARGIN, 1);
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     file = new File(path,fileName+".jpg");
		     logger.info(path+fileName+".jpg");
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
		     result = prevName+"/"+fileName+".jpg";
		     
		 } catch (Exception e) {
			 logger.info(e);
		     e.printStackTrace();
		 }
	  
	  return result;
  }
  
  public static void main(String[] args){
	  
	  //createQrImage("CCCCCCCCCC\nYYYYYYYY","123");
	  
  }
	   
}
