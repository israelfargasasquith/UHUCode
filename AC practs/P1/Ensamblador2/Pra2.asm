.MODEL SMALL

.CODE

    org 100h

Programa_Int:
    jmp Reside
    contador db 0
	cont_seg db 48,48
	cont_mins db 48,48

Rutina_Servicio PROC
    CLI
    
    
    mov ax,0b800h 
    mov es,ax
    
    
    mov di,150 
    
        
    mov ah,00001111b 
    mov al, cont_mins[0]
    mov es:[di],ax  
    mov al, cont_mins[1]
    mov es:[di+2],ax
    mov al, ':'
    mov es:[di+4],ax
    mov al, cont_seg[0]
    mov es:[di+6],ax  
    mov al, cont_seg[1]
    mov es:[di+8],ax
        
    cmp contador,18
    jnz suma_cont
    sub contador,18
    cmp cont_seg[1],57
    jnz sumar_segs
    cmp cont_seg[0],53
    jz sumar_mins1
    inc cont_seg[0]
    sub cont_seg[1],9
    jmp continuar
    
sumar_mins1:
    cmp cont_mins[1],57
    jz sumar_mins2
    inc cont_mins[1]
    sub cont_seg[0],5
    sub cont_seg[1],9
    jmp continuar
    
sumar_mins2:
    inc cont_mins[0]
    sub cont_mins[1],9
    sub cont_seg[0],5
    sub cont_seg[1],9
    jmp continuar

sumar_segs:
    
    inc cont_seg[1]

    jmp continuar

suma_cont:
    
    inc contador

continuar:

    
    
    STI
    IRET
    ENDP

Reside: 
        

    mov dx, offset Rutina_Servicio
    mov ax, 0
    mov es, ax
    mov si, 1Ch*4
    CLI
    mov es:[si], dx
    mov es:[si+2], cs
    STI
    mov dx, offset Reside
    int 27h

end Programa_Int