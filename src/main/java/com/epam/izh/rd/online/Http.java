package com.epam.izh.rd.online;

import com.epam.izh.rd.online.factory.ObjectMapper;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;

public class Http {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String namePokemon = "slowpoke";

        PokemonFetchingServiceImpl pokemonFetchingService = new PokemonFetchingServiceImpl(objectMapper);

        pokemonFetchingService.fetchByName(namePokemon);
    }
}
