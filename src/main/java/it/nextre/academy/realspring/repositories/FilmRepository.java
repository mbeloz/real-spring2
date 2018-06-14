package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {

    Film findByTitoloLike(String titolo);

    Film findByTitoloOrderByTitoloAscLike(String titolo);
}
