package com.altinay.laboratuvar.Controller;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Repository.RaporRepository;
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

    public RaporController(RaporRepository raporRepository) {
        this.raporRepository = raporRepository;
    }

    @PostMapping("/tanimla")
    @ResponseStatus(HttpStatus.CREATED)
    public Rapor raporTanimla(@RequestBody Rapor rapor, @AuthenticationPrincipal Laborant laborant){
        rapor.setLaborant(laborant);
        return raporRepository.save(rapor);
    }

    @GetMapping("/listele/hastaAdSoyad")
    public ResponseEntity<List<Rapor>> listeleByHastaAdi(@RequestParam String hastaAdi , @RequestParam String hastaSoyad){
        List<Rapor> raporlar = raporRepository.findByhastaAdAndHastaSoyad(hastaAdi,hastaSoyad);
        return ResponseEntity.ok(raporlar);
    }

    @GetMapping("/listele/hastaTc")
    public ResponseEntity<List<Rapor>> listeleByHastaTc(@RequestParam String hastaTc){
        List<Rapor> raporlar = raporRepository.findByTc(hastaTc);
        return ResponseEntity.ok(raporlar);
    }

    @GetMapping("/listele/laborantAdSoyad")
    public ResponseEntity<List<Rapor>> listeleByLaborantAdSoyad(@RequestParam String laborantAd , @RequestParam String laborantSoyad){
        List<Rapor> raporlar = raporRepository.findByLaborantAdAndSoyad(laborantAd , laborantSoyad);
        return ResponseEntity.ok(raporlar);
    }

    /*
    @PutMapping("/guncelleRapor")
    public ResponseEntity<Rapor> guncelleRapor(@RequestBody Rapor yRapor , @RequestParam Integer raporId){
        Optional<Rapor> rapor = raporRepository.findById(raporId);

        if(rapor.isPresent()){
               Rapor Rapor = rapor.get();
               Rapor.setLaborant(yRapor.getLaborant());
               Rapor.setDosyaNumarasi(yRapor.getDosyaNumarasi());
               Rapor.setTani(yRapor.getTani());
               Rapor.setHastaAd(yRapor.getHastaAd());
               Rapor.setHastaSoyad(yRapor.getHastaSoyad());
               Rapor.setTc(yRapor.getTc());
               Rapor.setTaniDetay(yRapor.getTaniDetay());

        }*/

    @GetMapping("/listele")
    public ResponseEntity<List<Rapor>> tumRaporlariListele(){
        List<Rapor> raporlar = raporRepository.findAll();
        return ResponseEntity.ok(raporlar);
    }

    @DeleteMapping("/sil")
    @ResponseStatus(HttpStatus.OK)
    public void raporSil(@RequestParam Integer raporId){
        raporRepository.deleteById(raporId);
    }
}
