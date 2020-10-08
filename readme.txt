1、不合适微服务架构
	(1) 业务形态不适合
		a. 系统中包含很多很多强事务场景的，如果大家对分布式事务有了解的，应该知道，基本CAP中
		（CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）。
			CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。），如果你的应用是强事务场景，那么微服务并不是一个很好的选择。
	(2) 业务相对稳定，迭代周期长
		如果你这个系统比较成熟，不需要经常迭代升级，也许几个月都不需要升级，那么改用微服务这个成本在我看来有点太高。
	(3) 访问压力不大，可用性要求不高
		中小型企业的OP系统，内部人员用用，偶尔出问题，停机个把小时也不是什么大事。像这样的项目上微服务简直就是杀鸡用牛刀。
	……
	
康威定律

zuul网关服务：80
注册中心端口：6611
配置中心端口：6622
用户服务端口：6633
商品服务端口：6644
订单服务端口：6655
订单服务端口：6666
后台服务端口：8080

如果下载安装软件总是失败，可以到https://pc.qq.com/detail/3/detail_1023.html 上面下载最新的Oracle VM VirtualBox

docker镜像地址：  hub.docker.com  国内的有:c.163yun.com/hub

docker常用命令
	docker pull <name>[:version]  -- 从远程仓库拉去镜像，如果没有指定版本，则会默认加上latest，即下载最新的版本
		如： 要下载redis，先进入网易云镜像中心，进入对应的镜像后，右上角会有一个下载地址，直接复制，然后运行
		指令：docker pull hub.c.163.com/public/redis:2.8.4
			
	docker images  -- 查看本机镜像
	
	docker ps  -- 查看本机运行的容器
	
	docker run [options] <image>[:version] [command][args]  -- 运行镜像，可以通过docker run --help来查看配置参数
		docker run 
			-d(说明在后台运行这个容器，返回容器的id)
			-p 主机端口:容器端口 (将主机端口映射到容器的端口)，
				比如：-p 5555:5672，将主机的5555端口映射到容器的5672端口上，即redis默认端口，这样就可以在主机通过5555端口
					访问到容器里面的redis了
			
	docker stop <镜像的id>  停止某个镜像
	
	docker restart <镜像id> 重启容器
			
	docker exec [options] <容器的id或者名称，可以只写id的前面一截，只要保证不重名就行> <command> [args] -- 进入指定的容器
		如： docker exec -it <运行的容器的id> bash，进入容器系统后，可以通过  which <应用程序名> 
			如：which nginx来获取软件的位置。通过exit来退出容器系统，通过ps -sf来查看一下运行的进程
	
	netstat -na|grep 3306 查看端口是否监听正常
	
	
制作自己的镜像
1、写一个Dockerfile文件  用来告诉docker我要怎么来制作自己的镜像，我要制作镜像的每一步操作分别是什么
	from hub.c.163.com/library/tomcat   这句是告诉docker我这个镜像是以tomcat为基础，如果是jar我们就需要jdk了
	MAINTAINER navy  haijunzhou@huit.edu.cn  所有者的名字 联系方式，可以不写
	COPY <本地要打包的jar或war包> <拷贝到镜像里面的目录位置>
		如： COPY fresh.war /usr/local/tomcat/webapps 放到tomcatwebapp下面，注意这个路径可以到下载镜像的位置去找，有说明的
	
2、使用docker build命令，用来执行Dockerfile中所描述的每一件事情
	docker build -t <镜像名字> <Dockerfile文件所在的目录>  如： docker build -t fresh:latest .
	
3、运行自己的镜像
	因为我们用的tomcat需要一个端口，所以我们需要做一下端口映射
	docker run -d -p 8080:8080 fresh

-- docker里面安装启动 rabbitmq
docker run -d --hostname navy-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3.8.8-management    -- 将容器的5672端口映射到主机的5672端口

-- docker安装redis
docker pull redis

-- docker里面安装启动 redis
docker run -d -p 6379:6379 redis:latest

--docker安装运行zipkin，用来监控链路信息
docker run -d -p 9411:9411 openzipkin/zipkin

java -jar DayFresh.jar --spring.profiles.active=dev