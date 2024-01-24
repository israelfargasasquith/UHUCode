% 1)A partir del código ejemplo para la representación del mapa, incluya la
% representación del mismo en la simulación utilizada en las Guías 5 y 6, de manera
% que el movimiento del vehículo se represente a la vez que el mapa.

Actividad7A

% 2)Experimente con el ejemplo de llamada a la función A_estrella(), escogiendo
% distintos tamaños de grid (100, 50, 35, 15) y distintos puntos de inicio y final para
% la ruta, de forma que comprobéis el efecto que tienen sobre el resultado
% conseguido y el tiempo de cómputo. Analice y saque conclusiones sobre los
% resultados alcanzados.

Actividad7B1
su
Actividad7B2

Actividad7B3

% 3)Cuando haya encontrado el tamaño de grid ideal para definir trayectorias sobre
% el mapa defina un punto de partida y otro destino para obtener una ruta libre de
% colisiones. A partir de la ruta, genere una curva spline. Posteriormente utilice
% dicha curva para que el vehículo de la simulación de las Guías 5 y 6 siga el camino
% generado. A la hora de generar la spline tenga en cuenta el ángulo de salida y el
% ángulo de llegada. Obsérvese que el inicio y el final de la ruta proporcionada por
% A* siempre se aproximan al centro de las celdas que contienen dichos puntos.
% 4) Integre el código de planificación y simulación de forma que puedan ejecutarse
% distintos ejemplos con facilidad.
% 5)Cree un mapa en el que haya definido un escenario. Deberá definir una tarea en
% la que el robot deberá visitar distintos puntos del escenario sin colisionar con
% ninguna zona ocupada del escenario. Entre las tareas a realizar deberá incluir un
% aparcamiento (paralelo o en batería). Las rutas que definen los movimientos de
% la tarea se han de obtener utilizando el algoritmo A* y los caminos que ha de
% seguir el robot deberán estar de finidos mediante curvas splines. El algoritmo de
% seguimiento deberá ser el Pure Pursuit.
Actividad7C

