package com.maanz.tutor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maanz.tutor.dao.BookDao;
import com.maanz.tutor.dto.BookBiodataDto;
import com.maanz.tutor.dto.ResponseApiDto;
import com.maanz.tutor.model.Book;
import com.maanz.tutor.util.CustomException;
import com.maanz.tutor.util.CustomMapper;

@RestController
@RequestMapping("/latihan")
public class BookController {
	@Autowired
	private CustomMapper mapper;

	@Autowired
	private BookDao dao;

	@GetMapping(value = "/book/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseApiDto> findAll(HttpServletRequest request, BookBiodataDto param, String encParams,
			Pageable pageable) throws CustomException {

		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id").withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Book exm = mapper.map(param, Book.class);

		Page<Book> bio = dao.findAll(Example.of(exm, matcher), pageable);
		Page<BookBiodataDto> dto = mapper.map(bio, BookBiodataDto.class);

		return new ResponseEntity<>(new ResponseApiDto(dto), HttpStatus.OK);
	}

}
