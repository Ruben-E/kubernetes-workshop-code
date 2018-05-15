package nl.openvalue.workshops.kubernetes.userserviceproxy.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ProxyController {

    @Value("${app.user.service.host}")
    private String userServiceHost;

    @Value("${app.user.service.port}")
    private int userServicePort;

    @RequestMapping(value = "/**", produces = "application/json")
    @ResponseBody
    public String forwardRequest() throws URISyntaxException
    {
        URI uri = new URI("http", null, userServiceHost, userServicePort, "/users", null, null);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity.getBody();
    }
}
