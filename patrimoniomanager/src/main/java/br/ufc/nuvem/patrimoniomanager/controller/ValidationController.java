package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.ValidationDTO;
import br.ufc.nuvem.patrimoniomanager.model.Validation;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import br.ufc.nuvem.patrimoniomanager.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;



    @PostMapping
    public ResponseEntity<Validation> addValidation(@RequestBody ValidationDTO validation) {
        return new ResponseEntity<>(validationService.createValidation(validation.toValidation()), HttpStatus.ACCEPTED);
    }
    @GetMapping()
    public ResponseEntity<List<Validation>> getValidationsByBemId(@RequestParam Long bemid) {
        return new ResponseEntity<>(validationService.getValidationListByBemId(bemid), HttpStatus.ACCEPTED);
    }

}
