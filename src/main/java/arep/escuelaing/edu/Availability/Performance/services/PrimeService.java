package arep.escuelaing.edu.Availability.Performance.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class PrimeService {
    private Set<Integer> sieve;

    public String isPrime(BigInteger number) {
        if(sieve == null) throw new IllegalStateException("Sieve is not initialized");
        for(Integer prime : sieve){
            BigInteger primeAsBigInteger = BigInteger.valueOf(prime);
            if (!number.equals(primeAsBigInteger) && number.remainder(primeAsBigInteger).equals(BigInteger.ZERO)){
                return String.format("The number %s is not prime because it is divisible by %d", number.toString(), prime);
            }
        }

        return String.format("%s could be a prime number!", number.toString());
    }

    public void calculateSieve(int maxInSieve) {
        sieve = new HashSet<>(maxInSieve);
        System.gc();

        for (int i = 2; i <= maxInSieve; i++) {
            sieve.add(i);
        }
        int upperBound = (int) Math.sqrt(maxInSieve) + 1;
        for (int i = 2; i <= upperBound; i++) {
            if (sieve.contains(i)) {
                for (int j = i * i; j <= maxInSieve; j += i) {
                    sieve.remove(j);
                }
            }
        }
        System.out.println("Finished calculate sieve");
        System.gc();
    }
}