package fr.esiea.ex4A.pickup;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PickupController {
    private final AgifyService agifyService;

    public PickupController(AgifyService agifyService) { this.agifyService = agifyService; }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody UserInfo userInfo) throws IOException {
        System.out.println(userInfo);
        this.agifyService.addUser(userInfo);
    }

    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserInfo> getMatchingUsers(@RequestParam(name = "userName", required = true) String name, @RequestParam(name = "userCountry", required = true) String country) {
        List<UserInfo> userList = this.agifyService.matchUser(name, country);
        return userList;
    }
}
