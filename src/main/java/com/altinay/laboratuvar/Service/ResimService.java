package com.altinay.laboratuvar.Service;
import com.altinay.laboratuvar.Entity.Rapor;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class ResimService {

    public byte[] resimYarat(Rapor rapor){
        try{
            BufferedImage resim = new BufferedImage(700,400,BufferedImage.TYPE_INT_RGB);
            Graphics g = resim.getGraphics();

            g.drawString("Dosya NO: "+ rapor.getDosyaNumarasi() , 20 , 20);
            g.drawString("Hasta TC: " + rapor.getTc() , 20 , 40);
            g.drawString("Hasta Adı: " + rapor.getHastaAd() , 20 , 60);
            g.drawString("Hasta Soyad: " + rapor.getHastaSoyad() , 20 , 80);
            g.drawString("Tani: " + rapor.getTani() , 20 , 100);
            g.drawString("Tani Detayi: " + rapor.getTaniDetay() , 20 , 120);
            g.drawString("Laborant Ad: " + rapor.getLaborant().getAd() , 20 , 140);
            g.drawString("Laborant Soyad: " + rapor.getLaborant().getSoyad() , 20 , 160);
            g.drawString("Laborant Hastane No: " + rapor.getLaborant().getHastaneKimlikNo() , 20 , 180);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resim, "png" , baos);
            return baos.toByteArray();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
