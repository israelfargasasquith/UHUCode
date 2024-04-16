/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include <memory.h> /* for memset */
#include "GestorBiblioteca.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = {25, 0};

int *conexion_1(char *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Conexion,
				  (xdrproc_t)xdr_Cadena, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

bool_t *
desconexion_1(int *argp, CLIENT *clnt)
{
	static bool_t clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Desconexion,
				  (xdrproc_t)xdr_int, (caddr_t)argp,
				  (xdrproc_t)xdr_bool, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *cargardatos_1(TConsulta *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, CargarDatos,
				  (xdrproc_t)xdr_TConsulta, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

bool_t *
guardardatos_1(int *argp, CLIENT *clnt)
{
	static bool_t clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, GuardarDatos,
				  (xdrproc_t)xdr_int, (caddr_t)argp,
				  (xdrproc_t)xdr_bool, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *nuevolibro_1(TNuevo *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, NuevoLibro,
				  (xdrproc_t)xdr_TNuevo, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *comprar_1(TComRet *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Comprar,
				  (xdrproc_t)xdr_TComRet, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *retirar_1(TComRet *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Retirar,
				  (xdrproc_t)xdr_TComRet, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

bool_t *
ordenar_1(TOrdenacion *argp, CLIENT *clnt)
{
	static bool_t clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Ordenar,
				  (xdrproc_t)xdr_TOrdenacion, (caddr_t)argp,
				  (xdrproc_t)xdr_bool, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *nlibros_1(int *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, NLibros,
				  (xdrproc_t)xdr_int, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *buscar_1(TConsulta *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Buscar,
				  (xdrproc_t)xdr_TConsulta, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

TLibro *
descargar_1(TPosicion *argp, CLIENT *clnt)
{
	static TLibro clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Descargar,
				  (xdrproc_t)xdr_TPosicion, (caddr_t)argp,
				  (xdrproc_t)xdr_TLibro, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *prestar_1(TPosicion *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Prestar,
				  (xdrproc_t)xdr_TPosicion, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}

int *devolver_1(TPosicion *argp, CLIENT *clnt)
{
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call(clnt, Devolver,
				  (xdrproc_t)xdr_TPosicion, (caddr_t)argp,
				  (xdrproc_t)xdr_int, (caddr_t)&clnt_res,
				  TIMEOUT) != RPC_SUCCESS)
	{
		return (NULL);
	}
	return (&clnt_res);
}
