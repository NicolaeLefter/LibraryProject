package com.example.demo.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;
@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{
    private List<String> clientIds;

    public KeycloakJwtAuthenticationConverter(List<String> clientIds){
        this.clientIds = clientIds;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source)
    {
        return new JwtAuthenticationToken(source, Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source)
                .stream(), extractResourceRoles(source).stream())
                .collect(toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt)
    {
        var resourceAccess = new HashMap<>(jwt.getClaim("realm_access"));
        var resourceRoles = new ArrayList<>();




              //var resource = (Map<String, List<String>>) resourceAccess.get("roles");

              //  resource.get("roles").forEach(role -> resourceRoles.add(role));
             List<String>  resourceRoles2 = (ArrayList<String>)resourceAccess.get("roles");


        return resourceRoles2.isEmpty() ? emptySet() : resourceRoles2.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(toSet());
    }
}