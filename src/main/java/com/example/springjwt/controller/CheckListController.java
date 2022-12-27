package com.example.springjwt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjwt.dto.CheckListDto;
import com.example.springjwt.entities.CheckList;
import com.example.springjwt.service.CheckListService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/checklist")
public class CheckListController {
    
    private final CheckListService checkListService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCheckList(@RequestBody CheckListDto checkListDto) {
        checkListService.createCheckList(checkListDto);
        return "Create CheckList Succesfull";
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckList> findAll(){
        return checkListService.findAllCheckList();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String removeOne(@PathVariable("id") Long id){
        checkListService.removeById(id);
        return "Deleted";
    }
}
