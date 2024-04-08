/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "calculadora.h"
#include <stdlib.h>
int Menu(){
	int opc;
	do{
		printf("1.- Sumar\n");
		printf("2.- Restar\n");
		printf("3.- Salir\n");
		printf("Elige una opcion\n");
		scanf("%d",&opc);
	}while(opc<1 || opc>3);
	return opc;
}

void
calculadora_1(char *host)
{
	CLIENT *clnt;
	int  *result_1;
	entrada  sumar_1_arg;
	int  *result_2;
	entrada  restar_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, CALCULADORA, CALCULADORA_VER, "tcp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	result_1 = sumar_1(&sumar_1_arg, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_2 = restar_1(&restar_1_arg, clnt);
	if (result_2 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
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
	CLIENT* clnt;

	int *result, opcion;
	entrada Datos;

	clnt = clnt_create(host,CALCULADORA,CALCULADORA_VER,"tcp");
	if(clnt == NULL){
		clnt_pcreateerror(host);
		exit(1);	
	}
	printf("Cliente: Tengo conexion con el servidor, comenzamos\n");
	do{
		opcion=Menu();
		printf("Introduce los datos: \nPrimer operando: ");
		scanf("%d",&Datos.arg1);
		printf("Segundo operando: ");
		scanf("%d",&Datos.arg2);
		switch(opcion){
			case 1: result = sumar_1(&Datos,clnt);
					if(result == (int*)NULL){
						clnt_perror(clnt,"Cliente: Error en la llamada al procedimiento Sumar\n");
					}
					printf("Cliente: Resultado de la operacion: %d\n",*result);
			break;

			case 2: result = restar_1(&Datos,clnt);
					if(result == (int*)NULL){
						clnt_perror(clnt,"Cliente: Error en la llamada al procedimiento restar\n");
					}
					printf("Cliente: Resultado de la operacion: %d\n",*result);
			break;

			default: break;
		}

	}while(opcion != 3);
	printf("Cliente: Adioooos\n");


	clnt_destroy(clnt);
	exit (0);
}