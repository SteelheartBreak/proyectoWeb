package com.musicalist.intermediator.intermediator;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VotoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void votarCancion_ValidVoto_ReturnsAccepted() throws Exception {
        String nombre = "{\"usuario\": {\"id\":7},\"cancion\": {\"id\": 1}}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isAccepted());
    }
    

    @Test
    void votarCancion_InvalidVoto() throws Exception {
        String nombre = "{\"usuario\": {\"id\":},\"cancion\": {\"id\": }}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }

    @Test
    void BorrarVoto_ExistingVoto() throws Exception {
        String nombre = "{\"usuario\": {\"id\":7},\"cancion\": {\"id\":1 }}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isAccepted());
    }

    @Test
    void BorrarVoto_NullExistingVoto() throws Exception {
        String nombre = "{\"usuario\": {\"id\":},\"cancion\": {\"id\": }}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }

    @Test
    void BorrarVoto_NonExistingVoto() throws Exception {
        String nombre = "{\"usuario\": {\"id\":7},\"cancion\": {\"id\":4 }}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }
    
}

