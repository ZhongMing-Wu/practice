#include "factory.h"
int deleteFd(int epfd,int newFd){
	struct epoll_event event;
	event.events=EPOLLIN;
	event.data.fd=newFd;
	epoll_ctl(epfd,EPOLL_CTL_DEL,newFd,&event);
	return 0;
}
