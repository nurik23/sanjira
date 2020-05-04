package kg.sanjyra.util.startPersonFilling;

import kg.sanjyra.model.Person;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonFillUtil {
    public static List<Person> generatePersonList() throws IOException {
        List<PersonDto> allPersonDtoList = new ArrayList<>();
        List<String> placesList = new ArrayList<>();
        List<String> mailList = Arrays.asList("@mail.ru", "@inbox.ru", "@bk.ru", "@gmail.com", "@yandex.ru", "@yahoo.com");
        List<PersonDto> malePersonDtoList = new ArrayList<>();
        List<PersonDto> femalePersonDtoList = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(PersonFillUtil.class.getClassLoader().getResourceAsStream("places.txt")))) {
            do {
                line = bufferedReader.readLine();
                placesList.add(line);
            }
            while (bufferedReader.ready());
        }
        PersonDto personDto = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(PersonFillUtil.class.getClassLoader().getResourceAsStream("names.txt")))) {
            do {
                line = bufferedReader.readLine();
                if (line.isEmpty() || line.length() < 5) continue;
                if (line.length() == 10) {
                    personDto.setBirthDate(line);
                    personDto.setPlace(placesList.get((int) (Math.random() * placesList.size())));
                    String[] fioPart = personDto.getFio().split(" ");
                    personDto.setMail(RussianToEnglishParser.getParsedWord(fioPart[(int) (Math.random() * fioPart.length)]) + mailList.get((int) (Math.random() * mailList.size())));
                    if (personDto.isFemale()) {
                        femalePersonDtoList.add(personDto);
                    } else {
                        malePersonDtoList.add(personDto);
                    }
                } else {
                    personDto = new PersonDto(line);
                    String[] fio = line.split(" ");
                    if (fio[0].endsWith("А") ||fio[0].endsWith("а") || line.contains("КЫЗЫ")|| line.contains("кызы") || (fio.length > 2 && fio[2].endsWith("А"))) {
                        personDto.setFemale(true);
                    }
                }
            }
            while (bufferedReader.ready());
        }
        malePersonDtoList.forEach(System.out::println);
        System.out.println("male size : " + malePersonDtoList.size());
        System.out.println("female size : " + femalePersonDtoList.size());
        for (int i = 0; i < malePersonDtoList.size(); i++) {
            personDto = malePersonDtoList.get(i);
            int randomIndex;
            do {
                randomIndex = (int) (Math.random() * malePersonDtoList.size());
            } while (randomIndex == i);
            String dadName = malePersonDtoList.get(randomIndex).getFio();
            do {
                randomIndex = (int) (Math.random() * malePersonDtoList.size());
            } while (randomIndex == i);
            String momName = femalePersonDtoList.get(randomIndex).getFio();
            personDto.setDadName(dadName);
            personDto.setMomName(momName);
            allPersonDtoList.add(personDto);
        }
        for (int i = 0; i < femalePersonDtoList.size(); i++) {
            personDto = femalePersonDtoList.get(i);
            int randomIndex;
            do {
                randomIndex = (int) (Math.random() * malePersonDtoList.size());
            } while (randomIndex == i);
            String dadName = malePersonDtoList.get(randomIndex).getFio();
            do {
                randomIndex = (int) (Math.random() * malePersonDtoList.size());
            } while (randomIndex == i);
            String momName = femalePersonDtoList.get(randomIndex).getFio();
            personDto.setDadName(dadName);
            personDto.setMomName(momName);
            allPersonDtoList.add(personDto);
        }
        List<Person> resultList = new ArrayList<>();
        allPersonDtoList.forEach(personDto1 -> {
            Person person = new Person();
            person.setName(personDto1.getFio());
            person.setNamedad(personDto1.getDadName());
            person.setNamemom(personDto1.getMomName());
            person.setEmail(personDto1.getMail());
            person.setGodrojdeniya(personDto1.getBirthDate());
            person.setMestojitelstva(personDto1.getPlace());
            resultList.add(person);
        });
        return resultList;
    }
}
