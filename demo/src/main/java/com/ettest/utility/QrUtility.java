package com.ettest.utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component("com.ettest.utility.QrUtility")
public class QrUtility {

  public void createQr(String data, String path, String charset, Map hashmap, int height, int width)
      throws IOException, WriterException {

    BitMatrix matrix = new MultiFormatWriter()
        .encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
    MatrixToImageWriter
        .writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
  }
}
