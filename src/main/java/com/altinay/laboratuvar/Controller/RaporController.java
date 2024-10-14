package com.altinay.laboratuvar.Controller;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rapor;
import com.altinay.laboratuvar.Repository.RaporRepository;
import org.springframework.http.HttpStatus;
import java.util.List;

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

    @GetMapping("hastaAd")
    public ResponseEntity<List<Rapor>> listeleByHastaAdi(){
        return null;
    }
}
