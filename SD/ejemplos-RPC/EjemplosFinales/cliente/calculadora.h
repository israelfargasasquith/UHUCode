/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _CALCULADORA_H_RPCGEN
#define _CALCULADORA_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct entrada {
	int arg1;
	int arg2;
};
typedef struct entrada entrada;

struct entrada_c {
	int arg1;
	char operador;
	int arg2;
};
typedef struct entrada_c entrada_c;

#define CALCULADORA 0x49080745
#define CALCULADORA_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define sumar 1
extern  int * sumar_1(entrada *, CLIENT *);
extern  int * sumar_1_svc(entrada *, struct svc_req *);
#define restar 2
extern  int * restar_1(entrada *, CLIENT *);
extern  int * restar_1_svc(entrada *, struct svc_req *);
extern int calculadora_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define sumar 1
extern  int * sumar_1();
extern  int * sumar_1_svc();
#define restar 2
extern  int * restar_1();
extern  int * restar_1_svc();
extern int calculadora_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_entrada (XDR *, entrada*);
extern  bool_t xdr_entrada_c (XDR *, entrada_c*);

#else /* K&R C */
extern bool_t xdr_entrada ();
extern bool_t xdr_entrada_c ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_CALCULADORA_H_RPCGEN */
