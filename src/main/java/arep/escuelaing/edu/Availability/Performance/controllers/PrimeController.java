package arep.escuelaing.edu.Availability.Performance.controllers;

import arep.escuelaing.edu.Availability.Performance.services.PrimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/prime")
public class PrimeController {
    private final PrimeService primeService;

    public PrimeController(PrimeService primeService) {
        this.primeService = primeService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<String> isPrime(@PathVariable BigInteger number) {
        try {
            return new ResponseEntity<>(primeService.isPrime(number), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{maxInSieve}")
    public ResponseEntity calculatePrimes(@PathVariable int maxInSieve){
        CompletableFuture.runAsync(() -> primeService.calculateSieve(maxInSieve));

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
