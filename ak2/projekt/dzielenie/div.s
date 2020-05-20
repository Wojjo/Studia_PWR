
.data
error_operation_prohibited: .ascii "Nie można dzielić przez zero!"
error_length = . - error_operation_prohibited

.text
.global division
.global subtract
.global compareNumbers
.global moveBitsLeft
.global moveBitsRight
.global copyArg1toArg2



   
#(dzielna, dzielnik, reszta_z_dzielenia, rozmiar)
.type division, @function
division:
    #arg1- dzielna, arg2- dzielnik, reszta_z_dzielenia, rozmiar, adr wyniku
	pushl %ebp							# ebp rejestr ramki
	movl %esp, %ebp						# wskaznik stosu 

	pushl %ebx
	pushl %edi
	
    movl 12(%ebp), %edi     			# adres dzielnika
    movl $0, %ecx       				# licznik (indeks) 
    
	check_arguments:
    cmpl 20(%ebp), %ecx                 # porownaj rozmiar dzielnej z ecx
    je end_check_arguments              # jesli rowne skocz
	
    cmpl $0, (%edi,%ecx,4)              # sprawdz czy dzielnik = 0
    jne not_zero						# skok jesli nierowne 
    
	incl %ecx							# ecx plus 1 
    jmp check_arguments                 # skocz do 
	
    end_check_arguments:
    # wyswietlenie bledu
    movl $4, %eax						# WRITE
    movl $1, %ebx						# STDOUT
    movl $error_operation_prohibited, %ecx  
    movl $error_length, %edx
    int $0x80							# SYSCALL32
	
    popl %edi
    popl %ebx
    movl %ebp, %esp
    popl %ebp
ret

    not_zero:
    #kopiuj dzielna do reszty_z_dzielenia
    pushl 20(%ebp)  					# rozmiar dzielnej
    pushl 16(%ebp)  					# reszta dzielenia 
    pushl 8(%ebp)   					# dzielna
    
	call copyArg1toArg2
    addl $12, %esp						
    movl $0, %ecx   					# licznik przesuniec
	
    #sprawdz czy najstarszy bit dzielnej =1
    movl 8(%ebp), %edi  				# adres dzielnej
    movl 20(%ebp), %edx 				# rozmiar dzielnej 
    decl %edx       					# rozmiar dzielnej -1
    movl $2147483648, %eax  			# 2^31, bin 10...0
    andl (%edi,%edx,4), %eax    		# koniunkcja, najstarszy bit =1 ??????
    jz scale    						# skok, jeśli zero
    movl 12(%ebp), %edi 				# adres dzielnika do edi
	
    scale_divider:
    movl $2147483648, %eax 				# 2^31, bin 10...0
    andl (%edi,%edx,4), %eax			# koniunkcja
    jnz end_scale       				# skok, jeśli nie zero
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 12(%ebp)  					# dzielnik
    call moveBitsLeft
	
    addl $8, %esp						
    incl %ecx							# ecx plus 1 
    jmp scale_divider
	
    #------
    #skalowanie dzielnej:
    #(adres 1 liczby, adres 2 liczby, rozmiar argumentow)
    scale:
    pushl 20(%ebp) 						# rozmiar argumentow
    pushl 12(%ebp)  					# dzielnik
    pushl 16(%ebp) 						# reszta_z_dzielenia
    call compareNumbers     			
    
	addl $12, %esp                      # dodanie 12 do esp
    cmpl $2, %eax                       # porownanie 2 z eax 
    je end_scale                        # jesli rowne skocz
    incl %ecx                           # ecx plus jeden
	
    #(adres arg, arg_size)
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 12(%ebp)  					# dzielnik
    call moveBitsLeft
	
    addl $8, %esp                       # dodanie 8 do esp
    jmp scale                           # skocz do petli 
    end_scale:
    #Dzielna przeskalowana
    #sprawdz i odejmij     				##########do tad dziala
	
    subtract_next:
    cmpl $0, %ecx       				# porownaj zero z ecx 
    je end_subtract                     # jesli rowne 
	
    # sprawdz czy mniejsze jezeli tak odejmij jezeli nie skaluj w prawo dzielnik
    # wynik przesun w lewo
    pushl 20(%ebp)  					# rozmiar
    pushl 24(%ebp)  					# wynik dzielenia
    call moveBitsLeft  
	
    addl $8, %esp                       # dodaj 8 do esp 
    pushl 20(%ebp) 						# rozmiar argumentow
    pushl 12(%ebp)  					# dzielnik
    pushl 16(%ebp) 						
	call compareNumbers 				
	
    addl $12, %esp                      # dodanie 12 do esp
    cmpl $2, %eax                       # porownaj 2 z eax 
    je greater							# jesli rowne to dzielnik wiekszy
	
    # dzielnik mniejszy -> odejmij,wynik+1, przeskaluj
    pushl %ecx  						# odejmowanie wykorzystuje ecx
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 16(%ebp)  					# adres wyniku
    pushl 12(%ebp)  					# adres 2 arg
    pushl 16(%ebp)  					# adres 1 arg
   
    call subtract    	                # modyfikuje rejestry #arg1-arg2 
    addl $16, %esp                      # dodanie 16 do esp
    popl %ecx
    movl 24(%ebp), %edi
    addl $1, (%edi)
    decl %ecx                           # ecx minus jeden
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 12(%ebp)  					# adres dzielnika
    
	call moveBitsRight
    addl $8, %esp                       
    jmp subtract_next                  
 
    greater:
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 12(%ebp) 						# adres dzielnika
    call moveBitsRight
	
    addl $8, %esp
    decl %ecx
    jmp subtract_next
    
	end_subtract:
    #Ostatni odejmowanie bez przesuwania dzielnika
    pushl 20(%ebp)  					# rozmiar
    pushl 24(%ebp)  					# wynik dzielenia
    call moveBitsLeft  
    
	addl $8, %esp
    pushl 20(%ebp)						# rozmiar argumentow
    pushl 12(%ebp)  					# dzielnik
    pushl 16(%ebp) 						# reszta_z_dzielenia     
    call compareNumbers 				
    
	addl $12, %esp
    cmpl $2, %eax                       # porownaj 2 z eax
    je end_division                     # jesli rowne to dzielnik wiekszy
    
	#dzielnik mniejszy -> odejmij,wynik+1
    pushl %ecx  						# odejmowanie wykorzystuje ecx
    pushl 20(%ebp)  					# rozmiar argumentow
    pushl 16(%ebp)  					# adres wyniku
    pushl 12(%ebp)  					# adres 2 arg
    pushl 16(%ebp)  					# adres 1 arg
    call subtract    		            # arg1-arg2	
    
	addl $16, %esp
    popl %ecx
    movl 24(%ebp), %edi
    addl $1, (%edi)
    decl %ecx
    
	end_division:
	popl %edi
	popl %ebx
	movl %ebp ,%esp
	popl %ebp
