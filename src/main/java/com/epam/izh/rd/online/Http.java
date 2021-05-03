package com.epam.izh.rd.online;

import com.epam.izh.rd.online.factory.ObjectMapper;
import com.epam.izh.rd.online.service.GameFunctionService;
import com.epam.izh.rd.online.service.PokemonFetchingServiceImpl;
import com.epam.izh.rd.online.service.PokemonFightingClubServiceImpl;

public class Http {
    public static void main(String[] args) {
        String namePokemon1 = "slowpoke";
        String namePokemon2 = "pikachu";

        GameFunctionService game = new GameFunctionService();
        game.startGame(namePokemon1, namePokemon2);
    }
}
