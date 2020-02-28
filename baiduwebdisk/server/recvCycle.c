#include "factory.h"

int recvCycle(int sfd,void *buf,int recvLen){
	char *p=(char*)buf;
	int total=0,ret;
	while(total<recvLen){
		ret=recv(sfd,p+total,recvLen-total,0);
		ERROR_CHECK(ret,-1,"recv");
		if(ret==0){
			return 1;
		}
		total+=ret;
	}
	return 0;
}
