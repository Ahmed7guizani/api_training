package fr.esiea.ex4A.pickup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

class AgifyClientTest {
    private final AgifyClient agifyClient;

    AgifyClientTest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.agify.io")
            .addConverterFactory(JacksonConverterFactory.create()).build();
        this.agifyClient = retrofit.create(AgifyClient.class);
    }

    @ParameterizedTest
    @CsvSource({
        "cris, PR, 69",
        "ahmed, TN, 30",
        "filepo, IT, 48",
        "ramirez, VE, 65"
    })
    void getAgeFromNames(String userName, String userCountry, int expectedAge){
        Call<AgifyUser> call = this.agifyClient.getUserAge(userName, userCountry);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<AgifyUser> call, Response<AgifyUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AgifyUser agifyUser = response.body();
                    getAge(agifyUser, expectedAge);
                }
            }

            @Override
            public void onFailure(Call<AgifyUser> call, Throwable throwable) {
                System.out.println("Error on API");
            }
        });
    }

    private void getAge(AgifyUser agifyUser, int expectedAge){
        Assertions.assertThat(agifyUser.getAge()).isEqualTo(expectedAge);
    }
}
