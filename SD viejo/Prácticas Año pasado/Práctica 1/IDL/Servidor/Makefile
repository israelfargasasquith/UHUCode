CFLAGS=Wall
CC=gcc

all: GestorJuegos

GestorJuegos: GestorJuegos.o GestorJuegos_svc.o GestorJuegos_xdr.o
	$(CC) -o $@ GestorJuegos.o GestorJuegos_svc.o GestorJuegos_xdr.o

GestorJuegos.o: GestorJuegos.h

clean:
	rm -f *.o GestorJuegos 
