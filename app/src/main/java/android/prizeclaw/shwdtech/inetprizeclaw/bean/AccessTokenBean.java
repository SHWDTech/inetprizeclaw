package android.prizeclaw.shwdtech.inetprizeclaw.bean;

/**
 * Created by autyan on 6/27/2017.
 */

public class AccessTokenBean {

    public class Data {
        private String accessToken;

        private String expireTime;

        public String getAccessToken() {return accessToken;}

        public void setAccessToken(String token) {this.accessToken = token;}

        public String getExpireTime() {return expireTime;}

        public void setExpireTime(String time) {this.expireTime = time;}
    }
    private Data data;

    private String code;

    private String msg;

    public Data getData() {return data;}

    public void setData(Data data) {this.data = data;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getMsg() {return msg;}

    public void setMsg(String msg) {this.msg = msg;}
}
