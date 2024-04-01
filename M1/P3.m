
%We need the matrix to change from Standarbassis to B1 ad to B2, then we
%need the inverse for MCB2 cuz if we just use the vectors all we have if
%the matrix from Stadar to B2 -> Mb2b1 = Mb2Std * MStdb1
MCB1= transpose([3 2 -1;4 1 1;2 -1 1])

MCB2 = inv(transpose(sym([1,1,-3;0,1,2;1,1,-1])))
%a)
MB2B1= MCB2*MCB1
%b)vB1(1,-2,3) = 1*(3,2,-1)-2*(4,1,1)+3(2,-1,1) = (1,-3,0)
%  vB2=> We multiply vB1 with the matrix that we have calculated and check
%  that if we change to the stdbasis we get the same
vB1 =transpose([1,-2,3]) 
vB2 = MB2B1*vB1
%c) 
MB1B2 = inv(MB2B1)
wB2 = transpose([1,0,-2])
wB1 =MB1B2*wB2

%2
U1 = transpose(sym([3,8,11,2,-11;0,2,2,2,-5;3,4,7,-2,-1;2,2,4,-2,1]))
rank(U1)
rref(U1)
BU1 = transpose(sym([3,8,11,2,-11;0,2,2,2,-5]))

%now we hvae to find the implicits  equations

syms x1 x2 x3 x4 x5
%det([3 0 x1;8 2 x2; 11 2 x3])
eq1 = det([BU1(1:3,1:2) [x1;x2;x3]])
eq2 = det([BU1([1,2,4],1:2) [x1;x2;x4]])
eq3 = det([BU1([1,2,5],1:2) [x1;x2;x5]])

%To know if u-> is in the subspace U1 we just have to substitude the values
%in the equations and it must give us 0

uinU = subs(eq1,[x1,x2,x3,x4,x5],[-3,1,0,1,1])
vinU = subs(eq1,[x1,x2,x3,x4,x5],[3,2,5,-4,4])

%To go to implicit equiations to parametric we hace to solve the ssytem 
U2ImplicitEqs = solve([x1-x2,x2+x4],[x1,x2,x3,x4,x5],'ReturnConditions',true)
%And the basis is just the coordinates of the parameters B<(-1,-1,0,1,0);(0,0,1,0,0);(0,0,0,0,1)>

%Tips for last part when we put all equiations together and we hace the
%intersection, for the other part we use the spaning set of both basis and
%
