package com.pitayafruit.chatcc.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


import java.io.IOException;


/**
 * 单元测试
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15552558428122/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22418522242882818%22%2C%22first_id%22%3A%221870439002462-0848451c05e64b8-d545429-2359296-187043900256fd%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MDQzOTAwMjQ2Mi0wODQ4NDUxYzA1ZTY0YjgtZDU0N…S0yMzU5Mjk2LTE4NzA0MzkwMDI1NmZkIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNDE4NTIyMjQyODgyODE4In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22418522242882818%22%7D%2C%22%24device_id%22%3A%221870439002462-0848451c05e64b8-d545429-2359296-187043900256fd%22%7D; UM_distinctid=187043913e0dbf-0e6a0ecea5711f-d545429-240000-187043913e195; abtest_env=product; zsxqsessionid=bbea31bf266d5ac009284e555b2bbd51; zsxq_access_token=9580D18F-72A9-0E48-1AB5-49FF58AF74B0_2B673E30B2986F88");
        get.addHeader("Content-Type","application/json;charset=UTF-8");

        CloseableHttpResponse reponse = httpClient.execute(get);

        if(reponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(reponse.getEntity());
            System.out.println(res);
        }else{
            System.out.println(reponse.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181428148415522/answer");

        post.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22418522242882818%22%2C%22first_id%22%3A%221870439002462-0848451c05e64b8-d545429-2359296-187043900256fd%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MDQzOTAwMjQ2Mi0wODQ4NDUxYzA1ZTY0YjgtZDU0N…S0yMzU5Mjk2LTE4NzA0MzkwMDI1NmZkIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNDE4NTIyMjQyODgyODE4In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22418522242882818%22%7D%2C%22%24device_id%22%3A%221870439002462-0848451c05e64b8-d545429-2359296-187043900256fd%22%7D; UM_distinctid=187043913e0dbf-0e6a0ecea5711f-d545429-240000-187043913e195; abtest_env=product; zsxqsessionid=bbea31bf266d5ac009284e555b2bbd51; zsxq_access_token=9580D18F-72A9-0E48-1AB5-49FF58AF74B0_2B673E30B2986F88");
        post.addHeader("Content-Type","application/json;charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"看书去！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";


        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
