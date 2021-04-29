package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    ObjectMapper mapper;
    Pokemon pokemon = new Pokemon();

    public PokemonFetchingServiceImpl(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {

        HttpURLConnection connection = null;
        String query = "https://pokeapi.co/api/v2/pokemon/";

        try {
            URL url = new URL(query + name);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("user-agent", "application/json");

            connection.connect();

            StringBuilder sb = new StringBuilder();


            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                System.out.println("fail " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }

            try {
                pokemon = mapper.getObjectMapper().readValue(sb.toString(), Pokemon.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }

        System.out.println(pokemon.toString());
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        return new byte[0];
    }
}
