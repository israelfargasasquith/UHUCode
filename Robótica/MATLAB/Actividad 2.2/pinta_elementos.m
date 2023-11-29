cla
%configuración de la pieza
posicion=[20 -10 0];
alfa=0; beta=pi/4; gamma=0;
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);
matriz_agarre=Desplazamiento(0,0,4)*Rotaciony(pi)*Rotacionz(-pi/2)
matriz_pinza=matriz_pieza*matriz_agarre;
pinta_bloque(matriz_pieza,'b')


codo=1;
avance=1;
simetrico=0;

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(matriz_pinza,codo,avance,simetrico);
 
matriz=funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6], matriz_pinza)


 

