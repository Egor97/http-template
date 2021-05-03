package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapper;

public class GameFunctionService {

    ObjectMapper objectMapper = new ObjectMapper();
    PokemonFetchingServiceImpl pokemonFetchingService = new PokemonFetchingServiceImpl(objectMapper);
    PokemonFightingClubServiceImpl pokemonFightingClubService = new PokemonFightingClubServiceImpl(pokemonFetchingService);

    public void startGame(String pokemonName1, String pokemonName2) {
        Pokemon firstOpponent =  pokemonFetchingService.fetchByName(pokemonName1);
        Pokemon secondOpponent = pokemonFetchingService.fetchByName(pokemonName2);

        Pokemon winner = pokemonFightingClubService.doBattle(firstOpponent, secondOpponent);
        pokemonFightingClubService.showWinner(winner);
    }
}
