CC = gcc
CFLAGS += -g -I/usr/include/tirpc
LDLIBS += -ltirpc -lnsl

all: calculadora

calculadora: calculadora.o calculadora_clnt.o calculadora_xdr.o
	$(CC) -o $@ $^ $(LDLIBS)

calculadora.o: calculadora.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

calculadora_clnt.o: calculadora_clnt.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

calculadora_xdr.o: calculadora_xdr.c calculadora.h
	$(CC) -c $(CFLAGS) $< -o $@

clean:
	rm -f *.o calculadora

