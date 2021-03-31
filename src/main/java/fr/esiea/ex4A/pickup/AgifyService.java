package fr.esiea.ex4A.pickup;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AgifyService {

    public final AgifyClient agifyClient;
    public final PickupRepository pickupRepository = new PickupRepository();

    public AgifyService(AgifyClient agifyClient) {
        this.agifyClient = agifyClient;
    }

    public void addUser(UserInfo user) throws IOException {
        AgifyUser agifyUser;
        if(!pickupRepository.seeIfUserExists(user)){
            agifyUser = this.agifyClient.getUserAge(user.name, user.country).execute().body();
            pickupRepository.addNewUser(user, agifyUser);
        }
    }

    public ArrayList<UserInfo> matchUser(String userName, String userCountry){
        UserInfo userRequestingMatch = pickupRepository.getUser(userName, userCountry);
        if(userRequestingMatch != null){
            return pickupRepository.matchUser(userRequestingMatch);
        } else {
            return new ArrayList<>();
        }
    }
}
