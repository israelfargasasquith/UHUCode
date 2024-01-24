cla

%Esto es lo comun, asi que si la modificamos modificamos las 3

posicion = [20  -10 0]
alfa=pi/2; beta=0; gamma=0;



matrizConstruccionGlobal = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma)

matrizRojaEnConstruccion = Desplazamiento(-1.5,0,0)
matrizRojaEnGlobal = matrizConstruccionGlobal*matrizRojaEnConstruccion;

matrizAzulEnConstruccion = Desplazamiento(1.5,0,0)
matrizAzulEnGlobal = matrizConstruccionGlobal*matrizAzulEnConstruccion ;

matrizVerdeEnConstruccion = Desplazamiento(0,0,6)
matrizVerdeEnGlobal = matrizConstruccionGlobal*matrizVerdeEnConstruccion;

%matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotaciony(pi) %Matriz de tranformacion de Pieza a Herramienta
%matriz_agarre2 = Desplazamiento(0,0,8)*Rotacionz(pi)*Rotaciony(pi) 
%matriz_agarre = Desplazamiento(0,0,8)*Rotacionx(pi) 
%matriz_pinza = matrizConstruccionGlobal*matriz_agarre;
%matriz_pinza2 = matrizConstruccionGlobal*matriz_agarre2
matriz_agarre3 = Desplazamiento(-0.8,0,8)*Rotacionx(pi)*Rotaciony(-pi/2)
matriz_pinza3 = matrizConstruccionGlobal*matriz_agarre3;

codo=1;
avance=1;
simetrico=1;

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(matriz_pinza3,codo,avance,simetrico);
 
matriz=funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6], matriz_pinza3)




pinta_bloque(matrizVerdeEnGlobal,'g')
pinta_bloque(matrizAzulEnGlobal,'b')
pinta_bloque(matrizRojaEnGlobal,'r')

