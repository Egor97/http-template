package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PokemonFetchingServiceImpl implements PokemonFetchingService {

    private final List<Pokemon> pokemonsDB = new ArrayList<>();
    private final ObjectMapper mapper;

    public PokemonFetchingServiceImpl(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = null;
        HttpURLConnection connection = null;
        String link = "https://pokeapi.co/api/v2/pokemon/";

        try {
            URL url = new URL(link + name);
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
                pokemon.setAllStats();
                pokemonsDB.add(pokemon);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }

        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (Pokemon pokemon : pokemonsDB) {
            if (pokemon.getPokemonName().equals(name)) {
                URL url = pokemon.getSprite().getImage();
                try (InputStream in = url.openStream()) {
                    byte[] buf = new byte[1024];
                    int count;
                    while (-1!=(count=in.read(buf))) {
                        out.write(buf, 0, count);
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return out.toByteArray();
    }
}
