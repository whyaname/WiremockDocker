package com.anava.spring;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@org.springframework.stereotype.Controller
public class Controller {
    Map map;
    Object object;
    RestTemplate restTemplate = new RestTemplate();
    List<User> users = new ArrayList<>();

    @GetMapping(value = "/")
    public String displayUsers(Model model) {
        users = returnUsers();
        model.addAttribute("users", users);
        return "display";
    }

    public List<User> returnUsers() {
        object = restTemplate.getForObject("http://localhost:8080/users/", Object.class);
        assert object != null;
        map = (Map) object;
        for (Object obj : map.entrySet()) { //get the first level map
            if (obj instanceof Entry) {
                if (((Entry) obj).getValue() instanceof ArrayList) {
                    int bound = ((ArrayList) ((Entry) obj).getValue()).size();
                    for (int i = 0; i < bound; i++) { //iterate over all entries
                        User user = new User();
                        if (((ArrayList)
                                ((Entry) obj).getValue()).get(i) instanceof LinkedHashMap) {
                            for (Object entry : ((LinkedHashMap)
                                    ((ArrayList) ((Entry) obj).getValue()).get(i)).entrySet()) {
                                if (entry instanceof Entry) {
                                    //base level entries
                                    if (((Entry) entry).getKey().equals("gender")) {
                                        user.gender = (String) ((Entry) entry).getValue();
                                    } else if (((Entry) entry).getKey().equals("dob")) {
                                        var dob = (LinkedHashMap) ((Entry) entry).getValue();
                                        user.dob = (String) dob.get("date");
                                    } else if (((Entry) entry).getKey().equals("name")) {
                                        var name = (LinkedHashMap) ((Entry) entry).getValue();
                                        user.first_name = (String) name.get("first");
                                        user.last_name = (String) name.get("last");
                                    } else {
                                        var picture = (LinkedHashMap) ((Entry) entry).getValue();
                                        user.picture = (String) picture.get("medium");
                                    }
                                }
                            }
                        }
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }
}


