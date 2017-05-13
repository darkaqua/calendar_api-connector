import net.darkaqua.apiconnector.ApiConnector;
import net.darkaqua.apiconnector.Request;
import org.json.JSONObject;

/**
 * Created by Pablo on 22/04/17.
 */
public class Example {

    private static final String CLIENT_ID = "b471a17e3371f3d223b390f108bb989f3dc7d3f432cfbff9f9c29ac80e10bb97";
    private static final String CLIENT_TOKEN = "5538fcbc29f7066ba02d5c024d3e3f4de49a0526f50f9b224325693d5472b851";

    public static void main(String[] args) throws Exception{    
        ApiConnector apiConnector = new ApiConnector("calendar.darkaqua.net", 8080);
        apiConnector.auth(CLIENT_ID, CLIENT_TOKEN);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_uuid", "asd");

        apiConnector.GET("user", jsonObject, new Request() {

            public void Response(Object object) {
                System.out.println(object.toString());
            }

        });
    }

}
