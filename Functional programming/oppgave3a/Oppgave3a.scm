

; Mahad hundub abdullahi


; ********  1. Strømmer  ************

(load "prekode3a.scm")

;; 1.a

(define (list-to-stream  items)
  (if(null? items)
     the-empty-stream
     (cons-stream (car items) (cdr items))))



(define (stream-to-list stream . args)
  (define (stream-iter stream n)
    (if (or (stream-null? stream) (zero? n)) 
        '()
        (cons (stream-car stream)
              (stream-iter (stream-cdr stream) (- n 1)))))
  (stream-iter stream
               (if (null? args) -1
                   (car args))))

;; 1.b
(define (stream-map proc . argstreams)
  (if (stream-null? argstreams)
      the-empty-stream
      (cons-stream

       (apply proc (map stream-car argstreams))
       (apply stream-map
              (cons proc (map stream-cdr argstreams))))))


;; 1.c

;; Et potensiel problem kan være når vi sammenligner car element med elementene i cdr for
;; å finne duplicater kan det gå i en evig loop siden strømmer kan være uendelige.

;; 1.d

(define x

  (stream-map show
              (stream-interval 0 10)))
;; ---> 0
;; Dette er pga utsatt evaluering av strømmer den bare viser stream-car

;; (stream-ref x 5)
;; ---> 1,2,3,4,5,5
;; stream-ref fungerer slik at den tvinger frem alt i strømmen.

;; (stream-ref x 7)
;; ---> 6,7,7
;; dette er pga 1-5 er allerede utført og trenges ikke vises.



; ********  2. Språkmodeller (basal ‘maskinlæring’)  ************



;; 2.a


(define (make-lm)
  (list '*table* ))


(define (lm-lookup-bigram lm string1 string2)
  (let ((firststr
         (assoc string1 (cdr lm))))
    (if firststr
        
        (let ((secondstr
               (assoc string2 (cdr firststr))))
          (if  secondstr
               (cdr secondstr) #f))
        #f)))

(define (lm-record-bigram!  lm string1 string2)
  ((lambda (count)
     (let ((firststr
            (assoc string1 (cdr lm))))
       (if firststr
           (let ((secondstr
                  (assoc string2 (cdr firststr))))
             (if secondstr
                 (set-cdr! secondstr (+ (cdr secondstr) 1) )
                 (set-cdr! firststr
                           (cons (cons string2 count)
                                 (cdr firststr)))))
           (set-cdr! lm
                     (cons (list string1 (cons string2 count))
                           (cdr lm)))))
     )1))





;; 2.b


(define (lm-train! nytable table)
  (if (null? table)
      '()
      (begin
        (let* ((sentence (car table))
               (rest (cdr table)))
          (lm-record-sentence! nytable sentence)
          (lm-train! nytable rest)))))


;;Den er hjelp prosedyren setter inn setninger, 
;;fordi vi har delt opp tablen flere setninger og sette in hver for seg.

(define (lm-record-sentence!  table sentence)
  ;; iterere over setning
  (if(null? sentence) '()
     (let ((first-word (car sentence)))
        (if (pair? (cdr sentence))
            (begin 
              (let((second-word (cadr sentence))
       
                 (rest-of-sentence (cdr sentence)))
                 (lm-record-bigram! table first-word second-word)
                 (lm-record-sentence! table rest-of-sentence)))))))



(define corpus-brown (read-corpus "brown.txt"))
  
(define lm-brown (make-lm))

;(lm-train! lm-brown corpus-brown)
        

;; 2.c


(define (lm-lookup-unigram table str)
   (if (null? table)
      '()
      (begin
        (let* ((sentence (cdr table))
               (rest (cdr table)))
         
          (lm-record-sent-unigram! str sentence)
          (lm-lookup-unigram rest str)))))
  
;; den også hjelpe prosedyre som gjør dele opp tabelen i flere setninger.
(define (lm-record-sent-unigram!  str sentence)
  
  (if(null? sentence) '()

      (let ((first-word (car sentence)))
        (if (pair? (cdr sentence))
            
            (begin
             
              (let((second-word (cadr sentence))
       
                (rest-of-sentence (cdr sentence)))
                
                 (count-frequency str '(first-word second-word))
                (lm-record-sent-unigram! str rest-of-sentence)
                ))))))
; denne prosedyre tar string og list og retuneren antall frekomster av stringen.
(define count-frequency
  (lambda (x ls)
    (cond
      ((memq x ls) =>
       (lambda (ls)
         (+ (count-count-frequency x (cdr ls)) 1)))
      (else 0))))
  
;; 2.d

;; 2.e




;; i oppgaven har vi valgte første å gjøre et naive implementasjon av lister
;; men senere når vi fikk noe tips fra piazza prøvde vi å implementere binær søke tre
;; men vi får ikke tid til dette. neden er binær søke tre structuren.

(define (make-lm2)
  (list '() '()))

  
  (define (make-record key value) 
    (cons key value))
  
  (define (record-key record) 
    (car record))
  
  (define (record-value record) 
    (cdr record))
  
  (define (entry tree) 
    (car tree))
  
  (define (the-left-branch tree) 
    (cadr tree))
  
  (define (the-right-brach tree) 
    (caddr tree))
  
  (define (make-tree entry left right)
    (list entry left right))

;; denne prosedyret settes verdi i et binær søke tree
;; tests om verdiene er lik eller ikke og settes riktig plass
;; venstre eller høyre side av treet.
  (define (insert-table verdi table)
    (cond ((null? table) (make-tree verdi '() '()))
          
          ((string=? (record-key verdi) (record-key (entry table)))
           table)
          
          ((string<=? (record-key verdi) (record-key (entry table)))
            
           (make-tree (entry table)
                      (insert-table verdi (the-left-branch table))
                      (the-right-brach table)))
          (else
           (make-tree (entry table)
                      (the-left-branch table)
                      (insert-table verdi (the-right-brach table))))))

