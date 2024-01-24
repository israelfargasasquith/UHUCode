clear
clc

%Ejercicio 1

%a) Introducir la matriz A.

A=[11 12 13 14;21 22 23 24;31 32 33 34;41 42 43 44]

%b) Obtener los valores de la primera columna

A(:,1)

%c) Obtener los valores de la segunda fila

A(2,:)

%d) Obtener los valores de la segunda y la tercera columna.

A(:,2:3)

%e) Obtener la diagonal de A

diag(A)

%f) Obtener una matriz de 2x2 donde todos lo elementos sean 1.

 ones(2,2)

%g) Obtener una matriz unidad de orden 2x2.

 eye(2)

%Ejercicio 2

%a) Entrar la siguiente matriz:

 A=[0 pi; pi/6 pi/2]

  B2=cos(A)


%b) Encontrar la matriz transpuesta de A

A'

%c) Encontrar los autovalores y autovectores de A
eig(A)
eigs(A)

%d) Calcular la matriz columna resultante de multiplicar elemento a elemento B y C

B = [1,1,1]'
C = [2,3,4]
BC = B.*C'

%Ejercicio 3

%a)y(t)= 2 · t
t = -5:0.001:5;
y = 2*recta(t);
figure()
plot(t,y);
title('Ejercicio 3.A')


%b)u(t) 1 para t > 0
%       0 para t < 0
u = escalon(t);
figure()
plot(t,u)
title('Ejercicio 3.B')


%c) f(t) = u(t-2)· y(t)
 u=escalon(t-2);
 figure()
plot(t,y,t,u,'r')
title('Ejercicio 3.C1')

f=y.*u;
figure()
plot(t,f)
title('Ejercicio 3.C2')

%Ejercicio 5
    A = [11 12  13 14; 21 22 23 24; 31 32 33 34; 41 42 43 44];

%a) Una matriz compuesta por los elementos que pertenezcan a las columnas 2 y 3 y a
%las filas 2 y 3.

mezcla = [A(2:3,2:3)]

%b) Una matriz de orden 4 x 6 formada por : la matriz A, una matriz de 2 x 2 con todos
%sus elementos iguales a uno y una matriz identidad de 2 x 2.

 mezcla = [A [ones(2,2);eye(2)]];

 %Ejercicio 6
%N(t) = -5*e^(-0.2*t)*cos(0.9*t-30º)+0.8*e^(-2*t)+5 para 0<=t<=30
%Representar dicha funcion graficamente y encontrar el valor máximo y el
%intante t donde se da da

t = -5:0.001:40;

funcion5 = funcionEj5(t)
figure()
plot(t,funcion5)
title('Ejercicio 6')

[valorMaximo,indiceMaximo] = max(funcion5)

%Ejercicio 7
figure()
ScriptClase
title('Ejercicio 7')

%Ejercicio 8

a = [1,1];
b = [1,-1];
c = [-1,-1];
d = [-1,1];

x = [a(1) b(1) c(1) d(1) a(1)];
y = [a(2) b(2) c(2) d(2) a(2)];

figure()
%rectangle('Position',[-1 -1 2 2])
cuadrado = line(x,y);
cuadrado.LineWidth = 3;

%axis([-2,-2 -2,-2]);
grid on;
                                                                                                         
xlim([-2 2]);
ylim([-2 2]);
title('Ejercicio 8')

%Ejercicio 9
clear
a = [1,1,0];
b = [1,-1,0];
c = [-1,-1,0];
d = [-1,1,0];
e = [1,1,3];
f = [1,-1,3];
g = [-1,-1,3];
h = [-1,1,3];


x = [a(1) b(1) c(1) d(1) a(1) e(1) f(1) b(1) f(1) g(1) c(1) g(1) h(1) d(1) h(1) e(1)];
y = [a(2) b(2) c(2) d(2) a(2) e(2) f(2) b(2) f(2) g(2) c(2) g(2) h(2) d(2) h(2) e(2)];
z = [a(3) b(3) c(3) d(3) a(3) e(3) f(3) b(3) f(3) g(3) c(3) g(3) h(3) d(3) h(3) e(3)];

figure()

cubo= line(x,y,z);
cubo.LineWidth = 3;

grid on;
view(3);

xlim([-2 2]);
ylim([-2 2]);
zlim([-3 3])
title('Ejercicio 9 Pulse para ver el cuboMoviendose')
%figure();
%Ejercicio 10
pause();
cuboMoviendose

