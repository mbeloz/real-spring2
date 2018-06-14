package it.nextre.academy.realspring.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idfilm")
    private long id;

    //@Transient = Il campo non verrà mappato in una colonna della tabella!

    @NotNull(message="Titolo non valido")
    @Column(length = 255)
    @Size(min=3, max=255, message = "Lunghezza non valida")
    private String titolo;

    private String regista;

    @Column(length = 4)
    @Min(value = 1870, message = "Valore minimo non rispettato")
    @Max(value = 2030, message = "Valore massimo non rispettato")
    private int anno;

}   //end class
