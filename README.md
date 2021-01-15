# Learn Netty (codes + notes)

### Netty Intro

- NIO framework
- supports protocols: FTP, SMTP, HTTP
- big data, gaming, telcom
- elastic search, hadoop(Avro), Dubbo

### BIO

- Blocking I/O
- Key class: SocketServer, Socket, BufferedReader(InputStreamReader), PrintWriter
- pros
  - easy to model and code
  - request -> create new thread -> handle
- cons
  - performance bottleneck, 1 request : 1 thread
  - hign concurrency scenario,  CPU context switch costs
- before tomcat 7 uses BIO, after uses NIO