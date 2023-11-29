%primer bloque
Matriz_1=Desplazamiento(5,5,5);
pinta_bloque(Matriz_1,'r')
%segundo bloque
Matriz_2=Desplazamiento(5,5,5)*Rotacionz(pi/4);
pinta_bloque(Matriz_2,'b')
%tercer bloque
Matriz_3=Desplazamiento(5,5,5)*Rotacionz(pi/4)*Rotacionx(pi/4);
pinta_bloque(Matriz_3,'c')