package com.jing.Controller;

/**
 * Created by jingjing on 8/2/15.
 */

import com.jing.Model.*;
import com.jing.Service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PeopleController {

    @Autowired
    private PeopleService service;

    @RequestMapping(method = RequestMethod.GET,  value="/get")
    public
    @ResponseBody
    Person getId(@RequestParam(value="id", required = true) final String id) {

        Person p = service.getPerson(id);
        return p;


    };

    @RequestMapping(method = RequestMethod.POST,  value="/add", headers = {"Content-type=application/json"})
    public
    @ResponseBody
    JsonResponse add(@RequestBody Person person) {
      String response = service.addPeople(person);
        return new JsonResponse("success", response);

    }
    @RequestMapping(method = RequestMethod.PUT,  value="/update", headers = {"Content-type=application/json"})
    public
    @ResponseBody
    JsonResponse updateId(@RequestBody Person person, @RequestParam(value="id", required = true) final String id) {
        String response = service.updatePerson(id, person);
        return new JsonResponse("success", response);

    }
    @RequestMapping(method = RequestMethod.DELETE,  value="/delete")
    public
    @ResponseBody
    JsonResponse removeId(@RequestParam(value="id", required = true) final String id) {
        String response = service.removePerson(id);
        return new JsonResponse("success", response);

    }

}
