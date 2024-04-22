.MODEL small

.STACK 100H

.DATA

	

.CODE
	MOV Ax,@DATA
	MOV DS, AX

	mov al,03h
	mov ah,00h
	int 10h
	mov ax,0b800h
	mov es,ax
	
	mov di,0
	mov ah,00001111b ; formato del caracter 
    mov al, 'B'
	
	volver:
    mov es:[di],ax 
	ADD di,2
	jmp volver
	MOV AH, 4Ch
	INT 21h
END