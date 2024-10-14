package com.altinay.laboratuvar.Security;
import com.altinay.laboratuvar.Entity.Laborant;
import com.altinay.laboratuvar.Repository.LaborantRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final LaborantRepository laborantRepository;
    public MyUserDetailsService(LaborantRepository laborantRepository){
        this.laborantRepository = laborantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String kimlikNo) throws UsernameNotFoundException {
        Laborant laborant = laborantRepository.findByhastaneKimlikNo(kimlikNo);
        if(laborant == null) {
            System.out.println("Bu numaraya sahip bir laborant bulunamadı");
            throw new UsernameNotFoundException("Bu numaraya sahip bir laborant bulunamadı");
        }
        System.out.println(laborant.getUsername());
        return laborant;
    }
}
