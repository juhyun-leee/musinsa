package com.jh.musinsa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public abstract class BaseRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected ResultActions get(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    protected ResultActions post(String url, Object json) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(json)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    protected ResultActions patch(String url, Object json) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(json)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    protected ResultActions delete(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

}
