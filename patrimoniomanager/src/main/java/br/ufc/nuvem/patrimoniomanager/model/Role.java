package br.ufc.nuvem.patrimoniomanager.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
    ROOT, USER;

    public GrantedAuthority getGrantedAuthority() {
        return new PatrimonioGrantedAuthority(this.name());
    }

    public static class PatrimonioGrantedAuthority implements GrantedAuthority {
        private final String authority;

        public PatrimonioGrantedAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}