ret



#(adres 1 liczby, adres 2 liczby, rozmiar argumentow)		
.type compareNumbers, @function
compareNumbers:
    pushl %ebp
    movl %esp, %ebp
    subl $12, %esp
	
    pushl %edx
    pushl %edi
    pushl %ecx
	
    movl 8(%ebp), %edx 				   # adres 1 liczby
    movl 12(%ebp), %edi 			   # adres 2 liczby
    movl 16(%ebp), %ecx 			   # rozmiar argumentow
    decl %ecx                          # zmniejszenie o 1 ecx
	
    check_all:
    cmpl $0, %ecx                      # porownaj zero z ecx
    jl end_check_all                   # skocz jesli mniej
	
    movl (%edx,%ecx,4), %eax           # przenies pierwsza liczbe do eax
    cmpl (%edi,%ecx,4), %eax           # porownaj liczbe druga z pierwsza
    ja first_greater                   # liczba pierwsza wieksza
    jb secound_greater                 # liczba druga wieksza 
	
    decl %ecx                          # zmniejszenie o 1 ecx (indeks)
    jmp check_all                      # skocz do petli
	
    end_check_all:
    movl $0, %eax           		   # zwraca 0 gdy liczby sa rowne
    jmp end_compare                    # skocz do konca porownania
	
    first_greater:
    movl $1, %eax           		   # zwraca 1 gdy pierwsza wieksza
    jmp end_compare                    # skocz do konca porownania
    
	secound_greater:
    movl $2, %eax           		   # zwraca 2 gdy druga wieksza
    
	end_compare:
    popl %ecx
    popl %edi
    popl %edx
    movl %ebp, %esp
    popl %ebp
ret
		
