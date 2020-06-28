
import groovy.json.JsonSlurper
import io.restassured.http.Header
import org.json.JSONObject

class BaseTest {

    protected static final Header CONTENT_TYPE = new Header("Content-type", "application/json; charset=utf-8")
    protected static String endpoint
    protected static int port

    static JSONObject getServiceDetails() {
        def fileLocation = new File(new File("").getAbsolutePath() + File.separator + 'configuration.json')
        return (JSONObject) new JsonSlurper().parse(fileLocation)
    }

    static void setUp(){
        // Add setup here as needed

        // Things like authentication

        JSONObject serviceDetails = getServiceDetails().getJSONObject("profile")
        endpoint = serviceDetails.getString("endpoint")
        port = serviceDetails.getInt("port")

    }


}
