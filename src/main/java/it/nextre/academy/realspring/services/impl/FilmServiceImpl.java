package it.nextre.academy.realspring.services.impl;

import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.repositories.FilmRepository;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("implfilm")
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    private Logger logger = Logger.getLogger(FilmServiceImpl.class);

    public List<Film> getAll() {
        //List<Film> tmp = new ArrayList<>();
        //return tmp;
        logger.debug("getAll() called");
        return (List<Film>) filmRepository.findAll();
    }

    public Film findById(long id) {
        logger.debug("findById() called");
        return filmRepository.findOne(id);
    }

    /*public Object findByTitolo(String titolo) {
        List<Film> tmp = this.videoteca.stream().filter(f -> f.getTitolo().toLowerCase().contains(titolo.toLowerCase())).collect(Collectors.toList());
        if (tmp.size() == 0)
            return null;
        else if (tmp.size() == 1)
            return tmp.get(0);
        else
            return tmp;
    }*/

    public List<Film> findByTitolo(String titolo) {
        logger.debug("findByTitolo() called");
        return filmRepository.findAllByTitoloLike(titolo);
    }

    public List<Film> findByRegista(String regista) {
        logger.debug("findByRegista() called");
        return filmRepository.findAllByRegistaLike(regista);
    }

    public List<Film> findByAnno(int anno) {
        logger.debug("findByAnno() called");
        return filmRepository.findAllByAnno(anno);
    }

    public Film add(Film film) throws Exception {
        if (film != null && film.getId() == 0 && film.getTitolo() != null && film.getTitolo().length() > 0) {
            Film tmp = filmRepository.save(film);
            logger.debug("add() returns: " + tmp);
            return tmp;
        }
        logger.debug("add() throws an InvalidArgumentException");
        throw new Exception("Malformed film data");
    }

    public Film save(Film film) throws Exception {
        if (film != null && film.getId() > 0 && film.getTitolo() != null && film.getTitolo().length() > 0) {
            Film tmp = filmRepository.save(film);
            logger.debug("save() returns: " + tmp);
            return tmp;
        }
        logger.debug("save() throws an InvalidArgumentException");
        throw new Exception("Malformed film data");
    }

    public boolean delete(Film film) throws Exception {
        if (film != null && film.getId() > 0) {
            filmRepository.delete(film.getId());
            logger.debug("delete() returns.");
            return true;
        }
        logger.debug("delete() throws an InvalidArgumentException");
        throw new Exception("Malformed film data");
    }
}   //end class
