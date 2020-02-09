package com.example.kyrgyzpedigree;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persons_db";
    private RestTemplate restTemplate;
    private String serverUrl = "http://192.168.0.105:8083";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        }
        return restTemplate;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setContentEncoding(ContentCodingType.ALL);
        return requestHeaders;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Person.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Person.TABLE_NAME);
        onCreate(db);
    }

    public void insertPerson(Person person) {
        getRestTemplate().postForEntity(serverUrl + "/person/",
                new HttpEntity<>(person, getHeaders()),
                ResponseEntity.class);

    }

    public List<Person> getAllPersons() {
        ResponseEntity<List> allPersonsResponseEntity = getRestTemplate().getForEntity(serverUrl + "/person/all", List.class);
        return (List<Person>) allPersonsResponseEntity.getBody();
    }

    public List<Person> getAllPersonsSanjyra(String podrod) {
        ResponseEntity<List> allPersonsResponseEntity = getRestTemplate().getForEntity(serverUrl + "/person/byPodrod/" + podrod, List.class);
        return (List<Person>) allPersonsResponseEntity.getBody().stream().map(map -> new Person((Map<String, Object>) map)
        ).collect(Collectors.toList());

    }

    public void deletePerson(int id) {
        getRestTemplate().delete(serverUrl + "/person/" + id);
    }

}
