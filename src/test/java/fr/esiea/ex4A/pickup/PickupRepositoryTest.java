package fr.esiea.ex4A.pickup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



class PickupRepositoryTest {
    private final PickupRepository pickupRepository = new PickupRepository();

    @Test
    void having_no_users_should_return_no_users(){
        Assertions.assertThat(pickupRepository.getNumberOfUsers()).isEqualTo(0);
    }

    @Test
    void adding_a_user_to_repository_increments_number_of_users(){
        UserInfo userInfo = new UserInfo("test", "test", "test", "test", "test", "test");
        AgifyUser agifyUser = new AgifyUser("test", 50, 500, "US");
        pickupRepository.addNewUser(userInfo, agifyUser);
        Assertions.assertThat(pickupRepository.getNumberOfUsers()).isEqualTo(1);
    }

    @Test
    void adding_multiple_users_should_increment_the_number_of_users(){
        UserInfo userData1 = new UserInfo("t1", "te1", "tes1", "test1", "test1", "test1");
        UserInfo userData2 = new UserInfo("t2", "te2", "tes2", "test2", "test2", "test2");
        UserInfo userData3 = new UserInfo("t3", "te3", "tes3", "test3", "test3", "test3");
        UserInfo userData4 = new UserInfo("t4", "te4", "tes4", "test4", "test4", "test4");
        AgifyUser agifyUser1 = new AgifyUser("t", 50, 500, "FR");
        AgifyUser agifyUser2 = new AgifyUser("te", 52, 500, "DE");
        AgifyUser agifyUser3 = new AgifyUser("tes", 53, 500, "GB");
        AgifyUser agifyUser4 = new AgifyUser("test", 54, 500, "ES");
        pickupRepository.addNewUser(userData1, agifyUser1);
        pickupRepository.addNewUser(userData2, agifyUser2);
        pickupRepository.addNewUser(userData3, agifyUser3);
        pickupRepository.addNewUser(userData4, agifyUser4);
        Assertions.assertThat(pickupRepository.getNumberOfUsers()).isEqualTo(4);
        Assertions.assertThat(4).isEqualTo(4);
    }
}
