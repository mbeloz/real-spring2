package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.services.FilmService;
import it.nextre.academy.realspring.services.impl.FilmServiceMock;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;
    @Autowired ResponseHelper responseHelper;

    Logger logger = Logger.getLogger(FilmController.class);

    @GetMapping("/")
    public ResponseEntity getAll() {
        logger.debug("getAll() (movies) called");
        return responseHelper.ok(filmService.getAll());
    }

    @GetMapping({"/{idFilm}", "/id/{idFilm}"})
    public ResponseEntity getFilmById(@PathVariable("idFilm") Long id) {
        logger.debug("getFilmById() called");
        if (id != null)
            return responseHelper.ok(filmService.findById(id));
        else
            return responseHelper.badRequest("Invalid input");
    }

//    @GetMapping("/titolo/{titolo}")
//    public Object getFilmsByTitolo(@PathVariable("titolo") String titolo) {
//        return titolo != null && titolo.length() > 0 ? filmService.findByTitolo(titolo) : null;
//    }

    @GetMapping("/titolo/{titolo}")
    public ResponseEntity getFilmsByTitolo(@PathVariable("titolo") String titolo) {
        logger.debug("getFilmsByTitolo() called");
        if (titolo != null && titolo.length() > 0)
            return responseHelper.ok(filmService.findByTitolo(titolo));
        else
            return responseHelper.badRequest("Invalid input");
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
    public ResponseEntity addFilm(@RequestBody Film film) {
        logger.debug("addFilm() called with film: " + film);
        try {
            return responseHelper.ok(filmService.add(film));
        } catch (Exception e) {
            return responseHelper.badRequest(e.getMessage());
        }
    }

    @PutMapping("/{idFilm}")
    public Film updateFilm(@RequestBody Film film, @PathVariable("idFilm") long id) {
        logger.debug("updateFilm() called with film: " + film + "and id: " + id);
        if (film.getId() == id)
            return filmService.save(film);
        else
            return new Film();
    }

    @DeleteMapping("/{idFilm}")
    public boolean deleteFilm(@RequestBody Film film, @PathVariable("idFilm") long id) {
        logger.debug("deleteFilm() called with film: " + film + "and id: " + id);
        if (film.getId() == id)
            return filmService.delete(film);
        else
            return false;
    }

}   //end class
