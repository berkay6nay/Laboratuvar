package com.altinay.laboratuvar.Controller;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Repository.RaporRepository;
import com.altinay.laboratuvar.Service.ResimService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rapor")
public class RaporController{
    private final RaporRepository raporRepository;
    private final ResimService resimService;

    public RaporController(RaporRepository raporRepository , ResimService resimService) {
        this.raporRepository = raporRepository;
        this.resimService = resimService;
    }
    @PostMapping("/tanimla")
    @ResponseStatus(HttpStatus.CREATED)
    public Rapor raporTanimla(@RequestBody Rapor rapor, @AuthenticationPrincipal Laborant laborant){
        rapor.setLaborant(laborant);
        byte[] resim = resimService.resimYarat(rapor);
        rapor.setResim(resim);
        return raporRepository.save(rapor);
    }
    @GetMapping("/listele/hastaAdSoyad/{hastaAdi}/{hastaSoyad}")
    public ResponseEntity<List<Rapor>> listeleByHastaAdi(@PathVariable String hastaAdi , @PathVariable String hastaSoyad){
        List<Rapor> raporlar = raporRepository.findByhastaAdAndHastaSoyad(hastaAdi,hastaSoyad);
        return ResponseEntity.ok(raporlar);
    }
    @GetMapping("/listele/hastaTc/{hastaTc}")
    public ResponseEntity<List<Rapor>> listeleByHastaTc(@PathVariable String hastaTc){
        List<Rapor> raporlar = raporRepository.findByTc(hastaTc);
        return ResponseEntity.ok(raporlar);
    }
    @GetMapping("/listele/laborantAdSoyad/{laborantAd}/{laborantSoyad}")
    public ResponseEntity<List<Rapor>> listeleByLaborantAdSoyad(@PathVariable String laborantAd , @PathVariable String laborantSoyad){
        List<Rapor> raporlar = raporRepository.findByLaborantAdAndSoyad(laborantAd , laborantSoyad);
        return ResponseEntity.ok(raporlar);
    }
    @PutMapping("/guncelleRapor/{raporId}")
    public ResponseEntity<Rapor> guncelleRapor(@RequestBody Rapor yRapor , @PathVariable Integer raporId , @AuthenticationPrincipal Laborant laborant){
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if(rapor.isPresent()){
            if (rapor.get().getLaborant().getId().equals(laborant.getId()) ) {
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
                return ResponseEntity.ok(Rapor);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listele")
    public ResponseEntity<List<Rapor>> tumRaporlariListele(){
        List<Rapor> raporlar = raporRepository.findAll();
        return ResponseEntity.ok(raporlar);
    }

    @DeleteMapping("/sil/{raporId}")
    public ResponseEntity<String> raporSil(@PathVariable Integer raporId){
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if(rapor.isPresent()){
            raporRepository.delete(rapor.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/resim/{raporId}")
    public ResponseEntity<byte[]> raporResimSorgula(@PathVariable Integer raporId){
        Optional<Rapor> rapor = raporRepository.findById(raporId);
        if(rapor.isPresent()){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE , "image/png").body(rapor.get().getResim());
        }
        else return ResponseEntity.notFound().build();
    }
    @GetMapping("/listeleEnYeni")
    public ResponseEntity<List<Rapor>> listeleEnYeni(){
        List<Rapor> raporlar = raporRepository.findAll();
        raporlar.sort((a,b) -> a.getVerildigiTarih().compareTo(b.getVerildigiTarih()));
        return ResponseEntity.ok(raporlar);
    }
    @GetMapping("/listeleEnEski")
    public ResponseEntity<List<Rapor>> listeleEnEski(){
        List<Rapor> raporlar = raporRepository.findAll();
        raporlar.sort((a,b) -> b.getVerildigiTarih().compareTo(a.getVerildigiTarih()));
        return ResponseEntity.ok(raporlar);
    }
}
