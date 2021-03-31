package fr.esiea.ex4A.pickup;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PickupRepository {
    final HashMap<String, UserInfo> userList = new HashMap<>();
    final HashMap<String, AgifyUser> userListWithAge = new HashMap<>();

    int getNumberOfUsers(){
        return this.userList.size();
    }

    void addNewUser(UserInfo userInfo, AgifyUser userDataWithAge){
        this.userList.put(userInfo.getUserId(), userInfo);
        this.userListWithAge.put(userInfo.getUserId(), userDataWithAge);
    }

    boolean seeIfUserExists(UserInfo userInfo){
        return this.userList.containsKey(userInfo.getUserId());
    }

    UserInfo getUser(String userName, String countryId){
        return this.userList.get(userName.concat(countryId));
    }

    ArrayList<UserInfo> matchUser(UserInfo userInfo){
        ArrayList<UserInfo> matchingUsers = new ArrayList<>();
        for(Map.Entry<String, AgifyUser> userEntry : this.userListWithAge.entrySet()){
            if(Math.abs(userEntry.getValue().getAge() - this.userListWithAge.get(userInfo.getUserId()).getAge()) <= 4){
                UserInfo possiblyMatchingUser = this.userList.get(userEntry.getValue().getUserId());
                if(possiblyMatchingUser.getSex().equals(userInfo.getSexPref()) && userInfo.getSex().equals(possiblyMatchingUser.getSexPref())) {
                    matchingUsers.add(this.userList.get(userEntry.getValue().getUserId()));
                }
            }
        }
        return matchingUsers;
    }
}
