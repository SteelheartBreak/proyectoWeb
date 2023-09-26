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
public class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;
    

    // Prueba 1: Insertar un usuario válido
    @Test
    public void testInsertarUsuario() throws Exception {
        String nombre = "{\r\n" + //
                "    \"nombre\":\"prueba\",\r\n" + //
                "    \"contrasenia\":\"ABC123\",\r\n" + //
                "    \"correo\":\"x@gmail.com\",\r\n" + //
                "    \"rol\":\"admin\"\r\n" + //
                "}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isAccepted());
    }

    // Prueba 2: Insertar un usuario con nombre vacío
    @Test
    public void testInsertarUsuarioConNombreVacio() throws Exception {
        String nombre = "{\r\n" + //
                "    \"nombre\":\"\",\r\n" + //
                "    \"contrasenia\":\"ABC789\",\r\n" + //
                "    \"correo\":\"x@gmail.com\",\r\n" + //
                "    \"rol\":\"usuario\"\r\n" + //
                "}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isBadRequest());
    }

    // Prueba 3: Buscar un usuario por ID existente
    @Test
    public void testBuscarUsuarioPorIdExistente() throws Exception {
        String nombre = "{\"id\":\"6\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

    // Prueba 4: Actualizar un usuario con datos válidos
    @Test
    public void testActualizarUsuarioConDatosValidos() throws Exception {
        String nombre = "{\r\n" + //
                "    \"id\": 6,\r\n" + //
                "    \"nombre\":\"Santiago\",\r\n" + //
                "    \"contrasenia\":\"ABC123\",\r\n" + //
                "    \"correo\":\"A@gmail.com\",\r\n" + //
                "    \"rol\":\"admin\"\r\n" + //
                "}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

    // Prueba 5: Borrar un usuario por ID existente
    @Test
    public void testBorrarUsuarioPorIdExistente() throws Exception {
        String nombre = "{\"correo\":\"x@gmail.com\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nombre))
                .andExpect(status().isOk());
    }

}

