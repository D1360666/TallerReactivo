package co.com.sofka.taller.controller;
import co.com.sofka.taller.model.MongoPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.com.sofka.taller.service.PlayerService;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")

public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/jugadores")
    public Flux<MongoPlayer> obtenerJugadores(){
        return playerService.obtenerJugadores();
    }

    @GetMapping(value = "/players/filtrarporedad")
    public Flux<MongoPlayer> jugadoresMayoresA34(){
        return playerService.obtenerJugadoresMayoresA34AñosDeEdad();
    }
    @GetMapping(value = "/players/filtrarporclub/{club}")
    public Flux<MongoPlayer> jugadoresPorClub(@PathVariable("club") String club){
        return playerService.obtenerJugadoresPorClub(club);
    }
    @GetMapping(value = "/jugadoresporpais")
    public Flux<List<MongoPlayer>> jugadoresPorPais(){
        return playerService.ordenarJugadoresPorNacionalidadYPuntaje();
    }
    @PostMapping("/jugadores/crear")
    private Mono<MongoPlayer> guardarJugador(@RequestBody Mono<MongoPlayer> jugador) {
        return playerService.guardarJugador(jugador);
    }
}



