;brukernavn  Mahadha
;Innlevering (1b) i INF2810 (Vår 2017)
;oppgave  1
;a
; oppvage 1a til 1e har jeg levert som egen fil

;f
(car(cdr'(0 42 #t bar)))

;g
(cdr(car'((0 42) (#t bar))))

;h
(car(car(cdr'((0) (42 #t) (bar)))))

;i
(cons (list 0 42) (list'(#t bar)))


;2 a
(define (length2 items)
  (define (hale-iter in out)
    (if (null? in)
        out
        (hale-iter (cdr in)
                   (+  out 1))))
  (hale-iter items 0))


;b
(define (reduce-reverse prod out items)
    (define (reverse-iter in out)
      (if (null? in)
          out
          (reverse-iter (cdr in)
                     (prod (car in) out))))
    (reverse-iter items out))

;  prosedyredefinisjonen er halerekusion fordi jeg har en blokkstruktur
;(revers-iter som er intern hjelpeprosedyre), en akumulator 'out' and en basistilfeller
; minnebruk er o(1) og tid er o(n)
; hver car blir kombinert med out som i det først tilfeller er '() og hver nye car
;blir først på den nye liste. Tilsutt list er reverset


;c
 (define (all?  pred? items)
    (cond ((null? items)
           #t)
          ((pred? (car items))
                 (all? pred? (cdr items)))
          
          (else
           #f)))

; jeg skal kalle lambda  sånn (all? (lambda (x)(< x 10))'(1 2 3 4 5 6))

;d
(define (nth indx lst)
   ( define(iter pos lst)
      (if(null? lst)
         '()
    (if(= pos indx)
       (car lst)
       (iter (+ pos 1)(cdr lst)))))
    (iter 0 lst))


;e
(define (where  indx lst)
   ( define(iter pos lst)
      (if(null? lst)
         #f
    (if(= indx (car lst))
       pos
       (iter (+ pos 1)(cdr lst)))))
    (iter 0 lst))


;f
 (define (map2 pred lst1 lst2)
      (if( or(null? lst1)(null? lst2))
             '()
      (cons(pred (car lst1)(car lst2))
            (map2 pred (cdr lst1)(cdr lst2)))))



;g
(define (map2 pred lst1 lst2)
      (if( or(null? lst1)(null? lst2))
             '()
      (cons(pred(+ (car lst1)(car lst2)))
            (map2 pred (cdr lst1)(cdr lst2)))))


;h

(define ( both? pre?)
  (lambda (x y)
    (and (pre? x)(pre? y))))


;i
(define (self pred)
(lambda (x)
  (pred x x)
    ))