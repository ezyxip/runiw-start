package com.ezyxip.runiwstart.system;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

public class PrimaryServiceConnector implements ServiceConnector{
    static Logger logger = Logger.getLogger(PrimaryServiceConnector.class.getName());

    protected String login;
    protected String password;

    @Value("${runiw.address.center}")
    public String url;

    public PrimaryServiceConnector(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void moduleLogin() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(login, password);
        restTemplate.exchange(url + "/authtest", HttpMethod.GET, new HttpEntity<>(headers), String.class);
    }

    @Override
    public String userLogin() {

        return null;
    }

    @Override
    public boolean isUserAuth(String token) {
        return false;
    }
}
