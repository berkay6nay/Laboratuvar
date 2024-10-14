package com.altinay.laboratuvar.Repository;
import com.altinay.laboratuvar.Entity.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborantRepository extends JpaRepository<Laborant, Integer> {
    Laborant findByhastaneKimlikNo(String kimlikNo);
}
