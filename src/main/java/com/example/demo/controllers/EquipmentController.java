package com.example.demo.controllers;

import com.example.demo.models.Equipment;
import com.example.demo.repositories.EquipmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping
    public List<Equipment> list () {
        return equipmentRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Equipment get(@PathVariable Long id) {
        if (equipmentRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment with ID "+id+" not found");
        }
        return equipmentRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment create(@RequestBody final Equipment equipment) {
        return equipmentRepository.saveAndFlush(equipment);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s’il faut
        // les enregistrements enfants
        equipmentRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Equipment update(@PathVariable Long id, @RequestBody Equipment equipment) {
        // TO DO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon , retourner une erreur 400 (Bad Payload )
        Equipment existingEquipment = equipmentRepository.getById(id);
        BeanUtils.copyProperties(equipment,existingEquipment ,"equipment_id");
        return equipmentRepository.saveAndFlush(existingEquipment);
    }

}
