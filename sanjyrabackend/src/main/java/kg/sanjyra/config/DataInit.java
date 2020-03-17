package kg.sanjyra.config;

import kg.sanjyra.model.Podrod;
import kg.sanjyra.model.Rod;
import kg.sanjyra.repository.DbUtil;
import kg.sanjyra.repository.PersonRepository;
import kg.sanjyra.repository.PodrodRepository;
import kg.sanjyra.repository.RodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInit {
    private PersonRepository personRepository;
    private RodRepository rodRepository;
    private PodrodRepository podrodRepository;

    @Autowired
    private DbUtil dbUtil;

    @Autowired
    public DataInit(PersonRepository personRepository, RodRepository rodRepository, PodrodRepository podrodRepository) {
        this.personRepository = personRepository;
        this.rodRepository = rodRepository;
        this.podrodRepository = podrodRepository;
    }

    @PostConstruct
    public void init() {
        long count = rodRepository.count();
        if (count > 0) {
            return;
        }
        dbUtil.setTablesCharsetUtf8();
        List<Rod> rodList = Arrays.asList("Саруу", "Кушчу", "Карабагыш", "Мундуз", "Чонбагыш", "Кытай", "Жетиген", "Басыз"
                , "Карачоро", "Саяк", "Монолдор", "Сарыбагыш", "Солто"
                , "Бугу", "Азык", "Монгуш", "Черик", "Багыш", "Адыгине", "Жедигер", "Ават", "Бостон", "Кыдыршаа", "Доолос"
                , "Найман", "Канды", "Кесек", "Жоокесек", "Каратейит"
                , "Нойгут", "Кыпчак").stream().map(Rod::new).collect(Collectors.toList());
        String[][] podrodArray = {{"Ачакей", "Чокон", "Ажыбек", "Тен-Терт"},
                {"Коткар", "Кушчу2"},
                {"Карабагыш1", "Карабагыш2", "Карабагыш3"}};
        int i = 0;
        for (; i < podrodArray.length; i++) {
            List<Podrod> podrodList = new ArrayList<>();
            for (int j = 0; j < podrodArray[i].length; j++) {
                Podrod podrod = new Podrod(podrodArray[i][j], rodList.get(i));
                podrodList.add(podrod);
            }
            Rod rod = rodList.get(i);
            rod.setPodrodList(podrodList);
            rodList.set(i, rod);
        }
        rodRepository.saveAll(rodList);
    }
}
