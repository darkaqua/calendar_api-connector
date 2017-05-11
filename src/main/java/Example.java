import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;
import org.json.JSONObject;

/**
 * Created by Pablo on 22/04/17.
 */
public class Example {

    private static final String CLIENT_ID = "33e921e00d69ce18a6143285756b01d93a178db59708d2c1290d915487e3c647";
    private static final String CLIENT_TOKEN = "4e8b107d9f5ba114a5a3eaa1b2513b2b7ddd16d24e874d9205137a52eae40bab";

    public static void main(String[] args) throws Exception{    
        ApiConnector apiConnector = new ApiConnector("localhost", 22322);
        apiConnector.auth(CLIENT_ID, CLIENT_TOKEN);

        JSONObject jsonObject = new JSONObject();

        apiConnector.GET("user/companies", jsonObject, new Request() {

            public void Response(Object object) {
                System.out.println(object.toString());
            }

        });
    }

}
