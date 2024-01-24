cla
clear all
%Primero hay que definir el Pick, después definir el Place, definicion del
%agarre(Configuracion del problema cinematico inverso) Definicion de pinza
%en pick y definicion de pinza en place, definicion de movimientos
%Lo anterior se hace en el orden de saber la posicion y los ángulos de
%euler del bloque a coger  (30,0,0)

%Configuración de Pick
posicion = [30 0 0]
alfa = 0; beta = 0; gamma = 0;
MatrizPickGlobal = Desplazamiento(posicion(1),posicion(2),posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma)


%Configuracion de Place
posicion = [20 10 0]
alfa = 0; beta = 0; gamma = 0;
MatrizPlaceGlobal = Desplazamiento(posicion(1),posicion(2),posicion(3))*Rotacionx(alfa)*Rotaciony(beta)*Rotacionz(gamma)




%Definicion de agarre y solucion problema cinematico directo
agarre2=Rotacionx(pi);
agarre3=Desplazamiento(0,-0.8,4)*Rotacionx(-pi/2)*Rotacionz(pi/2);
codo=1;avance=1; simetrico=0;

%Definicion  conf pinza Pick y Place
MatrizPinzaPickGlobal = MatrizPickGlobal*agarre3
MatrizPinzaPlaceGlobal = MatrizPlaceGlobal*agarre3



%Primero hay que hacer un movimiento de aproximacion a la pieza
%MatrizPickGlobal*Desplazamiento(0,0,-5) el 5 es el local de la pinza (porque es post)
%Segundo hay que hacer un movimiento de Place
%Tercero un movimiento de despegue
%Desplazamiento(0,0,5)*MatrizTransformacionGlobalPlace el 5 es al global (porque es Pre)

%Definicion de movimientos pick
pintaPieza
%1º Movimiento aproximacion
[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(MatrizPinzaPickGlobal*Desplazamiento(0,0,-5),codo,avance,simetrico); %Aproximacion
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],MatrizPinzaPickGlobal*Desplazamiento(0,0,-5));

title('AproxPick')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause

cla

pintaPieza

%2º Movimiento de pick
[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(MatrizPinzaPickGlobal,codo,avance,simetrico); %Pick
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],MatrizPinzaPickGlobal);
title('Pick')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause

cla
pintaPieza

%3º Movimiento de despegue
[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(Desplazamiento(0,0,5)*MatrizPinzaPickGlobal,codo,avance,simetrico); %Despegue
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],Desplazamiento(0,0,5)*MatrizPinzaPickGlobal);
title('DespeguePick')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause

cla
pintaPieza

%4º Movimiento despegue al place

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(Desplazamiento(0,0,5)*MatrizPinzaPlaceGlobal,codo,avance,simetrico); %Despegue place
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],Desplazamiento(0,0,5)*MatrizPinzaPlaceGlobal);

title('DespeguePlace')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause

cla
pintaPieza


%5º Movimiento Place

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(MatrizPinzaPlaceGlobal,codo,avance,simetrico); %Place
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],MatrizPinzaPlaceGlobal);

title('Place')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause

cla
pintaPieza


%6º Movimiento de aproximacion respecto al place

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(MatrizPinzaPlaceGlobal*Desplazamiento(0,0,-5),codo,avance,simetrico); %Aproximacion
funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],MatrizPinzaPlaceGlobal*Desplazamiento(0,0,-5));

title('AproxPlace')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
axis([-20,40,-30,35,-10,20]);

pause
pintaPieza


