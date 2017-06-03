

;; Mahad Hundub Abdullahi


;; ********  1. Innkapsling, lokal tilstand og omgivelsesmodellen  ************

(load "prekode2b.scm")

;; a). 

(define (make-counter)
  ((lambda (count)
     (lambda ()
       (set! count (+ count 1)) count)) 0))

;;b see vedlagt  1b


;; ******************** 2. Innkapsling, lokal tilstand og message passing  *********

;;2.a

(define (make-stack list!)  
  (define (push! element)
    (if (not (null? element))
        (cond
          ((set! list! (cons (car element) list!))
           (push! (cdr element))))))  
  (define (stack) list!)
  (define (pop!)
    (if (not (null? list!))
        (set! list! (cdr list!))))
  (define (disp  hva . element)
    (cond ((equal? hva 'push!) (push! element))          
          ((equal? hva 'stack) (stack))
          ((equal? hva 'pop!) (pop!))))
  disp)



;;2.b

  (define (pop! stack)
    (stack 'pop!))

(define (stack slist)
  (slist 'stack))

(define (push! stk . args)
  (define (push-it element)
    (if (not (null? element))
        (cond
          ((stk 'push! (car element))
           (push-it (cdr element))))))
  (push-it args))


;; ******************** 3. Strukturdeling og sirkulære lister  **********************

;; 3.a   see vedlagt  3a

;; Listen er ikke lenger en list som vi er definert lister før siden den sist elementen i listen
;; ikke peker en tom elemet '(), etter listen kommer til plass 1 går listen i evig loop med
;; b c d og e blir til søppel siden det ikke er noe som peker på, dermend får vi list-ref som gir
;; verdien 1 2 3 osv til løkken med b c d. dette betyr at b kan få list-ref 1, 4, og 7 osv. 

;; 3.b     see vedlagt  3b
;; Når vi endrer car av  car bah a til 42 blir det også endret car til cdr bah siden den peker 
;; på samme elemet i listen. 

;3.c

; Alle lister har en bestemt lengde og må terminerer med '(). 
; bah oppfyler kravene dermed er det en list. mens bar oppfyller ikke kravene fordi den er sirkulær.


; ******************** 4. Dynamisk programmering: memoisering **********************


;; 4a

;; den biten av koden er hentet fra boka LISP exercise 3.27
(define (memoize f)
  (let ((table (make-table)))
    (lambda x
      (let ((previously-computed-result
             (lookup x table)))
        (or previously-computed-result
            (let ((result (apply f x)))
              (insert! x result table)
              result))))))

(define fib-table (make-table))

(define (mema proc)
  ((lambda (fib!)
     (insert! fib! proc fib-table)
     fib!) (memoize proc)))

(define (mem message proc)
  (cond 
    ((equal? message 'memoize) (mema proc))
    ((equal? message 'unmemoize) (memb proc fib-table)))) ; modifisert for oppgave 4b

;; 4b

(define (memb proc fib-table)
  (or(lookup proc fib-table)
     proc))

;; 4c

;; forskjellen er at vi definerer mem-fib som henter sine verdier fra prekoden
;; hvis vi hadde brukt set! som vi har gjort tidligere hadde den hentet verdier fra
;; den memoized versjonen som vi har definert.