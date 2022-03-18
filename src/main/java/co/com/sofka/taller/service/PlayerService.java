package co.com.sofka.taller.service;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import co.com.sofka.taller.controller.MongoController;
import co.com.sofka.taller.model.MongoPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;


import co.com.sofka.taller.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
   private PlayerRepository playerRepository;
    public Flux<MongoPlayer> obtenerJugadoresMayoresA34AñosDeEdad(){
        Flux<MongoPlayer> playersOlderThan34 = playerRepository.findAll()
                .buffer(120)
                .flatMap(jugador -> Flux.fromStream(jugador.parallelStream()))
                .filter(jugador -> jugador.getAge() > 34).map(MongoController::jugadorMongoAJugador);
        return playersOlderThan34;
    }

    public Flux<MongoPlayer> obtenerJugadores(){
        Flux<MongoPlayer> jugadores = playerRepository.findAll()
                .buffer(120)
                .flatMap(jugador -> Flux.fromStream(jugador.parallelStream()).map(MongoController::jugadorMongoAJugador));
        return jugadores;
    }

    public Flux<MongoPlayer> obtenerJugadoresPorClub(String club){
        Flux<MongoPlayer> playersByClub = playerRepository.findAll()
                .buffer(120)
                .flatMap(player -> Flux.fromStream(player.parallelStream()))
                .filter(playerNoNullClub -> Objects.nonNull(playerNoNullClub.getClub()))
                .filter(jugador -> jugador.getClub().equals(club)).map(MongoController::jugadorMongoAJugador);
        return playersByClub;
    }

    public Flux<List<MongoPlayer>> ordenarJugadoresPorNacionalidadYPuntaje(){
        Flux<List<MongoPlayer>> jugadoresPorPais = playerRepository
                .findAll().map(MongoController::jugadorMongoAJugador)
                .buffer(120)
                .flatMap(jugador -> Flux.fromStream(jugador.parallelStream()))
                .distinct().groupBy(MongoPlayer::getNational)
                .flatMap(Flux::collectList).map(lista ->
                {
                    lista.sort(Comparator.comparingDouble(MongoPlayer::getRanking));
                    return lista;
                });
        return jugadoresPorPais;
    }

    public Mono<MongoPlayer> guardarJugador(Mono<MongoPlayer> jugador) {

        return jugador.map(MongoController::mongoToJugador)
                .flatMap(playerRepository::insert)
                .map(MongoController::jugadorMongoAJugador);
    }
}
