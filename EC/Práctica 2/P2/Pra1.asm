.model small

.stack 100h

.data

    cadena db 5,0,0,0,0,0,0
    peso db 8,4,2,1
    ;sig_neg db '-' 
    ;dosPuntos db ':'
    ;c2 db 'C2'
    
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
    
    ;Calculo de C2
    mov al,cadena[2]
    cmp al,1
    jz negativo
    mul peso[0]
    mov dl,al
    
    mov al,cadena[3]
    mul peso[1]
    add dl,al
    
    mov al,cadena[4]
    mul peso[2]
    add dl,al
    
    mov al,cadena[5]
    mul peso[3]
    add dl,al    
    jmp continuar
       
negativo:

    mul peso[0]
    mov dl,al
    
    mov al,cadena[3]
    mul peso[1]
    sub dl,al
    
    mov al,cadena[4]
    mul peso[2]
    sub dl,al
    
    mov al,cadena[5]
    mul peso[3]
    sub dl,al 
    
continuar:    
    
    ;Calculo de BNS
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
    

    mov al,dl
    mov bl,10
    
    div bl ; ax/bl
    mov dl,al ; en al el cociente
    add dl,48
    mov dh,ah ; en ah el resto
    add dh,48  
    
    mov ah,0
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
    mov al, 'B'
    mov es:[di],ax  
    mov al, 'N'
    mov es:[di+2],ax
    mov al, 'S'
    mov es:[di+4],ax
    mov al, ':'
    mov es:[di+6],ax
    mov al,cl ; caracter a escribir
    mov es:[di+8],ax ; escritura del caracter
    mov al,ch ; caracter a escribir
    mov es:[di+10],ax ; escritura del caracter       
    
    
    mov bl, cadena[2]
    cmp bl,1
    jz pinta_menos  
    mov al,'C' ; caracter a escribir
    mov es:[di+14],ax ; escritura del caracter
    mov al,'2' 
    mov es:[di+16],ax
    mov al, ':' 
    mov es:[di+18],ax 
    mov al,dl ; caracter a escribir
    mov es:[di+20],ax ; escritura del caracter
    mov al,dh ; caracter a escribir
    mov es:[di+22],ax ; escritura del caracter
    jmp salida
    
pinta_menos:

    mov al,'C' ; caracter a escribir
    mov es:[di+14],ax ; escritura del caracter
    mov al,'2' 
    mov es:[di+16],ax
    mov al, ':' 
    mov es:[di+18],ax  
    mov al, '-'
    mov es:[di+20],ax
    mov al,dl ; caracter a escribir
    mov es:[di+22],ax ; escritura del caracter
    mov al,dh ; caracter a escribir
    mov es:[di+24],ax ; escritura del caracter
    
salida:
    
    
    mov ah,00h ; permite que se quede la pantalla congelada
    int 16h
    
fin:
    mov ah,4ch
    int 21h
end                                               