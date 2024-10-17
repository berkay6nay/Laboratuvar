package com.altinay.laboratuvar.Service;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.RaporResponse;
import com.altinay.laboratuvar.Repository.RaporRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaporService {
    private final RaporRepository raporRepository;
    private final RaporMapper raporMapper;
    private final ResimService resimService;

    public RaporService(RaporRepository raporRepository, RaporMapper raporMapper, ResimService resimService) {
        this.raporRepository = raporRepository;
        this.raporMapper = raporMapper;
        this.resimService = resimService;
    }

    public RaporResponse raporTanimla(Rapor rapor, Laborant laborant) {
        rapor.setLaborant(laborant);
        byte[] resim = resimService.resimYarat(rapor);
        rapor.setResim(resim);
        raporRepository.save(rapor);
        return raporMapper.toRaporResponse(rapor);
    }

    public List<RaporResponse> listeleByHastaAdi(String hastaAdi, String hastaSoyad) {
        return raporRepository.findByhastaAdAndHastaSoyad(hastaAdi, hastaSoyad).
                stream()
                .map(raporMapper::toRaporResponse)
                .toList();
    }

    public List<RaporResponse> listeleByHastaTc(String hastaTc) {
        return raporRepository.findByTc(hastaTc).stream().map(raporMapper::toRaporResponse).toList();
    }

    public List<RaporResponse> listeleByLaborantAdSoyad(String laborantAd, String laborantSoyad) {
        return raporRepository.findByLaborantAdAndSoyad(laborantAd, laborantSoyad).stream().map(raporMapper::toRaporResponse).toList();
    }

    public ResponseEntity<RaporResponse> guncelleRapor(Rapor yRapor, Integer raporId, Laborant laborant) {
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if (rapor.isPresent()) {
            if (rapor.get().getLaborant().getId().equals(laborant.getId())) {
                Rapor Rapor = rapor.get();
                Rapor.setLaborant(yRapor.getLaborant());
                Rapor.setDosyaNumarasi(yRapor.getDosyaNumarasi());
                Rapor.setTani(yRapor.getTani());
                Rapor.setHastaAd(yRapor.getHastaAd());
                Rapor.setHastaSoyad(yRapor.getHastaSoyad());
                Rapor.setTc(yRapor.getTc());
                Rapor.setTaniDetay(yRapor.getTaniDetay());
                Rapor.setVerildigiTarih(yRapor.getVerildigiTarih());
                Rapor.setLaborant(laborant);
                byte[] yeniResim = resimService.resimYarat(Rapor);
                Rapor.setResim(yeniResim);
                raporRepository.save(Rapor);
                return ResponseEntity.ok(raporMapper.toRaporResponse(Rapor));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> raporSil(Integer raporId){
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if(rapor.isPresent()){
            raporRepository.delete(rapor.get());
            return ResponseEntity.ok("Rapor başarı ile silindi");
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<byte[]> raporResimSorgula(Integer raporId){
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if(rapor.isPresent()){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE , "image/png").body(rapor.get().getResim());
        }
        else return ResponseEntity.notFound().build();
    }

    public List<RaporResponse> listeleEnYeni(){
        List<Rapor> raporlar = raporRepository.findAll();
        raporlar.sort((a,b) -> b.getVerildigiTarih().compareTo(a.getVerildigiTarih()));
        return raporlar.stream().map(raporMapper::toRaporResponse).toList();
    }
    public List<RaporResponse> listeleEnEski(){
        List<Rapor> raporlar = raporRepository.findAll();
        raporlar.sort((a,b) -> a.getVerildigiTarih().compareTo(b.getVerildigiTarih()));
        return raporlar.stream().map(raporMapper::toRaporResponse).toList();
    }

    public List<RaporResponse> tumRaporlarListele(){
        List<Rapor> raporlar = raporRepository.findAll();
        return raporlar.stream().map(raporMapper::toRaporResponse).toList();
    }
}