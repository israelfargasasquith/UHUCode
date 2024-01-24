%Pintamos un bloque por ahí fino señore 😎
Angulos = [pi/4,pi/2,pi/4]
Posicion=[7,8,9]
%Matriz_1=Desplazamiento(7,8,9)*Rotacionz(pi/4)*Rotaciony(pi/2)*Rotacionz(pi/4)
Matriz_configuracion = Desplazamiento(Posicion(1),Posicion(2),Posicion(2))*Rotacionz(Angulos(1))*Rotaciony(Angulos(2))*Rotacionz(Angulos(3))
figure();
xlabel("x")
ylabel("y")
zlabel("z")
view(3);
pinta_bloque(Matriz_configuracion,'g')
%El bloque tiene de distancia 3 en las rectas cortas y 6 en las largas.
%La operacion para localizar y modificar los puntos dek rectangulo hay que
%calcular la Posicion Global (Pg = Tl * Pl) siendo Tl la matriz de
%transformacion 4x4 y Pl el punto que queremos localizar(en nuestro caso 
% pintar un * en un punto 🔥) 
Plocal1 = [1.5,1.5,6,1]' %Coordenadas locales del punto
Plocal2 =[-1.5,1.5,6,1]'
Plocal3 =[1.5,-1.5,6,1]'
Plocal4 =[-1.5,-1.5,6,1]'

Pglobal1 = Matriz_configuracion*Plocal1
Pglobal2 = Matriz_configuracion*Plocal2
Pglobal3 = Matriz_configuracion*Plocal3
Pglobal4 = Matriz_configuracion*Plocal4

plot3(Pglobal1(1),Pglobal1(2),Pglobal1(3),"*k")
plot3(Pglobal2(1),Pglobal2(2),Pglobal2(3),"*k")
plot3(Pglobal3(1),Pglobal3(2),Pglobal3(3),"*k")
plot3(Pglobal4(1),Pglobal4(2),Pglobal4(3),"*k")


