package com.tahmidu.notes_web_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tahmidu.notes_web_app.constant.JWTEnum;
import com.tahmidu.notes_web_app.constant.SecurityConstants;
import com.tahmidu.notes_web_app.model.FakeUser;
import com.tahmidu.notes_web_app.model.Note;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.repository.IUserRepository;
import com.tahmidu.notes_web_app.repository.IVerificationTokenRepository;
import com.tahmidu.notes_web_app.util.JWTUtil;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AuthControllerIT {

    @Autowired private MockMvc mockMvc;

    @Autowired private IUserRepository userRepository;
    @Autowired private IVerificationTokenRepository verificationTokenRepository;

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){

        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(userRepository);

        userRepository.save(new User("Tahmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev@gmail.com", passwordEncoder.encode("HellO!123"), true));
        
    }

    @Test
    public void givenCorrectCredentials_whenLogin_thenReturnRefreshTokenAccessTokenAndStatusOK() throws Exception {

        // Given
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("email", "tahmid.uddin.dev@gmail.com");
        jsonBody.put("password", "HellO!123");

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jsonBody)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String actualRefreshToken = objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.refresh_token"), new TypeReference<>() {});

        String actualAccessToken = objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.access_token"), new TypeReference<>() {});

        Assertions.assertNotEquals(actualAccessToken, actualRefreshToken);
        Assertions.assertNotNull(actualAccessToken);
        Assertions.assertNotNull(actualRefreshToken);

    }

    @Test
    public void givenInCorrectCredentials_whenLogin_thenReturnStatusForbidden() throws Exception {

        // Given
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("email", "tahmid.uddin.dev@gmail.com");
        jsonBody.put("password", "HellO!12");

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jsonBody)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String failedMsg = result.getResponse().getHeader(SecurityConstants.HEADER_AUTH_FAIL);

        Assertions.assertNotNull(failedMsg);
    }

    @Test
    public void givenValidRefreshToken_whenRefreshAccessToken_thenReturnRefreshTokenAndAccessTokenAndStatusOK()
            throws Exception {

        // Given
        String headerName = "Authorization";
        String headerContent = JWTUtil.generateToken("tahmid.uddin.dev@gmail.com", JWTEnum.REFRESH_TOKEN);

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/refresh-token")
                .header(headerName, headerContent))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertNotNull(objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.refresh_token"), new TypeReference<>() {}));
        Assertions.assertNotNull(objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.access_token"), new TypeReference<>() {}));
    }

    @Test
    public void givenAccessToken_whenRefreshAccessToken_thenReturnRefreshTokenAndAccessTokenAndStatusForbidden()
            throws Exception{

        // Given
        String headerName = "Authorization";
        String headerContent = JWTUtil.generateToken("tahmid.uddin.dev@gmail.com", JWTEnum.ACCESS_TOKEN);

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/refresh-token")
                .header(headerName, headerContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden()).andReturn();

    }

    @Test
    public void givenInvalidHeaderContent_whenRefreshAccessToken_thenReturnRefreshTokenAndAccessTokenAndStatusForbidden()
            throws Exception{

        // Given
        String headerName = "Authorization";
        String headerContent = "A Very Invalid Token";

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/refresh-token")
                .header(headerName, headerContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden()).andReturn();

    }

}
