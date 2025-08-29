package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.orderDto.OrderPlacedDto;
import com.online.orderapp.entity.OrderPlaced;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderPlacedMapper {

	
	OrderPlacedDto toDto(OrderPlaced orderPlaced);
}
