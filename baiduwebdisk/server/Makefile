SRCS:=$(wildcard *.c)
OBJS:=$(patsubst %.c,%.o,$(SRCS))
ELF:=server
CC:=gcc
CFLAGS:=-Wall -g
$(ELF):$(OBJS)
	gcc -o $@ $^ -pthread -lmysqlclient
clean:
	rm -rf $(OBJS) $(ELF)
