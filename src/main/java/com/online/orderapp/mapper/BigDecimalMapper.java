package com.online.orderapp.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalMapper {

	public Double asDouble(BigDecimal value) {
		if(value == null) return null;
		return value.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	public BigDecimal asBigDecimal(Double value) {
		if (value==null) return null;
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
	}
}
