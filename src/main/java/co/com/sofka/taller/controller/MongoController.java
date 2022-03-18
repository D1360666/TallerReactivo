package co.com.sofka.taller.controller;


import co.com.sofka.taller.model.MongoPlayer;
import org.springframework.beans.BeanUtils;
import co.com.sofka.taller.model.Player;

public class MongoController {
    public static MongoPlayer jugadorMongoAJugador(Player player) {
        MongoPlayer mongoPlayer = new MongoPlayer();
        BeanUtils.copyProperties(player, mongoPlayer);
        return mongoPlayer;
    }

    public static Player mongoToJugador(MongoPlayer mongojugador) {
        Player player = new Player();
        BeanUtils.copyProperties(mongojugador, player);
        return player;
    }
}
