% 
% a) Utilizando las funciones que se proporcionan y que sirven para generar matrices de
% transformación, pruebe a representar un bloque en distintas configuraciones del
% espacio, para asegurarse de que entiende como se realiza la llamada de la función y el
% tipo de movimientos que definen
cla
clear
%configuración de la pieza
posicion1=[20 -14 20];
rotaZ1=0; rotaY1=0; rotaX1=pi/4;

matrizPieza1=Desplazamiento(posicion1(1), posicion1(2), posicion1(3))*Rotacionz(rotaZ1)*Rotaciony(rotaY1)*Rotacionx(rotaX1);

title('Apartado A.1')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza1,'g')
 axis([-10,40,-30,30,-10,45]);

figure();

posicion2=[0 -20 10];
rotaZ2=pi/4; rotaY2=-pi/2; rotaX2=pi/2;
matrizPieza2=Desplazamiento(posicion2(1), posicion2(2), posicion2(3))*Rotacionz(rotaZ2)*Rotaciony(rotaY2)*Rotacionx(rotaX2);

title('Apartado A.2')
 xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza2,'r')
 axis([-10,40,-30,30,-10,45]);

figure();

% b) Determinar la matriz de transformación asociada a la pinza para que ésta se sitúe
% respecto de la pieza según se indica en la figura 2 a) y la pieza se encuentre ubicada en
% el punto x =20 cm ; y=-10 cm; z= 0 cm; siendo todos sus ángulos de Euler zyx iguales a
% cero. Haga los mismos cálculos para el agarre de la figura 2 b).

clear
posicion=[20 -10 0];
rotaZ=0; rotaY=0; rotaX=0;
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];

matrizPieza = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionx(rotaX)*Rotaciony(rotaY)*Rotacionz(rotaZ);

matrizPinza1 = Desplazamiento(0,0,4)*Rotacionx(0)*Rotaciony(pi)*Rotacionz(-pi/2)
matrizPinza2 = Desplazamiento(0,0,4)*Rotacionx(pi)*Rotaciony(0)*Rotacionz(0)

matrizPinza1 = matrizPieza * matrizPinza1;
matrizPinza2 = matrizPieza * matrizPinza2;


% c)Utilizando las matrices calculadas anteriormente, invoque la función
% funcion_pinta_UR3_new dentro del fichero pinta_elementos.m para
% comprobar que la pinza virtual se ubica correctamente.

title('Apartado C.1')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)

pinta_bloque(matrizPieza,'g');
matriz1 = funcion_pinta_UR3_new(q, matrizPinza1);
 axis([-10,40,-30,30,-10,45]);

figure();

title('Apartado C.2')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza,'g')
matriz2 = funcion_pinta_UR3_new(q, matrizPinza2);
 axis([-10,40,-30,30,-10,45]);

figure();

% d) Calcule la matriz de configuración para que la pinza se sitúe, con las mismas
% configuraciones de agarre, pero estando la pieza ubicada en el punto x=20 cm; y=10
% cm; z=5 cm; con ángulos de Euler zyx: alfa= pi/4 rad. ; beta=0 rad.; gamma=pi/6 rad.

clear

posicion=[20 10 5];
rotaZ=pi/4; rotaY=0; rotaX=pi/6;
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];

matrizPieza = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionx(rotaX)*Rotaciony(rotaY)*Rotacionz(rotaZ);

matrizPinza1 = Desplazamiento(0,0,4)*Rotacionx(0)*Rotaciony(pi)*Rotacionz(-pi/2)
matrizPinza2 = Desplazamiento(0,0,4)*Rotacionx(pi)*Rotaciony(0)*Rotacionz(0)

matrizPinza1 = matrizPieza * matrizPinza1;
matrizPinza2 = matrizPieza * matrizPinza2;

title('Apartado D.1')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza,'g');
matriz = funcion_pinta_UR3_new(q, matrizPinza1);
 axis([-10,40,-30,30,-10,45]);

figure();

title('Apartado D.2')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza,'g');
matriz1 = funcion_pinta_UR3_new(q, matrizPinza2);
 axis([-10,40,-30,30,-10,45]);

figure();


% e)Establecer el cálculo para determinar las matrices de transformación de la pinza que
% permiten manipular la pieza según lo indicado en la figura 3, estando la pieza ubicada
% en el punto x =20 cm; y=-10 cm; z=0 cm; y siendo sus ángulos de Euler zyx: alfa= 0
% rad. ; beta=0 rad.; gamma=0 rad. Establecer el mismo cálculo para los ángulos de Euler
% zyx: alfa= pi/4 rad. ; beta=0 rad.; gamma=0 rad.

clear

posicion=[20 -10 0];
rotaZ=0; rotaY=0; rotaX=0;
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];

matrizPieza = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionx(rotaX)*Rotaciony(rotaY)*Rotacionz(rotaZ);

matrizPinza = Desplazamiento(0,-0.8,4)*Rotacionx(0)*Rotaciony(pi/2)*Rotacionx(-pi/2)

matrizPinza = matrizPieza * matrizPinza;

title('Apartado E.1')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza,'g');
matriz = funcion_pinta_UR3_new(q, matrizPinza);
 axis([-10,40,-30,30,-10,45]);
figure();

clear
posicion=[20 -10 0];
rotaZ=pi/4; rotaY=0; rotaX=0;
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];

matrizPieza = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionx(rotaX)*Rotaciony(rotaY)*Rotacionz(rotaZ);

matrizPinza = Desplazamiento(0,-0.8,4)*Rotacionx(0)*Rotaciony(pi/2)*Rotacionx(-pi/2)

matrizPinza = matrizPieza * matrizPinza;

title('Apartado E.2')
xlabel('X') 
ylabel('Y') 
zlabel('Z')
view(3)
pinta_bloque(matrizPieza,'g');
matriz = funcion_pinta_UR3_new(q, matrizPinza);
 axis([-10,40,-30,30,-10,45]);


