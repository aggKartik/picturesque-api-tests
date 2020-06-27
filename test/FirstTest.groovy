import groovy.json.JsonBuilder
import org.junit.BeforeClass
import org.junit.Test

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

class FirstTest extends BaseTest {


    @BeforeClass
    static void setup() {
        setUp()
    }

    @Test
    void testPostPerson() {
        String now = new Date().getTime().toString()
        Map<String, Object> personDetails = ["name" : "John Doe",
                                             "userName": "testingdoe$now",
                                             "pass": "password123",
                                             "token": 12345,
                                             "dob": "1990-10-12"]

        JsonBuilder json = new JsonBuilder(personDetails)
        String message = given().header(CONTENT_TYPE).and().body(json.toString())
                .when().post("/v1/person").then().statusCode(200).and()
                .extract().jsonPath().get("response.message").toString()

        assertThat(message, equalTo("User testingdoe$now added successfully!"))


    }


}
