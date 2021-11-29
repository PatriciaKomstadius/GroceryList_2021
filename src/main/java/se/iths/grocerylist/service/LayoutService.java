package se.iths.grocerylist.service;

import org.springframework.stereotype.Service;
import se.iths.grocerylist.repository.LayoutRepository;

@Service
public class LayoutService {

    private final LayoutRepository layoutRepository;

    public LayoutService(LayoutRepository layoutRepository){
        this.layoutRepository = layoutRepository;
    }
}
