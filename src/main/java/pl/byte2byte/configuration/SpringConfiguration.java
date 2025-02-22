package pl.byte2byte.configuration;

import java.util.Locale;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * The class contains some Spring Boot configuration.
 * @author Jakub Kalinowski
 *
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class SpringConfiguration implements WebMvcConfigurer {

  // 1. Methods.

  /**
   * The method set a bean with default locales.
   * @return
   */

  @Bean
  public LocaleResolver localeResolver() {
    Locale locale = new Locale("pl");
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(locale);
    return slr;
  }

  /**
   * The method gets a 'lang' parameter value from the URL address and adds it
   * into the locale interceptor object.
   * @return
   */

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  /**
   * The method adds a new interceptor with the selected locale value to the
   * registry.
   */

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}