package com.altinay.laboratuvar;

import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Repository.LaborantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final LaborantRepository laborantRepository;

    public DataLoader(PasswordEncoder passwordEncoder , LaborantRepository laborantRepository){
        this.laborantRepository = laborantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Laborant laborant = new Laborant();
        laborant.setAd("Ahmet");
        laborant.setSoyad("Yılmaz");
        laborant.setHastaneKimlikNo("12565753");
        laborant.setPassword(passwordEncoder.encode("password"));

        laborantRepository.save(laborant);

    }
}
