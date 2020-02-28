#include "head.h"

int tcpInit(int *sfd,char *ip,char *port){
	int socketFd=socket(AF_INET,SOCK_STREAM,0);
	ERROR_CHECK(socketFd,-1,"socket");
	struct sockaddr_in server;
	server.sin_family=AF_INET;
	server.sin_port=htons(atoi(port));
	inet_pton(AF_INET,ip,&server.sin_addr);
	int reuse=1;
	int ret=setsockopt(socketFd,SOL_SOCKET,SO_REUSEADDR,&reuse,sizeof(int));
	ERROR_CHECK(ret,-1,"setsokcopt");
	ret=bind(socketFd,(struct sockaddr*)&server,sizeof(server));
	ERROR_CHECK(ret,-1,"bind");
	listen(socketFd,10);
	*sfd=socketFd;
	return 0;
}
