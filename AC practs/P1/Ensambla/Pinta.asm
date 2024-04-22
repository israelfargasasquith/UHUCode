.MODEL SMALL ;reserva 64kposiciones de memoria para datos y 64kposiciones para codigo
.DATA
		CADENA DB '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456*'
		CURSOR DW 0
		LINEA DW 0
		Formato DB 00000110b;01111000b
.CODE
	MOV AX, SEG CADENA 
	MOV DS, AX ;Inicializamos el segmento de DATOS con el registro DataSegment
	MOV BX,0B800H
	MOV ES,BX ;Accedemos a la zona de memoria en modo texto
	MOV AL,03H
	MOV AH,0
	INT 10H ;Esta interrupcion permite que este en modo texto
	nuevoFormato:
		MOV CURSOR,0
		INC Formato
		MOV DI,0
		MOV SI, 0
		nuevaLinea:
			siguienteLetra:
				MOV AH, Formato
				MOV AL,CADENA[DI]
				MOV ES:[SI],AX ;Movemos el caracter correspondiente en cadena al segmento extra
				;MOV ES:[SI+1],AH
				ADD SI,2
				INC DI
				CMP CADENA[DI],'*'
			JNE siguienteLetra
			ADD CURSOR,160
			;ADD LINEA,1
			MOV SI, CURSOR
			MOV DI,0
			CMP CURSOR,4000
			;CMP LINEA,25 ;24*79		JMP nuevaLinea
		JNZ nuevaLinea
	JMP nuevoFormato
	
	
	;falta que pinte la pantalla de otro color para que sepamos en que ciclo de pantalla estamos
	MOV AH,0
	INT 16H ;Esta interrupcion espera hasta que introduzcamos un caracter ("pause")

	MOV AH,4CH
	INT 21H ;interrupcion para salir del programa
END