package io.github.biezhi.qrgen;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;

/**
 * QRGen QR Code Generator Usages
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class QRGenExample {

    public static void main(String[] args) {
        // get QR file from text using defaults
        // File file = QRCode.from("Hello World").file();

        // get QR stream from text using defaults
        ByteArrayOutputStream stream = QRCode.from("Hello World").stream();

        // override the image type to be JPG
        QRCode.from("Hello World").to(ImageType.JPG).file();
        QRCode.from("Hello World").to(ImageType.JPG).stream();

        // override image size to be 250x250
        QRCode.from("Hello World").withSize(250, 250).file();
        QRCode.from("Hello World").withSize(250, 250).stream();

        // override size and image type
        QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).file();
        QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).stream();

        // supply own outputstream
        QRCode.from("Hello World").to(ImageType.PNG).writeTo(stream);

        // supply own file name
        QRCode.from("Hello World").file("QRCode");

        // supply charset hint to ZXING
        QRCode.from("Hello World").withCharset("UTF-8");

        // supply error correction level hint to ZXING
        QRCode.from("Hello World").withErrorCorrection(ErrorCorrectionLevel.L);

        // supply any hint to ZXING
        QRCode.from("Hello World").withHint(EncodeHintType.CHARACTER_SET, "UTF-8");

        // encode contact data as vcard using defaults
        VCard johnDoe = new VCard("John Doe")
                .setEmail("john.doe@example.org")
                .setAddress("John Doe Street 1, 5678 Doestown")
                .setTitle("Mister")
                .setCompany("Hello Inc.")
                .setPhoneNumber("1234")
                .setWebsite("www.example.org");
        QRCode.from(johnDoe).file();

        // if using special characters don't forget to supply the encoding
        VCard johnSpecial = new VCard("biezhi")
                .setAddress("上海张江");
        QRCode.from(johnSpecial).withCharset("UTF-8").file();
    }

}
