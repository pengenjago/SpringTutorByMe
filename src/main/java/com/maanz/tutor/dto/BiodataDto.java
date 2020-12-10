package com.maanz.tutor.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.maanz.tutor.model.Handphone;

import lombok.Data;

@Data
public class BiodataDto {
	private Integer id;

	private String nama;
	private String jenisKelamin;
	private Date tglLahir;
	private String alamat;

	private List<HandphoneDto> hp = new ArrayList<>();
	
}
