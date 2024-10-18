package com.altinay.laboratuvar.Service;

import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.Rol;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ResimServiceTest {

    @Test
    public void testResimYarat(){
        Laborant laborant = new Laborant( 1 , "Mehmet" , "Kaya" , "1234567890" , Rol.ADMIN , "password");
        Rapor rapor = new Rapor(1,"D-7389","Ahmet" , "Yilmaz" , "12345678912" , "Migren" ,
                "Baş Ağrısı , Baş Dönmesi"
                , new Date(),null, laborant);

        ResimService resimService = new ResimService();
        byte[] resim = resimService.resimYarat(rapor);

        assertNotNull(resim , "Resim null olmamalı");


    }

}