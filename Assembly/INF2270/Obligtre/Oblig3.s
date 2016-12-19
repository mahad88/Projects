
#ALL THE TESTS ARE WORKING
#I HAVE COMMENTED ON MOST PART OF THE CODE.
#I HOPE IT WILL BE EASY TO UNDERSTAND.


.extern	fread, fwrite
.data
pointer: .long 0

	 .text
.globl	readbyte
 # Navn:	readbyte
 # Synopsis:	Leser en byte fra en binÃ¦rfil.
 # C-signatur: 	int readbyte (FILE *f)
 # Registre:
	
#_readbyte:0
#	pushl	%ebp		# Standard funksjonsstart
#	movl	%esp,%ebp	#

#rb_x:	popl	%ebp		# Standard
	 #ret			# retur.



# sprinter:
readbyte:
	
	pushl	%ebp		 # Standard	
	movl	%esp, %ebp 	# Standard
	pushl %ebx
   	
	leal pointer,%ebx			# pointer to block memory 
	
	pushl 8(%ebp)				#File pointer which is the first parameter
	pushl $1
	pushl $1	
	pushl %ebx
	call fread				#call fread function
	addl $16, %esp
	popl %ebx
	cmpl $0, %eax
	jle rb_x 				# EOF done.
	js continue
	
	
            
	
	continue:
	movl pointer,%eax 
	popl	%ebp	# Standard		
	ret

	rb_x:
	movl $-1,%eax
      	popl	%ebp
	ret
	




.globl	writebyte
 # Navn:	writebyte
 # Synopsis:	Skriver en byte til en binÃ¦rfil.
 # C-signatur: 	void writebyte (FILE *f, unsigned char b)
 # Registre:

writebyte:
	
	
	pushl	%ebp		# Standard funksjonsstart
	movl	%esp,%ebp	#
	
	
	pushl 	8(%ebp)		#File pointer which is the first parameter
	pushl	$1
	pushl	$1
	leal   12(%ebp), %ecx   ##char b which is the second parameter
	pushl   %ecx
	call fwrite				#call fread function
	
	addl $16,%esp   

	
	popl	%ebp	         # Standard
	
	ret		 # retur.



.globl	readutf8char

 # Navn:	readutf8char
 # Synopsis:	Leser et Unicode-tegn fra en binÃ¦rfil.
 # C-signatur: 	long readutf8char (FILE *f)
 # Registre:
	
readutf8char:
	pushl	%ebp		# Standard funksjonsstart
	movl	%esp,%ebp	#
	
	pushl    %ebx
	
	
	pushl 8(%ebp)
	call readbyte 
	
 	
        cmpl          $0x80,%eax     # compare    $10000000 to %eax 
	jl		one_r 
	jmp     two_r
 	


one_r:

	
	addl 	$4, %esp
	jmp end_rutf	


two_r:	
	cmpl           $0xdf,%eax	 # compare    $11011111 to %eax 
	jle	 to_r
	jmp three_r			
		
		
    to_r:
    #######Leading byte######

	
	andl $0x1f, %eax		#mask(=00011111 &xxxxxxxx)=000xxxxx ->%eax
	sall $6, %eax			#shift left the leading byte =xxxxx000000
	movl %eax, %ebx
	
	addl $4, %esp
	
  ####### Continuation byte######

	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax	#mask eax=(00111111 & xxxxxxxx)=00xxxxxx ->%eax

	#linking the two bytes
	orl %ebx, %eax  	# (=xxxxx000000  | 00xxxxxx)= xxxxxxxxxxx -> %eax

	addl $4, %esp
	

	jmp end_rutf	

three_r:	
	cmpl           $0xef,%eax	 # compare    $11101111 to %eax 
	jle	 tre_r
	jmp four_r
	
	
	 tre_r:
  #######Leading byte######
	
	andl $0xf, %eax		#mask=(00001111 &xxxxxxxx)=0000xxxx -> %eax
	sall $12, %eax		#shift left eax 12=(xxxx000000000000)
	movl %eax, %ebx
	addl $4, %esp
	

 ####### Continuation byte######
	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax	#mask =00111111 &xxxxxxxx=00xxxxxx
	sall $6, %eax		# shift left #eax =(00xxxxxx000000)

	#linking the leading and continuation byte 1 
	orl %eax, %ebx		#= (xxxxxx000000|xxxx000000000000)=xxxxxxxxxx000000
	addl $4, %esp
	
 ####### Continuation byte######
	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax	#mask=00111111 &xxxxxxxx=00xxxxxx
	
	#linking the last byte to the firsr two.
	orl %ebx, %eax		#= (xxxxxxxxxx000000  |00xxxxxx)=xxxxxxxxxxxxxxxx
	addl $4, %esp

	jmp end_rutf	

