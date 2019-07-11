package com.field.statistics.fieldservice.controllers;

import com.field.statistics.fieldservice.controllers.dto.StatisticsDto;
import com.field.statistics.fieldservice.model.Condition;
import com.field.statistics.fieldservice.services.StatisticsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldController {

    @Autowired
    private StatisticsService service;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/field-conditions", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public void addFieldConditions(@RequestBody @Valid Condition condition) {
        service.addFieldConditions(condition);
    }

    @RequestMapping(path = "/field-statistics", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    StatisticsDto getFieldStatistics() {
        return service.getStatistics();
    }
}
