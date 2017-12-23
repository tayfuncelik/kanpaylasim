package com.kan.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MyWebConfig extends WebMvcConfigurerAdapter
{
  private Hibernate4Module hibernate4Module()
  {
    return new Hibernate4Module().disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
  }

  private MappingJackson2HttpMessageConverter jacksonMessageConverter()
  {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
      .featuresToDisable(new Object[] { SerializationFeature.WRITE_DATES_AS_TIMESTAMPS })
      .modulesToInstall(new Module[] { 
      hibernate4Module() });
    return new MappingJackson2HttpMessageConverter(builder.build());
  }

  public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
  {
    converters.add(jacksonMessageConverter());
    super.configureMessageConverters(converters);
  }
}