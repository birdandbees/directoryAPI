package com.jing.Service;

/**
 * Created by jingjing on 8/2/15.
 */

import com.jing.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {
    @Autowired
    private RedisTemplate<String, String> template;

    private final RedisAtomicLong userIdCounter;

    @Inject
    public PeopleService(StringRedisTemplate template) {
        this.template = template;


       userIdCounter = new RedisAtomicLong("uid", template.getConnectionFactory());
    }

    public String addPeople(Person person)
    {
        String uid = String.valueOf(userIdCounter.incrementAndGet());
        template.opsForValue().set(KeyHelper.getUser(uid), person.getName());
        template.opsForList().leftPushAll(KeyHelper.getUser(uid), person.getAddresses());
        return uid;

    }

    public Person getPerson(String key)
    {
        String existingRecord = (String) template.opsForValue().get(KeyHelper.getUser(key));
        if (existingRecord == null) {
            return null;
        }
        Person person = new Person();
        person.setName(existingRecord);
        List<String> addresses = new ArrayList<String>();
        addresses = template.boundListOps(KeyHelper.getAddress(key)).range(0, template.boundListOps(KeyHelper.getAddress(key)).size());
        person.setAddresses(addresses);
        return person;

    };


    public String updatePerson(String key, Person person)
    {

        String existingRecord = (String) template.opsForValue().get(KeyHelper.getUser(key));
        if (existingRecord == null) {
            return null;
        }
        template.opsForValue().set(KeyHelper.getUser(key), person.getName());
        template.boundListOps(KeyHelper.getAddress(key)).trim(0, template.boundListOps(KeyHelper.getAddress(key)).size());
        template.opsForList().leftPushAll(KeyHelper.getUser(key), person.getAddresses());

        return key;

    }
    public String removePerson(String key)
    {
        String existingRecord = (String) template.opsForValue().get(KeyHelper.getUser(key));
        if (existingRecord == null) {
            return null;
        }
        template.delete(KeyHelper.getUser(key));
        template.delete(KeyHelper.getAddress(key));
        return key;
    }
}
