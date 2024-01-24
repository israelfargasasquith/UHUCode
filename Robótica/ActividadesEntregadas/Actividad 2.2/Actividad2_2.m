% a) Para las distintas configuraciones de la pinza virtual, calculadas en cada uno de los
% apartados la guía anterior, encontrar las coordenadas articulares (q1, q2 y q3, q4, q5,
% q6) que hacen que el brazo sitúe su pinza coincidiendo con la pinza virtual y cogiendo
% correctamente la pieza. Pruebe a obtener cada una de las cuatro soluciones posibles.
% Si alguna configuración no es alcanzable, modifique la posición de la pieza hasta que
% sea alcanzable.

% codo: Permite configurar la solución del problema cinemático inverso con codo arriba
% (codo=1) o codo abajo (codo=-1).
% avance: Permite configurar la solución del problema cinemático inverso con la
% muñeca hacia adelante (avance=1) o hacia detrás (avance=-1).
% simétrico: Permite cambiar la configuración de la muñeca por otra equivalente cuando
% tenemos una solución degenerada: simetrico=0 proporciona la solución por defecto;
% simétrico =1 genera la solución con la configuración simétrica.

clc;
clear all;

posicionPieza=[20, -10, 0];
alfa = 0; beta = 0; gamma = 0;
codo = 1;
avance = 1;
simetrico = 0;

matrizPieza = Desplazamiento(posicionPieza(1), posicionPieza(2), posicionPieza(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);
    
pinta_bloque(matrizPieza,'g');

%Agarre 1

matrizAgarre1 = Desplazamiento(0,0,4) * Rotaciony(pi) * Rotacionz(-pi/2);
matrizPinza1 = matrizPieza * matrizAgarre1;

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matrizPinza1,codo, avance, simetrico);  
matriz1 = funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matrizPinza1);

view(3)
figure();


% Agarre 2
pinta_bloque(matrizPieza,'g');

matrizAgarre2 = Desplazamiento(0,0,4) * Rotacionx(pi); 
matrizPinza2 = matrizPieza * matrizAgarre2;

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matrizPinza2,codo, avance, simetrico);  
matriz2 = funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matrizPinza2);

view(3)
figure();


% Agarre 3
pinta_bloque(matrizPieza,'g');

matrizAgarre3 = Desplazamiento(0,-0.8,4) * Rotaciony(pi/2) * Rotacionx(-pi/2);
matrizPinza3 = matrizPieza * matrizAgarre3;

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matrizPinza3,codo, avance, simetrico);  
matriz3 = funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matrizPinza3);

view(3)

