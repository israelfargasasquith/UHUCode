clear all
miRobot=legoev3('USB');
Pulsador=touchSensor(miRobot,2);

motorCabeza=motor(miRobot,'A');
start(motorCabeza);
valorDeseado=90;
resetRotation(motorCabeza);
giroCabeza(1)=double(readRotation(motorCabeza));
kp = 0.6;


while(readTouch(Pulsador)== 0)
     disp('Esperando...');
end


while(readTouch(Pulsador)== 1)
end
k=1;
clc
tstart = tic;
tiempo(k) = toc(tstart);
tiempoFinal=10;
referencia(k)=0;
while(readTouch(Pulsador)== 0)
    k=k+1;
    tiempo(k) = toc(tstart);
    giroCabeza(k)=double(readRotation(motorCabeza));
    %referencia(k)=valorDeseado;
    referencia(k)=tiempo(k)*200;
    error(k)=referencia(k)-giroCabeza(k);
    controlador = kp*error(k);
    Power = int8(controlador);
    if Power > 100
        Power = 100;
    end
    if Power < -100
        Power = -100;
    end
    motorCabeza.Speed=Power;
        
    disp('Apagame');

end

stop(motorCabeza);
plot(tiempo, referencia, 'r');
hold on;
plot(tiempo, giroCabeza,'b')
hold on;
plot(tiempo, error)
grid on;
