package com.test.bookstore;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: schema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookServiceGrpc {

  private BookServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.test.bookstore.BookService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getCreateBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBook",
      requestType = com.test.bookstore.Book.class,
      responseType = com.test.bookstore.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getCreateBookMethod() {
    io.grpc.MethodDescriptor<com.test.bookstore.Book, com.test.bookstore.Book> getCreateBookMethod;
    if ((getCreateBookMethod = BookServiceGrpc.getCreateBookMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getCreateBookMethod = BookServiceGrpc.getCreateBookMethod) == null) {
          BookServiceGrpc.getCreateBookMethod = getCreateBookMethod =
              io.grpc.MethodDescriptor.<com.test.bookstore.Book, com.test.bookstore.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("CreateBook"))
              .build();
        }
      }
    }
    return getCreateBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getUpdateBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateBook",
      requestType = com.test.bookstore.Book.class,
      responseType = com.test.bookstore.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getUpdateBookMethod() {
    io.grpc.MethodDescriptor<com.test.bookstore.Book, com.test.bookstore.Book> getUpdateBookMethod;
    if ((getUpdateBookMethod = BookServiceGrpc.getUpdateBookMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getUpdateBookMethod = BookServiceGrpc.getUpdateBookMethod) == null) {
          BookServiceGrpc.getUpdateBookMethod = getUpdateBookMethod =
              io.grpc.MethodDescriptor.<com.test.bookstore.Book, com.test.bookstore.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("UpdateBook"))
              .build();
        }
      }
    }
    return getUpdateBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getGetBookByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookById",
      requestType = com.test.bookstore.Book.class,
      responseType = com.test.bookstore.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getGetBookByIdMethod() {
    io.grpc.MethodDescriptor<com.test.bookstore.Book, com.test.bookstore.Book> getGetBookByIdMethod;
    if ((getGetBookByIdMethod = BookServiceGrpc.getGetBookByIdMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getGetBookByIdMethod = BookServiceGrpc.getGetBookByIdMethod) == null) {
          BookServiceGrpc.getGetBookByIdMethod = getGetBookByIdMethod =
              io.grpc.MethodDescriptor.<com.test.bookstore.Book, com.test.bookstore.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("GetBookById"))
              .build();
        }
      }
    }
    return getGetBookByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getGetBookByTitleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookByTitle",
      requestType = com.test.bookstore.Book.class,
      responseType = com.test.bookstore.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getGetBookByTitleMethod() {
    io.grpc.MethodDescriptor<com.test.bookstore.Book, com.test.bookstore.Book> getGetBookByTitleMethod;
    if ((getGetBookByTitleMethod = BookServiceGrpc.getGetBookByTitleMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getGetBookByTitleMethod = BookServiceGrpc.getGetBookByTitleMethod) == null) {
          BookServiceGrpc.getGetBookByTitleMethod = getGetBookByTitleMethod =
              io.grpc.MethodDescriptor.<com.test.bookstore.Book, com.test.bookstore.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookByTitle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("GetBookByTitle"))
              .build();
        }
      }
    }
    return getGetBookByTitleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getDeleteBookByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteBookById",
      requestType = com.test.bookstore.Book.class,
      responseType = com.test.bookstore.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.test.bookstore.Book,
      com.test.bookstore.Book> getDeleteBookByIdMethod() {
    io.grpc.MethodDescriptor<com.test.bookstore.Book, com.test.bookstore.Book> getDeleteBookByIdMethod;
    if ((getDeleteBookByIdMethod = BookServiceGrpc.getDeleteBookByIdMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getDeleteBookByIdMethod = BookServiceGrpc.getDeleteBookByIdMethod) == null) {
          BookServiceGrpc.getDeleteBookByIdMethod = getDeleteBookByIdMethod =
              io.grpc.MethodDescriptor.<com.test.bookstore.Book, com.test.bookstore.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteBookById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.test.bookstore.Book.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("DeleteBookById"))
              .build();
        }
      }
    }
    return getDeleteBookByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceStub>() {
        @java.lang.Override
        public BookServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceStub(channel, callOptions);
        }
      };
    return BookServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub>() {
        @java.lang.Override
        public BookServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceBlockingStub(channel, callOptions);
        }
      };
    return BookServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub>() {
        @java.lang.Override
        public BookServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceFutureStub(channel, callOptions);
        }
      };
    return BookServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createBook(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBookMethod(), responseObserver);
    }

    /**
     */
    default void updateBook(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBookMethod(), responseObserver);
    }

    /**
     */
    default void getBookById(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookByIdMethod(), responseObserver);
    }

    /**
     */
    default void getBookByTitle(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookByTitleMethod(), responseObserver);
    }

    /**
     */
    default void deleteBookById(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteBookByIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookService.
   */
  public static abstract class BookServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookService.
   */
  public static final class BookServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookServiceStub> {
    private BookServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceStub(channel, callOptions);
    }

    /**
     */
    public void createBook(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBook(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBookById(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBookByTitle(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetBookByTitleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteBookById(com.test.bookstore.Book request,
        io.grpc.stub.StreamObserver<com.test.bookstore.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteBookByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookService.
   */
  public static final class BookServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookServiceBlockingStub> {
    private BookServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.test.bookstore.Book createBook(com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBookMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.test.bookstore.Book updateBook(com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateBookMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.test.bookstore.Book getBookById(com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.test.bookstore.Book> getBookByTitle(
        com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetBookByTitleMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.test.bookstore.Book deleteBookById(com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteBookByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookService.
   */
  public static final class BookServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookServiceFutureStub> {
    private BookServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.test.bookstore.Book> createBook(
        com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBookMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.test.bookstore.Book> updateBook(
        com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateBookMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.test.bookstore.Book> getBookById(
        com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.test.bookstore.Book> deleteBookById(
        com.test.bookstore.Book request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteBookByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_BOOK = 0;
  private static final int METHODID_UPDATE_BOOK = 1;
  private static final int METHODID_GET_BOOK_BY_ID = 2;
  private static final int METHODID_GET_BOOK_BY_TITLE = 3;
  private static final int METHODID_DELETE_BOOK_BY_ID = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_BOOK:
          serviceImpl.createBook((com.test.bookstore.Book) request,
              (io.grpc.stub.StreamObserver<com.test.bookstore.Book>) responseObserver);
          break;
        case METHODID_UPDATE_BOOK:
          serviceImpl.updateBook((com.test.bookstore.Book) request,
              (io.grpc.stub.StreamObserver<com.test.bookstore.Book>) responseObserver);
          break;
        case METHODID_GET_BOOK_BY_ID:
          serviceImpl.getBookById((com.test.bookstore.Book) request,
              (io.grpc.stub.StreamObserver<com.test.bookstore.Book>) responseObserver);
          break;
        case METHODID_GET_BOOK_BY_TITLE:
          serviceImpl.getBookByTitle((com.test.bookstore.Book) request,
              (io.grpc.stub.StreamObserver<com.test.bookstore.Book>) responseObserver);
          break;
        case METHODID_DELETE_BOOK_BY_ID:
          serviceImpl.deleteBookById((com.test.bookstore.Book) request,
              (io.grpc.stub.StreamObserver<com.test.bookstore.Book>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.test.bookstore.Book,
              com.test.bookstore.Book>(
                service, METHODID_CREATE_BOOK)))
        .addMethod(
          getUpdateBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.test.bookstore.Book,
              com.test.bookstore.Book>(
                service, METHODID_UPDATE_BOOK)))
        .addMethod(
          getGetBookByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.test.bookstore.Book,
              com.test.bookstore.Book>(
                service, METHODID_GET_BOOK_BY_ID)))
        .addMethod(
          getGetBookByTitleMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.test.bookstore.Book,
              com.test.bookstore.Book>(
                service, METHODID_GET_BOOK_BY_TITLE)))
        .addMethod(
          getDeleteBookByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.test.bookstore.Book,
              com.test.bookstore.Book>(
                service, METHODID_DELETE_BOOK_BY_ID)))
        .build();
  }

  private static abstract class BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.test.bookstore.Schema.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookService");
    }
  }

  private static final class BookServiceFileDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier {
    BookServiceFileDescriptorSupplier() {}
  }

  private static final class BookServiceMethodDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BookServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BookServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookServiceFileDescriptorSupplier())
              .addMethod(getCreateBookMethod())
              .addMethod(getUpdateBookMethod())
              .addMethod(getGetBookByIdMethod())
              .addMethod(getGetBookByTitleMethod())
              .addMethod(getDeleteBookByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
