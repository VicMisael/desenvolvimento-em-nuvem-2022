package br.ufc.nuvem.patrimoniomanager.configuration.security;

import br.ufc.nuvem.patrimoniomanager.model.Role;
import org.springframework.security.core.context.SecurityContext;

public class SecurityUtils {
    public static boolean containsAuthority(SecurityContext context, Role role) {
        //contains garantido pela implementação do equals no caso do PatrimonioGranthedAuthority
        return context.getAuthentication().getAuthorities()
                .contains(new Role.PatrimonioGrantedAuthority(role.name()));
    }
}
