clear all
clc
global camino
global MAPA
global ancho
global largo

 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %
 %  Inicialización de las variables fundamentales
 % NO TOCAR ESTA PARTE DEL CODIGO
 %------------------------------------------------

%dimensiones del coche
ancho = 10;
largo = 16;

MAPA = imread('.\cuadro_nuevo3.bmp');

volante=0;
velocidad=0;



x0=[450; 185;0; 0]
tf=5;
t0=0;

%paso de integracion 
h=0.01;;
%vector tiempo
t=0:h:tf;
%indice de la matriz
k=0;

%inicialización valores iniciales

x(:,k+1)=x0;
t(k+1)=t0;
%-------------------------------------------------------

 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %
 %  ELEMENTOS DE REPRESENTACIÓN GRÁFICA
 % NO TOCAR ESTA PARTE DEL CODIGO
 %------------------------------------------------
 %crea las figuras
  scrsz = get(0,'ScreenSize');
 
  figure('Position',[1 scrsz(4)*5/30 scrsz(3)*25/30 scrsz(4)*25/30])
   axHndl=gca
   set(axHndl,'Userdata',1,'Drawmode','fast', ...
       'Visible','on','NextPlot','add','Userdata',1)
   axis([-10 1034 -10 778]);

    image(MAPA);

for numero=1:(2*8+2)
     handles_coche(numero) = plot(0,'Erasemode','xor');
    hold on
end

for numero=1:10
    handles_plots(numero) = plot(0,'Erasemode','xor');
    hold on
end

handle_vector = plot(0,'Erasemode','xor','LineWidth', 2.0);
%-------------------------------------------------------------------

%Da valores inciales al estado y a las medidas del laser


[x(:,k+1),laser]=kuta(t(k+1),x,h,0,0,handles_plots,handles_coche,handle_vector,[45 45],MAPA);

  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %
 %  COMIENZA EL BUCLE DE SIMULACIÓN
 %
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 while (t0+h*k) < tf,

    %actualización
    k=k+1;

  
  modulos = laser(:,1);

    cosenos = cos(laser(:,2))
    senos = sin(laser(:,2))

    V1 = [modulos .* cosenos modulos .* senos]

    vPerception = [sum(V1(:,1)) sum(V1(:,2))]
    
 %componentes_x=75;
  %componentes_y=30;
  componentes_x = vPerception(1)
  componentes_y = vPerception(2)


  
  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  % Aquí hay que definir el controlador
  %------------------------------------
  velocidad=70;
  
  volante=0;
  %------------------------------------
  
  
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Limitación del ángulo de conducción
if volante>pi/4 volante=pi/4;
end

if volante<-pi/4 volante=-pi/4;
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


  


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%metodo de integración ruge-kuta, representación gráfica y simulacion del laser	 

   
     [x(:,k+1),laser]=kuta(t(k),x,h,volante,velocidad,handles_plots,handles_coche,handle_vector,[componentes_x componentes_y],MAPA);
    
   
  

 end


