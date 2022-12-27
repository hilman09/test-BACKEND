package com.example.springjwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springjwt.dto.CheckListDto;
import com.example.springjwt.entities.CheckList;
import com.example.springjwt.repository.CheckListRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CheckListService {
    
    private final CheckListRepository checkListRepository;

    public void createCheckList(CheckListDto checkListDto){
        var checklist = CheckList.builder()
                        .name(checkListDto.getName())
                        .build();
    
        checkListRepository.save(checklist);
    }

    public List<CheckList> findAllCheckList(){
        return checkListRepository.findAll();
    }

    public void removeById(Long id){
       checkListRepository.deleteById(id);
    }
}
