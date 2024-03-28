CC = gcc
CFLAGS += -g -I/usr/include/tirpc
LDLIBS += -ltirpc -lnsl

all: calculadorad

calculadorad: calculadorad.o calculadora_svc.o calculadora_xdr.o
	$(CC) -o $@ $^ $(LDLIBS)

calculadorad.o: calculadorad.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

calculadora_svc.o: calculadora_svc.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

calculadora_xdr.o: calculadora_xdr.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

clean:
	rm -f *.o calculadorad

