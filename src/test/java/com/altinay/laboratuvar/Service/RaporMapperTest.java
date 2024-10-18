package com.altinay.laboratuvar.Service;

import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.RaporResponse;
import com.altinay.laboratuvar.Entity.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RaporMapperTest {

    private RaporMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new RaporMapper();
    }

    @Test
    public void shouldMapRaportoRaporResponse(){

        Laborant laborant = new Laborant( 1 , "Mehmet" , "Kaya" , "1234567890" , Rol.ADMIN , "password");
        Rapor rapor = new Rapor(1,"D-7389","Ahmet" , "Yilmaz" , "12345678912" , "Migren" ,
                    "Baş Ağrısı , Baş Dönmesi"
                    , new Date(),null, laborant);

        RaporResponse response = mapper.toRaporResponse(rapor);

        assertEquals(response.hastaAd() , rapor.getHastaAd());
        assertEquals(response.hastaSoyad() , rapor.getHastaSoyad());
        assertEquals(response.dosyaNumarasi() , rapor.getDosyaNumarasi());
        assertEquals(response.laborantAd() , rapor.getLaborant().getAd());
        assertEquals(response.laborantKimlikNo() , rapor.getLaborant().getHastaneKimlikNo());
        assertEquals(response.laborantSoyad() , rapor.getLaborant().getSoyad());
        assertEquals(response.tc(), rapor.getTc());
        assertEquals(response.tani(), rapor.getTani());
        assertEquals(response.taniDetay(), rapor.getTaniDetay());
        assertEquals(response.verildigiTarih(), rapor.getVerildigiTarih());
        assertEquals(response.id(), rapor.getId());


    }

}