/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Mnzit
 */
@Controller
@RequestMapping("/admin")
public class DashBoardController {

    @GetMapping
    @ResponseBody
    public String index() {
        return "<h1>Admin Area</h1>";
    }
}
