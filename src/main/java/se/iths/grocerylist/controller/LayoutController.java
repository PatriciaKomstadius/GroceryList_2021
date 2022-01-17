package se.iths.grocerylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.grocerylist.entity.GroceryListEntity;
import se.iths.grocerylist.entity.LayoutEntity;
import se.iths.grocerylist.exception.BadRequestException;
import se.iths.grocerylist.exception.EntityNotFoundException;
import se.iths.grocerylist.mapper.LayoutMapper;
import se.iths.grocerylist.model.GroceryListModel;
import se.iths.grocerylist.model.LayoutModel;
import se.iths.grocerylist.service.LayoutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("layouts")
public class LayoutController {

    private final LayoutService layoutService;
    private final LayoutMapper layoutMapper;

    public LayoutController(LayoutService layoutService, LayoutMapper layoutMapper) {
        this.layoutService = layoutService;
        this.layoutMapper = layoutMapper;
    }

    @PostMapping()
    public ResponseEntity<LayoutModel> createLayout(@RequestBody LayoutModel layout) {

        if (layout.getType() == null || layout.getType().isEmpty()) {
            throw new BadRequestException("Layout type can not be empty.");
        }

        LayoutEntity createdLayout = layoutService.createLayout(layoutMapper.layoutModelToLayoutEntity(layout));
        LayoutModel response = layoutMapper.layoutEntityToLayoutModel(createdLayout);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<LayoutModel> findLayoutById(@PathVariable Long id) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if (foundLayout.isEmpty()) {
            throw new EntityNotFoundException("Layout with id " + id + " doesn't exist.");
        }

        LayoutModel response = layoutMapper.layoutEntityToLayoutModel(foundLayout.get());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<Iterable<LayoutModel>> findAllLayouts() {

        Iterable<LayoutEntity> findAllLayouts = layoutService.findAllLayouts();
        List<LayoutEntity> foundLayouts = new ArrayList<>();

        for (LayoutEntity layout : findAllLayouts) {
            foundLayouts.add(layout);
        }
        if (foundLayouts.isEmpty()) {
            throw new EntityNotFoundException("No layouts exists.");
        }
        Iterable<LayoutModel> allLayoutModels = layoutMapper.allEntityToAllModels(foundLayouts);

        return new ResponseEntity<>(allLayoutModels, HttpStatus.FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<LayoutModel> updateType(@PathVariable Long id, @RequestBody LayoutModel type) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if (foundLayout.isEmpty()) {
            throw new EntityNotFoundException("Layout with id " + id + " doesn't exist in database.");
        }
        if (type.getType() == null || type.getType().isEmpty()) {
            throw new BadRequestException("Type can not be empty.");
        }

        Optional<LayoutEntity> updatedLayout = layoutService.updateTypeOfLayout(id, type.getType());

        LayoutModel response = layoutMapper.layoutEntityToLayoutModel(updatedLayout.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<LayoutEntity> deleteLayout(@PathVariable Long id) {

        Optional<LayoutEntity> foundLayout = layoutService.findLayoutById(id);

        if (foundLayout.isEmpty()) {
            throw new EntityNotFoundException("Layout with id " + id + "doens't exist in databse. Couldn't fulfill the request.");
        }

        layoutService.deleteLayout(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
