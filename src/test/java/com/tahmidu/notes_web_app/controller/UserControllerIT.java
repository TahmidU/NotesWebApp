package com.tahmidu.notes_web_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tahmidu.notes_web_app.model.FakeUser;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.repository.IUserRepository;
import com.tahmidu.notes_web_app.repository.IVerificationTokenRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SuppressWarnings("unused")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerIT {

    @Autowired private MockMvc mockMvc;

    @Autowired private IUserRepository userRepository;
    @Autowired private IVerificationTokenRepository verificationTokenRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){

        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(userRepository);


    }

    @Test
    public void givenValidUser_whenValidUserSignsUp_thenSendVerificationEmailAndStatusOK() throws Exception {

        // Given
        FakeUser expectedUser = new FakeUser("Tahmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev@gmail.com", "HellO!123");

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        User actualUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        Assertions.assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        Assertions.assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        Assertions.assertEquals(expectedUser.getDisplayUsername(), actualUser.getDisplayUsername());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

}
