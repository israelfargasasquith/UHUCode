clc
clear all
Delay = 2;
Periodo = 6;
Amplitud = pi/2;
Tiempo_simulado = 0:0.01:pi*4;
y = signal_vf_v2(Tiempo_simulado, Periodo, Delay, Amplitud);
%plot(Tiempo_simulado, y);
mi_Robot=legoev3('USB');
motor_Cabeza=motor(mi_Robot,'A');
start(motor_Cabeza);
motor_Cabeza.Speed=0;
giro_Cabeza(1)=0;
resetRotation(motor_Cabeza);

k=1;
tiempo_real(k)=0;
tf=10;
referencia(k) = signal_vf_v2(tiempo_real(k), Periodo, Delay, Amplitud);
%mapa=[];
punto = animatedline('Marker', 'o'); % Permite que el punto vaya "moviéndose" sin tener que borrar entero el canvas
tStart = tic;
giro_Cabeza(1)=double(readRotation(motor_Cabeza));
while tiempo_real(k) < tf
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
    %motor_Cabeza.Speed=10;
    giro_Cabeza(k)=double(readRotation(motor_Cabeza));
   % mapa = pinta_robot_v3(0,0,0,referencia(k),2,mapa);
   
    % clearpoints(punto); %Borra el anterior punto, sin necesidad de borrar todo el canvas
     addpoints(punto, tiempo_real(k), giro_Cabeza(k)); %Añade el siguiente punto a dibujar, sin necesidad de repintar el canvas entero
    %
    % axis([0 4*pi -Amplitud Amplitud]);
    %
    
    drawnow
end

stop(motor_Cabeza);
hold on;
plot(tiempo_real, giro_Cabeza)