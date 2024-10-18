package com.altinay.laboratuvar.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.RaporResponse;
import com.altinay.laboratuvar.Entity.Rol;
import com.altinay.laboratuvar.Repository.RaporRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

class RaporServiceTest {

    @Mock
    private RaporRepository raporRepository;

    @Mock
    private RaporMapper raporMapper;

    @Mock
    private ResimService resimService;

    @InjectMocks
    private RaporService raporService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRaporTanimla() {

        Laborant laborant = new Laborant(1,"Mehmet" , "Bayram" , "2341564567" , Rol.ADMIN ,"password");
        Rapor rapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" ,
                "Beyin Kanseri" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );


        when(resimService.resimYarat(rapor)).thenReturn(new byte[]{});
        when(raporRepository.save(any(Rapor.class))).thenReturn(rapor);
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Kanseri", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        RaporResponse response = raporService.raporTanimla(rapor, laborant);

        assertNotNull(response);
        verify(raporRepository, times(1)).save(rapor);
        verify(resimService, times(1)).resimYarat(rapor);
    }


    @Test
    void testListeleByHastaAdi() {
        Laborant laborant = new Laborant(1,"Mehmet" , "Bayram" , "2341564567" , Rol.ADMIN ,"password");
        Rapor rapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" , "Beyin Kanseri" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );
        when(raporRepository.findByhastaAdAndHastaSoyad(anyString(), anyString()))
                .thenReturn(Arrays.asList(rapor));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Kanseri", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.listeleByHastaAdi("Ahmet", "Yılmaz");

        assertEquals(1, result.size());
        verify(raporRepository, times(1)).findByhastaAdAndHastaSoyad("Ahmet", "Yılmaz");
    }

    @Test
    void testListeleByHastaTc() {
        Laborant laborant = new Laborant(1,"Mehmet" , "Bayram" , "2341564567" , Rol.ADMIN ,"password");
        Rapor rapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" ,
                "Beyin Kanseri" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );
        when(raporRepository.findByTc(anyString())).thenReturn(Arrays.asList(rapor));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Kanseri", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.listeleByHastaTc("12345678901");

        assertEquals(1, result.size());
        verify(raporRepository, times(1)).findByTc("12345678901");
    }

    @Test
    void testListeleByLaborantAdSoyad() {
        Laborant laborant = new Laborant(1,"Mehmet" , "Bayram" , "2341564567" , Rol.ADMIN ,"password");
        Rapor rapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" ,
                "Beyin Kanseri" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );
        when(raporRepository.findByLaborantAdAndSoyad(anyString(), anyString()))
                .thenReturn(Arrays.asList(rapor));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Kanseri", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.listeleByLaborantAdSoyad("Mehmet", "Bayram");

        assertEquals(1, result.size());
        verify(raporRepository, times(1)).findByLaborantAdAndSoyad("Mehmet", "Bayram");
    }

    @Test
    void testGuncelleRapor() {
        Laborant laborant = new Laborant(1,"Mehmet" , "Bayram" , "2341564567" , Rol.ADMIN ,"password");

        Rapor mevcutRapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" ,
                "Beyin Kanseri" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );

        Rapor guncelRapor = new Rapor(1,"1231-2" , "Ahmet" , "Yılmaz" , "25874125896" ,
                "Beyin Uru" ,
                "Hipotalamus'da beyin tümörü" , new Date(2024,Calendar.APRIL,10) ,null ,laborant );


        when(raporRepository.findById(anyInt())).thenReturn(Optional.of(mevcutRapor));
        when(resimService.resimYarat(any(Rapor.class))).thenReturn(new byte[]{});
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Uru", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        RaporResponse response = raporService.guncelleRapor(guncelRapor, 1, laborant);

        assertNotNull(response);
        verify(raporRepository, times(1)).findById(1);
        verify(raporRepository, times(1)).save(mevcutRapor);
    }


    @Test
    void testRaporSil() {
        Rapor rapor = new Rapor();
        when(raporRepository.findById(anyInt())).thenReturn(Optional.of(rapor));

        String result = raporService.raporSil(1);

        assertEquals("Rapor başarı ile silindi", result);
        verify(raporRepository, times(1)).delete(rapor);
    }


    @Test
    void testRaporResimSorgula() {
        Rapor rapor = new Rapor();
        rapor.setResim(new byte[]{});
        when(raporRepository.findById(anyInt())).thenReturn(Optional.of(rapor));

        byte[] resim = raporService.raporResimSorgula(1);

        assertNotNull(resim);
        verify(raporRepository, times(1)).findById(1);
    }


    @Test
    void testListeleEnYeni() {
        Rapor rapor1 = new Rapor();
        rapor1.setVerildigiTarih(new Date(2024,Calendar.APRIL,10));
        Rapor rapor2 = new Rapor();
        rapor2.setVerildigiTarih(new Date(2024,Calendar.APRIL,15));
        when(raporRepository.findAll()).thenReturn(Arrays.asList(rapor1, rapor2));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Uru", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.listeleEnYeni();

        assertEquals(2, result.size());
        verify(raporRepository, times(1)).findAll();
    }


    @Test
    void testListeleEnEski() {
        Rapor rapor1 = new Rapor();
        rapor1.setVerildigiTarih(new Date(2024,Calendar.APRIL,14));
        Rapor rapor2 = new Rapor();
        rapor2.setVerildigiTarih(new Date(2024,Calendar.AUGUST,14));
        when(raporRepository.findAll()).thenReturn(Arrays.asList(rapor1, rapor2));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Uru", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.listeleEnEski();

        assertEquals(2, result.size());
        verify(raporRepository, times(1)).findAll();
    }


    @Test
    void testTumRaporlarListele() {
        Rapor rapor = new Rapor();
        when(raporRepository.findAll()).thenReturn(Arrays.asList(rapor));
        when(raporMapper.toRaporResponse(any(Rapor.class))).thenReturn(new RaporResponse(
                "1231-2", "Ahmet", "Yılmaz", "25874125896", "Beyin Uru", "Hipotalamusta beyin tümörü",
                new Date(2024,Calendar.APRIL,10),
                "Mehmet", "Bayram", "2341564567", 1));

        List<RaporResponse> result = raporService.tumRaporlarListele();

        assertEquals(1, result.size());
        verify(raporRepository, times(1)).findAll();
    }



}