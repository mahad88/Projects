

; mahad hundub abdullahi    


;; ********  1. Bli kjent med evaluatoren  ************

(load "evaluator.scm")
(set! the-global-environment (setup-environment))


;; 1.a


(mc-eval '(define (foo cond else)
            (cond ((= cond 2) 0)
                  (else (else cond)))) the-global-environment) ;; -> ok

(mc-eval '(define cond 3) the-global-environment)  ;; -> ok

(mc-eval '(define (else x) (/ x 2)) the-global-environment)  ;; -> ok

(mc-eval '(define (square x) (* x x)) the-global-environment) ;; -> ok


;; følgene uttrykket blir evaluert til 0 for prosedyren foo tar 2 og square som argument
;; (= cond 2) blir testet til sann dermed 0 blir retunert.

(mc-eval '(foo 2 square) the-global-environment) ;; -> 0

;; følgene uttrykket blir evaluert til 16 imotsetning til første uttrykket
;; (= cond 2) blir testet som usann dermed koden hoppet til else clauset.
;; else clauset blir evaluert (else cond) esle binder square prosedyren som tar ett argument så er cond lik 4
;; og (else cond) -> (square 4) -> 16.
(mc-eval '(foo 4 square) the-global-environment) ;; -> 16


;; Her evalueres cond uttrykket som sammenliknes med cond variablen (som definert til å være 3) og 2.
;; (= cond 2) testes som usann dermed blir else clauset blir evaluert.
;; (else 4) else er definert slik (/ 4 2) -> 2,  derfor får vi 2.

(mc-eval '(cond ((= cond 2) 0)
                (else (else 4)))
         the-global-environment)  ;; -> 2

; ********  2. Primitiver / innebygde prosedyrer ************

;; 2.a

;; vi har gjort endring til primitive-procedures og lagt til følgene primitiver 

;;(list '1+ (lambda (x) (+ x 1)))

;;(list '1- (lambda (x) (- x 1)))

(mc-eval '(1+ 2) the-global-environment) ;; ->3

(mc-eval '(1- 2) the-global-environment) ;; ->1

;; 2.b

(define (install-primitive! pro-navn pro-body)
  (set! primitive-procedures
        (cons (list pro-navn pro-body) primitive-procedures)))


;; ********  3. Nye special forms og alternativ syntaks ************



;; 3.a


;; se implementasjon av and?, eval-and, or? eval-or i evaluator.scm.

;; vi har merket kommentær med oppgave 3a alle endringene som gjelder 3a.

(display "\n TESTER 3A \n")

(mc-eval '(or 1 2 3) the-global-environment) ;; #t retunerer boolsk verdier isteden av aktuelle verdier.
(mc-eval '(and #t #t #t #t) the-global-environment) ;; #t



;; 3.b



(define (eval-if exp env)
 (cond ((tagged-list? exp 'else) (mc-eval (cadr exp) env))
       ((true? (mc-eval (cadr exp) env))
        (mc-eval (cadddr exp) env))
       (else (eval-if (cddddr exp) env))))

;; Her minst en elseif eller else er obligatorisk som oppgaven ber om.

;;Test (mc-eval '(if #f then 6 elseif #f then 10 elseif #t then 3 else 9) the-global-environment) ;; 3


;; 3.c

;; se implementasjon av let?, let-param, let-var, let-args  i evaluator.scm.

;; vi har merket kommentar med oppgave 3c alle endringene som gjelder 3c.

;; Tester

(display "\n TESTER 3C \n")

  (mc-eval '(let ((x 2) (y 2)(z 3))(* x y z)) the-global-environment) ;; 12

  (mc-eval '(let ((foo 2) (bar 3)) (+ (* 2 bar) foo)) the-global-environment) ;; 8

;; 3.d

;; se implementasjon av alt-let-args, alt-let-vars, alt-let-exps, alt-let-body  i evaluator.scm.
;; der er merket med kommentar som oppgave 3d.
;; for å test fjern kommentaret i linje 321 og 323
;; nemlig den prosedyren:
;;  (define (eval-let exp env)
;;  (mc-eval (cons (make-lambda (alt-let-vars exp) (list (alt-let-body exp))) (alt-let-exps exp)) env))

(display "\n TESTER 3D \n")

;(mc-eval '(let x = 2 and y = 3  in (display (cons x y)) (+ x y)) the-global-environment)
 

;; 3.e

(define (while set krop)
	((lambda pro
		(if set
			( begin
				krop
				(wile prod krop))
			#f))set))
 

(install-primitive! 'while while)
