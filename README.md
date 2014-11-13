aop
===

If you are reading this file  maybe you are searching for information about the code included in this repo:
the code represents the core of a prototypal, funny* AOP engine that realize a mechanism similar to the 
method-proxy mechanism realized by SpringFramework; this behaviour is realized by a runtime generation of a proxy class and without using java.lang.reflect.Proxy ; the proxy behaviour is configurable via a text file 
containing a JSON object (with key "targets") ; i've made it exclusively for purposes of knowledge.
I'd like you enjoy it .... have fun !!!

NOTE: 
- in package it/mapeto2my/java_aop/examples you'll find code that realize a simple example of usage,Main.java is the main class
- packages other than the previously mentioned contains the core
- the JSON config file is it/mapeto2my/java_aop/examples/aop_conf.json

NOTE on Dependencies:
-You'll need the following jars on classpath:
	a) json-simple-1.1.1.jar (or analogous)
-I've compiled and runned it with java7


*I think it's funny

