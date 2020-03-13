package kg.sanjyra.controller;

import kg.sanjyra.model.Rod;
import kg.sanjyra.repository.RodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rod")
public class RodRestController {

    @Autowired
    private RodRepository rodRepository;

    @GetMapping("/all")
    public List<Rod> getAllRods() {
        return rodRepository.findAll();
    }
}
