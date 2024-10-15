package com.altinay.laboratuvar.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class KayitForm {
    private String kimlikNo;
    private String password;
    private String isim;
    private String soyisim;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Laborant toLaborant(PasswordEncoder passwordEncoder){
        Laborant laborant = new Laborant();
        laborant.setAd(isim);
        laborant.setSoyad(soyisim);
        laborant.setPassword(passwordEncoder.encode(password));
        laborant.setHastaneKimlikNo(kimlikNo);
        laborant.setRol(rol);
        return laborant;
    }
}
