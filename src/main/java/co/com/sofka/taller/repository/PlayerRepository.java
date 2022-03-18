package co.com.sofka.taller.repository;

import co.com.sofka.taller.model.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {

}
