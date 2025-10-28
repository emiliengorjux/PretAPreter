package fr.hiit.pretapreter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class AbstractControllerTest {
    protected static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } // Permet de convertir/modifié mes objets en JSON, et mapper disable,
         // avec "write etc.." les date ne seront plus sérializé sous forme de timestemps
        // rend la chose plus lisible et permet d'écrire une date en format YY/MM/JJ(format américain)
    }
}
