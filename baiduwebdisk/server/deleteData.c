#include "head.h"
int deleteData(char *operate){
	MYSQL *conn;
	char server[]="localhost";
	char user[]="root";
	char password[]="123";
	char database[]="wu";
	char query[300]={0};
	memcpy(query,operate,strlen(operate));
	printf("query=%s\n",query);
	int t;
	conn=mysql_init(NULL);
	if(!mysql_real_connect(conn,server,user,password,database,0,NULL,0))
	{
		printf("Error connecting to database:%s\n",mysql_error(conn));
	}else{
		printf("Connected...\n");
	}
	t=mysql_query(conn,query);
	if(t)
	{
		printf("Error making query:%s\n",mysql_error(conn));
	}else{
		printf("delete success,delete row=%ld\n",(long)mysql_affected_rows(conn));
	}
	mysql_close(conn);
	return 0;
}
