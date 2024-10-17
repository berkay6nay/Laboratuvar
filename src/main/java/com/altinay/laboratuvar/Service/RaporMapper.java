package com.altinay.laboratuvar.Service;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.RaporResponse;
import org.springframework.stereotype.Service;

@Service
public class RaporMapper {
    public RaporResponse toRaporResponse(Rapor rapor){
        return new RaporResponse(
          rapor.getDosyaNumarasi(),
          rapor.getHastaAd(),
          rapor.getHastaSoyad(),
          rapor.getTc(),
          rapor.getTani(),
          rapor.getTaniDetay(),
          rapor.getVerildigiTarih(),
          rapor.getLaborant().getAd(),
          rapor.getLaborant().getSoyad(),
          rapor.getLaborant().getHastaneKimlikNo(),
          rapor.getId()
        );
    }
}
