package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

public class SessionController {
    @Autowired
    private SessionRepository SessionRepository;

    @GetMapping
    @RequestMapping("/api/v1/sessions")
    public List<Session> list() {return SessionRepository.findAll();}

    @GetMapping()
    @RequestMapping("/api/v1/sessions/{id}")
    public List<Session> listId(@PathVariable Long id){
       return SessionRepository.findAllById(Collections.singleton(id));
    }

    //DELETE Method
    @GetMapping
    @RequestMapping(value = "/api/v1/sessions/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        SessionRepository.deleteById(id);
    }

    //CREATE Method
    @PostMapping("/api/v1/sessions/create")
    public Session create(@RequestBody final Session Session){
        return SessionRepository.saveAndFlush(Session);
    }

    //UPDATE Method
    @RequestMapping(value = "/api/v1/sessions/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody Session Session){
        Session existingSession = SessionRepository.getReferenceById(id);
        BeanUtils.copyProperties(Session, existingSession,"session_id");
        SessionRepository.save(existingSession);
    }
}
