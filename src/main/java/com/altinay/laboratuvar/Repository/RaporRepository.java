package com.altinay.laboratuvar.Repository;
import com.altinay.laboratuvar.Entity.Rapor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaporRepository extends JpaRepository<Rapor, Integer>{
    List<Rapor> findByhastaAd(String hastaAd);
    List<Rapor> findByhastaSoyad(String hastaSoyad);
    List<Rapor> findByTc(String tc);

}
