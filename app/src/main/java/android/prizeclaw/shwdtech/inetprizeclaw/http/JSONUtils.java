package android.prizeclaw.shwdtech.inetprizeclaw.http;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bb on 16/9/12.
 */
public class JSONUtils {
    /**
     * 解析一组monitoryPointBean
     * @param json
     * @return
     */
/*    public static ArrayList<MonitoryPointBean> parseMonitoryPointBeanList(String json) {
        ArrayList<MonitoryPointBean> data = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return data;
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object =  jsonArray.getJSONObject(i);
                MonitoryPointBean monitoryPointBean = parseMonitoryPointBean(object);
                if (monitoryPointBean != null) {
                    data.add(monitoryPointBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }*/
}
