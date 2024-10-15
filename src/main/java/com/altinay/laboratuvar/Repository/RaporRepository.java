package com.altinay.laboratuvar.Repository;
import com.altinay.laboratuvar.Entity.Rapor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaporRepository extends JpaRepository<Rapor, Integer>{
    List<Rapor> findByhastaAdAndHastaSoyad(String hastaAd , String hastaSoyad);
    List<Rapor> findByTc(String tc);

    @Query("SELECT r FROM Rapor r WHERE r.laborant.ad = :ad AND r.laborant.soyad = :soyad")
    List<Rapor> findByLaborantAdAndSoyad(@Param("ad") String ad, @Param("soyad") String soyad);

}

