
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simulación del movimiento de un robot móvil
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc


MAPA = imread('cuadro4.bmp');

%Transformación para colocar correctamente el origen del Sistema de
%ReferenciaEsto se hace para que el bmp se represente como queremos, ya
%que al cargarlo se hace una representacion reflejada porque las Y crecen
%desde la esquina izquierda superior
MAPA(1:end,:,:)=MAPA(end:-1:1,:,:);
image(MAPA);
axis xy



j=1;

global l
global radio_rueda
global camino
global pose
global punto

%Fundamento teórico, Tema 4(Robots moviles apartado 4.5):
%Lo que buscamos es una secuencia de puntos que genrarn un camino cuya
%configuracion no interfiere con ningun obstacurlom hay 3 clases de
%algorimtos de planificacion de trayectorias, Los de busquedas, losbasados
%en roadmaps, los de aproximacion numericas. El mas sencillo es el de
%grafos de visibilidad, se enmarca en los algoritmos roadmaps, se trata en
%enconrtar un grafo de conectividad con un mapa. El ejemplo mas sencillo
%dia 4 de 29, unimos los vertices de los poligonos yestablecemos el grafo
%de conectividad tal que asi, con eso tenemos un punto de llegada un punto
%de salida con los vertices de los triangulos. Un objeto tiene visibilidad
%is podemos estableceer una recta entre un vertice y otro, desde el punto
%inicio establecemos la conectividad desde cada camino determinando cada
%nodo intermedio. Al ser muy rudimentario lo que hacemos es recrear los
%objetos para que no se produzcan colisiones. Con el diagramama de Voronaoi
%loq ue bscamos es generar arcos que equidistan de los objetos, genera
%rectas y parabolas. Otra forma es la descomposicon en celdas, descompones
%el escenario en casillas que tienen colision o con colision (un grid con obstaculo o sin obstaculo)
%Las casillas libres de obstaculos las usaremos para crear el mapa. Hay
%otros metodos como tomar al vehiculo como particula... Descenso de
%gradiente... 
%Lo que haremos es almacenar el valor en cada celda un valor ocn respecto
%al destino, buscaremos que crezca este valor sin chocarse (no tener en cuenta los obstaculos
% ) El algorimto RRT esta to guapo pero es dificil de programar, nosotros
% usaremos el A* y si usamos el RRT el nos dara el codigo, es el algoritmo
% basado en el alga marina o en las hormigas, se suele implementar de forma
% bidireccional, el problema es que al ser de crecmiento aleatorio la ruta
% de puntos que nos da es bastante irregular, lo que se debe hacer es
% filtrar para suavizar. La expansion de obstaculos se suele hacer antes de
% aplicar cualquier algoritmo, por seguridad mas que nada. Para filtrar los
% puntos se usa el splitecubico o el B-splite, la diferencia es que el
% splitecubico pasa por los puntos generados y el Bsplite aproxima a los
% puntos generados (Matematicamente es mas complejo de diferneciar pero groso modo es asi)
%Este campo de investigacion es inmenso y forma parte del ambito de la
%computacion. Nosotros vamos a trabajar con el A*. Cuando tenemos un mapa
%tenemos que encontrar el valor de delta adecuado (en ejemplo A* por ejemplo 25 esta bien, 10 es demasiado pequeño y 35 demasiado grande)
%A partir del codigo tenemos que calcular un camino bueno con el mapa y el
%A* y el sigue camino. Quitar que nos pida el origen con el raton ya que
%sera el origen del coche. El A* nos devuelve el optimalPath que es el
%camino que vamos a seguir, esto seran los puntos de paso de la splite



ds = 1;
origen = [2 2]
anguloPartida = pi/4
anguloLlegada = -pi/2

MAPA = imread('.\cuadro4.bmp');

%Transformación para colocar correctamente el origen del Sistema de
%Referencia 
MAPA(1:end,:,:)=MAPA(end:-1:1,:,:);

%Tamaño de las celdas del grid %El gridMap se usa para representar los
%obstaculos, el peligro que supone cierto camino... 
delta=25;
%delta=35;
%delta=50;
%Llamada del algoritmo
Optimal_path=A_estrella(MAPA, delta,origen);

%Dibujo de la ruta
plot(Optimal_path(:,1), Optimal_path(:,2))

puntoLlegada = Optimal_path(end,2)

dd = 5;
da = dd;
xc = Optimal_path(:,1)
yc = Optimal_path(:,2)

camino=funcion_spline_cubica_varios_puntos(xc',yc',ds)';
l=3.5; 
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
 punto=[0 0];
puntoFinal = [camino(end,1) camino(end,2)];

acaba = 0;
while  (t0+h*k) < tf & acaba ~= 1,
    %actualización
    k=k+1;
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los parámetros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    orden_minimo = minima_distancia(camino, [pose(1,k) pose(2,k)]);

    lookAhead = 5;
    %No se si esto esta solucionado para que no se salga del array xd
    
%punto = [camino( mod( orden_minimo+lookAhead,length(camino) )+1 ,1),camino( mod( orden_minimo+lookAhead,length(camino) )+1 ,2) ];

    %Usaremos clotoides para que no se pare en las curvas o cuando las
    %curvas sean muy bruscas para evitar el empellont
    if(orden_minimo +lookAhead >length(camino)) 
        %punto = [camino(1,1) camino(1,2)];
        punto = [camino(end,1) camino(end,2)];
    else 
        punto = [camino(orden_minimo+lookAhead,1) camino(orden_minimo+lookAhead,2)];
    end


Delta = (pose(1,k)-punto(1))*sin(pose(3,k))-((pose(2,k)-punto(2))*cos(pose(3,k)));
Lh = sqrt((pose(1,k)-punto(1))^2+(pose(2,k)-punto(2))^2);

V = 9*Lh;

if(V < 0.1)
acaba = 1
end


    
radio_curvatura=10;

 rho = 2*Delta/Lh^2;

 W = V*rho;
 
velocidad_derecha=(V+W*l)/radio_rueda;
velocidad_izquierda=(V-W*l)/radio_rueda;




 
 conduccion=[velocidad_derecha velocidad_izquierda];
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 

    
%metodo de integración ruge-kuta

pose(:,k+1)=kuta_diferencial_mapa(t(k),pose(:,k),h,conduccion,MAPA);

end




