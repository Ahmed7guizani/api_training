package fr.esiea.ex4A.pickup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    public final String userId;
    public final String email;
    public final String name;
    public final String twitter;
    public final String country;
    public final String sex;
    public final String sexPref;

    public UserInfo(@JsonProperty("userEmail") String email, @JsonProperty("userName") String name, @JsonProperty("userTweeter") String twitter, @JsonProperty("userCountry") String country, @JsonProperty("userSex") String sex, @JsonProperty("userSexPref") String sexPref) {
        this.email = email;
        this.name = name;
        this.twitter = twitter;
        this.country = country;
        this.sex = sex;
        this.sexPref = sexPref;
        this.userId = name.concat(country);
    }

    public UserInfo(String name, String twitter) {
        this.email = "";
        this.country = "";
        this.sex = "";
        this.sexPref = "";
        this.name = name;
        this.twitter = twitter;
        this.userId = name.concat(country);
    }

    @Override
    public String toString() {
        return "{" +
            "\"userEmail\":\"" + email + '"' +
            ",\"userName\":\"" + name + '"' +
            ",\"userTweeter\":\"" + twitter + '"' +
            ",\"userCountry\":\"" + country + '"' +
            ",\"userSex\":\"" + sex + '"' +
            ",\"userSexPref\":\"" + sexPref + '"' +
            '}';
    }

    public String getUserId(){ return this.userId; }

    public String getEmail() { return this.email; }

    public String getName() {
        return name;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getCountry() {
        return country;
    }

    public String getSex() {
        return sex;
    }

    public String getSexPref() {
        return sexPref;
    }
}
