

; Mahad Hundub Abdullahi


; ********  1. Diverse  ************

(load "huffman.scm")

; a). 
(define (p-cons x y)
  (lambda (proc) (proc x y)))


  

(define (p-car p)
    
  ( p (lambda (x y)  x)))

(define (p-car p)
    
  ( p (lambda (x y)  y)))

;b 

(define foo 42)

((lambda (foo x)
   (if(= x foo)
      'same
      'different)) 5 foo)

((lambda (bar baz)
   ((lambda (bar foo )
    
      (list foo bar)) (list bar baz) baz)) foo 'towel)

;c
(define (infix-eval exp1)
  ((cadr exp1) (car exp1) (caddr exp1)))

;d

; (define bah '(84 / 2))

; Når vi definere bah med quote " / " tegnet erkjennes ikke som prosedyre.
; Men med list går det helt greit. så gir den feilmelding som sier forventes prosedyre.




; ********  2. Huffman-koding  ************


;2.a

(define (member? pro x set)
  (cond ((null? set) #f)
        ((pro x (car set)) #t)
        (else (member? pro x (cdr set)))))


;2.b

;; Decode-1 tar argumenetene bits og current-branch, men current-branch får sine verdier
;; fra tree variable. current-branch blir oppdatert til å være tree som rekurserer gjenomm treet.
;; hvis vi bare kaller decode så går rekursonen i en evig loop.


; 2.c

(define (decode bits tree)
  (define (decode-1 bits current-branch set)
    (if (null? bits)
        set
        (let ((next-branch
               (choose-branch (car bits) current-branch)))
          (if (leaf? next-branch)
              (decode-1 (cdr bits) tree
                        (cons (symbol-leaf next-branch) set))
              (decode-1 (cdr bits) next-branch set))))) 
                    
              
  (reverse (decode-1 bits tree '())))

;2.d

;;(decode sample-code sample-tree) --> (ninjas fight ninjas by night)

;2.e
(define (encode melding tree)
  (if (null? melding)
      '()
      (append (encode-symbol (car melding) tree) (encode (cdr melding) tree))))

(define (encode-symbol symbol tree)  
  (if (leaf? tree)
      '()
      ((lambda (left-symb bit-sekvens)
         (cons bit-sekvens (encode-symbol symbol (choose-branch bit-sekvens tree))))
       (symbols (left-branch tree)) (if (member? eq? symbol (symbols (left-branch tree))) 0 1))))

;2.f

(define (grow-huffman-tree pairs)
  (define (grow nodes) 
    (if (null? (cdr nodes))
        (car nodes) 
        ((lambda(left-sym right-symb holder)

           (grow (adjoin-set holder (cddr nodes))))
         (car nodes) (cadr nodes) (make-code-tree (car nodes) (cadr nodes)))))
    
  (grow (make-leaf-set pairs)))

  
(define freqs '((a 2) (b 5) (c 1) (d 3) (e 1) (f 3)))
(define codebook (grow-huffman-tree freqs))


;2.g

(define alfabet '((samurais 57) (ninjas 20)
                                (ﬁght 45) (night 12) (hide 3) (in 2) (ambush 2) (defeat 1)
                                (the 5) (sword 4) (by 12) (assassin 1) (river 2) (forest 1)
                                (wait 1) (poison 1)))

(define codebook2 (grow-huffman-tree alfabet))

(define melding 
  '(ninjas fight ninjas fight ninjas ninjas
           fight samurais samurais fight samurais
           fight ninjas ninjas fight by night))


; Det brukes 43 bits
(length (encode melding codebook2)) ; retuneres hvor mange bits brukte.

; Gjennomsnittlige lengden på hvert kodeord som brukes er  2 9/17:

(length melding) ; retunere lengden av melding

( / (length (encode melding codebook2)) (length melding)) ; retunerer Gjennomsnittlige lengden på hvert kodeord som brukes

;Den minste antall bits man ville trengt for å kode meldingen med enkode med fast lengde
; (fixed-length code) over det samme alfabetet er 68.
;Fordi den representerer hvert symbol i meldingen samme antall bit. så det er log2(n) bit per symbol.
;Her har vi 16 symboler dermed blir det log2(16) = 4 , 4 bit per symbol. altså er lengden av melding er 17.
; da regne vi 4 * 17 = 68. Så meldingen skal brukes 68 bits med (fixed-length cod.



;2.h

(define (huffman-leaves tree)
  (if (null? tree)
      '()
      (if(leaf? tree)                     
         (cons (cdr tree) '())
         (append (huffman-leaves (left-branch tree))
                 (huffman-leaves (right-branch tree))))))


(huffman-leaves sample-tree)


;2.i

(define (ccdr3 x)
  (car(cdr(cdr(cdr x)))))


(define (expected-code-length tree)

  (if (null? tree)
      '()
      ((lambda(all-weight)
    
         (define (ex-co-le subtree)
      
           (if (leaf? subtree)
          
               (* (/ (car(cdr (cdr subtree)) ) all-weight) ;;  p(si) = freq. per symbol / total freq. på alle symboler. 

                  (length (encode (cons (cadr subtree) '()) tree))) ;;lengden av antall bits

               (+ (ex-co-le (left-branch subtree))  ; venstre brach

                  (ex-co-le (right-branch subtree))))) ; Høyre brach
    
         (ex-co-le tree)) (ccdr3 tree))))  

