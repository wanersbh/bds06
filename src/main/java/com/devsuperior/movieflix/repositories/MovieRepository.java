package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query("SELECT m FROM Movie m WHERE (:genreId IS NULL OR m.genre.id = :genreId)")
	Page<Movie> find(Long genreId, Pageable pageable);
	
	@Query("SELECT obj FROM Movie obj JOIN FETCH obj.reviews WHERE obj.id = :id")
	Movie findWithFetch(Long id);

}
