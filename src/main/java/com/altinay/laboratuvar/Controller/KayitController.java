package com.altinay.laboratuvar.Controller;
import com.altinay.laboratuvar.Entity.KayitForm;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Repository.LaborantRepository;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kayitOl")
public class KayitController {
    private PasswordEncoder passwordEncoder;
    private LaborantRepository laborantRepository;

    public KayitController(PasswordEncoder passwordEncoder, LaborantRepository laborantRepository) {
        this.laborantRepository = laborantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> kayitOl(@RequestBody KayitForm form) {
        try {
            Laborant laborant = form.toLaborant(passwordEncoder);
            laborantRepository.save(laborant);
            return ResponseEntity.ok("Laborant basari ile kayit edildi");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bu hastane numarası ile halihazırda kayıt mevcut.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kayıt sırasında bir hata oluştu");
        }
    }
}


