package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.FileOutputStream;
import java.io.IOException;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {

    PokemonFetchingServiceImpl pokemonFetchingService;

    public PokemonFightingClubServiceImpl(PokemonFetchingServiceImpl pokemonFetchingService) {
        this.pokemonFetchingService = pokemonFetchingService;
    }

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        Pokemon first;
        Pokemon second;
        Pokemon winner;

        if (p1.getPokemonId() > p2.getPokemonId()) {
            first = p1;
            second = p2;
        } else {
            first = p2;
            second = p1;
        }

        while (true) {
            if (second.getHp() > 0) {
                doDamage(first, second);
                if (second.getHp() <= 0) {
                    winner = first;
                    break;
                }
            }

            if (first.getHp() > 0) {
                doDamage(second, first);
                if (first.getHp() <= 0) {
                    winner = second;
                    break;
                }
            }
        }

        return winner;
    }

    @Override
    public void showWinner(Pokemon winner) {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/com/epam/izh/rd/online/images/" +
                    winner.getPokemonName() + ".jpg");
            fos.write(pokemonFetchingService.getPokemonImage(winner.getPokemonName()));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damage = (short) (from.getAttack() - (from.getAttack() * to.getDefense()/100));
        to.setHp((short) (to.getHp() - damage));
    }
}
