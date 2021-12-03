package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.entity.LayoutEntity;
import se.iths.grocerylist.repository.LayoutRepository;

import java.util.Optional;

@Service
public class LayoutService {

    private final LayoutRepository layoutRepository;

    public LayoutService(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    //Create
    public LayoutEntity createLayout(LayoutEntity layout) {
        return layoutRepository.save(layout);
    }

    //Read id
    public Optional<LayoutEntity> findLayoutById(Long id) {
        return layoutRepository.findById(id);
    }

    //Read all
    public Iterable<LayoutEntity> findAllLayouts(){
        return layoutRepository.findAll();
    }

    //Update type
    public Optional<LayoutEntity> updateTypeOfLayout(Long id, String type){

      Optional<LayoutEntity> foundLayout = layoutRepository.findById(id);

      foundLayout.get().setType(type);
      layoutRepository.save(foundLayout.get());

      return foundLayout;
    }

    //Delete
    public Optional<LayoutEntity> deleteLayout(Long id){

        Optional<LayoutEntity> deleteLayout = layoutRepository.findById(id);

        layoutRepository.deleteById(deleteLayout.get().getId());
        return deleteLayout;
    }
}
