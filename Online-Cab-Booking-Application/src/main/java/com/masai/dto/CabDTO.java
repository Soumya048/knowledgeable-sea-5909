package com.masai.dto;

import com.masai.model.CabType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabDTO {

	private CabType cabtype;
	private Integer cabTypeCount;
	
}
