package com.personal.basefuntionlibrary.Web;

import android.content.Context;

import com.personal.basefuntionlibrary.Common.BaseFunction;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 与服务器交互的基础类
 * Created by 杨培韬 on 2016/10/21.
 */

public class BaseWebClient {
    /**
     * 设置网络连接最大时间不超过5s
     */
    private static final int TIME_WEB_LINK = 5 * 1000;

    private BaseWebClient() {
    }

    public static ResponseEntity<String> getResponse(Context context, String url, String methodName, HttpMethod method, JSONObject request) {
        ResponseEntity<String> response = null;
        if (BaseFunction.isNetWorkConnected(context)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Connection", "Close");
            headers.setContentType(MediaType.APPLICATION_JSON);

            SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            clientHttpRequestFactory.setConnectTimeout(TIME_WEB_LINK);

            String str = formatString(request.toString());
            HttpEntity<String> entity = new HttpEntity<>(str, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.setRequestFactory(clientHttpRequestFactory);

            try {
                response = restTemplate.exchange(url + methodName, method, entity, String.class);
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    public static ResponseEntity<String> getResponse(Context context, String url, String methodName, MultiValueMap<String, Object> parts) {
        ResponseEntity<String> response = null;
        if (BaseFunction.isNetWorkConnected(context)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new SourceHttpMessageConverter<>());
            messageConverters.add(new StringHttpMessageConverter());

            RestTemplate template = new RestTemplate();
            template.setMessageConverters(messageConverters);

            try {
                response = template.exchange(url + methodName, HttpMethod.POST, requestEntity, String.class);
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    private static String formatString(String str) {
        try {
            byte[] requestByte = str.getBytes();
            return new String(requestByte, org.apache.http.protocol.HTTP.ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

}
