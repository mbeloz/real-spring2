package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.services.FilmService;
import it.nextre.academy.realspring.services.impl.FilmServiceMock;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired @Qualifier("implfilm") FilmService filmService;
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
    public ResponseEntity getFilmsByRegista(@PathVariable("regista") String regista) {
        logger.debug("getFilmsByRegista() called");
        if (regista != null && regista.length() > 0)
            return responseHelper.ok(filmService.findByRegista(regista));
        else
            return responseHelper.badRequest("Invalid input");
    }

    @GetMapping("/anno/{anno}")
    public ResponseEntity getFilmByAnno(@PathVariable("anno") Integer anno) {
        logger.debug("getFilmsByAnno() called");
        if (anno != null)
            return responseHelper.ok(filmService.findByAnno(anno));
        else
            return responseHelper.badRequest("Invalid input");
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
    public ResponseEntity updateFilm(@RequestBody Film film, @PathVariable("idFilm") long id) {
        logger.debug("updateFilm() called with film: " + film + "and id: " + id);
        if (film.getId() == id) {
            try {
                return responseHelper.ok(filmService.save(film));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid ID");
        }
    }

    @DeleteMapping("/{idFilm}")
    public ResponseEntity deleteFilm(@RequestBody Film film, @PathVariable("idFilm") long id) {
        logger.debug("deleteFilm() called with film: " + film + "and id: " + id);
        if (film.getId() == id) {
            try {
                return responseHelper.ok(filmService.delete(film));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid ID");
        }
    }

}   //end class
