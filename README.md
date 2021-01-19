# Learn Netty (codes + notes)

### Netty Introduction

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

### I/O models in network programming

- blocking vs non-blocking
- sync vs async
- 5 I/O models in Linux
  - blocking I/O
  - non-blocking I/O
  - multiplexing I/O: multi requests(threads)
  - signal-driven I/O: similar to events, SIGIO
  - async I/O: POSIX aio_xxx functions, Future-Listener
- I/O 2 steps
  - initialize I/O request, wait for data ready
  - actual I/O operation
- multiplexing
  - network I/O, multiple TCP connections(socket/channel), reuse one or many threads
  - use one or more threads to handle multiple TCP connections
  - reduce overheads, too many threads creation/destruction/maintain
  - select
    - how?
      - monitor file descriptors: writedfs, readfds, exceptfds
      - block after call select() till data is readable, writble, exception or timeout.
      - after select() returns, by iterating over fdset array to find out which hanldes triggered events. then get the fd for I/O operations
      - support on all platforms
      - performance descrease for more fds
      - for every select() call, fd is copied from user state to kernal state
      - limit on max fd per thread: 1024
  - poll
  - epoll
    - since kernel 2.6. no fds restrictions. copy once from user state to kernel state
    - via event notification
    - register fd via epoll_ctl. once fd is ready, kernel will use callback to activated fd.
    - max fd will be OS max file handle. 1G mem -> 100k handle
    - performance enhance. Callback
    - kernel and user space store same memory space.
    - epoll_create(), epoll_ctl(), epoll_wait()
  - example
    - 1M connections. 10k are active
    - select: need 1000 threads to support
    - poll: fd interation is stuck. need lots of space
    - epoll
- Java I/O history
  - before jdk1.4, BIO
  - jdk1.4, NIO
  - jdk1.7, NIO 2.0. support AIO. support file, socket AIO

### Netty echo service demo

- EventLoop, EventLoopGroup
  - thread, thread pool
- Bootstrap
- Channel
  - connection between client and server.
- ChannelHandler, ChannelPipeline

