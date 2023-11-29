
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simulaci�n del movimiento de un robot m�vil
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc

j=1;

global l
global camino
global pose
global punto
%cargamos el camino

camino=load('camino.dat');

l=3.5; %distancia entre rudas delanteras y traseras, tambien definido en modelo
%Condiciones iniciales 

pose0=[0; 0; 0];
%pose0=[10; 10; 0];

t0=0; 

%final de la simulaci�n
tf=35;

%paso de integracion, a mas chiko mas preciso pero tambien mas calculo
h=0.1;
%vector tiempo
t=0:h:tf;
%indice de la matriz
k=0;

%inicializaci�n valores iniciales
pose(:,k+1)=pose0;

t(k+1)=t0;

while (t0+h*k) < tf,
    %actualizaci�n
    k=k+1;
    Radio = 10;
    rho = 1/Radio; % si =0 gira sobre si mismo, 
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los par�metros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %Variables de control
    %volante=-0.1416;
    volante = atan(l*rho)
    velocidad=2;



 conduccion=[velocidad volante];
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 %para representar el punto onjetivo sobre la trayectoria
 
 punto=[30 30];

    
%metodo de integraci�n ruge-kuta

pose(:,k+1)=kuta_triciclo(t(k),pose(:,k),h,conduccion);

end




