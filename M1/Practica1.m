
%%1 Ex
A = [1, 2, 3, 4;
    5,6,7,8;
    9,10,11,12]
B = A
A(1,1)
A(1,:)
A(:,3)
A([1,3],[1,3,4])

A(1,2) = 0
A(2,:) = [1 0 1 2]
A(:,4) = []

B(2,:) = B(2,:)-5*B(1,:)
B(3,:) = B(3,:)-9*B(1,:)
B(3,:) = B(3,:)-2*B(2,:)


clear all
%%2 Ex
A = [1 2; 3 4]
B = [5 6; 7 8]

add = A+B
EscalarMulti = 2*A
product = A*B
inverse = A^(-1)
division = A/B
division2 = A\B
elementByMult = A.*B
elementByDiv =A./B
dision2ElemebyElement = A.\B
Mpow =A^2
pow = A.^2
2.^A 
A.^B
cos(A)

horzcat(A,B)
vertcat(A,B)

%3
%check those commands at home

%4
clear all
A = [1, 2, 3, 4;
    5,6,7,8;
    9,10,11,12]

rref(A)
rref(sym(A))
%L = Lower triangular matrix
%U = Upper triangular matrix

[LA, UA ,PA] = lu(A)
[LA2, UA2] = lu(A)
[LA3, UA3] = lu(sym(A))

B = [0 2 3 4; 4 6 7 8; 9 10 11 12]

[LB, UB ,PB] = lu(B)
[LB2, UB2] = lu(B)
[LB3, UB3] = lu(sym(B))

%5
clear all
A = [4 -2 -4; -2 10 5; -4 5 6]
chol(A)

