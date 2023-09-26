package com.musicalist.intermediator.intermediator;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Random;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Prueba 1: Insertar un género válido
    @Test
    public void testInsertarGenero() throws Exception {
        String nombre = "{\"nombre\":\"salsa\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isCreated());
    }

    // Prueba 2: Insertar un género con nombre vacío
    @Test
    public void testInsertarGeneroNombreVacio() throws Exception {
        String nombre = "{\"nombre\":\"\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }

    // Prueba 3: Buscar un género por ID existente
    @Test
    public void testBuscarGeneroPorIdExistente() throws Exception {
        String nombre = "{\"id\":1}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/Genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

    // Prueba 4: Actualizar un género con datos válidos
    @Test
    public void testActualizarGenero() throws Exception {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100) + 1;
        String nombre = "{\"id\":1,\"nombre\":\"Rap"+String.valueOf(numeroAleatorio)+"\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/Genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

    // Prueba 5: Borrar un género por ID existente
    @Test
    public void testBorrarGeneroPorIdExistente() throws Exception {
        String nombre = "{\"nombre\":\"salsa\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }
   
}
