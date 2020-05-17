package kg.sanjyra.config;

import kg.sanjyra.model.Person;
import kg.sanjyra.model.Podrod;
import kg.sanjyra.model.Rod;
import kg.sanjyra.repository.DbUtil;
import kg.sanjyra.repository.PersonRepository;
import kg.sanjyra.repository.PodrodRepository;
import kg.sanjyra.repository.RodRepository;
import kg.sanjyra.util.startPersonFilling.PersonFillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
        List<Rod> rodList = Arrays.asList("Кушчу", "Кытай", "Чон багыш", "Саруу", "Басыз", "Мундуз"
                , "Канды", "Доолос", "Тейит", "Найман", "Кыпчак", "Бостон", "Нойгут", "Аваат", "Кесек"
                , "Адигине", "Сарттар", "Баргы", "Жолжакшы", "Борю", "Бектен", "Мунгуш", "Тагай", "Богорстон"
                , "Койлон-Жедигер", "Кылжыр", "Саяк", "Карачоро", "Азык"
                , "Черик", "Конурат(Чертике)", "Монолдор").stream().map(Rod::new).collect(Collectors.toList());
        String[][] podrodArray = {{"Бурго", "Токтук", "Бекмурат", "Талаймамат", "Сулпаян"
                , "Сабатар", "Мамбет", "Кангелди", "Кайназар"},
                {"Чотон", "Олджобай", "Чопук", "Алак", "Байдебет", "Дос", "Каракебек"
                        , "Каракесек", "Итеш"},
                {"Кендебес", "Азыгаалы", "Мачак", "Кулун", "Тинчин", "Сарык", "Килен"},
                {"Тон торт", "Тогунай", "Конок", "Куркуроо", "Жамаке", "Тубай"},
                {"Туголбука", "Кашка", "Тилеке", "Оторгор", "Акбаш", "Кечек"},
                {"Зултай", "Зулум", "Зуркай", "Шерзар", "Калдык", "Шыгай"},
                //сол канат - Куу уул
                {"Сары канды", "Кара канды", "Алаша", "Тамга", "Туркмон", "Уйгурбулга", "Шаран"},
                {"Толмон", "Кудаш", "Чулум", "Жаныл"},
                {"Кызыл баш", "Галча", "кичи тейит", "Байтейит"},
                {"Жанбол", "Боо", "Кандыке", "Уркунчу", "Кожо", "Бобуй", "Боткочу", "Кеке"},
                {"Торайгыр", "Таз", "Кармыш", "Кожомшукур", "Омонок", "Ак кыпчак"},
                {"Кызыл уул", "Бозбала", "Жазар", "Ботманке", "Байман"},
                {"Лагман", "Чери", "Мунканай", "Бежей", "Кара моюн", "Чинатан"},
                {"Токмок", "Жой", "Чолой", "Кадыршаа", "Уста", "Гужен", "Аладаакты", "Могол"},
                {"Чуулдак", "Белек сары", "Можой", "Кэлдер", "Чопкылдак", "Чучук"},
                //ичкилик
                {"Aдигине"},//адигине
                {"Карагана", "Жакшылык", "Куртка", "Кашкатай", "Караменде", "Кадыр", "Кабылан"},
                {"Кекче", "Сатыке", "Бакай", "Сансыз", "Олджоке"},
                {"Жоору", "Кудайберды"},
                {"Казак", "Каражылкы", "Кудайкул", "Каракучкач", "Таздар", "Таранчы", "Сатар"},
                {"Баарын", "Карабагыш", "Акбагыш"},
                {"Жапалак", "Телейкен", "Мейне", "Жоош", "Соколок", "Жагалмай"},
                {"Койлон", "Кылжыр"},//Тагай
                {"Эштек", "Козор", "Солто", "Кунтуу", "Култуу", "Чаа"},
                {"Долу", "Токбай", "Бугубай", "Саякбай", "Чекир", "Сатылгын"},
                {"Сарыбагыш", "Бугу"},
                {"Каба", "Сарык"},
                {"Балта", "Багыш", "Келдике"},
                {"Кашкелен", "Козугуна", "Бычман", "Байкучук", "Бору"},
                {"Боогачы", "Куба", "Уч тамга"},
                {"Байбору", "Каргатай", "Бекен", "Жармамат"},
                {"Тогонбай", "Асан", "Адыл", "Самансур"}
                //он канат - Ак уул
        };
        
        for (int i = 0; i < podrodArray.length; i++) {
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
        List<Person> personList = null;
        try {
            personList = PersonFillUtil.generatePersonList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Podrod> podrodList = podrodRepository.findAll();
        int counter = 0;
        for (Podrod podrod : podrodList) {
            for (int j = 0; j < 4; j++, counter++) {
                Person person = personList.get(counter);
                person.setPodrod(podrod);
                person.setPodrodName(podrod.getName());
                person.setRodName(podrod.getRod().getName());
            }
        }
        while (counter < personList.size()) {
            Person person = personList.get(counter);
            Podrod podrod = podrodList.get((int) (Math.random() * podrodList.size()));
            person.setPodrod(podrod);
            person.setPodrodName(podrod.getName());
            person.setRodName(podrod.getRod().getName());
            counter++;
        }
        personRepository.saveAll(personList);
    }
}
