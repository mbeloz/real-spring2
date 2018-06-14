package it.nextre.academy.realspring.services;

import it.nextre.academy.realspring.models.Film;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private List<Film> videoteca = new ArrayList<>();

    private Logger logger = Logger.getLogger(FilmService.class);

    {
        videoteca.add(new Film(1, "300", "Zack Snyder", 2007));
        videoteca.add(new Film(2, "Pacific Rim", "Guillermo del Toro", 2013));
        videoteca.add(new Film(3, "Dunkirk", "Christopher Nolan", 2017));
        videoteca.add(new Film(4, "Saw", "James Wan", 2004));
        videoteca.add(new Film(5, "Il padrino", "Francis Ford Coppola", 1972));
        videoteca.add(new Film(6, "Arancia meccanica", "Stanley Kubrick", 1971));
        videoteca.add(new Film(7, "Shining", "Stanley Kubrick", 1980));
        videoteca.add(new Film(8, "Italiano medio", "Maccio Capatonda", 2015));
    }

    public List<Film> getAll() {
        //List<Film> tmp = new ArrayList<>();
        //return tmp;
        logger.debug("getAll() called");
        return videoteca;
    }

    public Film findById(long id) {
        logger.debug("findById() called");
        return this.videoteca.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
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
        return this.videoteca.stream().filter(f -> f.getTitolo().toLowerCase().contains(titolo.toLowerCase())).collect(Collectors.toList());
    }

    public List<Film> findByRegista(String regista) {
        logger.debug("findByRegista() called");
        return this.videoteca.stream().filter(f -> f.getRegista().toLowerCase().contains(regista.toLowerCase())).collect(Collectors.toList());
    }

    public List<Film> findByAnno(int anno) {
        logger.debug("findByAnno() called");
        return this.videoteca.stream().filter(f -> f.getAnno() == anno).collect(Collectors.toList());
    }

    public Film add(Film film) throws Exception {
        if (film != null && film.getId() == 0 && film.getTitolo() != null && film.getTitolo().length() > 0) {
            long maxId = videoteca.stream()
                    .max((f1, f2) -> (int) (f1.getId() - f2.getId()))
                    .get().getId();
            film.setId(++maxId);

            //memorizzo il nuovo film
            videoteca.add(film);
            logger.debug("add() returns: " + film);
            return film;
        }
        logger.debug("add() throws an InvalidArgumentException");
        throw new Exception("Malformed film data");
    }

    public Film save(Film film) {
        if (film != null && film.getId() > 0 && film.getTitolo() != null && film.getTitolo().length() > 0) {
            Optional<Film> op = videoteca.stream().filter(f -> f.getId() == film.getId()).findFirst();
            if (op.isPresent()) {
                op.get().setAnno(film.getAnno());
                op.get().setRegista(film.getRegista());
                op.get().setTitolo(film.getTitolo());
                logger.debug("save(): " + op.get());
                return op.get();
            }
            logger.debug("save(): no such element found");
            return new Film();
        }
        logger.debug("save() returns an empty film.");
        return new Film();
    }

    public boolean delete(Film film) {
        if (film != null && film.getId() > 0) {
            logger.debug("delete() " + film);
            return videoteca.removeIf(f -> f.getId() == film.getId());
        }
        logger.debug("delete(): Malformed input");
        return false;
    }
}   //end class
