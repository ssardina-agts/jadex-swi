Requirements
============

* User should be able to use Prolog syntax inside Jadex (plan bodies, ADF beliefs and expressions)
    * There are Prolog parsers written in Java (though it seems silly to parse Prolog to Java to then generate Prolog from Java)
* User should be able to put entire objects into Prolog, not just simple key/value pairs
    either as an opaque reference
        see InvisibleObject in InterProlog
    or as deserialised objects
        see teachObject in InterProlog
* Interface can be via JNI or sockets
    JNI
        provides better performance
        provides only single prolog instance (use modules to fake multiple knowledge bases)
        see JPL
    sockets
        provide distributability
        are possibly easier to debug
        provide multiple prolog instances
        see InterProlog

