# Delegating TLS in Multiple Languages

# Why?

On-the-wire encryption is great.  Sometimes an application shouldn't do
encryption itself, because something "underneath" will do it.  Scenarios:

- **Service Mesh**: Service Meshes want to understand layer 7 details for HTTP,
  HTTP/2 and other protocols they are forwarding, so that they can make smart
routing and policy decisions, collect per-request information, and perform
distributed tracing.  Service meshes can enhance security (always doing TLS 1.3
and mutual TLS).

- **Kernel TLS**: Kernel TLS is new linux kernel APIs that allow applications
  to write plaintext to the TCP socket, and let the kernel do the encryption.
This can improve performance (the kernel may be able to offload to hardware).

When doing TLS offload/delegation, you should be careful to ensure that
*something* will do the encryption.

# How?

If you have an application that works with HTTPS URLs, you may need to tell the
programming langauge/libraries "go to https://example.com, but actually speak
HTTP".  This is a set of examples on how to do that in different languages/environments.

- [Go, AWS SDK](./go/delegate-go-aws.go)
- [Spring Boot](./java-spring/client-http-request-interceptor/main/java/com/example/delegate-tls/DelegateTlsInterceptor.java)

# License

These examples may be short enough that they are not subject to copyright.  If
your organization requires that you have a license even for short examples, you
can use this one:

Copyright 2020 AspenMesh

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
