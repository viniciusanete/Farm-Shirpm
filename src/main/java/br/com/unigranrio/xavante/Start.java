package br.com.unigranrio.xavante;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//	public FilterRegistrationBean filtroJwt() {
//		FilterRegistrationBean frb = new FilterRegistrationBean();
//		frb.setFilter(new TokenFilter());
//		frb.addUrlPatterns("/active/*");
//		return frb;
//	}
	
	public static void main(String[] args) throws SQLException {
		
		SpringApplication.run(Start.class, args);
		

		

	}

}
