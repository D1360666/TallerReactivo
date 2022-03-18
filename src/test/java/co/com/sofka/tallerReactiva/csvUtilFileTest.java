package co.com.sofka.tallerReactiva;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.com.sofka.taller.model.Player;

import java.util.*;
import java.util.stream.Collectors;

import co.com.sofka.taller.csvUtilFile;

class csvUtilFileTest {
    @Test
    void converterData() {
        List<Player> list = csvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void stream_filtrarJugadoresMayoresA35() {
        List<Player> list = csvUtilFile.getPlayers();
        Map<String, List<Player>> listFilter = list.parallelStream()
                .filter(player -> player.age >= 35)
                .map(player -> {
                    player.name = player.name.toUpperCase(Locale.ROOT);
                    return player;
                })
                .flatMap(playerA -> list.parallelStream()
                        .filter(playerB -> playerA.club.equals(playerB.club))
                )
                .distinct()
                .collect(Collectors.groupingBy(Player::getClub));

        assert listFilter.size() == 322;
    }
    //FUNCIONES SOLICITADAS AQUI ABAJO
    @Test
    void JugadoresMayoresA34filtro() {
        List<Player> list = csvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .filter(player -> player.age >= 34)
                .distinct()
                .collectMultimap(Player::getName);
        System.out.println("Jugadores Mayores a 34 años o de 34 años");
        listFilter.block().forEach((jugadores, players) -> {
            players.forEach(player -> {
                System.out.println("Nombre: " + player.name + " edad: " + player.age + " años");
            });
        });
    }
    @Test
    void porClub() {
        List<Player> list = csvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .filter(player -> player.club.equals("Milan"))
                .distinct()
                .collectMultimap(Player::getClub);
        System.out.print("Equipo: ");
        listFilter.block().forEach((equipo, players) -> {
            System.out.println(equipo);
            players.forEach(player -> {
                System.out.println("Nombre: " + player.name);
                assert player.club.equals("Milan");
            });
        });
        assert listFilter.block().size() == 1;
    }
    @Test
    void obtenerPorRankingDeWinsYNacionalidad() {
        List<Player> list = csvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .buffer(100)
                .flatMap(jugardor1 -> listFlux
                        .filter(player2 -> jugardor1.stream()
                                .anyMatch(a -> a.national.equals(player2.national)))).distinct()
                .sort((k, player) -> player.winners).collectMultimap(Player::getNational);
        System.out.println("Por Nacionalidad: ");
        System.out.println(listFilter.block().size());
        listFilter.block().forEach((pais, players) -> {
            System.out.println("Pais (" + pais + ")");
            players.forEach(player -> {
                System.out.println("Nombre: " + player.name + " wins: " + player.winners);
            });
        });
    }
}