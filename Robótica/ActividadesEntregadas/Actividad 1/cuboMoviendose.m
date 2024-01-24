
PB1= [0;3;9];
PB2= [0;3;4];
PB3= [0;8;4];
PB4= [0;8;9];
PB5= [5;3;9];
PB6= [5;3;4];
PB7= [5;8;4];
PB8= [5;8;9];

cara1x=[PB1(1),PB2(1),PB3(1),PB4(1)];
cara1y=[PB1(2),PB2(2),PB3(2),PB4(2)];
cara1z=[PB1(3),PB2(3),PB3(3),PB4(3)];
cara2x=[PB4(1),PB3(1),PB7(1),PB8(1)];
cara2y=[PB4(2),PB3(2),PB7(2),PB8(2)];
cara2z=[PB4(3),PB3(3),PB7(3),PB8(3)];
cara3x=[PB8(1),PB7(1),PB6(1),PB5(1)];
cara3y=[PB8(2),PB7(2),PB6(2),PB5(2)];
cara3z=[PB8(3),PB7(3),PB6(3),PB5(3)];
cara4x=[PB5(1),PB6(1),PB2(1),PB1(1)];
cara4y=[PB5(2),PB6(2),PB2(2),PB1(2)];
cara4z=[PB5(3),PB6(3),PB2(3),PB1(3)];
cara5x=[PB1(1),PB4(1),PB8(1),PB5(1)];
cara5y=[PB1(2),PB4(2),PB8(2),PB5(2)];
cara5z=[PB1(3),PB4(3),PB8(3),PB5(3)];
cara6x=[PB2(1),PB3(1),PB7(1),PB6(1)];
cara6y=[PB2(2),PB3(2),PB7(2),PB6(2)];
cara6z=[PB2(3),PB3(3),PB7(3),PB6(3)];
patch(cara1x,cara1y,cara1z,[0.6350 0.0780 0.1840])
patch(cara2x,cara2y,cara2z,[0.6350 0.0780 0.1840])
patch(cara3x,cara3y,cara3z,[0.6350 0.0780 0.1840])
patch(cara4x,cara4y,cara4z,[0.6350 0.0780 0.1840])
for m=0:1 %Numero de vueltas
 for a=0:0.1:pi*2
 arb=[1,0,0; 0, cos(a),-sin(a); 0,sin(a),cos(a)];
 P1=arb*PB1;
 P2=arb*PB2;
 P3=arb*PB3; 
 P4=arb*PB4;
 P5=arb*PB5;
 P6=arb*PB6;
 P7=arb*PB7; 
 P8=arb*PB8;
cara1x=[P1(1),P2(1),P3(1),P4(1)];
cara1y=[P1(2),P2(2),P3(2),P4(2)];
cara1z=[P1(3),P2(3),P3(3),P4(3)];
cara2x=[P4(1),P3(1),P7(1),P8(1)];
cara2y=[P4(2),P3(2),P7(2),P8(2)];
cara2z=[P4(3),P3(3),P7(3),P8(3)];
cara3x=[P8(1),P7(1),P6(1),P5(1)];
cara3y=[P8(2),P7(2),P6(2),P5(2)];
cara3z=[P8(3),P7(3),P6(3),P5(3)];
cara4x=[P5(1),P6(1),P2(1),P1(1)];
cara4y=[P5(2),P6(2),P2(2),P1(2)];
cara4z=[P5(3),P6(3),P2(3),P1(3)];
cara5x=[P1(1),P4(1),P8(1),P5(1)];
cara5y=[P1(2),P4(2),P8(2),P5(2)];
cara5z=[P1(3),P4(3),P8(3),P5(3)];
cara6x=[P2(1),P3(1),P7(1),P6(1)];
cara6y=[P2(2),P3(2),P7(2),P6(2)];
cara6z=[P2(3),P3(3),P7(3),P6(3)];
patch(cara1x,cara1y,cara1z,[0.6350 0.0780 0.1840])
patch(cara2x,cara2y,cara2z,[0.6350 0.0780 0.1840])
patch(cara3x,cara3y,cara3z,[0.6350 0.0780 0.1840])
patch(cara4x,cara4y,cara4z,[0.6350 0.0780 0.1840])
patch(cara5x,cara5y,cara5z,[0.6350 0.0780 0.1840])
patch(cara6x,cara6y,cara6z,[0.6350 0.0780 0.1840])
 axis([-20,20,-20,20,-20,20]);
 xlabel('X')
ylabel('Y')
zlabel('Z')
 grid on;
 camlight
 view(3)
 pause(.01)
 clf;
 end
end