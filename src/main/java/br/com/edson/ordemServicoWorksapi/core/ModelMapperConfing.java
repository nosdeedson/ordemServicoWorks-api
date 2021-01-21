package br.com.edson.ordemServicoWorksapi.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfing {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
