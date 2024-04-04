/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "GestorBiblioteca.h"
#include <stdbool.h>
FILE *fdatos = NULL;
TLibro *Biblioteca = NULL; // Vector dinamico de libros
int numLibros = 0;		   // Numero de libros almacenados en el vector
int tama = 0;			   // Tamaño del vector dinamico, crece de 4 en 4 libros
int idAdmin = -1;		   // Copia del idAdmin mandado al usuario
Cadena nomFichero = "";	   // Copia del nombre del ultimo fichero binario cargado
int campoOrdenacion = 0;   // Copia del ultimo campo de ordenacion realizado

Cadena contraseñaAdmin = "1234";

bool_t EsMenor(int P1, int P2, int Campo)
{
	bool_t salida = FALSE;
	TLibro L1 = Biblioteca[P1];
	TLibro L2 = Biblioteca[P2];

	switch (Campo)
	{
	case 0:
		salida = strcmp(L1.Isbn, L2.Isbn) < 0 ? TRUE : FALSE;
		break;
	case 1:
		salida = strcmp(L1.Titulo, L2.Titulo) < 0 ? TRUE : FALSE;
		break;
	case 2:
		salida = strcmp(L1.Autor, L2.Autor) < 0 ? TRUE : FALSE;
		break;
	case 3:
		salida = L1.Anio < L2.Anio ? TRUE : FALSE;
		break;
	case 4:
		salida = strcmp(L1.Pais, L2.Pais) < 0 ? TRUE : FALSE;
		break;
	case 5:
		salida = strcmp(L1.Idioma, L2.Idioma) < 0 ? TRUE : FALSE;
		break;
	case 6:
		salida = L1.NoLibros < L2.NoLibros ? TRUE : FALSE;
		break;
	case 7:
		salida = L1.NoPrestados < L2.NoPrestados ? TRUE : FALSE;
		break;
	case 8:
		salida = L1.NoListaEspera < L2.NoListaEspera ? TRUE : FALSE;
		break;
	}
	return salida;
}

/*Se encarga de verificar la contraseña de administrador y devolverá un número (IDA) dependiendo de las
siguientes condiciones:
-1: Ya hay un usuario identificado como administrador, solo se permite uno.
-2: La contraseña es errónea.
N: Un número aleatorio generado como 1+rand()%RAND_MAX
Este número deberá ser utilizado en todas las operaciones de Administración en el campo Ida. */

int *conexion_1_svc(char *argp, struct svc_req *rqstp)
{
	static int result;
	if (idAdmin == -1)
	{
		printf("\nVamos a intentar iniciar sesion con la contraseña %s", argp);
		if (strcmp(argp, contraseñaAdmin) == 0)
		{
			idAdmin = 1 + rand() % RAND_MAX;
			printf("\nUn admin ha iniciado sesion con ID %d", idAdmin);
			result = idAdmin;
		}
		else
		{
			printf("\nServidor: La contraseña introducida ha sido erronea");
			result = -2;
		}
	}
	else
	{
		printf("\nServidor: Ya hay un admin en el sistema");
		result = -1;
	}

	return &result;
}
/*Comprueba que el Ida coincide con el almacenado en el servidor. Si no coincide devuelve FALSE y caso
contrario borra el Ida (lo pone a -1) almacenado en el servidor y devuelve TRUE. */
bool_t *
desconexion_1_svc(int *argp, struct svc_req *rqstp)
{
	static bool_t result;

	if (idAdmin == *argp)
	{
		printf("\nServidor: Se desconecta el Admin de forma correcta");
		result = true;
	}
	else
	{
		printf("\nServidor: El IdAdmin dado no es el correcto, error");
		result = false;
	}

	return &result;
}

int *cargardatos_1_svc(TConsulta *argp, struct svc_req *rqstp)
{
	static int result;

	if (argp->Ida > 0 && idAdmin == argp->Ida)
	{
		printf("\nIntentamos abrir el fichero %s", argp->Datos);
		fdatos = fopen(argp->Datos, "r");
		if (fdatos != NULL)
		{
			fread(&numLibros, sizeof(int), 1, fdatos);
			printf("\nHay %d libros en el fichero y en el array", numLibros);
			Biblioteca = (TLibro *)malloc(sizeof(TLibro) * numLibros);
			fread(Biblioteca, sizeof(TLibro), numLibros, fdatos);
		}
		else
		{
			printf("\nError: El nombre pasado por parametro no es un fichero");
		}
	}
	else
	{
		printf("\nServidor error: El IdAdmin no es correcto");
	}

	return &result;
}

bool_t *
guardardatos_1_svc(int *argp, struct svc_req *rqstp)
{
	static bool_t result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *nuevolibro_1_svc(TNuevo *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *comprar_1_svc(TComRet *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *retirar_1_svc(TComRet *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

bool_t *
ordenar_1_svc(TOrdenacion *argp, struct svc_req *rqstp)
{
	static bool_t result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *nlibros_1_svc(int *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *buscar_1_svc(TConsulta *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

TLibro *
descargar_1_svc(TPosicion *argp, struct svc_req *rqstp)
{
	static TLibro result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *prestar_1_svc(TPosicion *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}

int *devolver_1_svc(TPosicion *argp, struct svc_req *rqstp)
{
	static int result;

	/*
	 * insert server code here
	 */

	return &result;
}
