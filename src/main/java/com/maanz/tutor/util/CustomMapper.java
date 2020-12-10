package com.maanz.tutor.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import java.lang.reflect.Type;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomMapper{
	ModelMapper modelMapper = new ModelMapper();

	public <D> D map(Object sourceEntity, Class<D> destinationType) throws CustomException {
		try {
			return modelMapper.map(sourceEntity, destinationType);
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping exception : " + e.getMessage());
		}
	}

	public <D> D map(Object sourceEntity, Type destinationType) throws CustomException {
		try {
			return modelMapper.map(sourceEntity, destinationType);
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping exception : " + e.getMessage());
		}
	}

	public <D, T> Page<D> map(Page<T> entities, Class<D> dtoClass) throws CustomException {
		try {
			return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping exception : " + e.getMessage());
		}
	}
}
