/*package com.example.MSUser.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Cette méthode configure les règles CORS pour ton application
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applique cette configuration à toutes les URL de l'application
                .allowedOriginPatterns("*")  // Autorise toutes les origines
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permet toutes les méthodes HTTP courantes
                .allowedHeaders("*")  // Permet tous les en-têtes HTTP
                .allowCredentials(true)  // Permet l'envoi de cookies dans les requêtes CORS (si nécessaire)
                .maxAge(3600);  // La durée pendant laquelle les résultats de la requête CORS peuvent être mis en cache (en secondes)
    }

    // Méthode pour configurer d'autres aspects de Spring MVC si nécessaire
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Cette configuration affecte toutes les URL
                        .allowedOrigins("*")  // Autorise toutes les origines
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Autorise toutes les méthodes HTTP
                        .allowedHeaders("*")  // Autorise tous les en-têtes HTTP
                        .allowCredentials(true);  // Permet l'envoi de cookies
            }
        };
    }
}*/

