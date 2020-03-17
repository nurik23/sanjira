package kg.sanjyra.controller;

import kg.sanjyra.model.Podrod;
import kg.sanjyra.model.Rod;
import kg.sanjyra.repository.PodrodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/podrod")
public class PodrodRestController {
    @Autowired
    private PodrodRepository podrodRepository;


    @GetMapping("/byRodId/{rodId}")
    public List<Podrod> getPodrodListByRodName(@PathVariable int rodId) {
        return podrodRepository.findPodrodsByRodId(rodId);
    }
}
