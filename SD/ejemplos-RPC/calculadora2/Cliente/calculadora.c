/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "calculadora.h"
#include <stdlib.h>

#define rnd (random()/(float) RAND_MAX)

void
calculadora_1(char *host)
{
	CLIENT *clnt;
	int  *result_1;
	entrada  Datos;
	
	entrada_c  Datos2;

#ifndef	DEBUG
	clnt = clnt_create (host, CALCULADORA, CALCULADORA_VER, "tcp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	Datos.arg1=10;
	Datos.arg2=40;

	Datos2.arg1=10;
	Datos2.arg2=40;
	Datos2.operador='+';

	result_1 = sumar_1(&Datos, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_1 = restar_1(&Datos, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_1 = multiplicar_1(&Datos, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_1 = dividir_1(&Datos, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_1 = operacion_1(&Datos2, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}

int Menu()
{
  int opcion;
  do
  {
     printf("1.- Sumar\n2.- Restar\n3.- Multiplicar\n4.- Dividir\n5.- Automaticas\n6.- Salir\n\nElige opcion:");
     scanf("%d",&opcion);
  } while (opcion<1 || opcion>6);
  return opcion;
}

int
main (int argc, char *argv[])
{
	char *host;

	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}
	host = argv[1];
	
	//calculadora_1 (host);

	CLIENT *clnt;
	int  *result, opcion, nveces,i;
	entrada  Datos;
	entrada_c  Datos2;

	clnt = clnt_create (host, CALCULADORA, CALCULADORA_VER, "tcp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}

	printf("Cliente: Tengo Conexión con el servidor.... comenzamos\n\n");
	do
	{
	   opcion=Menu();
	   if (opcion<5)
	   {
  		printf("Cliente: Introduce el valor del primer argumento: ");
		scanf("%d",&Datos.arg1);
  		printf("Cliente: Introduce el valor del segundo argumento: ");
		scanf("%d",&Datos.arg2);
           };
	   switch (opcion)
	   {
		case 1: result = sumar_1(&Datos, clnt);
			if (result == (int *) NULL) 
                           clnt_perror (clnt, "Cliente: Error en la llamada al procedimiento Sumar");
			printf("Cliente: Resultado de la operación: %d\n",*result);
			break;
		case 2: result = restar_1(&Datos, clnt);
			if (result == (int *) NULL) 
                           clnt_perror (clnt, "Cliente: Error en la llamada al procedimiento Restar");
			printf("Cliente: Resultado de la operación: %d\n",*result);
			break;
		case 3: result = multiplicar_1(&Datos, clnt);
			if (result == (int *) NULL) 
                           clnt_perror (clnt, "Cliente: Error en la llamada al procedimiento Multiplicar");
			printf("Cliente: Resultado de la operación: %d\n",*result);
			break;
		case 4: result = dividir_1(&Datos, clnt);
			if (result == (int *) NULL) 
                           clnt_perror (clnt, "Cliente: Error en la llamada al procedimiento Dividir");
			printf("Cliente: Resultado de la operación: %d\n",*result);
			break;
		case 5: printf("Cliente: Introduce el valor de veces a ejecutar el comando operacion: ");
			scanf("%d",&nveces);
			for (i=0; i<nveces; i++)
			{
				Datos2.arg1=1+rnd*100;
				Datos2.arg2=1+rnd*100;
				switch((int)(rnd*4))
				{
				   case 0:Datos2.operador='+'; break;
				   case 1:Datos2.operador='-'; break;
				   case 2:Datos2.operador='*'; break;
				   case 3:Datos2.operador='/'; break;
				}
				result = operacion_1(&Datos2, clnt);
				if (result == (int *) NULL) 
                                   clnt_perror (clnt, "Cliente: Error en la llamada al procedimiento Operacion");
				printf("Cliente: Resultado de la operación %d es: %d\n",i,*result);
			}
  		
	   }

	} while (opcion!=6);
	printf("Cliente: Hemos terminado. Hasta otra\n\n");
	clnt_destroy (clnt);
	exit (0);
}