package co.com.sofka.taller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "jugadores")
public class MongoPlayer {

    @Id
    private String id;
    public String name;
    public int age;
    public String icon;
    public String national;
    public int winners;
    public int gamers;
    public double ranking;
    public String club;

    public MongoPlayer(String id, String name, int age, String icon, String national, int winners, int gamers, double ranking, String club) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.icon = icon;
        this.national = national;
        this.winners = winners;
        this.gamers = gamers;
        this.ranking = ranking;
        this.club = club;
    }
    public MongoPlayer() {

    }
    public int getAge() {
        return age;
    }

    public String getNational() {
        return national;
    }

    public double getRanking() {
        return ranking;
    }

    public String getClub() {
        return club;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public int getWinners() {
        return winners;
    }

    public void setWinners(int winners) {
        this.winners = winners;
    }

    public int getGamers() {
        return gamers;
    }

    public void setGamers(int gamers) {
        this.gamers = gamers;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
