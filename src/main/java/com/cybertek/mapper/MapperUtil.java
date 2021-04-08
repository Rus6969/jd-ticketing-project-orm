package com.cybertek.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    private ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // T is generic we do not know return type so we using casting to Type meaning we will  cast to any data type
//    public <T> T convertToEntity(Object objectToBeConverted, T convertedObject) {
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//
//    }
//
//    public <T> T convertToDto(Object objectToBeConverted, T convertedObject) {
//
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }

    // T is generic we do not know return type so we using casting to Type meaning we will  cast to any data type
    public <T> T convert(Object objectToBeConverted, T convertedObject) {

        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
    }

    // example of using one generic Mapper class will be in userService implementatiton class
}
