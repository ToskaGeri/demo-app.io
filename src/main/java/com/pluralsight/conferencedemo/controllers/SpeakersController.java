package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.Speakers;
import com.pluralsight.conferencedemo.repositories.SpeakersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

public class SpeakersController {
    @Autowired
    private SpeakersRepository speakersRepository;

    @GetMapping("/api/v1/speakers")
    public List<Speakers> list() {
        return speakersRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/api/v1/speakers/{id}")
    public List<Speakers> listId(@PathVariable Long id){
        return speakersRepository.findAllById(Collections.singleton(id));
    }

    //CREATE Method
    @PostMapping("/api/v1/speakers/create")
    public Speakers create(@RequestBody final Speakers speakers){
        return speakersRepository.saveAndFlush(speakers);
    }

    //DELETE Method
    @RequestMapping(value = "/api/v1/speakers/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        speakersRepository.deleteById(id);
    }

    //UPDATE Method
    @RequestMapping(value = "/api/v1/speakers/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody Speakers speakers){
        Speakers existingSpeakers = speakersRepository.getReferenceById(id);
        BeanUtils.copyProperties(speakers, existingSpeakers,"speaker_id");
        speakersRepository.save(existingSpeakers);
    }
}
