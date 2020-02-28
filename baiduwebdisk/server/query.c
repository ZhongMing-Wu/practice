#include "head.h"
int query(char *operate)
{
	MYSQL *conn;
	MYSQL_RES *res;
	MYSQL_ROW row;
	int ans=-1;
	char server[]="localhost";
	char user[]="root";
	char password[]="123";
	char database[]="wu";//要访问的数据库名称
	char query[300]={0};
	strcpy(query,operate);
	puts(query);
	int t;
	conn=mysql_init(NULL);
	if(!mysql_real_connect(conn,server,user,password,database,0,NULL,0))
	{
		printf("Error connecting to database:%s\n",mysql_error(conn));
		return -1;
	}else{
		printf("Connected...\n");
	}
	t=mysql_query(conn,query);
	if(t)
	{
		printf("Error making query:%s\n",mysql_error(conn));
	}else{
	//	printf("Query made...\n");
		res=mysql_use_result(conn);  //一次性获取所有数据
		if(res)
		{
			if((row=mysql_fetch_row(res))!=NULL) //提取一行数据
			{	
				//printf("num=%d\n",mysql_num_fields(res));//列数
				/*for(t=0;t<mysql_num_fields(res);t++)
				{
						printf("%8s ",row[t]);
				
				}
				printf("\n");*/
				ans=atoi(row[0]);
			}
		}else{
			printf("Don't find data\n");
		}
		mysql_free_result(res);
	}
	mysql_close(conn);
	return ans;
}
