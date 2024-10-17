package com.altinay.laboratuvar;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Entity.Rol;
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
        laborant.setRol(Rol.USER);

        Laborant laborant2 = new Laborant();
        laborant2.setAd("Mehmet");
        laborant2.setSoyad("Kaya");
        laborant2.setHastaneKimlikNo("8693257481");
        laborant2.setPassword(passwordEncoder.encode("password"));
        laborant2.setRol(Rol.ADMIN);

        laborantRepository.save(laborant);
        laborantRepository.save(laborant2);
    }
}
