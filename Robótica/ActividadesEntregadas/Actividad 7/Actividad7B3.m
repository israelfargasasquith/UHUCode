%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Ejemplo de uso del algorithmo A* en un mapa definido por un bmp 
% 21-12-2015
%   fgb
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc
%Carga el fichero  BMP

origen = [2 2]
anguloPartida = pi/4

MAPA = imread('.\cuadro4.bmp');

%Transformación para colocar correctamente el origen del Sistema de
%Referencia 
MAPA(1:end,:,:)=MAPA(end:-1:1,:,:);

%Tamaño de las celdas del grid %El gridMap se usa para representar los
%obstaculos, el peligro que supone cierto camino... 
delta=100
%Llamada del algoritmo
Optimal_path=A_estrella(MAPA, delta,origen);

%Dibujo de la ruta
plot(Optimal_path(:,1), Optimal_path(:,2))

