package com.example.kafka3.interfaces;

import com.example.kafka3.payload.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> {

}
