/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "../IDL/GestorBiblioteca.h"


#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdio.h> 
#include <ctype.h>


#define Cls system("clear")
#define Pause system("read -p \"Pulsa la tecla return para continuar..... \" a")
 
#define MostrarAviso(Texto) { printf(Texto); Pause; }



void
gestorbiblioteca_1(char *host)
{
	CLIENT *clnt;
	int  *result_1;
	char  conexion_1_arg;
	bool_t  *result_2;
	int  desconexion_1_arg;
	int  *result_3;
	TConsulta  cargardatos_1_arg;
	bool_t  *result_4;
	int  guardardatos_1_arg;
	int  *result_5;
	TNuevo  nuevolibro_1_arg;
	int  *result_6;
	TComRet  comprar_1_arg;
	int  *result_7;
	TComRet  retirar_1_arg;
	bool_t  *result_8;
	TOrdenacion  ordenar_1_arg;
	int  *result_9;
	int  nlibros_1_arg;
	int  *result_10;
	TConsulta  buscar_1_arg;
	TLibro  *result_11;
	TPosicion  descargar_1_arg;
	int  *result_12;
	TPosicion  prestar_1_arg;
	int  *result_13;
	TPosicion  devolver_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, GESTORBIBLIOTECA, GESTORBIBLIOTECA_VER, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	result_1 = conexion_1(&conexion_1_arg, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_2 = desconexion_1(&desconexion_1_arg, clnt);
	if (result_2 == (bool_t *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_3 = cargardatos_1(&cargardatos_1_arg, clnt);
	if (result_3 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_4 = guardardatos_1(&guardardatos_1_arg, clnt);
	if (result_4 == (bool_t *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_5 = nuevolibro_1(&nuevolibro_1_arg, clnt);
	if (result_5 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_6 = comprar_1(&comprar_1_arg, clnt);
	if (result_6 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_7 = retirar_1(&retirar_1_arg, clnt);
	if (result_7 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_8 = ordenar_1(&ordenar_1_arg, clnt);
	if (result_8 == (bool_t *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_9 = nlibros_1(&nlibros_1_arg, clnt);
	if (result_9 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_10 = buscar_1(&buscar_1_arg, clnt);
	if (result_10 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_11 = descargar_1(&descargar_1_arg, clnt);
	if (result_11 == (TLibro *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_12 = prestar_1(&prestar_1_arg, clnt);
	if (result_12 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_13 = devolver_1(&devolver_1_arg, clnt);
	if (result_13 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}


int MenuPrincipal()
{
	 int Salida;
	 do
	 {
		Cls;
		printf(" GESTOR BIBLIOTECARIO 1.0 (M. PRINCIPAL)\n");
		printf("*****************************************\n");
		printf("\t1.- M. Administración\n");
		printf("\t2.- Consulta de libros\n");
		printf("\t3.- Préstamo de libros\n");
		printf("\t4.- Devolución de libros\n");
		printf("\t0.- Salir\n\n");
		printf(" Elige opción: ");
		scanf("%d",&Salida);
		if (Salida<0 || Salida>4)
			MostrarAviso("\n\n *** Error en la entrada de Datos.***\n\n");
	 }while (Salida<0 || Salida>4);
	 return Salida;
}


int MenuAdministracion()
{
	 int Salida;
	 do
	 {
		Cls;
		printf(" GESTOR BIBLIOTECARIO 1.0 (M. ADMINISTRACION)\n");
		printf("**********************************************\n");
		printf("\t1.- Cargar datos Biblioteca\n");
		printf("\t2.- Guardar datos Biblioteca\n");
		printf("\t3.- Nuevo libro\n");
		printf("\t4.- Comprar libros\n");
		printf("\t5.- Retirar libros\n");
		printf("\t6.- Ordenar libros\n");
		printf("\t7.- Buscar libros\n");
		printf("\t8.- Listar libros\n");
		printf("\t0.- Salir\n\n");
		printf(" Elige opción: ");
		scanf("%d",&Salida);
		if (Salida<0 || Salida>8)
			MostrarAviso("\n\n *** Error en la entrada de Datos.***\n\n");
	 }while (Salida<0 || Salida>8);
	 return Salida;
}

void Formatea(char *Salida, const char *p, int ancho,char Caracter)
{
	Cadena vacia;
	int len=ancho-strlen(p);
	int l=0,c=0;
	
	while (p[l]!='\0')
	{
		if ((unsigned char)p[l]>128)
			c++;
		l++;
	}
	len+=c/2;
	
	
	if (len<0)
		len=0;
	for (int i=0;i<len; i++)
		vacia[i]=Caracter;
	vacia[len]='\0';
	
	sprintf(Salida,"%s%s",p,vacia);
}

void MostrarLibro(TLibro *L, int Pos, bool_t Cabecera)
{
	Cadena T,A,B,PI;
	if (Cabecera==TRUE)
	{
		printf("%-*s%-*s%-*s%*s%*s%*s\n",5,"POS",58,"TITULO",18,"ISBN",4,"DIS",4,"PRE",4,"RES");
		printf("     %-*s%-*s%-*s\n",30,"AUTOR",28,"PAIS (IDIOMA)",12,"AÑO");
		Formatea(B,"*",93,'*');
		printf("%s\n",B);
	}
	Formatea(T,L->Titulo,58,' ');
	Formatea(A,L->Autor,30,' ');
	strcpy(B,L->Pais);
	strcat(B," (");
	strcat(B,L->Idioma);
	strcat(B,")");
	Formatea(PI,B,28,' ');
	printf("%-5d%s%-*s%*d%*d%*d\n",Pos+1, T, 18,L->Isbn,4,L->NoLibros, 4,L->NoPrestados, 4, L->NoListaEspera);
	printf("     %s%s%-*d\n",A ,PI,12, L->Anio);
}



int
main (int argc, char *argv[])
{
	char *host;
	printf("\nInit client :D");
	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}
	host = argv[1];
	gestorbiblioteca_1 (host);

	int opcMP = 0;
	opcMP = MenuPrincipal();
	
	printf("%d",opcMP);



exit (0);
}
