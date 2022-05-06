package com.example.kvjp.controller;

import com.example.kvjp.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class ResponseController {
    protected static ResponseUtil responseUtil;

    static {
        responseUtil = new ResponseUtil();
    }
}
