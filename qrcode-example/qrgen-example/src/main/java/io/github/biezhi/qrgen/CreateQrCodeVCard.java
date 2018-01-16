package io.github.biezhi.qrgen;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

public class CreateQrCodeVCard {

    public static void main(String... args){
        VCard vCard = new VCard();
        vCard.setName("github.com");
        vCard.setAddress("street 1, xxxx address");
        vCard.setCompany("company Inc.");
        vCard.setPhoneNumber("+xx/xx.xx.xx");
        vCard.setTitle("title");
        vCard.setEmail("biezhi.me@gmail.com");
        vCard.setWebsite("https://github.com/biezhi");

        ByteArrayOutputStream bout =
                QRCode.from(vCard)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();

        try {
            OutputStream out = new FileOutputStream("qr-code-vcard.png");
            bout.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}