%We will use ResolveRowEcolonForm = rref and we will talk about the command
%solve and linsolve \

%Ex 1

E1 = [1 2 -2 3; 2 -5 1 4; -1 3 0 -1]
E2 = [1 -1 1; 4 1 1 ; 3 2 2]
E3 = [1 2 -3 -1 4; 1 2 1 5 2]

A1 = [1 2 -2 ; 2 -5 1 ; -1 3 0 ]
b1 = [3;4;-1]

A2 = [1 -1; 4 1 ; 3 2 ]
b2 = [1; 1 ;  2]

A3 = [1 2 -3 -1 ; 1 2 1 5 ]
b3 = [4; 2]
%a)
rref(E1)
%The second one is inconsistent because the rank of the aumented matrix !=
%the rank of the original matrix
rref(E2)
%The pivot variables are x and z so the rest can be free variables
rref(E3)

%b)
syms x y z t
[x1, y1, z1] = solve([x+2*y-2*z==3,2*x-5*y+z==4,-x+3*y==-1],[x,y,z])
%The second one is empty cuz its inconsistent
[x2, y2, z2] = solve([x-y==1,4*x+y==1,3*x+2*y==2],[x,y,z])
%if we want all infinity solutions we need to write the 'Retur
%Conditions',true
[x3, y3, z3,t3] = solve([x+2*y-3*z-t==4,x+2*y+z+5*t==2],[x,y,z,t])
solve([x+2*y-3*z-t==4,x+2*y+z+5*t==2],[x,y,z,t],'ReturnConditions',true)

%c)
A1\b1
A2\b2
A3\b3
%The second one gives us one solution but it shouldnt gives us that cuz its
%inconsistent
sym(A1)\sym(b1)
sym(A2)\sym(b2)
sym(A3)\sym(b3)
%LINSOLVE IS MISSING
%If we dont aproximate we get an error in the second one but we get only
%one solution in the third one, in order to get every possible solution we
%do the following

%d)
null(sym(A3))
%Null gives us the basis of the vector subspaces that we gives, in the case
%of A3 we give {(1 2 -3 -1),( 1 2 1 5)} and the function gives us the basis
%and all the solutions for the homogeneus system


%2
clear all
syms m
Am = [1 m-2 1; 1 1 m-2; m-2 1 1]
bm = [m;-2*m+2;m-2]
rref([Am bm])
%If m != 3 there is an unique solution 

A = subs(Am,m,3)
b = subs(bm,m,3)
rref([A b])
%We just saw that the system is inconsisten cuz the rank diff

