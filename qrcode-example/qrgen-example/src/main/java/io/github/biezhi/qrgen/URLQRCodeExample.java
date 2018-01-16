package io.github.biezhi.qrgen;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

public class URLQRCodeExample {

    public static void main(String... args){
        ByteArrayOutputStream bout =
                QRCode.from("https://github.com/biezhi")
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();
        try {
            OutputStream out = new FileOutputStream("qr-code.png");
            bout.writeTo(out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}