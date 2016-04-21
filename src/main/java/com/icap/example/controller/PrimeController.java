package com.icap.example.controller;

import com.google.common.collect.Sets;
import com.icap.example.calculator.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/prime")
@Api(value = "Prime", description = "Operations about Prime Numbers")
public class PrimeController {


    @Autowired
    BasicPrimeCalculator basicPrimeCalculator;

    @Autowired
    ParallelPrimeCalculator parallelPrimeCalculator;

    @Autowired
    StreamPrimeCalculator streamPrimeCalculator;

    @Autowired
    StreamParallelPrimeCalculator streamParallelPrimeCalculator;

    @RequestMapping(value = "/basic/{number}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get prime numbers till given number in basic mode")
    public ResponseEntity<Set> calculateBasic(@PathVariable Integer number) {
        return findPrime(basicPrimeCalculator,number);

    }

    @RequestMapping(value = "/parallel/{number}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get prime numbers till given number in parallel mode")
    public ResponseEntity<Set> calculateParallel(@PathVariable Integer number) {
        return findPrime(parallelPrimeCalculator, number);
    }

    @RequestMapping(value = "/stream/{number}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get prime numbers till given number in stream mode")
    public ResponseEntity<Set> calculateStream(@PathVariable Integer number) {
        return findPrime(streamPrimeCalculator, number);
    }

    @RequestMapping(value = "/stream/parallel/{number}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get prime numbers till given number in stream mode")
    public ResponseEntity<Set> calculateStreamParallel(@PathVariable Integer number) {
        return findPrime(streamParallelPrimeCalculator, number);
    }


    private ResponseEntity<Set> findPrime(PrimeCalculator primeCalculator, Integer number) {
        Set result = Sets.newHashSet();
        try {
            primeCalculator.validate(number);
            result = primeCalculator.findPrime(number);
        } catch (RuntimeException ex) {
            result.add("Please use valid numbers (number >1) ");
            return new ResponseEntity<Set>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            result.add("Error occured during calculation");
            return new ResponseEntity<Set>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Set>(result, HttpStatus.OK);
    }
}
