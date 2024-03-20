CFLAGS=-Wall
CC=gcc

all: GestorBiblioteca

GestorBiblioteca: GestorBiblioteca.o GestorBiblioteca_clnt.o GestorBiblioteca_xdr.o
	$(CC) -o $@ GestorBiblioteca.o GestorBiblioteca_clnt.o GestorBiblioteca_xdr.o

GestorBiblioteca.o: GestorBiblioteca.h

clean:
	rm -f *.o GestorBiblioteca