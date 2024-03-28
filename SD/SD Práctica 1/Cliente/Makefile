CC = gcc
CFLAGS += -g -I/usr/include/tirpc
LDLIBS += -ltirpc -lnsl

all: GestorBiblioteca

GestorBiblioteca: GestorBiblioteca.o GestorBiblioteca_clnt.o GestorBiblioteca_xdr.o
	$(CC) -o $@ $^ $(LDLIBS)

GestorBiblioteca.o: GestorBiblioteca.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

GestorBiblioteca_clnt.o: GestorBiblioteca_clnt.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

GestorBiblioteca_xdr.o: GestorBiblioteca_xdr.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

clean:
	rm -f *.o GestorBiblioteca

