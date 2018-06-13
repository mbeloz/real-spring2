package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.models.Film;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    Logger logger = Logger.getLogger(FilmController.class);

    @GetMapping("/")
    public List<Film> getAll() {
        logger.debug("getAll() (movies) called");
        return filmService.getAll();
    }

    @GetMapping({"/{idFilm}", "/id/{idFilm}"})
    public Film getFilmById(@PathVariable("idFilm") Long id) {
        logger.debug("getFilmById() called");
        return id != null ? filmService.findById(id) : null;
    }

//    @GetMapping("/titolo/{titolo}")
//    public Object getFilmsByTitolo(@PathVariable("titolo") String titolo) {
//        return titolo != null && titolo.length() > 0 ? filmService.findByTitolo(titolo) : null;
//    }

    @GetMapping("/titolo/{titolo}")
    public List<Film> getFilmsByTitolo(@PathVariable("titolo") String titolo) {
        logger.debug("getFilmsByTitolo() called");
        return titolo != null && titolo.length() > 0 ? filmService.findByTitolo(titolo) : null;
    }

    @GetMapping("/regista/{regista}")
    public List<Film> getFilmsByRegista(@PathVariable("regista") String regista) {
        logger.debug("getFilmsByRegista() called");
        return regista != null && regista.length() > 0 ? filmService.findByRegista(regista) : null;
    }

    @GetMapping("/anno/{anno}")
    public List<Film> getFilmByAnno(@PathVariable("anno") Integer anno) {
        logger.debug("getFilmsByAnno() called");
        return anno != null ? filmService.findByAnno(anno) : null;
    }

    @PostMapping("/")
    public Film addFilm(@RequestBody Film film) {
        logger.debug("addFilm() called with film: " + film);
        return filmService.add(film);
    }

}   //end class
