package kg.sanjyra.controller;

import kg.sanjyra.model.Person;
import kg.sanjyra.model.Podrod;
import kg.sanjyra.repository.PersonRepository;
import kg.sanjyra.repository.PodrodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/person")
public class PersonRestController {
    private PersonRepository personRepository;
    private PodrodRepository podrodRepository;
    @Autowired
    public PersonRestController(PersonRepository personRepository, PodrodRepository podrodRepository) {
        this.personRepository = personRepository;
        this.podrodRepository = podrodRepository;
    }

    @PostMapping
    public ResponseEntity savePerson(HttpEntity<Person> httpEntity) {
        Person person = httpEntity.getBody();
        int podrodId = Integer.parseInt(httpEntity.getHeaders().get("podrodId").get(0));
        Podrod podrodById = podrodRepository.findById(podrodId).orElse(new Podrod());
        person.setPodrod(podrodById);
        personRepository.save(person);
        sendEmail(person, personRepository.findAllByPodrodId(podrodById.getId()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePerson(@RequestParam Person person) {
        personRepository.save(person);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable int id) {
        personRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personRepository.findById(id).orElse(new Person());
    }

    @GetMapping("/byPodrodId/{podrodId}")
    public ResponseEntity<List> getPersonListByPodrod(@PathVariable int podrodId) {
        return new ResponseEntity<>(personRepository.findAllByPodrodId(podrodId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List> getPersonList() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    private void sendEmail(Person registeredPerson, List<Person> receivers) {
        final String username = "sanjyra.kyrgyzfamile@bk.ru";
        final String password = "Karpinka23";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.mail.ru");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        receivers.forEach(receiver -> {
            String theme = "Санжыра";
            String description = "В вашем роду " + registeredPerson.getPodrod() + " зарегистрировался " + registeredPerson.getName()  + "   " +  registeredPerson.getGodrojdeniya()  + " года рождения !  ";
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(receiver.getEmail())
                );
                message.setSubject(theme, "utf-8");
                message.setText(description, "utf-8");
                Transport.send(message);
                System.out.println("Sent to: " + receiver.getEmail());

            } catch (SendFailedException e) {
                System.out.println("Failed to send to: " + receiver.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });


    }
}

