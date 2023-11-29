cla
clear

posicion = [20 -10 5]
alpha = 0
beta = pi/4
gama = 0

matriz_pinza=eye(4,4)*Desplazamiento(posicion(1),posicion(2),posicion(3))*Rotacionx(alpha)*Rotaciony(beta)*Rotacionz(gama)
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];
matriz=funcion_pinta_UR3_new(q, matriz_pinza)

matrizBloque = Desplazamiento(posicion(1),posicion(2),posicion(3))*Rotacionx(alpha)*Rotaciony(beta)*Rotacionz(gama)

pinta_bloque(matrizBloque,'m')