.MODEL SMALL
.CODE
     org 100h
Programa_Int:
     JMP Reside
contador_int db 0

Rutina_Servicio PROC
    CLI
    INC contador_int
    CMP contador_int, 90000   ; cuando el contador = 54 (3 segundos x 18 interrupciones/seg ), detiene todo y espera a que se pulse una tecla
    JNE fin                           ; si contador no es = 54, sale de la Rutina con IRET
    
    MOV AH, 0		
    INT 16h		; interrupción para parar todo y esperar a que se pulse una tecla
    
    MOV AL, 03h          
    MOV AH, 0
    INT 10h                 ; interrupción para poner el modo vídeo de pantalla. Después de que se pulse una tecla, se limpia la pantalla completa
   
    MOV AH, 4ch
    INT 21h               ; y finalmente se provoca esta interrupción para poner salir a MS-DOS
fin:
   STI
   IRET
ENDP

Reside: ;etiqueta para determinar la dirección siguiente a la última de la rutina  que debe quedar residente
  MOV DX, offset Rutina_Servicio
  MOV AX, 0
  MOV ES, AX
  MOV SI, 1ch*4
  CLI
  MOV ES:[SI], DX          ; en la dirección de memoria es:[si] se deja el offset de la Rutina de Servicio
  MOV ES:[SI+2], CS     ; en la dirección de memoria es:[si+2] se deja el valor del segmento de código CS
  STI
  MOV DX, offset Reside    ; 
  INT 27h                             ; esta interrupción deja residente todo el código que hay desde 'Reside' hacia arriba
END Programa_Int