four_r:	
	
	cmpl           $0xf7,%eax	 # compare    $11110111 to %eax 
	jle	 for_r
	jmp end
	
	
	for_r:
 #######Leading byte######

	andl $0x7, %eax		#mask =(00000111 &xxxxxxxx)=00000xxx	
	sall $18, %eax		#shift left %eax=(xxxx000000000000000000)
	movl %eax, %ebx
	addl $4, %esp
	

  ####### Continuation byte 1 ######
	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax	#mask=(00111111 &xxxxxxxx)=00xxxxxx ->%eax	
	sall $12, %eax		#shift left eax=(xxxxxx000000000000)
	
	
	#linking the leading and continuation byte 1 
	orl %eax, %ebx		#= (xxxxxx000000000000|xxxx000000000000000000) = xxxxxxxxxx000000000000
	addl $4, %esp

  ##### Continuation byte 2 ######
	
	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax	#mask=(00111111 &xxxxxxxx=00xxxxxx)
	shll $6, %eax		#shift left eax=(00xxxxxx000000)
	

	#linking continuation byte 2 wth the first 2  bytes
	orl %eax, %ebx		#=00xxxxxx000000 | xxxxxxxxxx000000000000=xxxxxxxxxxxxxxxx000000
	addl $4, %esp


 ##### Continuation byte 3 ######
	pushl 8(%ebp)
	call readbyte
	andl  $0x3f, %eax		#mask %eax=(00111111 &xxxxxxxx)=00xxxxxx
	
	#linking continuation byte 3 wth the first 3 bytes 
	orl %ebx, %eax		#= (xxxxxxxxxxxxxxxx000000| 00xxxxxx)=xxxxxxxxxxxxxxxxxxxxxx

	addl $4, %esp

	jmp end_rutf	
	

end_rutf:
	popl %ebx
      	popl	%ebp
	ret
	




.globl	writeutf8char
 # Navn:	writeutf8char
 # Synopsis:	Skriver et tegn kodet som UTF-8 til en binÃ¦rfil.
 # C-signatur: 	void writeutf8char (FILE *f, unsigned long u)
 # Registre:
	
writeutf8char:

		pushl	%ebp		# Standard funksjonsstart
		movl	%esp,%ebp	#
	

		movl 		12(%ebp),%edx
		cmpl		$0x80,%edx  	# compare %edx with 128
		jl		one_w
		cmpl		$0x07ff,%edx	# compare %edx with 2047
		jle		two_w
		cmpl		$0xffff, %edx	# compare %edx with 65535
		jle		three_w
		cmpl		$0x10ffff, %edx	# compare %edx with 1,114,111
		jle		four_bytes

one_w: 
		
			
		pushl 12(%ebp)  #char b which is the second parameter
		pushl 8(%ebp)	#File pointer which is the first parameter
	
		call    writebyte
		jmp 		end_wuft
			
	
two_w:
		
	####### Leading byte######	
		movl		12(%ebp), %eax

		shrl		$6, %eax    	#shift right %eax =(000000xxxxxxxx)
		andl		$0x1f,%eax	#mask  %eax(00011111 &000000xxxxxxxx)= 000000000xxxxx
		orl		$0xC0, %eax	#mask (11000000 | 00000000xxxxx)=110xxxxx
		
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
	
		addl 		$8, %esp

	####### Continuation byte  ######
		movl 		12(%ebp),%eax
				
		andl		$0xbF, %eax	#mask=(10111111 & xxxxxxxx)=x0xxxxxx		
		orl		$0x80, %eax	#10000000   |x0xxxxxx=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte

		jmp 		end_wuft
 			
		
three_w:
	
		#####  Leading byte   ####	
		movl		12(%ebp), %eax
		shrl		$12, %eax		#shift right %eax=000000000000xxxxxxxx)
		andl		$0xf,%eax	#mask  %eax(00001111 &000000000000xxxxxxxx)= 0000000000000000xxxx			
		orl		$0xE0, %eax              #=mask =(11100000 |0000000000000000xxxx)=1110xxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
		addl 		$8, %esp
		
	####### Continuation byte   ######
		movl 		12(%ebp),%eax
		shrl		$6, %eax		#shift right %eax =(000000xxxxxxxx)		
		andl		$0x3F, %eax		#mask= (00111111 & xxxxxxxx)=00xxxxxx	
		orl		$0x80, %eax		#10000000   |00xxxxxx=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
		addl 		$8, %esp
		
	####### Continuation byte   ######
		movl 		12(%ebp),%eax
		andl		$0x3F, %eax	    #mask= (00111111 & xxxxxxxx)=00xxxxxx		
		orl		$0x80, %eax	    #=(10000000 |00xxxxxx)=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte

		jmp 		end_wuft


four_bytes:

	#####  Leading byte   ####	
		movl		12(%ebp), %eax		
		
		shrl		$18, %eax	#shift right %eax=000000000000000000xxxxxxxx)
		andl		$0x7, %eax	#mask %eax(00000111 &000000000000xxxxxxxx)= 00000000000000000000000xxx		
		orl		$0xF0, %eax	#=(11110000 |00000000000000000000000xxx)=11110xxx
		
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
		addl 		$8, %esp
		
	####### Continuation byte   ######
		movl 		12(%ebp),%eax	
		shrl		$12, %eax	#shift right %eax=000000000000xxxxxxxx)		
		andl		$0x3F, %eax	#mask= (00111111 &000000000000xxxxxxxx=00000000000000xxxxxx			
		orl		$0x80, %eax	#=(10000000 |00000000000000xxxxxx)=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
		addl 		$8, %esp

	####### Continuation byte   ######
		movl 		12(%ebp),%eax

		shrl		$6, %eax	#shift right %eax=000000xxxxxxxx)			
		andl		$0x3F, %eax	#mask= (00111111 &000000xxxxxxxx=00000000xxxxxx			
		orl		$0x80, %eax	#=(10000000 |00000000xxxxxx)=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte
	
		addl 		$8, %esp
		
	####### Continuation byte  ######
		movl 		12(%ebp),%eax

		andl		$0x3F, %eax	#mask= (00111111 &xxxxxxxx)=00xxxxxx			
		orl		$0x80, %eax	#=(10000000 |00xxxxxx)=10xxxxxx
		pushl		%eax
		pushl 		8(%ebp)
		call 		writebyte

		jmp 		end_wuft
	

end_wuft:	
	addl 	$8, %esp	
	popl	%ebp			# Standard
	ret			        # retur..



