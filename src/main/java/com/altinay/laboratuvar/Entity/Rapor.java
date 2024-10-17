package com.altinay.laboratuvar.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rapor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String dosyaNumarasi;

    private String hastaAd;
    private String hastaSoyad;

    private String tc;

    private String tani;

    private String taniDetay;

    private Date verildigiTarih = new Date();

    @Lob
    private byte[] resim;

    @ManyToOne
    private Laborant laborant;

}
