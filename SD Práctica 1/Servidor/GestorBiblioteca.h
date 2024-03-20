/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _GESTORBIBLIOTECA_H_RPCGEN
#define _GESTORBIBLIOTECA_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


typedef char Cadena[150];

struct TLibro {
	Cadena Isbn;
	Cadena Titulo;
	Cadena Autor;
	int Anio;
	Cadena Pais;
	Cadena Idioma;
	int NoLibros;
	int NoPrestados;
	int NoListaEspera;
};
typedef struct TLibro TLibro;

struct TNuevo {
	int Ida;
	TLibro Libro;
};
typedef struct TNuevo TNuevo;

struct TComRet {
	int Ida;
	Cadena Isbn;
	int NoLibros;
};
typedef struct TComRet TComRet;

struct TConsulta {
	int Ida;
	Cadena Datos;
};
typedef struct TConsulta TConsulta;

struct TPosicion {
	int Ida;
	int Pos;
};
typedef struct TPosicion TPosicion;

struct TOrdenacion {
	int Ida;
	int Campo;
};
typedef struct TOrdenacion TOrdenacion;

#define GESTORBIBLIOTECA 0x30000001
#define GESTORBIBLIOTECA_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define Conexion 1
extern  int * conexion_1(char *, CLIENT *);
extern  int * conexion_1_svc(char *, struct svc_req *);
#define Desconexion 2
extern  bool_t * desconexion_1(int *, CLIENT *);
extern  bool_t * desconexion_1_svc(int *, struct svc_req *);
#define CargarDatos 3
extern  int * cargardatos_1(TConsulta *, CLIENT *);
extern  int * cargardatos_1_svc(TConsulta *, struct svc_req *);
#define GuardarDatos 4
extern  bool_t * guardardatos_1(int *, CLIENT *);
extern  bool_t * guardardatos_1_svc(int *, struct svc_req *);
#define NuevoLibro 5
extern  int * nuevolibro_1(TNuevo *, CLIENT *);
extern  int * nuevolibro_1_svc(TNuevo *, struct svc_req *);
#define Comprar 6
extern  int * comprar_1(TComRet *, CLIENT *);
extern  int * comprar_1_svc(TComRet *, struct svc_req *);
#define Retirar 7
extern  int * retirar_1(TComRet *, CLIENT *);
extern  int * retirar_1_svc(TComRet *, struct svc_req *);
#define Ordenar 8
extern  bool_t * ordenar_1(TOrdenacion *, CLIENT *);
extern  bool_t * ordenar_1_svc(TOrdenacion *, struct svc_req *);
#define NLibros 9
extern  int * nlibros_1(int *, CLIENT *);
extern  int * nlibros_1_svc(int *, struct svc_req *);
#define Buscar 11
extern  int * buscar_1(TConsulta *, CLIENT *);
extern  int * buscar_1_svc(TConsulta *, struct svc_req *);
#define Descargar 10
extern  TLibro * descargar_1(TPosicion *, CLIENT *);
extern  TLibro * descargar_1_svc(TPosicion *, struct svc_req *);
#define Prestar 12
extern  int * prestar_1(TPosicion *, CLIENT *);
extern  int * prestar_1_svc(TPosicion *, struct svc_req *);
#define Devolver 13
extern  int * devolver_1(TPosicion *, CLIENT *);
extern  int * devolver_1_svc(TPosicion *, struct svc_req *);
extern int gestorbiblioteca_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define Conexion 1
extern  int * conexion_1();
extern  int * conexion_1_svc();
#define Desconexion 2
extern  bool_t * desconexion_1();
extern  bool_t * desconexion_1_svc();
#define CargarDatos 3
extern  int * cargardatos_1();
extern  int * cargardatos_1_svc();
#define GuardarDatos 4
extern  bool_t * guardardatos_1();
extern  bool_t * guardardatos_1_svc();
#define NuevoLibro 5
extern  int * nuevolibro_1();
extern  int * nuevolibro_1_svc();
#define Comprar 6
extern  int * comprar_1();
extern  int * comprar_1_svc();
#define Retirar 7
extern  int * retirar_1();
extern  int * retirar_1_svc();
#define Ordenar 8
extern  bool_t * ordenar_1();
extern  bool_t * ordenar_1_svc();
#define NLibros 9
extern  int * nlibros_1();
extern  int * nlibros_1_svc();
#define Buscar 11
extern  int * buscar_1();
extern  int * buscar_1_svc();
#define Descargar 10
extern  TLibro * descargar_1();
extern  TLibro * descargar_1_svc();
#define Prestar 12
extern  int * prestar_1();
extern  int * prestar_1_svc();
#define Devolver 13
extern  int * devolver_1();
extern  int * devolver_1_svc();
extern int gestorbiblioteca_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_Cadena (XDR *, Cadena);
extern  bool_t xdr_TLibro (XDR *, TLibro*);
extern  bool_t xdr_TNuevo (XDR *, TNuevo*);
extern  bool_t xdr_TComRet (XDR *, TComRet*);
extern  bool_t xdr_TConsulta (XDR *, TConsulta*);
extern  bool_t xdr_TPosicion (XDR *, TPosicion*);
extern  bool_t xdr_TOrdenacion (XDR *, TOrdenacion*);

#else /* K&R C */
extern bool_t xdr_Cadena ();
extern bool_t xdr_TLibro ();
extern bool_t xdr_TNuevo ();
extern bool_t xdr_TComRet ();
extern bool_t xdr_TConsulta ();
extern bool_t xdr_TPosicion ();
extern bool_t xdr_TOrdenacion ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_GESTORBIBLIOTECA_H_RPCGEN */
