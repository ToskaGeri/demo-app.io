package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speakers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakersRepository extends JpaRepository<Speakers, Long> {
}
