package br.com.unigranrio.xavante.configurer;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import br.com.unigranrio.xavante.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class TokenFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  req = (HttpServletRequest) request;
		String header = req.getHeader("Authorization");
		
		
		if(header == null || !header.startsWith("Bearer"))
			throw new ServletException("Token Inexistente");
		
		String token = header.substring(7);
		//TODO atribuir uma palavra passe mais forte
		try {
			 Jwts.parser().setSigningKey("teste").parseClaimsJws(token).getBody();
		}catch (SignatureException e) {
			throw new ServletException("token invalido");
		}
		chain.doFilter(request, response);
		
	}
		
	

	public static String gerarToken(Usuario  usuario) {
		 
		Map<String, Object> claims = new HashMap<>();
		 claims.put("permissao", usuario.getPerfil());
		 claims.put("ususario", usuario.getId());
		 
			try{
				//TODO melhorar essa chave.. depois trocar no token Filter		
				String token = Jwts.builder()
								.signWith(SignatureAlgorithm.HS256, "teste")
								.setExpiration(new Date(System.currentTimeMillis()+ 60 * 60000))
								.setClaims(claims)
								.compact();	
				return token;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
