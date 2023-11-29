cla
Matriz_Construccion_global=Desplazamiento(20,10,0);

Matriz_Azul_Contruccion=Desplazamiento(-1.5,0,0);

Matriz_Rojo_Contruccion=Desplazamiento(1.5,0,0);

Matriz_Verde_Contruccion=Desplazamiento(0,0,6);

Matriz_Azul_Global=Matriz_Construccion_global*Matriz_Azul_Contruccion;
Matriz_Rojo_Global=Matriz_Construccion_global*Matriz_Rojo_Contruccion;
Matriz_Verde_Global=Matriz_Construccion_global*Matriz_Verde_Contruccion;

pinta_bloque(Matriz_Azul_Global,'b');
pinta_bloque(Matriz_Rojo_Global,'r');
pinta_bloque(Matriz_Verde_Global,'g');

agarre2=Rotacionx(pi);
agarre3=Desplazamiento(0,-0.8,4)*Rotacionx(-pi/2)*Rotacionz(pi/2);
Matriz_Pinza_Global=Matriz_Verde_Global*agarre3;

Matriz_Pinza_Global=Matriz_Pinza_Global;
codo=1;avance=1; simetrico=0;

%Configuración de agarre claculada
[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(Matriz_Pinza_Global,codo,avance,simetrico); 


funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],Matriz_Pinza_Global);

%Configuración de aproximación del agarre movimiento respecto del eje Z
%local

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(Matriz_Pinza_Global*Desplazamiento(0,0,-10),codo,avance,simetrico); 


funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],Matriz_Pinza_Global*Desplazamiento(0,0,-10));


%Configuración de despegue,  una vez agarrado, el movimiento de realiza
%respecto de los ejes globales

[q1 q2 q3 q4 q5 q6]=inv_kinema_ur3_new(Desplazamiento(0,0,10)*Matriz_Pinza_Global,codo,avance,simetrico); 


funcion_pinta_UR3_new([q1 q2 q3 q4 q5 q6],Desplazamiento(0,0,10)*Matriz_Pinza_Global);







