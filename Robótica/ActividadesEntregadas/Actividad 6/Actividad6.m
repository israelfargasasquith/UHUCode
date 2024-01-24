% 1)Experimentar con la función que genera una spline cúbica a partir de la
% especificación de varios puntos por los que ha de pasar.
% Usar el archivo ejemplo_llamada para analizar su funcionamiento, los
% argumentos que recibe la función son:
% x: vector con las coordenadas x de los puntos de paso
% y: vector con las coordenadas y de los puntos de paso
% ds: distancia entre los puntos que definen la curva
% Devuelve:
% curva : array de dos columnas con las coordenadas x e y de los puntos que
% definen la curva separados una distancia ds.
% El camino que se describe es el que aparece en la siguiente figura (ds=3):

Actividad6A
title('Pulse para continuar cuando termine la simulacion')
pause();

% 2)Para hacer que la curva sea tangente a la configuración de salida y llegada hay
% que tener en cuenta el ángulo que el vehículo tiene en ambas configuraciones
% Para ello, se deben fijar dos puntos adicionales. Uno de ellos Pd (punto de
% despegue), se fijará posterior a la configuración inicial, separado del mismo una
% distancia arbitraria dd (distancia de despegue). El otro, Pa (punto de aterrizaje)
% será definido anterior al correspondiente a la configuración final, separado del
% mismo una distancia arbitraria da (distancia de aterrizaje).
% El cálculo de los mismos será de la forma:
% Pdx=X0+dd*cos() ; Pdy=Y0+dd*sin()
% Pax=Xf - da*cos( f) ; Pay=Y0 - da*cos( f)
% Defina estos puntos y al menos un punto de paso para generar un camino con
% una configuración inicial (X0,Y0, 0)= (10,15,-pi/4) y otra final (Xf,Yf, f)= (80,80,-
% pi/4). Probar con otras configuraciones añadiendo algún punto más.

Actividad6B
pause();

% 3)Usar el simulador de la guía 5 para que el vehículo siga una trayectoria igual a la
% definida en el apartado anterior. Probar con diferentes caminos y
% configuraciones.
Actividad6C1
pause();

Actividad6C2
pause();