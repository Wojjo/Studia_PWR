CZAS EQU        50    
LOAD EQU        (65536 - 1000*CZAS)

READ_STATUS	EQU 0FF2EH
CMD_WRITE	EQU 0FF2CH
DATA_WRITE	EQU 0FF2DH

INIT	EQU 038H
CLEAR	EQU 001H
WLACZ	EQU 06H

LICZNIK	EQU 34H
SEC		EQU 31H
MIN		EQU 32H
HOUR	EQU 33H

SEC_CHG EQU 0

ORG 0
    LJMP    START ; kod nie zmiesci sie w 0-0BH

;----------------------------------------------------------------
;Procedura realizujaca licznik
;----------------------------------------------------------------
ORG 0BH
    MOV TH0,#HIGH(LOAD)     
    MOV TL0,#LOW(LOAD) 

    DJNZ LICZNIK, KON_1
    MOV LICZNIK, #20

	PUSH PSW
	PUSH ACC

    SETB SEC_CHG

	INC SEC
    INC LICZNIK
    MOV A, SEC
    CJNE A, #60, KONIEC
    MOV SEC, #0
    INC MIN
    MOV A, MIN
    CJNE A, #60, KONIEC
    MOV MIN,    #0
    INC HOUR
    MOV A, HOUR
    CJNE A, #24, KONIEC
    MOV HOUR, #0
    
KONIEC:
	POP ACC  
	POP PSW  
KON_1:
    RETI
;----------------------------------------------------------------------
;Przesylanie danych do ekranu
;----------------------------------------------------------------------
START:
	LCALL INIT_LCD
	LCALL init_timer

LOOP:
	JNB SEC_CHG, $ 
	CLR	SEC_CHG
	LCALL WRITE_TIME

    SJMP    LOOP
        
;----------------------------------------------------------------------
init_timer:
    MOV LICZNIK, #20		   	;20*50 ms = 1s
    MOV SEC, #0
    MOV MIN, #0
    MOV HOUR, #0
    
    SETB SEC_CHG

    CLR TR0
    MOV TMOD, #00000001B
    MOV TH0,#HIGH(LOAD)     
    MOV TL0,#LOW(LOAD) 
    CLR TF0        				;zeruj przepelnienie
    SETB ET0    				;ustawienie przerwania timera 0
    SETB EA        				;wlaczenie ukladu przerwan
    SETB TR0   
    RET

;----------------------------------------------------------------------
WRITE_CMD:
    PUSH ACC 					;spychamy akumulator na stos, by nie stracic danych
    MOV DPTR, #READ_STATUS 		;adres statusu
petla1: 						;petla sprawdza zajetosc wyswietlacza
    MOVX A, @DPTR 				;przesylamy wartosc z adresu w DPTR do akumulatora, by przeslac komende
    JB ACC.7, petla1
    MOV DPTR, #CMD_WRITE 		;przeslanie adresu linii sterowania
    POP ACC
    MOVX @DPTR, A 				;przeslanie akumulatora na linie sterowania
    RET

;----------------------------------------------------------------------
WRITE_CHAR:
    PUSH ACC 					;spychamy akumulator na stos, by nie stracic danych
    MOV DPTR, #READ_STATUS 		;adres statusu
petla2: 				     	;petla sprawdza zajetosc wyswietlacza
    MOVX A, @DPTR 				;przesylamy wartosc z adresu w DPTR do akumulatora, by przeslac komende
    JB ACC.7, petla2 			;sprawdzenie, czy flaga zajetosci wyswietlacza
    MOV DPTR, #DATA_WRITE 		;przeslanie adresu linii zapisu danych
    POP ACC
    MOVX @DPTR, A  				;przeslanie akumulatora na linie zapisu danych
    RET 

;----------------------------------------------------------------------
INIT_LCD:
    MOV A, #INIT 				;kod inicjalizacji wyswietlacza
    LCALL WRITE_CMD
    MOV A, #CLEAR 				;kod czyszczenia wyswietlacza
    LCALL WRITE_CMD
    MOV A, #WLACZ 				;kod wlaczenia wyswietlacza
    LCALL WRITE_CMD


    RET

;----------------------------------------------------------------------
WRITE_DEC:
    MOV B, #10 					;dzielenie przed podstawe systemu
    DIV AB     				  	;by podzielic liczbe na czesc dziesiatek i jednosci
    ADD A, #'0' 				;dodajemy 48, by otrzymac kod ascii cyfry
    LCALL WRITE_CHAR
    MOV A, B   					;to samo dla reszty z dzielenia
    ADD A, #'0'
    LCALL WRITE_CHAR
    RET

;----------------------------------------------------------------------
WRITE_TIME:

	MOV A, #CLEAR 				;kod czyszczenia wyswietlacza
    LCALL WRITE_CMD
	MOV A, HOUR         		;przesylanie godzin do akumulatora
	LCALL WRITE_DEC 			;wywolanie procedury wyswietlajacej liczbe dziesietna
    MOV A, #':'	   			
    LCALL WRITE_CHAR 			;wywolanie procedury wypisujacej znak na ekranie
	MOV A, MIN	     			;przesylanie minut do akumulatora	
    LCALL WRITE_DEC
    MOV A, #':'
    LCALL WRITE_CHAR
	MOV A, SEC					;przesylanie sekund do akumulatora
	LCALL WRITE_DEC

	RET

END
