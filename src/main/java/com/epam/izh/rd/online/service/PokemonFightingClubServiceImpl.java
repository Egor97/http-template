package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
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
                    System.out.println(second.getPokemonName() + " have " + second.getHp() + " hp");
                    winner = first;
                    break;
                }
            }

            if (first.getHp() > 0) {
                doDamage(second, first);
                if (first.getHp() <= 0) {
                    System.out.println(first.getPokemonName() + " have " + first.getHp() + " hp");
                    winner = second;
                    break;
                }
            }
        }

        return winner;
    }

    @Override
    public void showWinner(Pokemon winner) {

    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damage = (short) (from.getAttack() - (from.getAttack() * to.getDefense()/100));
        to.setHp((short) (to.getHp() - damage));
    }
}
