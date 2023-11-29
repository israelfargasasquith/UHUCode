clc
clear all

Delay = 2;
Periodo = 6;
Amplitud = pi/2;
Tiempo_simulado = 0:0.01:pi*4;

y = signal_vf_v2(Tiempo_simulado, Periodo, Delay, Amplitud);

plot(Tiempo_simulado, y);
punto = animatedline('Marker','o'); %crea un array para representar graficamente datos, mientras
%añadimos datos al array los muestra en pantalla, no tenemos que borrar la
%pantalla en cada iteración para pintar los puntos, asi que no sale a
%trompicones y no hacemos llamadas a varios plot seguidos y es mucho mas
%eficiente.
k=1;
tiempo_real(k)=0;
referencia(k) = signal_vf_v2(tiempo_real(k), Periodo, Delay, Amplitud);
axis([0 4*pi -Amplitud Amplitud])
tStart = tic;
while tiempo_real(k) < 4*pi
    k = k+1;
    tiempo_real(k) = toc(tStart);
    referencia(k) = signal_vf_v2(tiempo_real(k), Periodo, Delay, Amplitud);

    %------------------------------------------
    % Aqui en el futuro:
    %   1- Leeremos la posicion del motor
    %   2- El controlador calculará la acción de control a partir de la
    %   referencia(i)
    %   3- Mandaremos el comando con la acción de control al ladrillo
    %------------------------------------------

    clearpoints(punto);
    addpoints(punto,tiempo_real(k),referencia(k));
    drawnow
end