package com.fromthebasement.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jeffginn on 8/30/14.
 */
public class HTLController {
    @ExceptionHandler( Exception.class )
    public
    @ResponseBody
    ResponseEntity<Map> handleException( Exception ex )
    {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        HashMap<String,String> hashMap = new LinkedHashMap<>();
        hashMap.put( "message", ex.getLocalizedMessage() );

        return new ResponseEntity<Map>( hashMap, httpStatus );
    }

}
