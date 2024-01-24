clear all;
%AC 1
tiempoVirtual = 0:0.01:4*pi;
Delay = 2;
Periodo = 6;
Amplitud = pi/2;


%y=signal_V0(tiempoVirtual);
y = signal_vf_v2(tiempoVirtual,Periodo,Delay,Amplitud);
plot(tiempoVirtual,y)

%ahora vamos a usar el "tiempo real" con los comandos tic y toc

%Arrancamos cronómetro
n=1;
tStart = tic;


tiempoReal(n) = toc(tStart);

while tiempoReal<2*pi
    n = n+1;
    tiempoReal(n) = toc(tStart);
    tiempoReal(n);
    %salida = signal_V0(tiempoReal);
    %salida = singal_vf_v2(TIENE 3 VARIABLES);
    salida = signal_vf_v2(tiempoReal,Periodo,Delay,Amplitud);
end
hold on
plot(tiempoReal,salida,'-')
%El plot sale al final de todos los cálculos a no ser 
