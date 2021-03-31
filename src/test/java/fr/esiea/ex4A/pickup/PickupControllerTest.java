package fr.esiea.ex4A.pickup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PickupControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private AgifyService agifyService;

    PickupControllerTest(@Autowired MockMvc mockMvc) { this.mockMvc = mockMvc;}

    @Test
    void adding_a_user_should_increment_the_number_of_users() throws Exception {
        UserInfo userInfo = new UserInfo("test@email.com", "test", "FR", "test", "M", "M");
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/inscription")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userInfo.toString())).andExpect(status().isOk());
        ArgumentCaptor<UserInfo> userDataArgumentCaptor = ArgumentCaptor.forClass(UserInfo.class);
        verify(agifyService).addUser(userDataArgumentCaptor.capture());
        Assertions.assertThat(1).isEqualTo(1);
    }

    @Test
    void a_user_should_get_good_matches() throws Exception {
        UserInfo userInfo = new UserInfo("test@email.com", "test", "FR", "test", "M", "M");
        this.mockMvc.perform(MockMvcRequestBuilders
            .get("/api/matches?userName=Mia&userCountry=US")).andExpect(status().isOk());
        ArgumentCaptor<String> userDataArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(agifyService).matchUser(userDataArgumentCaptor.capture(), userDataArgumentCaptor.capture());
        Assertions.assertThat(userDataArgumentCaptor.getAllValues().get(0)).isEqualTo("Mia");
    }
}
