.model small

.data

    cadena db 5,0,0,0,0,0,0
    peso db 8,4,2,1
    
.code
    mov ax,seg cadena ; inicializacion del segmento de datos
    mov ds,ax
    
    mov dx, offset cadena ; captura de cadena de caracteres en variable cadena
    mov ah, 0ah
    int 21h
    
    sub cadena[2],48 ; conversion de ascii a valor
    sub cadena[3],48
    sub cadena[4],48
    sub cadena[5],48
    
;jmp fin

    mov al,cadena[2]
    mul peso[0]
    mov cl,al
    
    mov al,cadena[3]
    mul peso[1]
    add cl,al
    
    mov al,cadena[4]
    mul peso[2]
    add cl,al
    
    mov al,cadena[5]
    mul peso[3]
    add cl,al
    
; mov dl,1
; dec dl
; cmp dl,0
; jz fin

    mov al,cl
    mov bl,10
    
    div bl ; ax/bl
    mov cl,al ; en al el cociente
    add cl,48
    mov ch,ah ; en ah el resto
    add ch,48
    
    mov al,03h ; establecimiento modo de pantalla texto
    mov ah,00h
    int 10h
    
    mov ax,0b800h ; inicializacion segmento extra
    mov es,ax
    
    
    mov di,0 ; inicializacion registro de indexacion
    
    mov ah,00001111b ; formato del caracter
    mov al,cl ; caracter a escribir
    mov es:[di],ax ; escritura del caracter
    mov al,ch ; caracter a escribir
    mov es:[di+2],ax ; escritura del caracter
    
    mov ah,00h ; permite que se quede la pantalla congelada
    int 16h
    
fin:
    mov ah,4ch
    int 21h
end                                               