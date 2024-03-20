CFLAGS=-Wall
CC=gcc

all: GestorJuegod

GestorJuegod: GestorJuegod.o GestorJuegos_svc.o GestorJuegos_xdr.o
	$(CC) -o $@ GestorJuegod.o GestorJuegos_clnt.o GestorJuegos_xdr.o

GestorJuegod.o: GestorJuegos.h

clean:
	rm -f *.o GestorJuegod
