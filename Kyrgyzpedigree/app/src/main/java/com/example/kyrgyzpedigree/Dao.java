package com.example.kyrgyzpedigree;

import android.os.StrictMode;

import com.example.kyrgyzpedigree.models.Podrod;
import com.example.kyrgyzpedigree.models.Rod;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dao {

    private static Dao dao;
    private RestTemplate restTemplate;
    //    private String serverUrl = "http://18.219.37.92:8083";
    private String serverUrl = "http://192.168.43.182:8083";
    private List<Rod> rodList;
    private Map<String, Rod> rodNameToRodMap = new HashMap<>();
    private Map<String, Podrod> podrodNameToPodrodMap = new HashMap<>();

    private Dao() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static Dao getInstance() {
        if (dao == null) {
            dao = new Dao();
        }
        return dao;
    }


    private RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }
        return restTemplate;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setContentEncoding(ContentCodingType.ALL);
        return requestHeaders;
    }

    public void savePerson(Person person, String podrodName) {
        HttpHeaders headers = getHeaders();
        headers.add("podrodId", String.valueOf(dao.getPodrodNameToPodrodMap().get(podrodName).getId()));
        getRestTemplate().postForEntity(serverUrl + "/person/",
                new HttpEntity<>(person, headers),
                ResponseEntity.class);

    }

    public List<Person> getAllPersons() {
        ResponseEntity<List> allPersonsResponseEntity = getRestTemplate().getForEntity(serverUrl + "/person/all", List.class);
        return (List<Person>) allPersonsResponseEntity.getBody();
    }

    public List<Person> getPersonListByPodrodId(int podrodId) {
        ResponseEntity<List> allPersonsResponseEntity = getRestTemplate().getForEntity(serverUrl + "/person/byPodrodId/" + podrodId, List.class);
        return (List<Person>) allPersonsResponseEntity.getBody().stream().map(map -> new Person((Map<String, Object>) map)).collect(Collectors.toList());

    }

    public List<Rod> getRodList() throws ResourceAccessException {
        if (rodList == null) {
            ResponseEntity<List> rodListResponseEntity = getRestTemplate().getForEntity(serverUrl + "/rod/all", List.class);
            List<Map<String, Object>> rodListMap = rodListResponseEntity.getBody();
            rodList = new ArrayList<>();
            for (Map<String, Object> element : rodListMap) {
                int id = (int) element.get("id");
                String name = (String) element.get("name");
                List<Podrod> podrodList = new ArrayList<>();
                List<Map<String, Object>> podrodListMap = (List<Map<String, Object>>) element.get("podrodList");
                for (Map<String, Object> stringObjectMap : podrodListMap) {
                    int podrodId = (int) stringObjectMap.get("id");
                    String podrodName = (String) stringObjectMap.get("name");
                    podrodList.add(new Podrod(podrodId, podrodName));
                }
                rodList.add(new Rod(id, name, podrodList));
            }
            for (Rod rod : rodList) {
                rodNameToRodMap.put(rod.getName(), rod);
                for (Podrod podrod : rod.getPodrodList()) {
                    podrodNameToPodrodMap.put(podrod.getName(), podrod);
                }
            }
        }
        return rodList;
    }

    public List<Podrod> getPodrodListByRodId(int rodId) {
        ResponseEntity<List> allPersonsResponseEntity = getRestTemplate().getForEntity(serverUrl + "/podrod/byRodId/" + rodId, List.class);
        return (List<Podrod>) allPersonsResponseEntity.getBody();
    }

    public void deletePerson(int id) {
        getRestTemplate().delete(serverUrl + "/person/" + id);
    }

    public Map<String, Rod> getRodNameToRodMap() {
        return rodNameToRodMap;
    }

    public Map<String, Podrod> getPodrodNameToPodrodMap() {
        return podrodNameToPodrodMap;
    }
}
