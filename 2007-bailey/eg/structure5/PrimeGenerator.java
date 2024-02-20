public class PrimeGenerator extends AbstractGenerator
{
    /**
     * @post construct a generator that delivers primes starting at 2.
     */
    public PrimeGenerator()
    {
        reset();
    }
    
    /**
     * @post reset the generator to return primes starting at 2
     */
    public void reset()
    {
        set(2);
    }
    
    /**
     * Generate the next prime
     * @post generate the next prime
     */
    public int next()
    {
        int f,n = get();
        do
        {
            if (n == 2) n = 3;
            else n += 2;
        
            // check the next value
            for (f = 2; f*f <= n; f++)
            {
                if (0 ==(n % f)) break;
            }
        } while (f*f <= n);
        set(n);
        return n;
    }
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        PrimeGenerator g = new PrimeGenerator();
        for (int i = 0; i < n; i++) {
            System.out.printf("Prime %d = %d\n",i,g.get());
            g.next();
        }
    }
}
/*
java PrimeGenerator 100
Prime 0 = 2
Prime 1 = 3
Prime 2 = 5
Prime 3 = 7
Prime 4 = 11
Prime 5 = 13
Prime 6 = 17
Prime 7 = 19
Prime 8 = 23
Prime 9 = 29
Prime 10 = 31
Prime 11 = 37
Prime 12 = 41
Prime 13 = 43
Prime 14 = 47
Prime 15 = 53
Prime 16 = 59
Prime 17 = 61
Prime 18 = 67
Prime 19 = 71
Prime 20 = 73
Prime 21 = 79
Prime 22 = 83
Prime 23 = 89
Prime 24 = 97
Prime 25 = 101
Prime 26 = 103
Prime 27 = 107
Prime 28 = 109
Prime 29 = 113
Prime 30 = 127
Prime 31 = 131
Prime 32 = 137
Prime 33 = 139
Prime 34 = 149
Prime 35 = 151
Prime 36 = 157
Prime 37 = 163
Prime 38 = 167
Prime 39 = 173
Prime 40 = 179
Prime 41 = 181
Prime 42 = 191
Prime 43 = 193
Prime 44 = 197
Prime 45 = 199
Prime 46 = 211
Prime 47 = 223
Prime 48 = 227
Prime 49 = 229
Prime 50 = 233
Prime 51 = 239
Prime 52 = 241
Prime 53 = 251
Prime 54 = 257
Prime 55 = 263
Prime 56 = 269
Prime 57 = 271
Prime 58 = 277
Prime 59 = 281
Prime 60 = 283
Prime 61 = 293
Prime 62 = 307
Prime 63 = 311
Prime 64 = 313
Prime 65 = 317
Prime 66 = 331
Prime 67 = 337
Prime 68 = 347
Prime 69 = 349
Prime 70 = 353
Prime 71 = 359
Prime 72 = 367
Prime 73 = 373
Prime 74 = 379
Prime 75 = 383
Prime 76 = 389
Prime 77 = 397
Prime 78 = 401
Prime 79 = 409
Prime 80 = 419
Prime 81 = 421
Prime 82 = 431
Prime 83 = 433
Prime 84 = 439
Prime 85 = 443
Prime 86 = 449
Prime 87 = 457
Prime 88 = 461
Prime 89 = 463
Prime 90 = 467
Prime 91 = 479
Prime 92 = 487
Prime 93 = 491
Prime 94 = 499
Prime 95 = 503
Prime 96 = 509
Prime 97 = 521
Prime 98 = 523
Prime 99 = 541
*/

