
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simulación del movimiento de un robot móvil
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc

j=1;

global l
global radio_rueda
global camino
global pose
global punto
%cargamos el camino

camino=load('camino.dat');

l=3.5; %distancia entre rudas delanteras y traseras, tambien definido en modelo
radio_rueda=1;

%Condiciones iniciales 
pose0=[0; 0; 0];

t0=0;

%final de la simulación
tf=300;

%paso de integracion
h=0.1;
%vector tiempo
t=0:h:tf;
%indice de la matriz
k=0;

%llegamos al punto 

%inicialización valores iniciales
pose(:,k+1)=pose0;

t(k+1)=t0;
%Modificaciones

punto_destino =[49 08];
punto_destino2 = [07 45];
llegaPunto1 = 0;

while (llegaPunto1 ~= 2),
    %actualización
    k=k+1;
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los parámetros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
%velocidad_derecha=2.1;
%velocidad_izquierda=2.1;
%W=V/radio_curvatura;
%velocidad_derecha=(V+W*l)/radio_rueda;
%velocidad_izquierda=(V-W*l)/radio_rueda;
if(llegaPunto1 ~= 1)
 punto=[49 08];

Delta = (pose(1,k)-punto_destino(1))*sin(pose(3,k))-((pose(2,k)-punto_destino(2))*cos(pose(3,k)));
Lh = sqrt((pose(1,k)-punto_destino(1))^2+(pose(2,k)-punto_destino(2))^2);



 %V=2;
    V = Lh
    if(V > 5) %Para que al principio no corra demasiado
    V = 2
    end
%TENEMOS QUE MODIFICARLO PARA QUE PRIMERO VAYA A UN PUNTO [49 08] Y EL SEGUNDO [07 45] 
radio_curvatura=10;

 %rho=1/10;
 rho = 2*Delta/Lh^2;
% phi=atan(rho*l);

 W = V*rho;
 
velocidad_derecha=(V+W*l)/radio_rueda;
velocidad_izquierda=(V-W*l)/radio_rueda;
if(V < 0.1)
llegaPunto1 =1;
end

end
if(llegaPunto1 == 1)
 punto=[07 45];

Delta2 = (pose(1,k)-punto_destino2(1))*sin(pose(3,k))-((pose(2,k)-punto_destino2(2))*cos(pose(3,k)));
Lh2 = sqrt((pose(1,k)-punto_destino2(1))^2+(pose(2,k)-punto_destino2(2))^2);


 %V=2;
    V = Lh2
    if(V > 5) %Para que al principio no corra demasiado
    V = 2
    end
%TENEMOS QUE MODIFICARLO PARA QUE PRIMERO VAYA A UN PUNTO [49 08] Y EL SEGUNDO [07 45] 
radio_curvatura=10;

 %rho=1/10;
 rho = 2*Delta2/Lh2^2;
% phi=atan(rho*l);

 W = V*rho;
 
velocidad_derecha=(V+W*l)/radio_rueda;
velocidad_izquierda=(V-W*l)/radio_rueda;
if(V < 0.01)
llegaPunto1 = 2;
end

end
 
 conduccion=[velocidad_derecha velocidad_izquierda];
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 %para representar el punto onjetivo sobre la trayectoria
 

    
%metodo de integración ruge-kuta

pose(:,k+1)=kuta_diferencial(t(k),pose(:,k),h,conduccion);

end




