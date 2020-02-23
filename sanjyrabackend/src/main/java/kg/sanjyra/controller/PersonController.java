package kg.sanjyra.controller;

import kg.sanjyra.model.Person;
import kg.sanjyra.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;

import java.util.List;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity savePerson(HttpEntity<Person> httpEntity) {
        Person person = httpEntity.getBody();
        sendEmail(person, personService.getPersonListByPodrod(person.getPodrod()));
        personService.savePerson(person);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePerson(@RequestParam Person person) {
        personService.updatePerson(person);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/byPodrod/{podrod}")
    public ResponseEntity<List> getPersonListByPodrod(@PathVariable String podrod) {
        return new ResponseEntity<>(personService.getPersonListByPodrod(podrod), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List> getPersonList() {
        return new ResponseEntity<>(personService.getPersonList(), HttpStatus.OK);
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

