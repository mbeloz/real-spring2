package it.nextre.academy.realspring.services;

import it.nextre.academy.realspring.entities.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAll();

    Film findById(long id);

    List<Film> findByTitolo(String titolo);

    List<Film> findByRegista(String regista);

    List<Film> findByAnno(int anno);

    Film add(Film film) throws Exception;

    Film save(Film film);

    boolean delete(Film film);
}
