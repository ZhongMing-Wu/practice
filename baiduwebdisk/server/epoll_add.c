#include "factory.h"
void epollAdd(int epfd,int fd){
	struct epoll_event event;
	bzero(&event,sizeof(event));
	event.events=EPOLLIN;
	event.data.fd=fd;
	epoll_ctl(epfd,EPOLL_CTL_ADD,fd,&event);
}
