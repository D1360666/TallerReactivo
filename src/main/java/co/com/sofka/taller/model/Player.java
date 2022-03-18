package co.com.sofka.taller.model;

public class Player {
    public String id;
    public String name;
    public int age;
    public String icon;
    public String national;
    public int winners;
    public int games;
    public String club;
    public double ranking;
    public Player(){

    }

    public Player(String id, String name, int age, String icon, String national, int winners, int games, String club, double ranking){
        this.id = id;
        this.name = name;
        this.age = age;
        this.icon = icon;
        this.national = national;
        this.winners = winners;
        this.games = games;
        this.ranking=ranking;

        this.club = club;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getNational() {
        return national;
    }
    public String getClub() {
        return club;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public double getRanking() {
        return ranking;
    }
}
