;brukernavn  Mahadha
;Innlevering (1a) i INF2810 (Vår 2017)
;oppgave  1
;(a)
(* (+ 4 2) 5) ; resultat blir (6*5) =30

;(b)
(* (+ 4 2) (5)); error fordi (5) er feil syntaks. 

;(c)
(* (4 + 2) 5); blir feil fordi  + operator er i midten men + må være en prefix
;(d)
(define bar (/ 42 2))
; bar er lik 21

;(e)
(- bar 11); resultat blir 10


;(f)
(/ (* bar 3 4 1) bar); resultat blir 12

;oppgave 2 a i)
(or (= 1 2); først teste er #f derfor returner  "piff" og resten evalueres ikke
    "piff!"
    "paff!"
    (zero? (1 - 1))); og det siste linje er syntax feil fordi - operator er i midten men ikke evaluert


;del a ii

(and (= 1 2)
     "piff!"
     "paff!"
     (zero? (1 - 1)));result blir #f(false) fordi 1 er ikke lik 2. returner #f
;; og det siste linje er syntax feil fordi - operator er i midten men ikke evaluert

; del a iii
(if (positive? 42); den først linje blir #t(true ) og "poff blir skrevet ut"
    "poff!"
    (i-am-undefined)); det vil blir evaluert hvis det av if  result er  #f

; del b med if 
(define (sign-if tall)
  (if (negative? tall)
      -1
      (if(positive? tall)
         1
         ( if(zero? tall)
             0
             ))))

;del b med cond
(define(sign-cond tall)
  (cond ((negative? tall)
         -1)
        ((positive? tall)
         1)
        (else 0)) 

  )
; del 2 c med and & or
(define (sign-and-or tall)
  (or (and (positive? tall)
           1)
      (and (negative? tall)
           -1)
      0))


;oppgave 3 a
;addisjon
(define (addl tall)
  (+ tall 1))

;substrasjon
(define (subl tall)
  (- tall 1))

;oppgave 3 b

(define(pluss-to-nummer-rec a b)   ;  det rekursive prosedyre og process. Hvert steg på rekursjon beregn retur verdi
                                    ;og når  base case blir treft så returner verdi av b
  (if(zero? a)
     b
  
     (addl(pluss-to-nummer-rec (subl a)   b)))
  )

; oppgave 3 c
(define(pluss-to-nummer a b); det rekursive prosedyre og iterative process. hvert steg på interasjon minke verdi av 'a' med
                              ;1 til når base case are oppnåd og øker verdi av b med 1  .til i hver iterasjon
                             ;Til slutt returner verdi av b
  (if(zero? a)
     b
  
     (pluss-to-nummer(subl a) (addl b))
     ))

;3 d
(define (power-close-to b n);  definert hjelpe-prosedyre som internt da blir nested definisjon og danner blokk strukturert.
                               ;parameter b og n bler fjernet fra hjelpe-prosedyret for å forenkle
                               ; og variablene b og n er frie fra power-iter definisjonen og blir brukt direkt og når power-iter er ferdig
                               ; de skal kaller seg selv med verdi '1' som parameter og blir retur verdi av power-close-to
  (define (power-iter e)
    (if (> (expt b e) n)
        e
        (power-iter (+ 1 e))))
  (power-iter 1))

; 3 e
(define (fib-iter a b count)
  (if (= count 0)
      b
      (fib-iter (+ a b) a (- count 1))))


; skal for example kaller (power-iter 1 0 5) det først to variable  parameter må altid være 1  og 0.
; kan ikke initialisere  parameter 'a' og 'b' på inne i rekurssion fordi, på hver rekursion steg de er  ikke altid 1 og 0 .