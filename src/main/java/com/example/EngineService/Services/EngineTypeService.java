package com.example.EngineService.Services;

import com.example.EngineService.DTOs.EngineTypeDto;
import com.example.EngineService.Domain.EngineType;
import com.example.EngineService.Exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EngineTypeService {

    private static HashMap<String, EngineType> engineTypes = new HashMap<String, EngineType>();

    public List<EngineTypeDto> getAllEngineType(){
        return engineTypes.values().stream().map(engineType -> entityToDto(engineType)).collect(Collectors.toList());
    }

    public EngineTypeDto entityToDto(EngineType engineType){
        EngineTypeDto result = new EngineTypeDto();
        result.setCode(engineType.getCode());
        result.setDescription(engineType.getDescription());
        return result;
    }

    public EngineTypeDto getEngineType(String originalCode){
        validateEngineTypeCodeExist(originalCode);
        return entityToDto(engineTypes.get(originalCode));
    }

    public void validateEngineTypeCodeExist(String engineTypeCode){
        if(!engineTypes.containsKey(engineTypeCode))
            throw new EntityNotFoundException(String.format("Doesn't exist an Engine Type with code %s",engineTypeCode));
    }
    public EngineTypeDto createEngineType(EngineTypeDto engineTypeDto){
        EngineType newEngineType = dtoToEntity(engineTypeDto);
        engineTypes.put(newEngineType.getCode(),newEngineType);
        return engineTypeDto;
    }

    public EngineType dtoToEntity(EngineTypeDto engineTypeDto){
        EngineType engineType = new EngineType(engineTypeDto.getCode());
        engineType.setDescription(engineTypeDto.getDescription());
        return engineType;
    }

    public EngineTypeDto modifyEngineType(String originalCode, EngineTypeDto engineTypeDto){
        validateEngineTypeCodeExist(originalCode);
        EngineType removedEngine = engineTypes.remove(originalCode);

        EngineType engineType = dtoToEntity(engineTypeDto);
        engineTypes.put(removedEngine.getCode(), engineType);

        return engineTypeDto;
    }

    public EngineTypeDto deleteEngineType(String codeToDelete){
        validateEngineTypeCodeExist(codeToDelete);

        EngineType removedEngine = engineTypes.remove(codeToDelete);
        return entityToDto(removedEngine);
    }

    public void deleteAllEngineTypes(){
        engineTypes.clear();
    }
}
