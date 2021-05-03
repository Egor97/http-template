package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинга значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    /**
     * Уникальный идентификатор, маппится из поля pokemonId
     */

    private long pokemonId;

    /**
     * Имя покемона, маппится из поля pokemonName
     */

    private String pokemonName;

    /**
     * Здоровье покемона, маппится из массива объектов stats со значением name: "hp"
     */
    private short hp;

    /**
     * Атака покемона, маппится из массива объектов stats со значением name: "attack"
     */
    private short attack;

    /**
     * Защита покемона, маппится из массива объектов stats со значением name: "defense"
     */
    private short defense;

    private final ArrayList<Stats> stats;

    private Sprite sprite;

    public Pokemon(@JsonProperty("id") long pokemonId, @JsonProperty("name") String pokemonName,
                   @JsonProperty("stats") ArrayList<Stats> stats, @JsonProperty("sprites") Sprite sprite) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.stats = stats;
        this.sprite = sprite;
    }

    public long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public short getAttack() {
        return attack;
    }

    public void setAttack(short attack) {
        this.attack = attack;
    }

    public short getDefense() {
        return defense;
    }

    public void setDefense(short defense) {
        this.defense = defense;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setAllStats() {
        for (Stats stat : stats) {
            switch ((stat.getStat().name)) {
                case "hp":
                    setHp(stat.baseStat);
                    break;
                case "attack":
                    setAttack(stat.baseStat);
                    break;
                case "defense":
                    setDefense(stat.baseStat);
                    break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemonId == pokemon.pokemonId && hp == pokemon.hp && attack == pokemon.attack && defense == pokemon.defense && pokemonName.equals(pokemon.pokemonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, pokemonName, hp, attack, defense);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Stats {

        private short baseStat;
        private Stat stat;

        @JsonCreator
        public Stats(@JsonProperty("base_stat") short baseStat, @JsonProperty("stat") Stat stat) {
            this.baseStat = baseStat;
            this.stat = stat;
        }

        public short getBaseStat() {
            return baseStat;
        }

        public void setBaseStat(short baseStat) {
            this.baseStat = baseStat;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stats stats = (Stats) o;
            return baseStat == stats.baseStat && Objects.equals(stat, stats.stat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(baseStat, stat);
        }

        @Override
        public String toString() {
            return "Stats{" +
                    "baseStat=" + baseStat +
                    ", stat=" + stat +
                    '}';
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Stat {

            private String name;

            @JsonCreator
            public Stat(@JsonProperty("name") String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Stat stat = (Stat) o;
                return Objects.equals(name, stat.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name);
            }

            @Override
            public String toString() {
                return "Stat{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprite {

        private URL image;

        @JsonCreator
        public Sprite(@JsonProperty("front_default") URL image) {
            this.image = image;
        }

        public URL getImage() {
            return image;
        }

        public void setImage(URL image) {
            this.image = image;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sprite sprite = (Sprite) o;
            return Objects.equals(image, sprite.image);
        }

        @Override
        public int hashCode() {
            return Objects.hash(image);
        }

        @Override
        public String toString() {
            return "Sprite{" +
                    "image='" + image + '\'' +
                    '}';
        }
    }
}