#(Arg1, Arg2, rozmiar) 					
.type copyArg1toArg2, @function
copyArg1toArg2:
    pushl %ebp
    movl %esp, %ebp
    movl 8(%ebp), %edi					# arg1 do edi
    movl 12(%ebp), %edx 				# arg2 do edx
    movl $0, %ecx						# 0 do ecx
    
	copy:
    cmpl 16(%ebp), %ecx					# porownaj rozmiar argumentów z wskaznikiem ecx
    je end_copy         				# jesli rowne skocz
    movl (%edi,%ecx,4), %eax			# arg1 do eax
    movl %eax, (%edx,%ecx,4)			# eax do arg2 
    incl %ecx							# ecx plus 1 
    jmp copy							# skocz do petli 
	
    end_copy:
    movl %ebp, %esp
    popl %ebp
ret
	
#(adres arg, arg_size)					
.type moveBitsLeft, @function
moveBitsLeft:
    pushl %ebp
    movl %esp, %ebp
    subl $8, %esp
	
    pushl %edi
    pushl %ecx
    movl 8(%ebp), %edi                  # adres argumentu
    movl $1, %ecx                       # 1 do ecx
    shll $1, (%edi)                     # przesunięcie w lewo
    
	move_left:
    pushf								# zachowaj przeniesienie
    cmpl 12(%ebp), %ecx                 # porownaj rozmiar argumentu z ecx
    je end_move_left                    # jesli rowne skocz 
	
    popf						        # pobierz flage cf
    shll $1, (%edi,%ecx,4)              # zamiana z rcll ?????????????
    incl %ecx                           # ecx plus jeden 
    jmp move_left                       # skocz do petli
	
    end_move_left:
    popf
    popl %ecx
    popl %edi
    movl %ebp, %esp
    popl %ebp  
ret

#(arg1, arg1_size)						
.type moveBitsRight, @function
moveBitsRight:
    pushl %ebp
    movl %esp, %ebp
    subl $8, %esp
	
    pushl %edi
    pushl %ecx
    movl 8(%ebp), %edi 					# adres argumentu
    movl 12(%ebp), %ecx 				# rozmiar argumentow
    decl %ecx                           # zmniejszenie o 1 ecx
    shrl $1, (%edi,%ecx,4)              # przesunięcie w prawo
    decl %ecx                           # zmniejszenie o 1 ecx
    
	move_right:
    pushf           					# zachowaj przeniesienie
    cmpl $0, %ecx                       # porownaj zero i ecx
    jl end_move_right                   # skocz jesli mniejsze 
    
	popf       							# pobierz flage cf
    shrl $1, (%edi,%ecx,4)              # zamiana z rcrl ??????????????
    decl %ecx                           # zmniejszenie o 1 ecx
    jmp move_right                      # skocz do petli 
    
	end_move_right:
    popf
    popl %ecx
    popl %edi
    movl %ebp, %esp
    popl %ebp    
ret
 

#Funkcja odejmuje liczby o dowolnej wielokrotnosci 4bajtow
#Funkcja przyjmuje 4 argumenty: adres 1 skladnika , adres 2 skaldnika,
# adres wyniku, rozmiar argumentow
.type subtract, @function  					
subtract:
    pushl %ebp
    movl %esp, %ebp
    movl 8(%ebp), %edx  			   # adres arg1 do edx
    movl (%edx), %eax   			   # wartosc arg1 do eax
    movl 12(%ebp), %edx 			   # adres arg2 do edx
    subl (%edx), %eax   			   # odejmij arg2 - arg1
   
    movl 16(%ebp), %edx 			   # adres wyniku do edx
    movl %eax, (%edx)  				   # wartosc odejmowania do wyniku
    movl $1, %ecx       			   # 1 do ecx licznik 
   
    subtract_:  
    pushf                              # flagi na stos
    cmpl 20(%ebp), %ecx 			   # porownaj dlugosci argumentow
    je end_sub                         # jesli rowne skocz
    
	popf             				   # pobierz flagi ze stosu
    movl 8(%ebp), %edx   			   # adres arg1 do edx
    movl (%edx,%ecx,4), %eax 		   # wyraz arg1 zalezny od ecx przenies do edx
    movl 12(%ebp), %edx  			   # adres arg2 do edx
    sbbl (%edx,%ecx,4), %eax 		   # odejmowanie
    
	movl 16(%ebp), %edx 			   # adres wyniku do edx
    movl %eax, (%edx,%ecx,4) 		   # przenies wartosc do wyniku
    incl %ecx       				   # zwieksz licznik
    jmp subtract_                      # skocz do petli 
    
	end_sub:
    popf                               # ?????????????????
    movl %ebp, %esp
    popl %ebp
ret



