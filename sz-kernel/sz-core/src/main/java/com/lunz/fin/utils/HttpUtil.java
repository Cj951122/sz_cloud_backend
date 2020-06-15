package com.lunz.fin.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    private static final long   serialVersionUID = -292336323378065582L;
    private static final String CHAR_SET         = "UTF-8";
    private static final String CONTENT_TYPE     = "application/json";

    public JSONObject doPostRequestJSON(String requestUrl, JSONObject json) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);
        JSONObject response = null;
        try {
            StringEntity string = new StringEntity(json.toString(),CHAR_SET);
            String strJson=json.toString();
            string.setContentEncoding(CHAR_SET);
            string.setContentType(CONTENT_TYPE);
            post.setEntity(string);
            HttpResponse res = client.execute(post);
            System.err.println("http status is " + res.getStatusLine().getStatusCode());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                //UTF-8解码返回数据
                String result = EntityUtils.toString(entity,CHAR_SET);
                response = JSONObject.parseObject(result);
                response.put("success",true);
            }else{

                HttpEntity entity = res.getEntity();
                //UTF-8解码返回数据
                String result = EntityUtils.toString(entity,CHAR_SET);
                response = JSONObject.parseObject(result);
                response.put("success",false);
            }
        }
        catch (Exception e) {
            response = new JSONObject();
            this.SetError(response,e);
            e.printStackTrace();
        }
        return response;
    }

    private void SetError(JSONObject response,Exception e){

        response.put("success",false);
        response.put("status",500);
        response.put("error","-1");
        response.put("message",String.format("系统异常：%s %n %s",e.getMessage(),e.getStackTrace()));
    }
}
