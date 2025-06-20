package com.pm.patient_service.config;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    PatientResponseDTO toDto(Patient patient);

    List<PatientResponseDTO> toDtoList(List<Patient> patients);

    @Mappings({
            @Mapping(source = "dateOfBirth", target = "dateOfBirth", qualifiedByName = "stringToLocalDate"),
            @Mapping(source = "registeredDate", target = "registeredDate", qualifiedByName = "stringToLocalDate")
    })
    Patient toEntity(PatientRequestDTO dto);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    // Optional: reverse for string → LocalDate
    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date); // ISO format
    }


}
