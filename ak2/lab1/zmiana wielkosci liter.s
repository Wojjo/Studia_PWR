SYSCALL32 = 0x80 # sysfun: nr funkcji w %eax, parametry: %ebx, %ecx, %edx
EXIT = 1 #nr funkcji restartu (=1) – zwrot sterowania do s.o.
STDIN = 0 # nr wejścia standardowego (klawiatura) do %ebx
READ = 3 # nr funkcji odczytu wejścia (=3)
STDOUT = 1 # nr wyjścia standardowego (ekran tekstowy) do %ebx
WRITE = 4 # nr funkcji wyjścia (=4)
BUF_SIZE = 254 # rozmiar bufora (w bajtach/znakach ASCII) – max 254

.data 
    txt_size: .long 0            # zmienna robocza, long sklada, wyrownuje do pelnego slowa ?
    BUF: .space BUF_SIZE         # deklaracja nieinicjowanego bufora wejścia, space wypełnia puste pola zerami
    MASK: .int 32                # maska 100000

.text
.globl _start
_start:
    #wczytanie z klawiatury
    movl $BUF_SIZE, %edx
    movl $BUF, %ecx
    movl $STDIN, %ebx
    movl $READ, %eax
    int $SYSCALL32
    movl %eax, txt_size

    #inicjalizacja wskaznika
    movl $0, %edi

modify:
    movb BUF(%edi), %al #pobierz znak z bufora 
    movb $'\n', %cl
    cmp %al, %cl
    je write
    movb MASK, %cl
    xor %al, %cl 
    movb %cl, BUF(%edi)
    # warunek wyjscia z petli 
    inc %edi
    cmp %edi, txt_size
    je write
    jmp modify


write:
    movl $WRITE, %eax
    movl $STDOUT, %ebx
    movl $BUF, %ecx
    movl txt_size, %edx
    int $SYSCALL32

out:
	movl $EXIT , %EAX
	int $SYSCALL32
