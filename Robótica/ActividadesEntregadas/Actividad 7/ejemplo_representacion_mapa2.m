%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Ejemplo de visualización de un mapa bmp colocando correctamente el origen
% de coordenadas
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc
%Carga el fichero  BMP

MAPA = imread('cuadro42.bmp');

%Transformación para colocar correctamente el origen del Sistema de
%ReferenciaEsto se hace para que el bmp se represente como queremos, ya
%que al cargarlo se hace una representacion reflejada porque las Y crecen
%desde la esquina izquierda superior
MAPA(1:end,:,:)=MAPA(end:-1:1,:,:);
image(MAPA);
axis xy

