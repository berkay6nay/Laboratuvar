package com.altinay.laboratuvar.Controller;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Entity.RaporResponse;
import com.altinay.laboratuvar.Repository.RaporRepository;
import com.altinay.laboratuvar.Service.RaporService;
import com.altinay.laboratuvar.Service.ResimService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rapor")
public class RaporController{

    private final RaporService raporService;

    public RaporController(RaporRepository raporRepository , ResimService resimService , RaporService raporService) {
        this.raporService = raporService;
    }

    @PostMapping("/tanimla")
    public RaporResponse raporTanimla(@RequestBody Rapor rapor, @AuthenticationPrincipal Laborant laborant){
        return raporService.raporTanimla(rapor, laborant);
    }
    @GetMapping("/listele/hastaAdSoyad/{hastaAdi}/{hastaSoyad}")
    public ResponseEntity<List<RaporResponse>> listeleByHastaAdi(@PathVariable String hastaAdi , @PathVariable String hastaSoyad){
        return ResponseEntity.ok(raporService.listeleByHastaAdi(hastaAdi , hastaSoyad));
    }
    @GetMapping("/listele/hastaTc/{hastaTc}")
    public ResponseEntity<List<RaporResponse>> listeleByHastaTc(@PathVariable String hastaTc){
        return ResponseEntity.ok(raporService.listeleByHastaTc(hastaTc));
    }
    @GetMapping("/listele/laborantAdSoyad/{laborantAd}/{laborantSoyad}")
    public ResponseEntity<List<RaporResponse>> listeleByLaborantAdSoyad(@PathVariable String laborantAd , @PathVariable String laborantSoyad){
        return ResponseEntity.ok(raporService.listeleByLaborantAdSoyad(laborantAd , laborantSoyad));
    }
    @PutMapping("/guncelle/{raporId}")
    public ResponseEntity<RaporResponse> guncelleRapor(@RequestBody Rapor yRapor , @PathVariable Integer raporId , @AuthenticationPrincipal Laborant laborant){
        RaporResponse response = raporService.guncelleRapor(yRapor, raporId, laborant);
        if(response == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/listele")
    public ResponseEntity<List<RaporResponse>> tumRaporlariListele(){
        return ResponseEntity.ok(raporService.tumRaporlarListele());
    }
    @DeleteMapping("/sil/{raporId}")
    public ResponseEntity<String> raporSil(@PathVariable Integer raporId){
         String response = raporService.raporSil(raporId);
         if(response == null ) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(response);
    }
    @GetMapping("/resim/{raporId}")
    public ResponseEntity<byte[]> raporResimSorgula(@PathVariable Integer raporId){
        byte[] response = raporService.raporResimSorgula(raporId);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE , "image/png").body(response);
    }
    @GetMapping("/listeleEnYeni")
    public ResponseEntity<List<RaporResponse>> listeleEnYeni(){
        return ResponseEntity.ok(raporService.listeleEnYeni());
    }
    @GetMapping("/listeleEnEski")
    public ResponseEntity<List<RaporResponse>> listeleEnEski(){
        return ResponseEntity.ok(raporService.listeleEnEski());
    }

    @GetMapping("sorgula/{id}")
    public ResponseEntity<RaporResponse> raporSorgula(@PathVariable Integer id){
        RaporResponse response = raporService.raporSorgula(id);
        if(response == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(response);
    }
}
