package com.maanz.tutor.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maanz.tutor.dao.BiodataDao;
import com.maanz.tutor.dto.BiodataBookDto;
import com.maanz.tutor.dto.BiodataDto;
import com.maanz.tutor.dto.ResponseApiDto;
import com.maanz.tutor.model.Biodata;
import com.maanz.tutor.util.CustomException;
import com.maanz.tutor.util.CustomMapper;

@RestController
@RequestMapping("/latihan")
public class BiodataController {
	@Autowired
	private BiodataDao dao;

	@Autowired
	private CustomMapper mapper;

	@GetMapping(value = "/biodata/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApiDto> findAll(HttpServletRequest request, BiodataDto param, Pageable pageable)
			throws CustomException {

		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id").withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Biodata exm = mapper.map(param, Biodata.class);

		Page<Biodata> bio = dao.findAll(Example.of(exm, matcher), pageable);
		Page<BiodataBookDto> dto = mapper.map(bio, BiodataBookDto.class);

		return new ResponseEntity<>(new ResponseApiDto(dto), HttpStatus.OK);
	}

	@PostMapping(value = "/biodata/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApiDto> create(HttpServletRequest request, @Valid @RequestBody BiodataDto param)
			throws CustomException {

		Biodata bio = mapper.map(param, Biodata.class);
		Biodata save = dao.save(bio);

		return new ResponseEntity<>(new ResponseApiDto(mapper.map(save, BiodataDto.class)), HttpStatus.OK);
	}

	@PutMapping(value = "/biodata/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApiDto> update(@PathVariable Integer id, HttpServletRequest request,
			@Valid @RequestBody BiodataDto param) throws CustomException {

		if (!dao.existsById(id)) {

		}
		
		Biodata data = this.findOne(id);
		data = mapper.map(param, Biodata.class);
		data.setId(id);
		
		dao.save(data);

		return new ResponseEntity<>(new ResponseApiDto(mapper.map(data, BiodataDto.class)), HttpStatus.OK);
	}

	private Biodata findOne(Integer id) throws CustomException {
		Optional<Biodata> data = dao.findById(id);

		if (!data.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, "ID Tidak Ditemukan");
		}
		
		return data.get();
	}
}
