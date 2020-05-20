.file "multiply.s"
base = 10
.align 32
.data
.bss
	.lcomm length, 100000
	.lcomm lengthA, 100000
	.lcomm lengthB, 100000
	.lcomm carry, 100
.text
.globl multiply
.type multiply, @function
multiply:
	#preparing stack frame
	push %ebp                   # ebp rejestr ramki
	movl %esp, %ebp             # wskaznik stosu 

	#preserving register (ABI)
	push %edi
	push %esi
	push %ebx

	#move number A address
	movl 8(%ebp), %edi          # adres liczby A do edi 
	
	#move number A length
	movl 20(%ebp), %eax         # dlugosc liczby A do eax
	movl %eax, lengthA          # zapisanie dlugosci liczby A lengthA
	
	#move number B address
	movl 12(%ebp), %esi         # adres liczby B do esi 

	#move number B length
	movl 24(%ebp), %eax         # dlugosc liczby B do eax
	movl %eax, lengthB          # dlugosc liczby B do lenghtB

	#calculate result length
	movl 20(%ebp), %eax         # dlugosc liczby A do eax
	movl 24(%ebp), %edx         # dlugosc liczby B do edx
	addl %edx, %eax             # dodanie edx do eax
	movl %eax, length           # zapisanie dlugosci wyniku do length

	movl $0, %eax               # przypisanie zera do eax
	movl %eax, carry            # przypisanie zera do carry
	movl $0, %ebx               # zero do ebx i do ecx
	movl $0, %ecx

mulloop:
	movl (%edi,%ebx,4), %eax    # przeslij cyfre liczby A do eax
	mull (%esi,%ecx,4)          # mnozenie cyfr z liczby A i B
	addl carry, %eax            # dodaj przeniesienie 

	push %edi                   # edi na stos 
	push %esi                   # esi na stos 

	movl 16(%ebp), %edi         # przesunięcie o 16 bitów przesłane do edi

	movl %ebx, %esi             # ebx do esi 
	addl %ecx, %esi             # dodaj ecx do esi - indeks 

	addl (%edi,%esi,4), %eax    
		
	push %ecx                   
	movl $base, %ecx            # baza do ecx 
	divl %ecx                   # sprawdzamy czy jest przeniesienie
	pop %ecx                    # zdejmij ecx

	movl %edx, (%edi,%esi,4)    # przypisz (reszte z dzielenia) do wyniku 
	
	movl %eax, carry            # zapisz przeniesienie do carry

	pop %esi                    # zdejmij esi 
	pop %edi                    # zdejmij edi 

	inc %ebx                    # zwieksz wskaznik ebx
	movl lengthA, %eax          # przenies lengthA do eax 
    cmp %eax, %ebx              # porownaj dlugosc liczby A z wskaznikiem ebx
    jl mulloop                  # jesli wskaznik mniejszy to skocz do pętli mulloop

	push %edi                   # edi na stos 
	push %esi                   # esi na stos 
	
	movl 16(%ebp), %edi         # przesunięcie o 16 bitów przesłane do edi
		
	movl %ebx, %esi             # ebx do esi 
	addl %ecx, %esi             # dodanie 

	movl carry, %eax           
	addl %eax, (%edi,%esi,4)    # dodaj przeniesienie do tablicy
		
	pop %esi                    
	pop %edi

	inc %ecx                    # wskaznik drugiej liczby plus jeden
	movl $0, %eax               # przenies zero do eax
	movl %eax, carry            # zero do carry 

	movl $0, %ebx               # zero do ebx 
	movl 24(%ebp), %eax         # dlugosc liczby B do eax 
    cmp %eax, %ecx              # porownaj dlugosc liczby B z wskaznikiem ecx
    jl mulloop                  # jesli wskaznik mniejszy to skocz do pętli mulloop
	
	mov $0, %ecx                # zero do ecx
	movl $base, %ebx            # baza do ebx
	movl 20(%ebp), %eax         # dlugosc liczby A do eax
	movl 24(%ebp), %edx         # dlugosc liczby B do edx
	addl %edx, %eax             # dodanie eax i edx
	movl %eax, %ecx             # wartosc eax przenies do ecx 
	dec %ecx                    # zmniejszenie rejestru o 1
	movl 16(%ebp), %edi         

	pop %ebx
	pop %esi
	pop %edi
	movl %ebp, %esp
	pop %ebp
ret
