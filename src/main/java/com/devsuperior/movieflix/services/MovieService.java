package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieSimpleDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		
		 Movie entity = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found!"));
		 
		 return new MovieDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsByMovie(Long id) {

		Movie entity = movieRepository.findWithFetch(id);
		
		if( entity == null ) {
			throw new ResourceNotFoundException("Entity Not Found!");
		}

		List<Review> list = entity.getReviews();

		return list.stream().map(r -> new ReviewDTO(r)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<MovieSimpleDTO> findAllPaged(Long genreId, Pageable pageable){
		
		Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title").ascending());
		genreId = genreId == 0L ? null : genreId;
		Page<Movie> page = movieRepository.find(genreId, paging);
		
		return page.map(m -> new MovieSimpleDTO(m));
	}
	
	


}
