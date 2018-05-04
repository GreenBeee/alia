package com.botscrew.facebook.controller;

import com.botscrew.facebook.model.incomming.UserInfo;
import com.botscrew.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class AuthorizeController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${facebook.token}")
    private String TOKEN;

    @Value("${facebook.account_linking.userinfo.url}")
    private String getUserInfoUrl;

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ModelAndView getAuthPage(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "redirect_uri") String redirectUri,
                                    @RequestParam(value = "account_linking_token") String linkingToken)
    {
        ModelAndView modelAndView = new ModelAndView("authorization");
        modelAndView.addObject("uri", redirectUri);
        modelAndView.addObject("token", linkingToken);
        return modelAndView;
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public void postAuthPage(HttpServletResponse response,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("uri") String uri,
                               @RequestParam("token") String linkingToken) {

        String url = String.format(getUserInfoUrl, TOKEN, linkingToken);
        UserInfo info = restTemplate.getForObject(url, UserInfo.class);

        Optional<String> code = userService.saveLoginAndPasswordAndGetAuthCode(username, password, info.getUserId());

        try {
            if (code.isPresent()) {
                response.sendRedirect(uri + "&authorization_code=" + code);
            }
            else {
                response.sendRedirect(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
