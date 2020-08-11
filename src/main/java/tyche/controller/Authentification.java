/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyche.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import tyche.exception.NotAdminException;
import tyche.message.BearerTokenMessage;
import tyche.model.JsonResp;
import tyche.model.Login;
import tyche.service.AuthentificationService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author michael
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authentification")
public class Authentification {
	

    @Autowired
    AuthentificationService authentificationService;

    @PostMapping(path = "/verifierTokenAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResp verifierTokenAdmin(@RequestBody String token) {
        JsonResp json = new JsonResp();
        try {
            Claims claims = authentificationService.verifierBearerToken(token);
            authentificationService.checkAdminToken(claims);
            json.setStatusSuccess();
        } catch (ExpiredJwtException e) {
            json.setStatusError();
            json.addError(BearerTokenMessage.sessionExpireMessage);
        } catch (MalformedJwtException e) {
        	json.setStatusError();
            json.addError(BearerTokenMessage.bearerTokenInvalide);
        } catch (NotAdminException ex) {
        	json.setStatusError();
            json.addError(ex.getMessage());
        }

        return json;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResp login(@RequestBody Login login) {
        JsonResp json = new JsonResp();
        Login loginDb = authentificationService.findLogin(login.getUsername(), login.getPassword());
        if (loginDb != null) {
            json.setStatusSuccess();
            json.setData(authentificationService.genererJwt(loginDb.getUsername(), 1000 * 60 * 60));
        } else {
        	json.setStatusError();
            json.addError("Username/password wrong.");
        }

        return json;
    }
}
