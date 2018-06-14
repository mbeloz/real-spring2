package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    List<Film> findAllByTitoloLike(String titolo);

    //List<Film> findByTitoloLikeOrderByTitoloAsc(String titolo);

    List<Film> findAllByRegistaLike(String regista);

    List<Film> findAllByAnno(int anno);


}
