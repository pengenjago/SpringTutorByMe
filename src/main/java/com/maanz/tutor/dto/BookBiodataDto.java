package com.maanz.tutor.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookBiodataDto {
	private Integer id;
	private String judul;
	private String terbitan;
	private List<BiodataDto> biodata;
}
