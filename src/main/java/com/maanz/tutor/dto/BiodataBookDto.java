package com.maanz.tutor.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BiodataBookDto {
	private Integer id;

	private String nama;
	private String jenisKelamin;
	private Date tglLahir;
	private String alamat;

	private List<HandphoneDto> hp = new ArrayList<>();
	private List<BookDto> book = new ArrayList<>();
}
