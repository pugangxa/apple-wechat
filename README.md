### 启动server
```
nohup java -jar -server -Xms128m -Xmx512m -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod apple-0.0.1.jar > /dev/null 2>&1 &
```
