package com.altinay.laboratuvar.Entity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class KayitForm {
    private String kimlikNo;
    private String password;
    private String isim;
    private String soyisim;

    public Laborant toLaborant(PasswordEncoder passwordEncoder){
        Laborant laborant = new Laborant();
        laborant.setAd(isim);
        laborant.setSoyad(soyisim);
        laborant.setPassword(passwordEncoder.encode(password));
        laborant.setHastaneKimlikNo(kimlikNo);
        return laborant;
    }
}
