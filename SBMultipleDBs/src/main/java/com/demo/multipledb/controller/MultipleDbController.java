package com.demo.multipledb.controller;

import com.demo.multipledb.service.MultipleDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multipledb")
public class MultipleDbController {

    private final MultipleDbService multipleDbService;

    public MultipleDbController(MultipleDbService multipleDbService) {
        this.multipleDbService = multipleDbService;
    }

    @GetMapping("/saveInDb1PostgreSql")
    public ResponseEntity<String> saveInDb1PostgreSql() {
        multipleDbService.saveInDB1PostgreSql();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/saveInDb2MySql")
    public ResponseEntity<String> saveInDb2MySql() {
        multipleDbService.saveInDB2MySql();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
