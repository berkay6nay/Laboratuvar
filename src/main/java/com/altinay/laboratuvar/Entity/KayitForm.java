package com.altinay.laboratuvar.Entity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class KayitForm {
    private String kimlikNo;
    private String password;
    private String isim;
    private String soyisim;
    private String rol;

    public Laborant toLaborant(PasswordEncoder passwordEncoder) throws IllegalArgumentException{
        Laborant laborant = new Laborant();
        laborant.setAd(isim);
        laborant.setSoyad(soyisim);
        laborant.setPassword(passwordEncoder.encode(password));
        if(kimlikNo.matches("[0-9]+") && kimlikNo.length() == 10){
            laborant.setHastaneKimlikNo(kimlikNo);
            if(Rol.gecerliMi(rol)){
                Rol enumRol = Rol.valueOf(rol);
                laborant.setRol(enumRol);
                return laborant;
            }
            throw new IllegalArgumentException("Lütfen gecerli bir rol secin('ADMIN' ya da 'USER')");
        }
        else throw new IllegalArgumentException("Hastane Numarası 10 haneli ve nümerik olmalı.");

    }
}
