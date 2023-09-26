package com.musicalist.intermediator.intermediator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Modelo.Genero;

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
class CancionControllerTests {

    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
   

    @Test
    void testInsertarCancionValida() throws Exception {
        Cancion cancion = new Cancion("crear", "crear", new Genero());
        cancion.getGenero().setId(1);
        String requestBody = objectMapper.writeValueAsString(cancion);


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Cancion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

     @Test
    void testBorrarCancionPorIdExistente() throws Exception {
        String nombre = "{\"nombre\":\"crear\"}";
       

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Cancion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

    @Test
    void testInsertarCancionConNombreVacio() throws Exception {
        String nombre = "{\"nombre\":\"\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Cancion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBuscarCancionPorIdExistente() throws Exception {
        String nombre = "{\"id\":1}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/Cancion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());

    }

    @Test
    void testActualizarCancionConDatosValidos() throws Exception {
        String nombre = "{\r\n" + //
                "  \"id\":1,\r\n" + //
                "  \"nombreArtista\":\"San pedro\",\r\n" + //
                "  \"genero\": {\r\n" + //
                "  }\r\n" + //
                "}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/Cancion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }
}

