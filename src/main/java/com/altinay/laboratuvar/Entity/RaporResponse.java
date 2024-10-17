package com.altinay.laboratuvar.Entity;
import java.util.Date;

public record RaporResponse(
        String dosyaNumarasi,
        String hastaAd,
        String hastaSoyad,
        String tc,
        String tani,
        String taniDetay,
        Date verildigiTarih,
        String laborantAd,
        String laborantSoyad,
        String laborantKimlikNo,
        Integer id
) {
}
