cla
%configuración de la pieza
posicion=[20 -10 0];
alfa=0; beta=0; gamma=0;
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);

%pinta_pieza_delgada(matriz_pieza)
pinta_bloque(matriz_pieza,'b')


%matriz_pinza=eye(4,4) % matriz unidad
matrizAgarre = Desplazamiento(0,0,4)*Rotacionz(pi/2)*Rotaciony(pi) %Matriz de tranformacion de Pieza a Herramienta
%pTh
matriz_pinza= matriz_pieza * matrizAgarre
%q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];
codo = 1
avance = -1
simetrico = 1
[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(matriz_pinza,codo,avance,simetrico)
matriz=funcion_pinta_UR3_new(q, matriz_pinza)

 
 
%Hay que hacer la actividad 2 y esto no lo hace bien por algun motivo,
%leerlo y ver que cambiar
