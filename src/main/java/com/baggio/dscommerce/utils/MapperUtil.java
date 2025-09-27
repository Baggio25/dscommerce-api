package com.baggio.dscommerce.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Converte um objeto para outro tipo
    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    // Converte lista de objetos - Page
    public <S, T> Page<T> mapList(Page<S> page, Class<T> targetClass) {
        return page
                .map(element -> modelMapper.map(element, targetClass));
    }
    
    // Converte lista de objetos - List
    public <S, T> List<T> mapList(List<S> list, Class<T> targetClass) {
        return list
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());

    }

}
